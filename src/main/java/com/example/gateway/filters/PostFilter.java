package com.example.gateway.filters;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
	return null;
	// return POST_TYPE;
    }

    @Override
    public int filterOrder() {
	return -1;
	// return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
	return true;
    }

    @Override
    public Object run() {
	RequestContext context = RequestContext.getCurrentContext();
	HttpServletResponse servletResponse = context.getResponse();
	servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
	return null;
    }

}
