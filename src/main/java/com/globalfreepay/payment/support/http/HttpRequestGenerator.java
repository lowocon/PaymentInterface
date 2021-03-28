package com.globalfreepay.payment.support.http;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * tools for http request Created by yixian on 2015-11-30.
 */
public final class HttpRequestGenerator {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String url;
	private RequestMethod method;
	private List<NameValuePair> queryStringParams = new ArrayList<NameValuePair>();
	private HttpEntity currentEntity;
	private int timeout = 5000;
	private PikachuFileEntity fileEntity;
	private Map<String, String> headers;
	private CookieStore cookieStore;

	public HttpRequestGenerator(String url, RequestMethod method) {
		Assert.isTrue(StringUtils.isNotBlank(url), "请求URL不可为空");
		RequestMethod[] acceptableMethods = { GET, POST, PUT, DELETE };
		if (!ArrayUtils.contains(acceptableMethods, method)) {
			throw new RuntimeException("不支持的请求方式" + method.toString());
		}
		this.url = StringUtils.strip(url);
		this.method = method;
	}

	public HttpRequestGenerator setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	public HttpRequestGenerator addQueryString(String key, String value) {
		this.queryStringParams.add(new BasicNameValuePair(key, value));
		return this;
	}

	public HttpRequestGenerator setJSONEntity(Object entity) {
		if (method != POST && method != PUT) {
			throw new RuntimeException("不支持的请求方式");
		}
		currentEntity = new StringEntity(JSON.toJSONString(entity, SerializerFeature.DisableCircularReferenceDetect),
				ContentType.APPLICATION_JSON);
		return this;
	}

	public HttpRequestGenerator setStringEntity(String entity, ContentType contentType) {
		if (method != POST && method != PUT) {
			throw new RuntimeException("不支持的请求方式");
		}
		currentEntity = new StringEntity(entity, contentType);
		return this;
	}

	public HttpRequestGenerator setByteArrayEntity(byte[] entity, ContentType contentType) {
		if (method != POST && method != PUT) {
			throw new RuntimeException("不支持的请求方式");
		}
		currentEntity = new ByteArrayEntity(entity, contentType);
		return this;
	}

	public HttpRequestGenerator addHeader(String name, String value) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(name, value);
		return this;
	}

	public HttpRequestGenerator addCookie(String key, String value) {
		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
		}
		cookieStore.addCookie(new BasicClientCookie(key, value));
		return this;
	}

	public PikachuFileEntity initFileEntity() {
		if (method != POST && method != PUT) {
			throw new RuntimeException("不支持的请求方式");
		}
		this.fileEntity = new PikachuFileEntity();
		return this.fileEntity;
	}

	public HttpRequestResult execute() throws URISyntaxException {
		HttpClient client = buildHttpClient();
		return execute(client);
	}

	public HttpRequestResult execute1() throws URISyntaxException {
		HttpClient client = buildHttpClient();
		return execute(client);
	}

	public HttpRequestResult execute(HttpClient client) throws URISyntaxException {
		HttpRequestBase httpMethod = generateRequestMethod();
		try {
			HttpResponse response = client.execute(httpMethod);
			HttpRequestResult responseResult = new HttpRequestResult(response);
			return responseResult;
		} catch (IOException e) {
			return new HttpRequestResult(e);
		}
	}

	private HttpClient buildHttpClient() {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();
		ConnectionConfig connCfg = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
		return HttpClientBuilder.create().setMaxConnTotal(200).setDefaultRequestConfig(config)
				.setDefaultConnectionConfig(connCfg).setDefaultCookieStore(cookieStore).build();
	}

	private HttpRequestBase generateRequestMethod() throws URISyntaxException {
		HttpRequestBase httpMethod;
		URIBuilder uriBuilder = new URIBuilder(url);
		uriBuilder.addParameters(queryStringParams);
		URI uri = uriBuilder.build();
		logger.debug("executing request:" + uri.toString() + "[" + method.toString() + "]");
		if (method == GET) {
			httpMethod = new HttpGet(uri);
		} else if (method == DELETE) {
			httpMethod = new HttpDelete(uri);
		} else if (method == POST) {
			httpMethod = new HttpPost(uri);
		} else {
			httpMethod = new HttpPut(uri);
		}
		if (httpMethod instanceof HttpEntityEnclosingRequestBase) {
			if (fileEntity != null) {
				((HttpEntityEnclosingRequestBase) httpMethod).setEntity(fileEntity.getEntity());
			} else {
				((HttpEntityEnclosingRequestBase) httpMethod).setEntity(currentEntity);
			}
		}
		if (headers != null) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				httpMethod.addHeader(header.getKey(), header.getValue());
			}
		}
		return httpMethod;
	}

	private NameValuePair[] buildQueryStringPairs() {
		NameValuePair[] pairs = new NameValuePair[queryStringParams.size()];
		return queryStringParams.toArray(pairs);
	}

	public class PikachuFileEntity {
		private MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		public PikachuFileEntity setContentType(ContentType contentType) {
			builder.setContentType(contentType);
			return this;
		}

		public PikachuFileEntity setMode(HttpMultipartMode mode) {
			builder.setMode(mode);
			return this;
		}

		public PikachuFileEntity attachFile(String fileParamKey, String fileName, byte[] fileBytes) {
			builder.addBinaryBody(fileParamKey, fileBytes, ContentType.DEFAULT_BINARY,
					new String(fileName.getBytes(Consts.UTF_8), Consts.ISO_8859_1));
			return this;
		}

		public PikachuFileEntity attachFile(String fileParamKey, ContentType contentType, String fileName,
				byte[] fileBytes) {
			builder.addBinaryBody(fileParamKey, fileBytes, contentType,
					new String(fileName.getBytes(Consts.UTF_8), Consts.ISO_8859_1));
			return this;
		}

		public PikachuFileEntity attachFile(String fileParamKey, String fileName, InputStream fileInputStream) {
			builder.addBinaryBody(fileParamKey, fileInputStream, ContentType.DEFAULT_BINARY,
					new String(fileName.getBytes(Consts.UTF_8), Consts.ISO_8859_1));
			return this;
		}

		public PikachuFileEntity attachFile(String fileParamKey, ContentType contentType, String fileName,
				InputStream fileInputStream) {
			builder.addBinaryBody(fileParamKey, fileInputStream, contentType,
					new String(fileName.getBytes(Consts.UTF_8), Consts.ISO_8859_1));
			return this;
		}

		public PikachuFileEntity attachFile(String fileParamKey, File file) throws FileNotFoundException {
			builder.addBinaryBody(fileParamKey, file);
			return this;
		}

		public PikachuFileEntity attachExternalParam(String key, String value) {
			builder.addTextBody(key, value);
			return this;
		}

		HttpEntity getEntity() {
			return builder.build();
		}
	}
}
