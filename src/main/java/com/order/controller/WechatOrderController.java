package com.order.controller;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.entity.jsonbean.JsonGetOpenId;
import com.order.until.GsonUtil;
import com.order.until.HttpUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("wechat")
@Slf4j
@Api(tags ="微信JSAPI支付处理")
public class WechatOrderController {
	
	@Value("${wechat.appid}")
	private String appid;
	@Value("${wechat.scope}")
	private String scope;
	@Value("${wechat.authorizeUrl}")
	private String authorizeUrl;		//获取code的url
	@Value("${wechat.state}")
	private String state;
	@Value("${wechat.appsecret}")
	private String secret;
	@Value("${wechat.access_token_url}")
	private String accessTokenUrl;		//获取openid的url
	
	@Value("${wechat.unified_order_url}")
	private String unifiedOrderUrl;		//统一下单接口
	
	@GetMapping("/unifiedOrder")
	@ApiOperation("统一下单接口")
	public String UnifiedOrder() {
		
		
		return "redirect:";
	}
	
	
	@SuppressWarnings("deprecation")
	@GetMapping("/authorize")
	@ApiOperation("网页授权获取code")
	public String authorize(@RequestParam(value="redirectUrl") String redirectUrl) {
		log.info("param----->"+redirectUrl);
		
		//拼接参数
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("appid", appid);
		params.put("redirect_uri", URLEncoder.encode(redirectUrl));
		params.put("response_type", "code");
		params.put("scope", scope);
		params.put("state", state);
		//如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
		String redirectUri = HttpUtil.get(authorizeUrl, params);
		
		return "redirect:"+redirectUri;
	}
	
	@GetMapping("/userInfo")
	@ApiOperation("网页授权根据code获取openId")
	public String userInfo(@RequestParam(value="code") String code,@RequestParam(value="state") String redirectUrl) {
		log.info("param----->"+redirectUrl);
		
		//拼接参数
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("appid", appid);
		params.put("secret", secret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		//
		String result = HttpUtil.get(accessTokenUrl, params);
		//
		JsonGetOpenId gsonToBean = GsonUtil.GsonToBean(result, JsonGetOpenId.class);
		//
		String openId = gsonToBean.getOpenid();
		
		return "redirect:"+redirectUrl+"?openId"+openId;
	}
	
	
}
