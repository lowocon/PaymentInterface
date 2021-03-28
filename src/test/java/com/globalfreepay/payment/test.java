package com.globalfreepay.payment;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.globalfreepay.payment.url.PaymentURLs;
import com.globalfreepay.payment.util.QueryParams;

public class test {
	public static void main(String[] args) {
		System.out.println(PaymentURLs.getPUT_createJSApi().replace("{partner_code}", QueryParams.getPartnerCode())
				.replace("{order_id}", "123445") + "?" + QueryParams.queryParams4());
		System.out.println("TEST_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "_"
				+ RandomStringUtils.random(5, true, true).toUpperCase());
	}
}
