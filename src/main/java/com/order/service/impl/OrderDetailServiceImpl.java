package com.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.OrderDetailEntity;
import com.order.mapper.OrderDetailMapper;
import com.order.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailEntity> implements OrderDetailService{

}
