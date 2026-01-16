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

    /**
     * 业务线编码
     */
    @NotBlank(message = "业务线标识不能为空")
    private String lineNo;

    /**
     * 场景名称
     */
    @NotBlank(message = "场景名称不能为空")
    private String sceneName;

    /**
     * 场景字段
     */
    @NotBlank(message = "场景标识不能为空")
    private String field;

    /**
     * 场景编码
     */
    private String sceneNo;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
