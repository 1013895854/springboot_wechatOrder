package com.order.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryVo {
	
	
	@JsonProperty(value="name")
    private  String categoryName;
	@JsonProperty(value="type")
    private  Integer categoryType;
	@JsonProperty(value="foods")
    private List<ProductInfoVo> foods;
	
	
	
}
