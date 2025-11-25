package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineQueryParam;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:25
 */
public interface LineService {

    ResponseContent queryList(LineQueryParam param);

}
