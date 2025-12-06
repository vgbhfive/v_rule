package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/6 20:35
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseContent updateProductDeployTime(List<String> productNoList, Date deployTime) {
        if (productNoList.isEmpty()) {
            return ResponseContent.success();
        }
        Integer update = productMapper.updateDeployTimeBatch(productNoList, deployTime);
        if (update != productNoList.size()) {
            throw new DataBaseException("更新产品上线时间失败");
        }
        return ResponseContent.success();
    }

}
