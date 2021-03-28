package com.globalfreepay.payment.web;

import com.alibaba.fastjson.JSONObject;
import com.globalfreepay.payment.support.exceptions.ServerErrorException;
import com.globalfreepay.payment.support.http.HttpRequestGenerator;
import com.globalfreepay.payment.support.http.HttpRequestResult;
import com.globalfreepay.payment.util.QueryParams;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by davep on 2016-07-24.
 */
@Controller
@RequestMapping("/tmp/api/payment")
public class PayController_tmp {

    @Value("${app.host}")
    private String host;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orderPage() {
        return "order";
    }

    @RequestMapping(value = "/new_order", method = RequestMethod.GET)
    public String wxOrderJump(@RequestHeader(name = "User-Agent") String userAgent, @RequestParam(defaultValue = "false") boolean qrcode,
                              @RequestParam(defaultValue = "false") boolean direct) throws URISyntaxException, IOException {
        String orderId = "TEST_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "_" + RandomStringUtils.random(5, true, true).toUpperCase();
        JSONObject param = new JSONObject();
        param.put("price", 10);
        param.put("description", "DemoTest");
        param.put("notify_url", host + "api/orders/" + orderId + "/callback");
        param.put("operator", "web");
        String createUrl;
        if (userAgent.toLowerCase().contains("micromessenger") && !qrcode) {
            //wechat
            createUrl = "https://pay.globepay.co/api/v1.0/wechat_jsapi_gateway/partners/" + QueryParams.getPartnerCode()+ "/orders/" + orderId + "?" + QueryParams.queryParams4();
        } else {
            createUrl = "https://pay.globepay.co/api/v1.0/gateway/partners/" + QueryParams.getPartnerCode() + "/orders/" + orderId + "?" + QueryParams.queryParams4();
        }
        HttpRequestResult result = new HttpRequestGenerator(createUrl, RequestMethod.PUT).setJSONEntity(param).execute();
        if (result.isSuccess()) {
            JSONObject res = result.getResponseContentJSONObj();
            if ("SUCCESS".equals(res.getString("return_code"))) {
                String payUrl = res.getString("pay_url") + "?" + QueryParams.queryParams4() + "&redirect=" + host + "api/payment/orders/" + orderId + "/success";
                if (direct) {
                    payUrl += "&directpay=true";
                }
                return "redirect:" + payUrl;
            }
        }
        throw new ServerErrorException("Failed to Request RoyalPay! Wait for a minute");
    }

    @RequestMapping(value = "/orders/{orderId}/success", method = RequestMethod.GET)
    public String payCallbackPage(@PathVariable String orderId, Model model) throws URISyntaxException, IOException {
        model.addAttribute("order_id", orderId);
        String url = "https://pay.globepay.co/api/v1.0/gateway/partners/" + QueryParams.getPartnerCode() + "/orders/" + orderId + "?" + QueryParams.queryParams4();
        HttpRequestResult result = new HttpRequestGenerator(url, RequestMethod.GET).execute();
        if (result.isSuccess()) {
            JSONObject res = result.getResponseContentJSONObj();
            model.addAttribute("order_info", res);
        }
        return "success";
    }

}
