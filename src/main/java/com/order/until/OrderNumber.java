package com.order.until;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class OrderNumber {
	
	/**
	 * 获取指定长度的随机字符串(指定长度的字符加时间)
	 * @param length
	 * @return
	 */
	public static String getStringByLength(Integer length) {
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<length;i++) {
			int index = rd.nextInt(chars.length);
			sb.append(chars[index]);
		}
		return sb.toString().toUpperCase()+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
//	public static void main(String[] args) {
//		String randomUUID = UUID.randomUUID().toString().replace("-", "").toUpperCase();
//		System.out.println(randomUUID.substring(5));
//		System.out.println(getStringByLength(6));
//	}
}
