package com.globalfreepay.payment.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

public class QueryParams {
	private static final String PARTNER_CODE = "F1XHKK";
	// use test
	// private static final String CREDENTIAL_CODE1 =
	// "xOjMTaG0Tk7fqNYjsNqDKXtJvE9MFCqC";
	private static final String CREDENTIAL_CODE = "h03GpBExu3TBYKwB0QFgWYZ5MoSbe5eE";

	public static String queryParams4() {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = PARTNER_CODE + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String queryParams4(String partner_code) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = partner_code + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String GET_jumpJSApi_VX(String partner_code, String redirect, boolean directpay) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = partner_code + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "redirect=" + redirect + "&directpay=" + directpay + "&time=" + time + "&nonce_str=" + nonceStr
				+ "&sign=" + sign;
	}

	public static String queryParams_GET_queryOrders(String partner_code, String date, String status, int page,
			int limit) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = partner_code + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "&date=" + date + "&status=" + status + "&page=" + page + "&limit=" + limit + "&time=" + time
				+ "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String queryParams_GET_queryOrdersFollow(String partner_code, String date) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = partner_code + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "&date=" + date + "&time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String queryParams_GET_jumpMobileH5(String partner_code, String redirect) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = partner_code + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "redirect=" + redirect + "&time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String queryParams5(String str1, String str2) {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = str1 + "&" + str2 + "&" + time + "&" + nonceStr + "&" + CREDENTIAL_CODE;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String queryParams2() {
		long time = System.currentTimeMillis();
		String nonceStr = RandomStringUtils.random(15, true, true);
		String validStr = time + "&" + nonceStr;
		String sign = DigestUtils.sha256Hex(validStr).toLowerCase();
		return "time=" + time + "&nonce_str=" + nonceStr + "&sign=" + sign;
	}

	public static String getPartnerCode() {
		return PARTNER_CODE;
	}

	public static String getCredentialCode() {
		return CREDENTIAL_CODE;
	}

}
