package com.vgbhfive.v_rule.service.valid;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:05
 */
public interface ValidService<T> {

    /**
     * 验证参数需放置在方法的第一位
     * @param entity
     * @param isUpdate
     */
    void checkParams(T entity, boolean isUpdate);

}
