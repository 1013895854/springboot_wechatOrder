package com.order.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.order.entity.ProductCategoryEntity;

public interface ProductCategoryService extends IService<ProductCategoryEntity>{
	//
	public IPage<ProductCategoryEntity> selectPageVo(Page page,Integer categoryId);
}
