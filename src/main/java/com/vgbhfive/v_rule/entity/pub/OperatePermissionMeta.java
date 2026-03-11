package com.vgbhfive.v_rule.entity.pub;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2026/3/11 12:18
 */
@Data
public class OperatePermissionMeta {

    private String title;

    private String icon;

    private boolean keepAlive;

    private Integer order;

    private boolean affixTab;

}
