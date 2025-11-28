package com.vgbhfive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:45
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("strategy")
public class StrategyEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 场景编码
     */
    private String sceneNo;

    /**
     * 策略集名称
     */
    private String strategyName;

    /**
     * 策略集编码
     */
    private String strategyNo;

    /**
     * 运行模式
     * @see com.vgbhfive.common.enums.StrategyModel
     */
    private String model;

    /**
     * 版本
     */
    private Integer version;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

    @TableField(exist = false)
    private List<StrategyRuleDetailEntity> ruleDetailEntityList;

}
