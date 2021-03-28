package com.globalfreepay.payment.param.req;

public class CreateRetailPay {
	private String description;
	// 必填，订单标题（最大长度128字符，超出自动截取）

	private int price;
	// 必填，金额，单位为货币最小单位，例如使用100表示1.00 GBP

	private String currency;
	// 币种代码
	// 默认值:
	// GBP
	// 允许值:GBP,
	// CNY

	private String device_id;

	private String auth_code;

	private String notify_url;
	// 支付通知url，详见支付通知api，
	// 不填则不会推送支付通知

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

}
