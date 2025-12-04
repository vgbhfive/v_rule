package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.ProductType;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.divide.DivideListDto;
import com.vgbhfive.v_rule.dto.divide.DivideQueryParam;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.entity.DivideProductEntity;
import com.vgbhfive.v_rule.mapper.DivideMapper;
import com.vgbhfive.v_rule.mapper.DivideProductMapper;
import com.vgbhfive.v_rule.service.DivideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:51
 */
@Service
public class DivideServiceImpl implements DivideService {

    @Autowired
    private DivideMapper divideMapper;
    @Autowired
    private DivideProductMapper divideProductMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(DivideQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<DivideListDto> divideListDtoList = divideMapper.queryList(param, start, limit);
        int totalCount = divideMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<DivideListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, divideListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent create(DivideEntity divideEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            divideEntity.setVersion(divideEntity.getVersion() + 1);
        } else {
            divideEntity.setCreateAt(now);
            divideEntity.setDivideNo(noGenerateUtil.generateNo(Constant.NO_FLQ));
        }
        List<DivideProductEntity> productEntityList = buildProductEntityList(divideEntity);
        Integer insertProduct = divideProductMapper.batchInsert(productEntityList);
        if (insertProduct < divideEntity.getProductEntityList().size()) {
            throw new DataBaseException("创建分流器失败");
        }
        divideEntity.setId(null);
        divideEntity.setUpdateAt(now);
        Integer insert = divideMapper.insert(divideEntity);
        if (insert < 1) {
            throw new DataBaseException("创建分流器失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent detail(Integer id) {
        return ResponseContent.success(divideMapper.queryDetailById(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent update(DivideEntity divideEntity) {
        DivideEntity oldDivideEntity = new DivideEntity();
        oldDivideEntity.setIsDelete(1);
        oldDivideEntity.setUpdateAt(new Date());
        Integer update = divideMapper.update(oldDivideEntity,
                new UpdateWrapper<DivideEntity>().eq("id", divideEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改分流器失败");
        }
        DivideProductEntity oldDivideProductEntity = new DivideProductEntity();
        oldDivideProductEntity.setIsDelete(1);
        oldDivideProductEntity.setUpdateAt(new Date());
        Integer updateProduct = divideProductMapper.update(oldDivideProductEntity,
                new UpdateWrapper<DivideProductEntity>().eq("divide_no", divideEntity.getDivideNo()).eq("is_delete", 0));
        if (updateProduct < divideEntity.getProductEntityList().size()) {
            throw new DataBaseException("修改分流器失败");
        }
        return this.create(divideEntity, true);
    }

    private List<DivideProductEntity> buildProductEntityList(DivideEntity divideEntity) {
        List<DivideProductEntity> productEntityList = new ArrayList<>();
        Date now = new Date();
        divideEntity.getProductEntityList().forEach(productEntity -> {
            productEntity.setId(null);
            productEntity.setDivideNo(divideEntity.getDivideNo());
            productEntity.setVersion(divideEntity.getVersion());
            productEntity.setIsDelete(0);
            productEntity.setCreateAt(now);
            productEntity.setUpdateAt(now);
            productEntityList.add(productEntity);
        });
        return productEntityList;
    }

    @Override
    public List<SceneStruct.Divide> queryDivideBySceneNo(String sceneNo) {
        List<SceneStruct.Divide> divideList = divideMapper.queryDivideBySceneNo(sceneNo);
        divideList.forEach(divide -> {
            divide.setProductInterestNoList(divideProductMapper.queryProductNoListByDivideNo(divide.getDivideNo(), ProductType.INTEREST.getType()));
            divide.setProductPeriodNoList(divideProductMapper.queryProductNoListByDivideNo(divide.getDivideNo(), ProductType.PERIOD.getType()));
            divide.setProductLimitNoList(divideProductMapper.queryProductNoListByDivideNo(divide.getDivideNo(), ProductType.LIMIT.getType()));
        });
        return divideList;
    }

}
