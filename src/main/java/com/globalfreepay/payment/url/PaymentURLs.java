package com.globalfreepay.payment.url;

public class PaymentURLs {

	final static String PUT_createQRCode = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}";
	final static String GET_jumpQRCode = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}/pay";
	final static String PUT_createJSApi = "https://pay.globepay.co/api/v1.0/jsapi_gateway/partners/{partner_code}/orders/{order_id}";
	final static String GET_jumpJSApi_VX = "https://pay.globepay.co/api/v1.0/wechat_jsapi_gateway/partners/{partner_code}_order_{order_id}";
	final static String GET_jumpJSApi_ZFB = "https://pay.globepay.co/api/v1.0/gateway/alipay/partners/{partner_code}/orders/{order_id}/app_pay";
	final static String PUT_createMobileH5 = "https://pay.globepay.co/api/v1.0/h5_payment/partners/{partner_code}/orders/{order_id}";
	final static String GET_jumpMobileH5 = "https://pay.globepay.co/api/v1.0/h5_payment/partners/{partner_code}/orders/{order_id}/pay";
	final static String PUT_createMiniprogramPayment = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/microapp_orders/{order_id}";
	final static String PUT_createRetailPay = "https://pay.globepay.co/api/v1.0/micropay/partners/{partner_code}/orders/{order_id}";
	final static String PUT_createRetailPay_QRCode = "https://pay.globepay.co/api/v1.0/retail_qrcode/partners/{partner_code}/orders/{order_id}";
	final static String PUT_createAlipayOnlinePayment = "https://pay.globepay.co/api/v1.0/alipay/partners/{partner_code}/orders/{order_id}";
	final static String PUT_createSDKPayment = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/app_orders/{order_id}";
	final static String PUT_createCustom = "https://pay.globepay.co/api/v1.0/customs/partners/{partner_code}/declare/report/{client_report_id}";
	final static String GET_queryCustom = "https://pay.globepay.co/api/v1.0/customs/partners/{partner_code}/declare/query/{client_report_id}";
	final static String PUT_recreateCustom = "https://pay.globepay.co/api/v1.0/customs/partners/{partner_code}/redeclare/report/{client_report_id}";
	final static String GET_queryCurrentExchangeRate = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/channel_exchange_rate";
	final static String GET_queryOrdersStatus = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}";
	final static String PUT_applyForRefund = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}/refunds/{refund_id}";
	final static String GET_queryRefundStatus = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}/refunds/{refund_id}";
	final static String GET_queryOrders = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders";
	final static String GET_queryOrdersFollow = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/transactions";
	final static String GET_querySettlements = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/settlements";
	final static String POST_uploadFile_Merchants = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/upload_file";
	final static String POST_uploadFile_OrderComplianceMaterials = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}/extension";
	final static String GET_uploadFile_OrderComplianceMaterials = "https://pay.globepay.co/api/v1.0/gateway/partners/{partner_code}/orders/{order_id}/extension";
	final static String PUT_createChina_Pay_Payment = "https://pay.globepay.co/api/v1.0/chinapay/partners/{partner_code}/orders/{order_id}";
	final static String PUT_createWeb_Gateway = "https://pay.globepay.co/api/v1.0/web_gateway/partners/{partner_code}/orders/{order_id}";

	public static String getPUT_createQRCode() {
		return PUT_createQRCode;
	}

	public static String getGET_jumpQRCode() {
		return GET_jumpQRCode;
	}

	public static String getPUT_createJSApi() {
		return PUT_createJSApi;
	}

	public static String getGET_jumpJSApi_VX() {
		return GET_jumpJSApi_VX;
	}

	public static String getGET_jumpJSApi_ZFB() {
		return GET_jumpJSApi_ZFB;
	}

	public static String getPUT_createMobileH5() {
		return PUT_createMobileH5;
	}

	public static String getGET_jumpMobileH5() {
		return GET_jumpMobileH5;
	}

	public static String getPUT_createMiniprogramPayment() {
		return PUT_createMiniprogramPayment;
	}

	public static String getPUT_createRetailPay() {
		return PUT_createRetailPay;
	}

	public static String getPUT_createRetailPay_QRCode() {
		return PUT_createRetailPay_QRCode;
	}

	public static String getPUT_createAlipayOnlinePayment() {
		return PUT_createAlipayOnlinePayment;
	}

	public static String getPUT_createSDKPayment() {
		return PUT_createSDKPayment;
	}

	public static String getPUT_createCustom() {
		return PUT_createCustom;
	}

	public static String getGET_queryCustom() {
		return GET_queryCustom;
	}

	public static String getPUT_recreateCustom() {
		return PUT_recreateCustom;
	}

	public static String getGET_queryCurrentExchangeRate() {
		return GET_queryCurrentExchangeRate;
	}

	public static String getGET_queryOrdersStatus() {
		return GET_queryOrdersStatus;
	}

	public static String getPUT_applyForRefund() {
		return PUT_applyForRefund;
	}

	public static String getGET_queryRefundStatus() {
		return GET_queryRefundStatus;
	}

	public static String getGET_queryOrders() {
		return GET_queryOrders;
	}

	public static String getGET_queryOrdersFollow() {
		return GET_queryOrdersFollow;
	}

	public static String getGET_querySettlements() {
		return GET_querySettlements;
	}

	public static String getPOST_uploadFile_Merchants() {
		return POST_uploadFile_Merchants;
	}

	public static String getPOST_uploadFile_OrderComplianceMaterials() {
		return POST_uploadFile_OrderComplianceMaterials;
	}

	public static String getGET_uploadFile_OrderComplianceMaterials() {
		return GET_uploadFile_OrderComplianceMaterials;
	}

	public static String getPUT_createChina_Pay_Payment() {
		return PUT_createChina_Pay_Payment;
	}

	public static String getPUT_createWeb_Gateway() {
		return PUT_createWeb_Gateway;
	}

}
