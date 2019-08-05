package com.order.service;

import java.util.List;

import com.order.entity.dto.OrderMasterDto;
import com.order.entity.dto.OrderMasterVo;
import com.order.until.exception.BusinessException;

public interface ProductOrderService {
	
	//保存订单统一处理
	public String createOrder(OrderMasterVo orderMasterVo) throws BusinessException;
	
	//查询订单
	public List<OrderMasterDto> getOrderList(String openid,Integer page,Integer size);
	
	//查询订单详情
	public OrderMasterDto getOrderDetaisList(String openid, String orderId);
	
	//取消订单
	public boolean updateOrderCancel(String openid, String orderId) throws BusinessException;
}
