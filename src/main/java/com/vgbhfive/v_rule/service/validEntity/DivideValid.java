package com.vgbhfive.v_rule.service.validEntity;

import org.springframework.stereotype.Service;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 11:58
 */
@Service
public class DivideValid {

    public void checkParams() {
        // 1. 所属同一场景下不允许存在相同优先级的分流器

        // 2. 所属同一场景下修改失效状态时先检查是否从场景摘除

    }

}
