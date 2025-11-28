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
 * @Date 2025/11/28 16:20
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("strategy_rule_detail")
public class StrategyRuleDetailEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 策略集编码
     */
    private String strategyNo;

    /**
     * 规则编码
     */
    private String detailNo;

    /**
     * 规则类型
     * @see com.vgbhfive.common.enums.RuleType
     */
    private String ruleType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 版本
     */
    private Integer version;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
