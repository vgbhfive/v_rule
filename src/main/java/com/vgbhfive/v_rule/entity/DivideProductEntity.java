package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vgbhfive.v_rule.common.enums.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 0:10
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("divide_product")
public class DivideProductEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分流器编码
     */
    private String divideNo;

    /**
     * 产品编码
     */
    private String productNo;

    /**
     * 产品类型
     * @see ProductType
     */
    private String type;

    /**
     * 版本
     */
    private Integer version;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
