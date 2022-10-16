package com.example.api.gateway.filters;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return !RequestContext.getCurrentContext().getZuulResponseHeaders().isEmpty()
                || RequestContext.getCurrentContext().getResponseDataStream() != null
                || RequestContext.getCurrentContext().getResponseBody() != null;
    }

    @Override
    public Object run() {
        return null;
    }

//    @Override
//    public Object run() {
//        addResponseHeaders();
//        try {
//            writeResponse();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static final Logger LOG = LoggerFactory.getLogger(SendResponseFilter.class);
//    static DynamicBooleanProperty INCLUDE_DEBUG_HEADER = DynamicPropertyFactory.getInstance()
//            .getBooleanProperty(ZuulConstants.ZUUL_INCLUDE_DEBUG_HEADER, false);
//    static DynamicIntProperty INITIAL_STREAM_BUFFER_SIZE = DynamicPropertyFactory.getInstance()
//            .getIntProperty(ZuulConstants.ZUUL_INITIAL_STREAM_BUFFER_SIZE, 1024);
//    static DynamicBooleanProperty SET_CONTENT_LENGTH = DynamicPropertyFactory.getInstance()
//            .getBooleanProperty(ZuulConstants.ZUUL_SET_CONTENT_LENGTH, false);
//
//    private void writeResponse() throws IOException {
//        RequestContext context = RequestContext.getCurrentContext();
//        if (context.getResponseBody() == null && context.getResponseDataStream() == null) {
//            return;
//        }
//
//        HttpServletResponse servletResponse = context.getResponse();
//        servletResponse.setCharacterEncoding("UTF-8");
//
//        OutputStream outStream = servletResponse.getOutputStream();
//        InputStream is = null;
//        try {
//            if (RequestContext.getCurrentContext().getResponseBody() != null) {
//                String body = RequestContext.getCurrentContext().getResponseBody();
//                writeResponse(new ByteArrayInputStream(body.getBytes(Charset.forName("UTF-8"))), outStream);
//                return;
//            }
//
//            boolean isGzipRequested = false;
//            final String requestEncoding = context.getRequest().getHeader(ZuulHeaders.ACCEPT_ENCODING);
//            if (requestEncoding != null && requestEncoding.equals("gzip")) {
//                isGzipRequested = true;
//            }
//
//            is = context.getResponseDataStream();
//            InputStream inputStream = is;
//            if (is != null) {
//                if (context.sendZuulResponse()) {
//                    if (context.getResponseGZipped() && !isGzipRequested)
//                        try {
//                            inputStream = new GZIPInputStream(is);
//                        } catch (ZipException e) {
//                            LOG.error("gzip expected but not received assuming unencoded response"
//                                    + RequestContext.getCurrentContext().getRequest().getRequestURL().toString());
//                            inputStream = is;
//                        }
//                    else if (context.getResponseGZipped() && isGzipRequested) {
//                        servletResponse.setHeader(ZuulHeaders.CONTENT_ENCODING, "gzip");
//                    }
//                    writeResponse(inputStream, outStream);
//                }
//            }
//
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//            outStream.flush();
//            outStream.close();
//        }
//    }
//
//    private void writeResponse(InputStream zin, OutputStream out) throws IOException {
//        byte[] bytes = new byte[INITIAL_STREAM_BUFFER_SIZE.get()];
//        int bytesRead = -1;
//        while ((bytesRead = zin.read(bytes)) != -1) {
//
//            try {
//                out.write(bytes, 0, bytesRead);
//                out.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (bytesRead == bytes.length) {
//                bytes = new byte[bytes.length * 2];
//            }
//        }
//    }
//
//    private void addResponseHeaders() {
//        RequestContext context = RequestContext.getCurrentContext();
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Long contentLength = ctx.getOriginContentLength();
//
//        HttpServletResponse servletResponse = context.getResponse();
//        if (SET_CONTENT_LENGTH.get()) {
//            if (contentLength != null && !ctx.getResponseGZipped())
//                servletResponse.setContentLengthLong(contentLength);
//        }
//    }

}
