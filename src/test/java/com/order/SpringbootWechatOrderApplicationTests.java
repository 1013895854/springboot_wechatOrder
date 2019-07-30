package com.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWechatOrderApplicationTests {
	 private Logger logger = LoggerFactory.getLogger(SpringbootWechatOrderApplicationTests.class);
	@Test
	public void contextLoads() {
		logger.error("error"+6);
	}
	
	@Test
	public void test() {
		System.out.println();
	}

}
