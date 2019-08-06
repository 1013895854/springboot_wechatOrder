package com.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts.OAuth2Scope;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping("wechat")
@Slf4j
@Api(tags ="微信JSAPI支付处理(SDK)")
public class WechatOrderSDKController {
	
	@Value("${wechat.state}")
	private String state;
	
	@Autowired
	private WxMpService wxMpService;
	
	
	@GetMapping("/authorize")
	@ApiOperation("网页授权获取code")
	public String authorize(@RequestParam(value="redirectUrl") String redirectUrl) {
		log.info("param----->"+redirectUrl);
		String authorizationUrl = wxMpService.oauth2buildAuthorizationUrl(redirectUrl, OAuth2Scope.SNSAPI_USERINFO, state);
		
		return "redirect:"+authorizationUrl;
	}
	
	@GetMapping("/userInfo")
	@ApiOperation("网页授权根据code获取openId")
	public String userInfo(@RequestParam(value="code") String code,@RequestParam(value="state") String redirectUrl) {
		try {
			//根据code获取用户信息
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			//
			WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			String openId = wxMpUser.getOpenId();
			return "redirect:"+redirectUrl+"?openId"+openId;
		} catch (Exception e) {
			return "";
		}
		
	}
	
	
}
