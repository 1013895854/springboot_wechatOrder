package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.entity.dto.OrderMasterDto;
import com.order.entity.dto.OrderMasterVo;
import com.order.service.ProductOrderService;
import com.order.until.GsonUtil;
import com.order.until.R;
import com.order.until.ValidatorUtils;
import com.order.until.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("api/order")
@Slf4j
@Api(tags="订单处理接口")
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@PostMapping("/createOrder")
	@ResponseBody
	@ApiOperation("创建订单")
	public R createOrder(@RequestBody OrderMasterVo orderMasterVo) {
		try {
			log.info(GsonUtil.GsonString(orderMasterVo));
			ValidatorUtils.validateEntity(orderMasterVo);
			String createOrder = productOrderService.createOrder(orderMasterVo);
			return R.ok(createOrder);
		} catch (BusinessException e) {
			log.error(e.getErrorDesc());
			return R.error(e.getErrorDesc());
		}
		
		
	}
	
	@GetMapping("/getOrderList")
	@ResponseBody
	@ApiOperation("获取订单列表")
	public R getOrderByOpenid(@RequestParam("openid") String openid ,@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
		log.info(openid);
//		String createOrder = productOrderService.createOrder(orderMasterVo);
		System.out.println(openid);
		List<OrderMasterDto> orderList = productOrderService.getOrderList(openid, page, size);
		return R.success(orderList);
	}
	
	@GetMapping("/getOrderDetaisList")
	@ResponseBody
	@ApiOperation("获取订单详情")
	public R getOrderDetaisList(@RequestParam("openid") String openid ,@RequestParam("orderId") String orderId) {
//		log.info(GsonUtil.GsonString(orderMasterVo));
//		String createOrder = productOrderService.createOrder(orderMasterVo);
//		System.out.println(openid);
		OrderMasterDto orderList = productOrderService.getOrderDetaisList(openid, orderId);
		return R.success(orderList);
	}
	
	@GetMapping("/updateOrderCancel")
	@ResponseBody
	@ApiOperation("取消订单")
	public R updateOrderCancel(@RequestParam("openid") String openid ,@RequestParam("orderId") String orderId) {
		try {
			productOrderService.updateOrderCancel(openid, orderId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return R.error(e.getErrorDesc());
		}
		return R.ok();
	}
}
