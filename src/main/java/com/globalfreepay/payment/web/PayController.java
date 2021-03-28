package com.globalfreepay.payment.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.globalfreepay.payment.param.req.CreateAlipayOnlinePayment;
import com.globalfreepay.payment.param.req.CreateApplyForRefund;
import com.globalfreepay.payment.param.req.CreateChina_Pay_Payment;
import com.globalfreepay.payment.param.req.CreateCustom;
import com.globalfreepay.payment.param.req.CreateMiniprogramPayment;
import com.globalfreepay.payment.param.req.CreateQRCode;
import com.globalfreepay.payment.param.req.CreateRetailPay;
import com.globalfreepay.payment.param.req.CreateSDKPayment;
import com.globalfreepay.payment.param.req.FileName;
import com.globalfreepay.payment.param.req.Filess;
import com.globalfreepay.payment.param.req.QueryOrders;
import com.globalfreepay.payment.support.exceptions.ServerErrorException;
import com.globalfreepay.payment.support.http.HttpRequestGenerator;
import com.globalfreepay.payment.support.http.HttpRequestMultipartFile;
import com.globalfreepay.payment.support.http.HttpRequestResult;
import com.globalfreepay.payment.url.PaymentURLs;
import com.globalfreepay.payment.util.QueryParams;

/**
 * Created by davep on 2016-07-24.
 */
