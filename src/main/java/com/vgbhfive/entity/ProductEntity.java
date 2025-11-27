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
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 22:49
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("product")
public class ProductEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码
     */
    private String productNo;

    /**
     * 产品类型
     * @see com.vgbhfive.common.enums.ProductType
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

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
