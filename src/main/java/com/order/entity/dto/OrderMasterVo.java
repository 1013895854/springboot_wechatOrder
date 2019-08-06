package com.order.entity.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ApiModel(value ="创建订单的接收参数")
public class OrderMasterVo {

	// 买家名字
	@JsonProperty("name")
	@ApiModelProperty(value ="买家名字")
	@NotBlank(message="买家名字不能为空")
	private String buyerName;
	
	// 买家电话
	@JsonProperty("phone")
	@ApiModelProperty(value ="买家电话")
	@NotBlank(message="买家电话不能为空")
	private String buyerPhone;
	
	// 买家地址
	@JsonProperty("address")
	@ApiModelProperty(value ="买家地址")
	@NotBlank(message="买家地址不能为空")
	private String buyerAddress;
	
	// 买家微信openid
	@JsonProperty("openid")
	@ApiModelProperty(value ="买家微信openid")
	@NotBlank(message="买家微信openid不能为空")
	private String buyerOpenid;
	
	@ApiModelProperty(value ="购物车")
	private List<OrderDetailVo> items;
}
