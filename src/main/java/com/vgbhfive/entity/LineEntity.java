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
    private String lineName;

    /**
     * 业务线编码
     */
    private String lineNo;

    private String url;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
