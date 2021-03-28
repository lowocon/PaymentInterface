package com.globalfreepay.payment.support.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * bean contains http request result
 * Created by yixian on 2015-11-30.
 */
public class HttpRequestResult {
    private IOException e;
    private HttpResponse response;
    private byte[] responseBytes;
    private final Map<String, String> headerMap = new HashMap<String, String>();

    HttpRequestResult(IOException e) {
        this.e = e;
    }

    HttpRequestResult(HttpResponse response) {
        this.response = response;
        for (Header header : response.getAllHeaders()) {
            headerMap.put(header.getName().toLowerCase(), header.getValue());
        }
    }

    public boolean isSuccess() {
        return (e == null) && (getStatusCode() - 200 < 100);
    }

    public InputStream getResponseContentStream() throws IOException {
        return response.getEntity().getContent();
    }

    public byte[] getResponseContent() throws IOException {
        if (responseBytes == null) {
            InputStream responseContent = getResponseContentStream();
            responseBytes = IOUtils.toByteArray(responseContent);
            IOUtils.closeQuietly(responseContent);
        }
        return responseBytes;
    }

    public String getResponseContentString() throws IOException {
        return StringUtils.newStringUtf8(getResponseContent());
    }

    public JSONObject getResponseContentJSONObj() throws IOException {
        return JSON.parseObject(getResponseContentString());
    }

    public JSONArray getResponseContentJSONArr() throws IOException {
        return JSON.parseArray(getResponseContentString());
    }

    public HttpResponse getResponse() {
        return response;
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getHeader(String key) {
        return headerMap.get(key.toLowerCase());
    }
}
