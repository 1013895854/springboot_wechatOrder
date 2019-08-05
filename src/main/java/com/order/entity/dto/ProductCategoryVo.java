package com.order.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductCategoryVo {
	
	
	@JsonProperty(value="name")
    private  String categoryName;
	@JsonProperty(value="type")
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
