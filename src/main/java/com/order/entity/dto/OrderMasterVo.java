package com.order.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderMasterVo {

	// 买家名字
	@JsonProperty("name")
	private String buyerName;
	
	// 买家电话
	@JsonProperty("phone")
	private String buyerPhone;
	
	// 买家地址
	@JsonProperty("address")
	private String buyerAddress;
	
	// 买家微信openid
	@JsonProperty("openid")
	private String buyerOpenid;
	
	
	private List<OrderDetailVo> items;
}
