package com.order.entity.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInfoVo {
	
	@JsonProperty(value="id")
	private String productId;
	
	@JsonProperty(value="name")
	private String productName;
	
	@JsonProperty(value="price")
	private BigDecimal productPrice;
	
	@JsonProperty(value="description")
	private String productDescription;
	
	@JsonProperty(value="icon")
	private String productIcon;
}