@RestController
@RequestMapping("/api/payment")
public class PayController {
	@RequestMapping(value = "/PUT_createQRCode", method = RequestMethod.POST)
	public JSONObject PUT_createQRCode(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateQRCode createQRCde)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createQRCde);
		String createUrl = PaymentURLs.getPUT_createQRCode().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_jumpQRCode", method = RequestMethod.GET)
	public String GET_jumpQRCode(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "redirect") String redirect)
			throws URISyntaxException, IOException {
		// JSONObject param = (JSONObject) JSONObject.toJSON(createQRCde);
		String createUrl = PaymentURLs.getGET_jumpQRCode().replace("{partner_code}", partner_code).replace("{order_id}",
				order_id) + "?" + QueryParams.queryParams_GET_jumpMobileH5(partner_code, redirect);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			// System.out.println(res.toJSONString());
			return result.getResponseContentString();
			// JSONObject res = result.getResponseContentJSONObj();

			// return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createJSApi", method = RequestMethod.POST)
	public JSONObject PUT_createJSApi(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateQRCode createQRCde)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createQRCde);
		String createUrl = PaymentURLs.getPUT_createJSApi().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_jumpJSApi_VX", method = RequestMethod.GET)
	public JSONObject GET_jumpJSApi_VX(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "redirect") String redirect,
			@RequestParam(name = "directpay") boolean directpay) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_jumpJSApi_VX().replace("{partner_code}", partner_code).replace(
				"{order_id}", order_id) + "?" + QueryParams.GET_jumpJSApi_VX(partner_code, redirect, directpay);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_jumpJSApi_ZFB", method = RequestMethod.GET)
	public JSONObject GET_jumpJSApi_ZFB(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "redirect") String redirect,
			@RequestParam(name = "directpay") boolean directpay) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_jumpJSApi_ZFB().replace("{partner_code}", partner_code).replace(
				"{order_id}", order_id) + "?" + QueryParams.GET_jumpJSApi_VX(partner_code, redirect, directpay);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createMobileH5", method = RequestMethod.POST)
	public JSONObject PUT_createMobileH5(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateQRCode createQRCde)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createQRCde);
		String createUrl = PaymentURLs.getPUT_createMobileH5().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_jumpMobileH5", method = RequestMethod.GET)
	public String GET_jumpMobileH5(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "redirect") String redirect)
			throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_jumpMobileH5().replace("{partner_code}", partner_code).replace(
				"{order_id}", order_id) + "?" + QueryParams.queryParams_GET_jumpMobileH5(partner_code, redirect);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			// JSONObject res = result.getResponseContentJSONObj();
			// System.out.println(res.toJSONString());
			return result.getResponseContentString();
			// return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createMiniprogramPayment", method = RequestMethod.POST)
	public JSONObject PUT_createMiniprogramPayment(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id,
			@RequestBody CreateMiniprogramPayment createMiniprogramPayment) throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createMiniprogramPayment);
		String createUrl = PaymentURLs.getPUT_createMiniprogramPayment().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createRetailPay", method = RequestMethod.POST)
	public JSONObject PUT_createRetailPay(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateRetailPay createRetailPay)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createRetailPay);
		String createUrl = PaymentURLs.getPUT_createRetailPay().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createRetailPay_QRCode", method = RequestMethod.POST)
	public JSONObject PUT_createRetailPay_QRCode(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateRetailPay createRetailPay)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createRetailPay);
		String createUrl = PaymentURLs.getPUT_createRetailPay_QRCode().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createAlipayOnlinePayment", method = RequestMethod.POST)
	public JSONObject PUT_createAlipayOnlinePayment(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id,
			@RequestBody CreateAlipayOnlinePayment createAlipayOnlinePayment) throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createAlipayOnlinePayment);
		String createUrl = PaymentURLs.getPUT_createAlipayOnlinePayment().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createSDKPayment", method = RequestMethod.POST)
	public JSONObject PUT_createSDKPayment(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateSDKPayment createSDKPayment)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createSDKPayment);
		String createUrl = PaymentURLs.getPUT_createSDKPayment().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createCustom", method = RequestMethod.POST)
	public JSONObject PUT_createCustom(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "client_report_id") String client_report_id, @RequestBody CreateCustom createCustom)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createCustom);
		// System.out.println(param.toJSONString());
		String createUrl = PaymentURLs.getPUT_createCustom().replace("{partner_code}", partner_code)
				.replace("{client_report_id}", client_report_id) + "?" + QueryParams.queryParams4(partner_code);

		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryCustom", method = RequestMethod.GET)
	public JSONObject GET_queryCustom(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "client_report_id") String client_report_id) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryCustom().replace("{partner_code}", partner_code)
				.replace("{client_report_id}", client_report_id) + "?" + QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_recreateCustom", method = RequestMethod.POST)
	public JSONObject PUT_recreateCustom(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "client_report_id") String client_report_id, @RequestBody CreateCustom createCustom)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createCustom);
		String createUrl = PaymentURLs.getPUT_recreateCustom().replace("{partner_code}", partner_code)
				.replace("{client_report_id}", client_report_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryCurrentExchangeRate", method = RequestMethod.GET)
	public JSONObject GET_queryCurrentExchangeRate(@RequestParam(name = "partner_code") String partner_code)
			throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryCurrentExchangeRate().replace("{partner_code}", partner_code) + "?"
				+ QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryOrdersStatus", method = RequestMethod.GET)
	public JSONObject GET_queryOrdersStatus(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryOrdersStatus().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_applyForRefund", method = RequestMethod.POST)
	public JSONObject PUT_applyForRefund(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "refund_id") String refund_id,
			@RequestBody CreateApplyForRefund createApplyForRefund) throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createApplyForRefund);
		String createUrl = PaymentURLs.getPUT_applyForRefund().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id).replace("{refund_id}", refund_id) + "?"
				+ QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryRefundStatus", method = RequestMethod.GET)
	public JSONObject GET_queryRefundStatus(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestParam(name = "refund_id") String refund_id)
			throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryRefundStatus().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id).replace("{refund_id}", refund_id) + "?"
				+ QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryOrders", method = RequestMethod.POST)
	public JSONObject GET_queryOrders(@RequestParam(name = "partner_code") String partner_code,
			@RequestBody QueryOrders QueryOrders) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryOrders().replace("{partner_code}", partner_code) + "?"
				+ QueryParams.queryParams_GET_queryOrders(partner_code, QueryOrders.getDate(), QueryOrders.getStatus(),
						QueryOrders.getPage(), QueryOrders.getLimit());
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_queryOrdersFollow", method = RequestMethod.POST)
	public JSONObject GET_queryOrdersFollow(@RequestParam(name = "partner_code") String partner_code,
			@RequestBody QueryOrders QueryOrders) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_queryOrdersFollow().replace("{partner_code}", partner_code) + "?"
				+ QueryParams.queryParams_GET_queryOrdersFollow(partner_code, QueryOrders.getDate());
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_querySettlements", method = RequestMethod.GET)
	public JSONObject GET_querySettlements(@RequestParam(name = "partner_code") String partner_code,
			@RequestBody QueryOrders QueryOrders) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_querySettlements().replace("{partner_code}", partner_code) + "?"
				+ QueryParams.queryParams_GET_queryOrdersFollow(partner_code, QueryOrders.getDate());
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/POST_uploadFile_Merchants", method = RequestMethod.POST)
	public JSONObject POST_uploadFile_Merchants(@RequestParam(name = "partner_code") String partner_code,
			@RequestBody FileName fileName) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getPOST_uploadFile_Merchants().replace("{partner_code}", partner_code) + "?"
				+ QueryParams.queryParams4(partner_code);
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", fileName.getFile());
		String result = HttpRequestMultipartFile.formUpload(createUrl, null, fileMap);
		if (result != null) {
			JSONObject res = JSON.parseObject(result);
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/POST_uploadFile_OrderComplianceMaterials", method = RequestMethod.POST)
	public JSONObject POST_uploadFile_OrderComplianceMaterials(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody Filess file)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(file);
		String createUrl = PaymentURLs.getPOST_uploadFile_OrderComplianceMaterials()
				.replace("{partner_code}", partner_code).replace("{order_id}", order_id) + "?"
				+ QueryParams.queryParams4(partner_code);
		System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.POST).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/GET_uploadFile_OrderComplianceMaterials", method = RequestMethod.GET)
	public JSONObject GET_uploadFile_OrderComplianceMaterials(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id) throws URISyntaxException, IOException {
		String createUrl = PaymentURLs.getGET_uploadFile_OrderComplianceMaterials()
				.replace("{partner_code}", partner_code).replace("{order_id}", order_id) + "?"
				+ QueryParams.queryParams4(partner_code);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.GET).execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createChina_Pay_Payment", method = RequestMethod.POST)
	public JSONObject PUT_createChina_Pay_Payment(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id,
			@RequestBody CreateChina_Pay_Payment createChina_Pay_Payment) throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createChina_Pay_Payment);
		String createUrl = PaymentURLs.getPUT_createChina_Pay_Payment().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}

	@RequestMapping(value = "/PUT_createWeb_Gateway", method = RequestMethod.POST)
	public JSONObject PUT_createWeb_Gateway(@RequestParam(name = "partner_code") String partner_code,
			@RequestParam(name = "order_id") String order_id, @RequestBody CreateQRCode createQRCde)
			throws URISyntaxException, IOException {
		JSONObject param = (JSONObject) JSONObject.toJSON(createQRCde);
		String createUrl = PaymentURLs.getPUT_createWeb_Gateway().replace("{partner_code}", partner_code)
				.replace("{order_id}", order_id) + "?" + QueryParams.queryParams4(partner_code);
		// System.out.println(createUrl);
		HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param)
				.execute();
		if (result.isSuccess()) {
			JSONObject res = result.getResponseContentJSONObj();
			System.out.println(res.toJSONString());
			return res;
		}
		throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
	}
}
