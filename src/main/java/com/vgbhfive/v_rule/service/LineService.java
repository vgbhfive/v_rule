package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.line.LineQueryParam;
import com.vgbhfive.v_rule.entity.LineEntity;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:25
 */
public interface LineService {

    ResponseContent queryList(LineQueryParam param);

    ResponseContent create(LineEntity lineEntity, boolean isUpdate);

    ResponseContent update(LineEntity lineEntity);

}
