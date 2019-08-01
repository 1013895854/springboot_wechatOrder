package com.order.until.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
	PAID(1,"已支付"),
	UNPAID(0,"未支付");
	
	private Integer code;
	
	private String message;

	private OrderStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
