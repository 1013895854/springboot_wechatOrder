package com.order.entity.jsonbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JsonGetOpenId {

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}