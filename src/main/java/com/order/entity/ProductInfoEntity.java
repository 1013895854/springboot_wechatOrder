package com.order.entity;

import java.math.BigDecimal;
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
@TableName("product_info")
public class ProductInfoEntity {
	
	private String productId;
	// 商品名称
	private String productName;
	// 单价
	private BigDecimal productPrice;
	// 库存
	private Integer productStock;
	// 描述
	private String productDescription;
	// 小图
	private String productIcon;
	// 商品状态,0正常1下架
	private Integer productStatus;
	// 类目编号
	private Integer categoryType;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;
}
