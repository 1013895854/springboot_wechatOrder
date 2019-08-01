package com.order.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.order.entity.ProductCategoryEntity;
import com.order.entity.ProductInfoEntity;
import com.order.entity.dto.ProductCategoryVo;
import com.order.entity.dto.ProductInfoVo;
import com.order.entity.dto.QueryProductParamDto;
import com.order.service.ProductCategoryService;
import com.order.service.ProductInfoService;
import com.order.until.GsonUtil;
import com.order.until.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("api/productInfo")
@Slf4j
@Api(tags="商品信息表")
public class ProductInfoController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@PostMapping("/getAllProductInfo")
	@ResponseBody
	@ApiOperation("查询所有类目和商品")
	public R selectAllInfo(){
		//获取所有类目
		List<ProductCategoryEntity> categoryEntity = productCategoryService.list();
		List<Integer> collect = categoryEntity.stream().map(x->x.getCategoryType()).collect(Collectors.toList());
//		System.out.println(GsonUtil.GsonString(collect));
		//获取所有商品
		QueryWrapper<ProductInfoEntity> queryWrapper = new QueryWrapper<ProductInfoEntity>();
		queryWrapper.in("category_type", collect);
		List<ProductInfoEntity> products = productInfoService.list(queryWrapper);
		//拼接数据
		List<ProductCategoryVo> productCategoryList = new ArrayList<>();
		for(ProductCategoryEntity pce : categoryEntity) {
			//创建类目VO
			ProductCategoryVo productCategory  = new ProductCategoryVo();
			BeanUtils.copyProperties(pce, productCategory);
			//使用过滤器 过滤类目type相同的商品
			List<ProductInfoEntity> list = products.stream().filter(x->x.getCategoryType().equals(productCategory.getCategoryType())).collect(Collectors.toList());
			//
			List<ProductInfoVo> infoVo = new ArrayList<>();
			list.forEach(x->{
				ProductInfoVo productInfoVo = new ProductInfoVo();
				BeanUtils.copyProperties(x, productInfoVo);
				infoVo.add(productInfoVo);
			});
			//赋值
			productCategory.setFoods(infoVo);
			productCategoryList.add(productCategory);
		}
		
		return R.success(productCategoryList);
		
	}
	
	
	@PostMapping("/getAllProduct")
	@ResponseBody
	@ApiOperation("查询所有商品")
	public R selectAll(){
		List<ProductInfoEntity> list = productInfoService.list();
		return R.success(list);
		
	}
	
	@PostMapping("/getProductInfoByStatus")
	@ResponseBody
	@ApiOperation("查询上架/下架 商品")
	public R selectAllByStatus(@RequestBody QueryProductParamDto param){
		log.info(GsonUtil.GsonString(param));
		List<ProductInfoEntity> list = productInfoService.selectAllByStatus(param);
		if(list==null) {
			R.error();
		}
		return R.success(list);
		
	}
	
	@GetMapping("/deleteProductInfoByIds")
	@ResponseBody
	@ApiOperation("根据id批量删除")
	public R deleteById(@RequestBody Long[] roleIds){
		productInfoService.removeByIds(Arrays.asList(roleIds));
		return R.ok();
		
	}
}
