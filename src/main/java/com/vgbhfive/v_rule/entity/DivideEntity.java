package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:34
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("divide")
public class DivideEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    @NotBlank(message = "业务线标识不能为空")
    private String lineNo;

    /**
     * 场景编码
     */
    @NotBlank(message = "分流器所属场景不能为空")
    private String sceneNo;

    /**
     * 分流器名称
     */
    @NotBlank(message = "分流器名称不能为空")
    private String divideName;

    /**
     * 分流器编码
     */
    private String divideNo;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 准入策略编码
     */
    private String accessStrategyNo;

    /**
     * 风控策略编码
     */
    private String riskStrategyNo;

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

    private String orderRand3;

    @TableField(exist = false)
    private List<DivideProductEntity> productEntityList;

}
