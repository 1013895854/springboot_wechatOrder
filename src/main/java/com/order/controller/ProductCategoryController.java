package com.order.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.entity.ProductCategoryEntity;
import com.order.entity.dto.ProductCategoryDto;
import com.order.service.ProductCategoryService;
import com.order.until.GsonUtil;
import com.order.until.R;
import com.order.until.ValidatorUtils;
import com.order.until.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("api/productCategory")
@Api(tags="商品类目接口")
@Slf4j
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/**
	 * 查询商品类目
	 */
	@PostMapping("/getProductCategory")
	@ResponseBody
	@ApiOperation("商品类目查询")
	public R getProductCategory() {
		List<ProductCategoryEntity> list = productCategoryService.list();
		log.info("获取的数据"+list);
		return R.success(list);
		
	}
	
	/**
	 * 新增商品类目
	 */
	@PostMapping("/saveProductCategory")
	@ResponseBody
	@ApiOperation("新增商品类目")
	public R saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		try {
			log.info("参数"+GsonUtil.GsonString(productCategoryDto));
			ValidatorUtils.validateEntity(productCategoryDto);
			ProductCategoryEntity entity =new ProductCategoryEntity();
			//赋值
			BeanUtils.copyProperties(productCategoryDto, entity);
			boolean result = productCategoryService.save(entity);
			if(!result) {
				R.error("保存失败");
			}
			return R.ok();
		} catch (BusinessException e) {
			e.printStackTrace();
			return R.error(e.getErrorDesc());
		}
		
		
	}
}
