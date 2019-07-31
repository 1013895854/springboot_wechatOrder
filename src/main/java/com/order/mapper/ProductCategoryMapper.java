package com.order.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.entity.ProductCategoryEntity;

@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryEntity>{
	//分页
	public IPage<ProductCategoryEntity> selectPageVo(Page page,@Param("categoryId") Integer categoryId);
}
