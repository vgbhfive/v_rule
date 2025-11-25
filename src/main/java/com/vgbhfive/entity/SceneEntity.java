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
 * @date: 2025/11/23 23:17
 */
@TableName("scene")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SceneEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String lineNo;

    private String sceneName;

    private String field;

    private String sceneNo;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
