package com.vgbhfive.v_rule.service.valid;

import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.entity.ProductEntity;
import org.springframework.stereotype.Service;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:45
 */
@Service
@Valid(type = ElementTypes.PRODUCT)
public class ProductValid implements ValidService<ProductEntity> {

    @Override
    public void checkParams(ProductEntity entity, boolean isUpdate) {

    }

}
