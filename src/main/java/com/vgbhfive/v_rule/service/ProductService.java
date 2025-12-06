package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;

import java.util.Date;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/6 20:34
 */
public interface ProductService {

    ResponseContent updateProductDeployTime(List<String> productNoList, Date deployTime);

}
