package com.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.ProductInfoEntity;
import com.order.mapper.ProductInfoMapper;
import com.order.service.ProductInfoService;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfoEntity> implements ProductInfoService{

}
