package com.globalfreepay.payment;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.commons.codec.Charsets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PaymentApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter4 jsonConverter = new FastJsonHttpMessageConverter4();
		jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charsets.UTF_8);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(jsonConverter);
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charsets.UTF_8);
		stringConverter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
		converters.add(stringConverter);
		super.configureMessageConverters(converters);
	}
}
