package com.weng.system.common.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

//	private static final HostnameVerifier PROMISCUOUS_VERIFIER = (s, sslSession) -> true;

	
	
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate();
		// 验证主机名和服务器验证方案的匹配是可接受的
//		restTemplate.setRequestFactory(getRequestFactory(200000, 200000));// 读写、连接默认200s
//		List<ClientHttpRequestInterceptor> interceptorsTimeout = new ArrayList<ClientHttpRequestInterceptor>();
//		interceptorsTimeout.add(new HeaderRequestInterceptor());
//		restTemplate.setInterceptors(interceptorsTimeout);
//		 StringHttpMessageConverter stringConverter = new  StringHttpMessageConverter(Charset.forName("UTF-8"));
//		 restTemplate.getMessageConverters().set(1, stringConverter);
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(10000);
		httpRequestFactory.setConnectTimeout(20000);
		httpRequestFactory.setReadTimeout(60000);
		restTemplate.setRequestFactory(httpRequestFactory);
		List<ClientHttpRequestInterceptor> interceptorsTimeout = new ArrayList<ClientHttpRequestInterceptor>();
		interceptorsTimeout.add(new HeaderRequestInterceptor());
		restTemplate.setInterceptors(interceptorsTimeout);
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		restTemplate.getMessageConverters().set(1, stringConverter);
		/**
		 * 下面这段处理no suitable httpmessageconverter found for response type
		 */
		/*FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames,
				SerializerFeature.DisableCircularReferenceDetect);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastMediaTypes.add(MediaType.parseMediaType(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		restTemplate.getMessageConverters().add(fastConverter);*/
		return restTemplate;
	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}
	
	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(20000);
		factory.setReadTimeout(20000);
		return factory;
	}

//	private SimpleClientHttpRequestFactory getRequestFactory(int readTimeout, int connectTimeout) {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
//			@Override
//			protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
//				if (connection instanceof HttpsURLConnection) {
//					((HttpsURLConnection) connection).setHostnameVerifier(PROMISCUOUS_VERIFIER);
//					((HttpsURLConnection) connection).setSSLSocketFactory(trustSelfSignedSSL());
//				}
//				super.prepareConnection(connection, httpMethod);
//			}
//		};
//		requestFactory.setReadTimeout(readTimeout);
//		requestFactory.setConnectTimeout(connectTimeout);
//		return requestFactory;
//	}

	private static class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {
			HttpRequest wrapper = new HttpRequestWrapper(request);
			wrapper.getHeaders().set("Accept-charset", "utf-8");
			return execution.execute(wrapper, body);
		}
	}

	public SSLSocketFactory trustSelfSignedSSL() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			return ctx.getSocketFactory();
		} catch (Exception ex) {
			throw new RuntimeException("Exception occurred ", ex);
		}
	}
}
