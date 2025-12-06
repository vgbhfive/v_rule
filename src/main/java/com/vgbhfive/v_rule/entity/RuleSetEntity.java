package com.vgbhfive.v_rule.entity;

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
 * @Date 2025/11/27 16:33
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("rule_set")
public class RuleSetEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 规则集名称
     */
    private String ruleSetName;

    /**
     * 规则集编码
     */
    private String ruleSetNo;

    /**
     * 第一个规则/规则集编码
     */
    private String firstNo;

    /**
     * 第一个规则/规则集类型
     * @see com.vgbhfive.v_rule.common.enums.RuleType
     */
    private String firstType;

    /**
     * 第二个规则/规则集编码
     */
    private String secondNo;

    /**
     * 第二个规则/规则集类型
     * @see com.vgbhfive.v_rule.common.enums.RuleType
     */
    private String secondType;

    /**
     * 联合符号
     */
    private String combine;

    /**
     * 运算符
     */
    private String cond;

    /**
     * 阈值
     */
    private String threshold;

    /**
     * 阈值类型
     * @see com.vgbhfive.v_rule.common.enums.ValueType
     */
    private String thresholdType;

    /**
     * 结果
     * @see com.vgbhfive.v_rule.common.enums.ResultType
     */
    private String result;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 部署时间
     */
    private Date deployAt;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
