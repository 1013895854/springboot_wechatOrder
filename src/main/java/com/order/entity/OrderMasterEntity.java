package com.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@TableName("order_master")
public class OrderMasterEntity {
	
	@TableId(type= IdType.INPUT)
	private String orderId;
	// 买家名字
	private String buyerName;
	// 买家电话
	private String buyerPhone;
	// 买家地址
	private String buyerAddress;
	// 买家微信openid
	private String buyerOpenid;
	// 订单总金额
	private BigDecimal orderAmount;
	// 订单状态, 默认为新下单
	private Integer orderStatus;
	// 支付状态, 默认未支付
	private Integer payStatus;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;
}
