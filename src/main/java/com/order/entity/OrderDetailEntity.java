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
@TableName("order_detail")
public class OrderDetailEntity {

	@TableId(type= IdType.AUTO)
	private Integer detailId;
	// order_id
	private String orderId;
	// product_id
	private String productId;
	// 商品名称
	private String productName;
	// 当前价格，单位分
	private BigDecimal productPrice;
	// 数量
	private Integer productQuantity;
	// 小图
	private String productIcon;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;
}
