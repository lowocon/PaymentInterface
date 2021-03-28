package com.globalfreepay.payment.param.req;

import com.alibaba.fastjson.JSON;

public class CreateCustom {
	private String order_id;
	// 必填，订单标题（最大长度128字符，超出自动截取）

	private String custom;
	private String mch_custom_id;
	private String mch_custom_name;
	private JSON sub_order;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getMch_custom_id() {
		return mch_custom_id;
	}

	public void setMch_custom_id(String mch_custom_id) {
		this.mch_custom_id = mch_custom_id;
	}

	public String getMch_custom_name() {
		return mch_custom_name;
	}

	public void setMch_custom_name(String mch_custom_name) {
		this.mch_custom_name = mch_custom_name;
	}

	public JSON getSub_order() {
		return sub_order;
	}

	public void setSub_order(JSON sub_order) {
		this.sub_order = sub_order;
	}

}
