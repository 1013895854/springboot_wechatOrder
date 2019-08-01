package com.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.ProductInfoEntity;
import com.order.entity.dto.QueryProductParamDto;
import com.order.mapper.ProductInfoMapper;
import com.order.service.ProductInfoService;
import com.order.until.enums.ProductStatusEnum;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfoEntity> implements ProductInfoService{
	
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public List<ProductInfoEntity> selectAllByStatus(QueryProductParamDto param) {
		QueryWrapper<ProductInfoEntity> queryWrapper = new QueryWrapper<>();
		List<ProductInfoEntity> list = null;
		if(param.getType()== ProductStatusEnum.UP.getCode()) {
			//上架
			queryWrapper.eq("product_status", ProductStatusEnum.UP.getCode());
			 list = productInfoMapper.selectList(queryWrapper);
		}else if(param.getType() == ProductStatusEnum.DOWN.getCode()) {
			//下架
			queryWrapper.eq("product_status", ProductStatusEnum.DOWN.getCode());
			list = productInfoMapper.selectList(queryWrapper);
		}else {
			return null;
		}
		return list;
	}
	
	
}
