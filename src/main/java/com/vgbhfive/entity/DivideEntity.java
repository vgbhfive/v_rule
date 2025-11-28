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
    private String lineNo;

    /**
     * 场景编码
     */
    private String sceneNo;

    /**
     * 分流器名称
     */
    private String divideName;

    /**
     * 分流器编码
     */
    private String divideNo;

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

    @TableField(exist = false)
    private List<DivideProductEntity> productEntityList;

}
