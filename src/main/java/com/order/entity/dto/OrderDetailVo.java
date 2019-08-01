package com.order.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderDetailVo {

	// product_id
	private String productId;
	// 数量
	private Integer productQuantity;
}
