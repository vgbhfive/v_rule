package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vgbhfive.v_rule.common.enums.StrategyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "业务线标识不能为空")
    private String lineNo;

    /**
     * 策略集名称
     */
    @NotBlank(message = "策略集名称不能为空")
    private String strategyName;

    /**
     * 策略集编码
     */
    private String strategyNo;

    /**
     * 运行模式
     * @see StrategyModel
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
