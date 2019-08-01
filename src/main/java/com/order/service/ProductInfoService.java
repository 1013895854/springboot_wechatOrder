package com.order.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.order.entity.ProductInfoEntity;
import com.order.entity.dto.QueryProductParamDto;

public interface ProductInfoService extends IService<ProductInfoEntity>{
	
	//根据type 查询上架/下架 商品
	public List<ProductInfoEntity> selectAllByStatus(QueryProductParamDto param);
	
}
