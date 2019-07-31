package com.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper") //mybatis-plus mapper 扫描包
public class SpringbootWechatOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWechatOrderApplication.class, args);
	}

}
