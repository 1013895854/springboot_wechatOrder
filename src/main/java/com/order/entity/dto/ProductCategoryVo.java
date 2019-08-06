package com.order.entity.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCategoryVo {
	
	
	@JsonProperty(value="name")
	@NotBlank(message="name 不能为空")
    private  String categoryName;
	
	@JsonProperty(value="type")
	@NotBlank(message="type 不能为空")
    private  Integer categoryType;
	
	@JsonProperty(value="foods")
    private List<ProductInfoVo> foods;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public List<ProductInfoVo> getFoods() {
		return foods;
	}

	public void setFoods(List<ProductInfoVo> foods) {
		this.foods = foods;
	}
	
	
	
}
