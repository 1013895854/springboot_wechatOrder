package com.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.entity.ProductCategoryEntity;
import com.order.mapper.ProductCategoryMapper;
import com.order.service.ProductCategoryService;

//import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class Demo {
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	public void test01() {
		List<ProductCategoryEntity> selectList = productCategoryMapper.selectList(null);
		System.out.println(selectList);
	}
	@Test
	public void test02() {
		ProductCategoryEntity entity = new ProductCategoryEntity();
		entity.setCategoryName("水果");
		entity.setCategoryType(003);
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		int insert = productCategoryMapper.insert(entity);
		System.out.println("插入数据=="+insert);
	}
	
	@Test
	public void test03() {
		Page<ProductCategoryEntity> page = new Page<ProductCategoryEntity>(1,2);
	//	IPage<ProductCategoryEntity> selectPageVo = productCategoryServiceImpl.selectPageVo(page);
		QueryWrapper<ProductCategoryEntity> abstractWrapper = new QueryWrapper<ProductCategoryEntity>();
		//查询条件
		//abstractWrapper.eq("category_id", 2);
		abstractWrapper.orderByDesc("category_id");
		//abstractWrapper.select
		IPage<ProductCategoryEntity> iPage = productCategoryService.page(page, abstractWrapper);
		System.out.println(iPage.getTotal());
		System.out.println(iPage.getRecords());
	}
	@Test
	public void test04() {
		Page<ProductCategoryEntity> page = new Page<ProductCategoryEntity>(1,2);
		IPage<ProductCategoryEntity> selectPageVo = productCategoryService.selectPageVo(page,2);
		System.out.println(selectPageVo.getTotal());
		System.out.println(selectPageVo.getRecords());
		
	}
}
