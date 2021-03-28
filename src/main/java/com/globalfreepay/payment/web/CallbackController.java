package com.globalfreepay.payment.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by davep on 2016-07-24.
 */
@RestController
@RequestMapping("/api/orders/{orderId}/callback")
public class CallbackController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(method = RequestMethod.POST)
    public JSONObject handleCallback(@RequestBody JSONObject entity, @PathVariable String orderId) {
        logger.info("callback:" + JSON.toJSONString(entity, SerializerFeature.PrettyFormat));
        messagingTemplate.convertAndSend("/app/orders/" + orderId + "/paid", "{}");
        JSONObject res = new JSONObject();
        res.put("return_code", "SUCCESS");
        return res;
    }
}
