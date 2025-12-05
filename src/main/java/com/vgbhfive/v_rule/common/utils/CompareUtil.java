package com.vgbhfive.v_rule.common.utils;

import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/4 23:55
 */
public class CompareUtil {

    private static final List<Class> basicClassList = new ArrayList<>();

    static {
        basicClassList.add(Byte.class);
        basicClassList.add(Short.class);
        basicClassList.add(Integer.class);
        basicClassList.add(Float.class);
        basicClassList.add(Double.class);
        basicClassList.add(Long.class);
        basicClassList.add(Boolean.class);
        basicClassList.add(Character.class);
        basicClassList.add(String.class);
    }

    /**
     * @param obj1 oldObject
     * @param obj2 newObject
     * @param ignoreList object 中需要忽略的属性
     * @return
     * @throws Exception
     */
    public static List<DetailCompareResult> compare(Object obj1, Object obj2, List<String> ignoreList) throws Exception {
        List<DetailCompareResult> resultList = new ArrayList<>();
        compare(obj1, obj2, ignoreList, "", resultList);
        return resultList;
    }

    private static void compare(Object obj1, Object obj2, List<String> ignoreList, String namePrefix, List<DetailCompareResult> resultList) throws Exception {
        if (Objects.isNull(obj1) && Objects.isNull(obj2)) {
            return;
        }
        if (Objects.isNull(obj1) || Objects.isNull(obj2)) {
            if (Objects.isNull(obj1) && isEmpty(obj2)) {
                return;
            } else if (Objects.isNull(obj2) && isEmpty(obj1)) {
                return;
            }
            resultList.add(new DetailCompareResult(namePrefix, obj1, obj2));
            return;
        }
        if (obj1.equals(obj2)) {
            return;
        }
        if (!obj1.getClass().equals(obj2.getClass())) {
            resultList.add(new DetailCompareResult(namePrefix, obj1, obj2));
            return;
        }

        Class<?> clazz = obj1.getClass();
        if (basicClassList.contains(clazz)) {
            resultList.add(new DetailCompareResult(namePrefix, obj1, obj2));
        } else if (obj1 instanceof Map) {
            Set<?> keySet = ((Map<?, ?>) obj1).keySet();
            for (Object key : keySet) {
                if (Objects.nonNull(ignoreList) && ignoreList.contains(key.toString())) {
                    ((Map<?, ?>) obj2).remove(key);
                    continue;
                }
                String name = getName(namePrefix, key);
                Object subObj1 = ((Map<?, ?>) obj1).get(key);
                Object subObj2 = ((Map<?, ?>) obj2).remove(key);
                compare(subObj1, subObj2, ignoreList, name, resultList);
            }
            for (Object key : ((Map<?, ?>) obj2).keySet()) {
                String name = getName(namePrefix, key);
                if (Objects.isNull(ignoreList) || !ignoreList.contains(key.toString())) {
                    resultList.add(new DetailCompareResult(name, null, ((Map<?, ?>) obj2).get(key)));
                }
            }
        } else if (obj1 instanceof Set) {
            List<Object> list1 = new ArrayList<>((Set<?>) obj1);
            List<Object> list2 = new ArrayList<>((Set<?>) obj2);
            compare(list1, list2, ignoreList, namePrefix, resultList);
        } else if (obj1 instanceof List) {
            List<?> list1 = (List<?>) obj1;
            List<?> list2 = (List<?>) obj2;
            if (list1.isEmpty() && list2.isEmpty()) {
                return;
            }
            int size = Math.min(list1.size(), list2.size());
            for (int i = 0; i < size; i++) {
                Object subObj1 = list1.get(i);
                Object subObj2 = list2.get(i);
                compare(subObj1, subObj2, ignoreList, String.format("%s[%s]", namePrefix, i), resultList);
            }
            if (list1.size() > size) {
                for (int i = size; i < list1.size(); i++) {
                    resultList.add(new DetailCompareResult(String.format("%s[%s]", namePrefix, i), list1.get(i), null));
                }
            }
            if (list2.size() > size) {
                for (int i = size; i < list2.size(); i++) {
                    resultList.add(new DetailCompareResult(String.format("%s[%s]", namePrefix, i), null, list2.get(i)));
                }
            }
        } else {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String fieldName = pd.getName();
                if (Objects.nonNull(ignoreList) && ignoreList.contains(fieldName)) {
                    continue;
                }
                String name = getName(namePrefix, fieldName);
                Method readMethod = pd.getReadMethod();
                Object o1 = readMethod.invoke(obj1);
                Object o2 = readMethod.invoke(obj2);
                compare(o1, o2, ignoreList, name, resultList);
            }
        }
    }

    private static boolean isEmpty(Object obj) {
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        return false;
    }

    private static String getName(String namePrefix, Object name) {
        return "".equals(namePrefix) ? name.toString() : namePrefix + "." + name;
    }

}
