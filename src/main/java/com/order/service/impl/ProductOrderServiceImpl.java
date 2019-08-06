package com.order.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.entity.OrderDetailEntity;
import com.order.entity.OrderMasterEntity;
import com.order.entity.ProductInfoEntity;
import com.order.entity.dto.OrderDetailVo;
import com.order.entity.dto.OrderMasterDto;
import com.order.entity.dto.OrderMasterVo;
import com.order.service.OrderDetailService;
import com.order.service.OrderMasterService;
import com.order.service.ProductInfoService;
import com.order.service.ProductOrderService;
import com.order.until.GsonUtil;
import com.order.until.OrderNumber;
import com.order.until.enums.OrderStatusEnum;
import com.order.until.enums.PayStatusEnum;
import com.order.until.exception.BusinessException;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProductOrderServiceImpl implements ProductOrderService{
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private OrderMasterService orderMasterService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private WebSocket webSocket;
	
	@Override
	public String createOrder(OrderMasterVo orderMasterVo) throws BusinessException {
		//获取购物车内容
		List<OrderDetailVo> items = orderMasterVo.getItems();
		//总金额
		BigDecimal total = new BigDecimal("0");
		//订单 号
		String orderNumber = OrderNumber.getStringByLength(4);
		for(OrderDetailVo item : items) {
			String productId = item.getProductId();
			ProductInfoEntity infoEntity = productInfoService.getById(productId);
			//减少库存
			Integer stock = infoEntity.getProductStock();
			if(stock<item.getProductQuantity()) {
				throw new BusinessException("库存不符");
			}
			Integer productStock = stock - item.getProductQuantity() ;
			infoEntity.setProductStock(productStock);
			boolean updateById = productInfoService.updateById(infoEntity);
			if(!updateById) {
				throw new BusinessException("更新库存失败");
			}
			
			BigDecimal decimal = infoEntity.getProductPrice().multiply(new BigDecimal(item.getProductQuantity()+"")).setScale(2, RoundingMode.HALF_UP);
			//计算总金额
			total = total.add(decimal);
			//商品详情
			OrderDetailEntity detailEntity = new OrderDetailEntity();
			BeanUtils.copyProperties(item, detailEntity);
			detailEntity.setOrderId(orderNumber);
			detailEntity.setProductName(infoEntity.getProductName());
			detailEntity.setProductPrice(infoEntity.getProductPrice());
			detailEntity.setProductIcon(infoEntity.getProductIcon());
			detailEntity.setCreateTime(new Date());
			boolean save = orderDetailService.save(detailEntity);
			if(!save) {
				throw new BusinessException("保存商品详情失败");
			}
			
		}
//		items.forEach(x->{
//			String productId = x.getProductId();
//			ProductInfoEntity infoEntity = productInfoService.getById(productId);
//			total = infoEntity.getProductPrice().multiply(new BigDecimal(x.getProductQuantity()+"")).setScale(2, RoundingMode.HALF_UP);
//			
//		});
		
		//生成订单
		OrderMasterEntity masterEntity = new OrderMasterEntity();
		BeanUtils.copyProperties(orderMasterVo, masterEntity);
		masterEntity.setOrderId(orderNumber);
		masterEntity.setOrderAmount(total);
		boolean save = orderMasterService.save(masterEntity);
		if(!save) {
			new BusinessException("保存商品订单失败");
		}
		//创建订单后通知浏览器（websocket）
		webSocket.sendMessage("有新的订单，准备查收");
//		webSocket.sendMessage(GsonUtil.GsonString(masterEntity));
		return orderNumber;
	}
	
	@Override
	public List<OrderMasterDto> getOrderList(String openid, Integer page, Integer size) {
		Page<OrderMasterEntity> pages = new Page<OrderMasterEntity>(page,size);
		QueryWrapper<OrderMasterEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("buyer_openid", openid);
		IPage<OrderMasterEntity> orderMasterList = orderMasterService.page(pages, queryWrapper);
		List<OrderMasterDto> orderMasterDto = new ArrayList<>();
		orderMasterList.getRecords().forEach(x->{
			OrderMasterDto masterDto = new OrderMasterDto();
			BeanUtils.copyProperties(x, masterDto);
			orderMasterDto.add(masterDto);
		});
		return orderMasterDto;
	}

	@Override
	public OrderMasterDto getOrderDetaisList(String openid, String orderId) {
		QueryWrapper<OrderMasterEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("buyer_openid", openid);
		queryWrapper.eq("order_id", orderId);
		OrderMasterEntity masterEntity = orderMasterService.getOne(queryWrapper);
		
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		
		BeanUtils.copyProperties(masterEntity, orderMasterDto);
		
		//获取所有订单号
//		List<String> collect = list.stream().map(x->x.getOrderId()).collect(Collectors.toList());
		//根据 订单号查询所有订单详情
		QueryWrapper<OrderDetailEntity> query = new QueryWrapper<>();
		query.eq("order_id", orderId);
		List<OrderDetailEntity> orderDetailList = orderDetailService.list(query);
		//
//		List<OrderMasterDto> orderMasterDto = new ArrayList<>();
//		list.forEach(x->{
//			OrderMasterDto masterDto = new OrderMasterDto();
//			BeanUtils.copyProperties(x, masterDto);
//			orderMasterDto.add(masterDto);
//		});
//		orderMasterDto.forEach(x->{
//			List<OrderDetailEntity> filter = orderDetailList.stream().filter(item->item.getOrderId().equals(x.getOrderId())).collect(Collectors.toList());
//			x.setOrderDetailList(filter);
//		});
		orderMasterDto.setOrderDetailList(orderDetailList);
		
		return orderMasterDto;
	}
	
	
	@Override
	public boolean updateOrderCancel(String openid, String orderId) throws BusinessException {
		// 获取订单
		QueryWrapper<OrderMasterEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("buyer_openid", openid);
		queryWrapper.eq("order_id", orderId);
		OrderMasterEntity masterEntity = orderMasterService.getOne(queryWrapper);
		//判断支付状态
		Integer payStatus = masterEntity.getPayStatus();
		//未支付状态
		if(payStatus == PayStatusEnum.WAIT.getCode()) {
			masterEntity.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
			boolean result = orderMasterService.updateById(masterEntity);
			if(!result) {
				throw new BusinessException("更新状态失败");
			}
		}else if(payStatus == PayStatusEnum.SUCCESS.getCode()) {
			//支付状态，退款
			
		}
		return true;
	}


}
