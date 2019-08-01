package com.order.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value="查询商品")
@Setter
@Getter
public class QueryProductParamDto {
	
	@ApiModelProperty(value="查询商品：type:0 上架 ,type:1 下架")
	private Integer type;
	
}
