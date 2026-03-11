package com.vgbhfive.v_rule.entity.pub;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 15:58
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName(value = "operate_permission", autoResultMap = true)
public class OperatePermission {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private OperatePermissionMeta meta;

    private String path;

    private String component;

    /**
     * 系统交互唯一标识
     */
    private String uniqueSign;

    /**
     * 类型 1-页面 2-按钮
     */
    private Integer type;

    /**
     * 父ID
     */
    private Integer parentId;

    private Integer isValid;

    private Date createAt;

    private Date updateAt;

    @TableField(exist = false)
    private List<OperatePermission> children = new ArrayList<>();

}
