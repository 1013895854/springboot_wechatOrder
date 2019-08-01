package com.order.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryVo {
	
	@JsonProperty("name")
    private  String categoryName;
	@JsonProperty("type")
    private  Integer categoryType;
	@JsonProperty("foods")
    private List<ProductInfoVo> foods;
}
