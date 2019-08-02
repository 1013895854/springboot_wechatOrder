package com.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.OrderMasterEntity;
import com.order.mapper.OrderMasterMapper;
import com.order.service.OrderMasterService;

@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMasterEntity> implements OrderMasterService{

}
