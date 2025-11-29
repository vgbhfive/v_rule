package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vgbhfive.v_rule.common.enums.DeployStatusCode;
import com.vgbhfive.v_rule.common.enums.SceneType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 22:47
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("deploy")
public class DeployEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 发布模式
     * @see SceneType
     */
    private String deployType;

    /**
     * 编码
     */
    private String deployNo;

    /**
     * 字段
     */
    private String field;

    /**
     * 名称
     */
    private String name;

    /**
     * 分流器编码列表
     */
    private String divideList;

    /**
     * 上线描述
     */
    private String deployDesc;

    /**
     * 策略数据
     */
    private String param;

    /**
     * 本地版本与线上版本差异
     */
    private String diff;

    /**
     * 申请状态码
     * @see DeployStatusCode
     */
    private Integer statusCode;

    /**
     * 发布时间
     */
    private Date deployAt;

    /**
     * 版本
     */
    private Integer version;

    private Integer isValid;

    private Date createAt;

    private Date updateAt;

}
