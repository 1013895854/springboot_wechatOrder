package com.order.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ApiModel(value="购物车")
public class OrderDetailVo {

	// product_id
	@ApiModelProperty(value="商品编号")
	private String productId;
	// 数量
	@ApiModelProperty(value="购买商品数量")
	private Integer productQuantity;
}
