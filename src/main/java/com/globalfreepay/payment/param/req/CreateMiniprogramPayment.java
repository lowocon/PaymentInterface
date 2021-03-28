package com.globalfreepay.payment.param.req;

public class CreateMiniprogramPayment {
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

	private String notify_url;
	// 支付通知url，详见支付通知api，
	// 不填则不会推送支付通知

	private String appid;
	private String customer_id;
	private String operator;
	// 操作人员标识

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

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

}
