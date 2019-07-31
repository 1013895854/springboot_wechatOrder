package com.order.entity.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ApiModel(value="添加商品类目数据DTO")
public class ProductCategoryDto {
	 
	@ApiModelProperty(value="类目名字")
    private  String categoryName;
	 
	@ApiModelProperty(value="类目编号")
    private  Integer categoryType;
   
}
