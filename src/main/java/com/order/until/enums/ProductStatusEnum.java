package com.order.until.enums;

import lombok.Getter;

/**
 * 通用枚举类
 * @author myad
 *
 */
@Getter
public enum ProductStatusEnum {
	UP(0,"在架"),
	DOWN(1,"下架");
	
	private Integer code;
	
	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
