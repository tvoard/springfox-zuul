package com.example.gateway.filters;
//package com.example.gateway.filters;
//
//import java.io.InputStream;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.http.client.HttpClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.function.ServerRequest.Headers;
//
//import com.netflix.zuul.ZuulFilter;
//
//public class RouteFilter extends ZuulFilter {
//
//    @Autowired
//    private ProxyRequestHelper helper;
//
//    @Override
//    public String filterType() {
//	// return ROUTE_TYPE;
//	return "";
//    }
//
//    @Override
//    public int filterOrder() {
//	return -1;
//	// return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//	return true;
//	// return RequestContext.getCurrentContext().getRouteHost() != null &&
//	// RequestContext.getCurrentContext().sendZuulResponse();
//    }
//
//    @Override
//    public Object run() {
//	HttpClient httpClient = new HttpClient.Builder()
//		// customize
//		.build();
//
//	RequestContext context = RequestContext.getCurrentContext();
//	HttpServletRequest request = context.getRequest();
//
//	String method = request.getMethod();
//
//	String uri = this.helper.buildZuulRequestURI(request);
//
//	Headers.Builder headers = new Headers.Builder();
//	Enumeration<String> headerNames = request.getHeaderNames();
//	while (headerNames.hasMoreElements()) {
//	    String name = headerNames.nextElement();
//	    Enumeration<String> values = request.getHeaders(name);
//
//	    while (values.hasMoreElements()) {
//		String value = values.nextElement();
//		headers.add(name, value);
//	    }
//	}
//
//	InputStream inputStream = request.getInputStream();
//
//	RequestBody requestBody = null;
//	if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//	    MediaType mediaType = null;
//	    if (headers.get("Content-Type") != null) {
//		mediaType = MediaType.parse(headers.get("Content-Type"));
//	    }
//	    requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
//	}
//
//	Request.Builder builder = new Request.Builder().headers(headers.build()).url(uri).method(method, requestBody);
//
//	Response response = httpClient.newCall(builder.build()).execute();
//
//	LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//
//	for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
//	    responseHeaders.put(entry.getKey(), entry.getValue());
//	}
//
//	this.helper.setResponse(response.code(), response.body().byteStream(), responseHeaders);
//	context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
//	return null;
//    }
//}
