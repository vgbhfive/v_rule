package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:28
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("data_category")
public class DataCategoryEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    @NotBlank(message = "业务线标识不能为空")
    private String lineNo;

    /**
     * 数据源分类名称
     */
    @NotBlank(message = "数据源分类不能为空")
    private String dataCategoryName;

    /**
     * 数据源分类编码
     */
    private String dataCategoryNo;

    /**
     * 一级来源
     */
    private String sourceFrom;

    /**
     * 二级来源
     */
    private String sourceType;

    /**
     * 分类类型
     */
    private Integer categoryType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 版本
     */
    private Integer version;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
