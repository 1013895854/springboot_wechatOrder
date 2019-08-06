/**
 * Copyright  
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.order.until;


import java.io.Serializable;

/**
 * 返回数据
 * 
 * @author
 * @email 
 * @date 
 */
public class R implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int code;

	private String msg;

	private Object data;

	public R() {
		this.code = 0;
		this.msg = "success";
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.code = code;
		r.msg = msg;
		return r;
	}

//	public static R error(ErrorCode errorCode) {
//		R r = new R();
//		r.code = errorCode.getCode();
//		r.msg = errorCode.getMsg();
//		return r;
//	}


	public static R ok(String msg) {
		R r = new R();
		r.msg = msg;
		return r;
	}
	
	public static R ok() {
		return new R();
	}
	public static R success(Object data) {
		R r = new R();
		r.data = data;
		return r;
	}


	public R put(String key, Object value) {
		if("code".equals(key)) {
			this.code=(int)value;
		}
		if("msg".equals(key)) {
			this.msg=value.toString();
		}
		if("data".equals(key)) {
			this.data=value;
		}
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public R setData(Object data) {
		this.data = data;
		return this;
	}
}
