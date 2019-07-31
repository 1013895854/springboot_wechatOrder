package com.order.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.ProductCategoryEntity;
import com.order.mapper.ProductCategoryMapper;
import com.order.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService{
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Override
	public IPage<ProductCategoryEntity> selectPageVo(Page page,Integer categoryId) {
		// TODO Auto-generated method stub
		return productCategoryMapper.selectPageVo(page,categoryId);
	}

}
