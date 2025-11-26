package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.entity.LineEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:25
 */
public interface LineService {

    ResponseContent queryList(LineQueryParam param);

    ResponseContent create(LineEntity lineEntity);

    ResponseContent update(LineEntity lineEntity);

}
