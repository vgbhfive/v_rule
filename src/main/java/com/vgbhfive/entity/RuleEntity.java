package com.vgbhfive.entity;

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
 * @Date 2025/11/27 15:58
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("rule")
public class RuleEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则编码
     */
    private String ruleNo;

    /**
     * 数据源编码
     */
    private String dataSourceNo;

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
     */
    private String thresholdType;

    /**
     * 结果
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
