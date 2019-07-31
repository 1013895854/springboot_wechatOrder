package com.order.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@TableName("product_category")
public class ProductCategoryEntity {
	
	@TableId(type=IdType.AUTO)
    private  Integer categoryId;
	 //类目名字
    private  String categoryName;
	 //类目编号
    private  Integer categoryType;
	 //创建时间
    private  Date createTime;
	 //修改时间
    private  Date updateTime;
   
}
