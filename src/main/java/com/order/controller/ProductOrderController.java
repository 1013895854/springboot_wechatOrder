package com.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.entity.dto.OrderMasterVo;
import com.order.until.GsonUtil;
import com.order.until.R;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("api/order")
@Slf4j
@Api(tags="订单处理接口")
public class ProductOrderController {
	
	@PostMapping("/createOrder")
	@ResponseBody
	public R createOrder(@RequestBody OrderMasterVo orderMasterVo) {
		log.info(GsonUtil.GsonString(orderMasterVo));
		return R.ok();
	}
}
