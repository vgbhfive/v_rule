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
 * @date: 2025/11/24 23:29
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("line")
public class LineEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线名称
     */
    @NotBlank(message = "业务线名称不能为空")
    private String lineName;

    /**
     * 业务线编码
     */
    @NotBlank(message = "业务线标识不能为空")
    private String lineNo;

    /**
     * 引擎url
     */
    @NotBlank(message = "业务线所属 URL 不能为空")
    private String url;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
