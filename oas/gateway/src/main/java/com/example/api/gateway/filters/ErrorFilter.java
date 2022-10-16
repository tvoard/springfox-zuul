package com.example.api.gateway.filters;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

import java.net.ConnectException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    private static final String RESPONSE_BODY = "{\n" + "    \"timestamp\": " + "\"" + Instant.now().toString() + "\""
            + ",\n" + "    \"status\": 503,\n" + "    \"error\": \"Service Unavailable\"\n" + "}";

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = context.getThrowable();

        if (throwable instanceof ZuulException) {
            final ZuulException zuulException = (ZuulException) throwable;
            log.error("Zuul exception: " + zuulException.getMessage());
            if (throwable.getCause().getCause().getCause() instanceof ConnectException) {
                context.remove("throwable");
                context.setResponseBody(RESPONSE_BODY);
                context.getResponse().setContentType("application/json");
                context.setResponseStatusCode(503);
            }
        }
        return null;
    }

}
