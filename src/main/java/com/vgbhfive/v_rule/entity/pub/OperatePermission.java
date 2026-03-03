package com.vgbhfive.v_rule.entity.pub;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 15:58
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("operate_permission")
public class OperatePermission {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

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

}
