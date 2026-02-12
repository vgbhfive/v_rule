package com.vgbhfive.v_rule.common.factory;

import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.service.valid.ValidService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:05
 */
public class ValidServiceFactory {

    private static Map<ElementTypes, ValidService<Object>> handlerMap = new HashMap<>();

    public static ValidService<Object> getHandler(ElementTypes type) {
        return handlerMap.get(type);
    }

    public static void register(ElementTypes type, ValidService<Object> validService) {
        handlerMap.put(type, validService);
    }

    public static Map<ElementTypes, ValidService<Object>> getHandlerMap() {
        return ValidServiceFactory.handlerMap;
    }

}
