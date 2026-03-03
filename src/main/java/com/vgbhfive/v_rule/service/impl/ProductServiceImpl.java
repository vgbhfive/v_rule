package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.RequestHolder;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public ResponseContent updateValid(Integer id, Integer status) {
        if (Objects.isNull(id)) {
            throw new ParamException("无效参数");
        }
        ProductEntity oldProductEntity = new ProductEntity();
        oldProductEntity.setIsValid(status);
        oldProductEntity.setUpdateAt(new Date());
        int update = productMapper.update(oldProductEntity,
                new UpdateWrapper<ProductEntity>().eq("id", id));
        if (update < 1) {
            throw new DataBaseException("更新利率产品状态失败");
        }
        return ResponseContent.success("更新利率产品状态成功");
    }

    @Override
    public ResponseContent dropdownList(ProductQueryParam param) {
        List<String> lineList = (List<String>) RequestHolder.get().get(Constant.LINE_PERMISSION_SET);
        return ResponseContent.success(productMapper.selectDropdownList(param.getLineNo(), param.getType(), lineList));
    }

}
