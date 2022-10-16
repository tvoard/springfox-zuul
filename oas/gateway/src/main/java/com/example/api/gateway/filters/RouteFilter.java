package com.example.api.gateway.filters;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RouteFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRouteHost() != null
                && RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        return context;
    }

//    public static final String CONTENT_ENCODING = "Content-Encoding";
//    private static final Logger LOG = LoggerFactory.getLogger(SimpleHostRoutingFilter.class);
//    private static final Runnable CLIENTLOADER = new Runnable() {
//        @Override
//        public void run() {
//            loadClient();
//        }
//    };
//
//    private static final DynamicIntProperty SOCKET_TIMEOUT = DynamicPropertyFactory.getInstance()
//            .getIntProperty(ZuulConstants.ZUUL_HOST_SOCKET_TIMEOUT_MILLIS, 10000);
//
//    private static final DynamicIntProperty CONNECTION_TIMEOUT = DynamicPropertyFactory.getInstance()
//            .getIntProperty(ZuulConstants.ZUUL_HOST_CONNECT_TIMEOUT_MILLIS, 2000);
//
//    private static final AtomicReference<CloseableHttpClient> CLIENT = new AtomicReference<CloseableHttpClient>(
//            newClient());
//
//    private static final Timer CONNECTION_MANAGER_TIMER = new Timer(true);
//
//    @Autowired
//    private ProxyRequestHelper helper;
//
//    static {
//        SOCKET_TIMEOUT.addCallback(CLIENTLOADER);
//        CONNECTION_TIMEOUT.addCallback(CLIENTLOADER);
//        CONNECTION_MANAGER_TIMER.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    final HttpClient hc = CLIENT.get();
//                    if (hc == null)
//                        return;
//                    hc.getConnectionManager().closeExpiredConnections();
//                } catch (Throwable t) {
//                    LOG.error("error closing expired connections", t);
//                }
//            }
//        }, 30000, 5000);
//    }
//
//    public RouteFilter() {
//        super();
//    }
//
//    private static final HttpClientConnectionManager newConnectionManager() {
//        SSLContext sslContext = SSLContexts.createSystemDefault();
//
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", new SSLConnectionSocketFactory(sslContext)).build();
//
//        HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        ((PoolingHttpClientConnectionManager) cm)
//                .setMaxTotal(Integer.parseInt(System.getProperty("zuul.max.host.connections", "200")));
//        ((PoolingHttpClientConnectionManager) cm)
//                .setDefaultMaxPerRoute(Integer.parseInt(System.getProperty("zuul.max.host.connections", "20")));
//        return cm;
//    }
//
//    private static final void loadClient() {
//        final CloseableHttpClient oldClient = CLIENT.get();
//        CLIENT.set(newClient());
//        if (oldClient != null) {
//            CONNECTION_MANAGER_TIMER.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    try {
//                        oldClient.close();
//                    } catch (Throwable t) {
//                        LOG.error("error shutting down old connection manager", t);
//                    }
//                }
//            }, 30000);
//        }
//
//    }
//
//    private static final CloseableHttpClient newClient() {
//        HttpClientBuilder builder = HttpClientBuilder.create();
//        builder.setConnectionManager(newConnectionManager());
//        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
//        builder.setRedirectStrategy(new RedirectStrategy() {
//            @Override
//            public boolean isRedirected(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext)
//                    throws ProtocolException {
//                return false;
//            }
//
//            @Override
//            public HttpUriRequest getRedirect(HttpRequest httpRequest, HttpResponse httpResponse,
//                    HttpContext httpContext) throws ProtocolException {
//                return null;
//            }
//        });
//        return builder.build();
//    }
//
//    HttpResponse forward(CloseableHttpClient httpclient, String verb, String uri, HttpServletRequest request,
//            Header[] headers, InputStream requestEntity)
//            throws URISyntaxException, ClientProtocolException, IOException {
//        HttpHost httpHost = getHttpHost();
//        HttpRequest httpRequest;
//
//        switch (verb) {
//        case "POST":
//            httpRequest = new HttpPost(uri + getQueryString());
//            ((HttpPost) httpRequest).setEntity(new InputStreamEntity(requestEntity, request.getContentLength()));
//            break;
//        case "PUT":
//            httpRequest = new HttpPut(uri + getQueryString());
//            ((HttpPut) httpRequest).setEntity(new InputStreamEntity(requestEntity, request.getContentLength()));
//            break;
//        default:
//            httpRequest = new BasicHttpRequest(verb, uri + getQueryString());
//        }
//
//        try {
//            httpRequest.setHeaders(headers);
//            return forwardRequest(httpclient, httpHost, httpRequest);
//        } finally {
//            // httpclient.close();
//        }
//    }
//
//    HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost, HttpRequest httpRequest)
//            throws ClientProtocolException, IOException {
//        return httpclient.execute(httpHost, httpRequest);
//    }
//
//    String getQueryString() throws UnsupportedEncodingException, URISyntaxException {
//        String encoding = "UTF-8";
//        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
//        String currentQueryString = request.getQueryString();
//        if (currentQueryString == null || currentQueryString.equals("")) {
//            return "";
//        }
//
//        String rebuiltQueryString = "";
//        for (String keyPair : currentQueryString.split("&")) {
//            if (rebuiltQueryString.length() > 0) {
//                rebuiltQueryString = rebuiltQueryString + "&";
//            }
//
//            if (keyPair.contains("=")) {
//                String[] nameAndValue = keyPair.split("=", 2);
//                String name = nameAndValue[0];
//                String value = nameAndValue[1];
//                value = URLDecoder.decode(value, encoding);
//                value = new URI(null, null, null, value, null).toString().substring(1);
//                value = value.replaceAll("&", "%26");
//                rebuiltQueryString = rebuiltQueryString + name + "=" + value;
//            } else {
//                String value = URLDecoder.decode(keyPair, encoding);
//                value = new URI(null, null, null, value, null).toString().substring(1);
//                rebuiltQueryString = rebuiltQueryString + value;
//            }
//        }
//        return "?" + rebuiltQueryString;
//    }
//
//    HttpHost getHttpHost() {
//        HttpHost httpHost;
//        URL host = RequestContext.getCurrentContext().getRouteHost();
//        httpHost = new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
//        return httpHost;
//    }
//
//    InputStream getRequestBody(HttpServletRequest request) {
//        try {
//            return request.getInputStream();
//        } catch (IOException e) {
//            LOG.warn(e.getMessage());
//            return null;
//        }
//    }
//
//    boolean isValidHeader(String name) {
//        if (name.toLowerCase().contains("content-length")) {
//            return false;
//        }
//        if (name.toLowerCase().equals("host")) {
//            return false;
//        }
//        if (!RequestContext.getCurrentContext().getResponseGZipped()) {
//            if (name.toLowerCase().contains("accept-encoding")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    Header[] buildZuulRequestHeaders(HttpServletRequest request) {
//
//        ArrayList<BasicHeader> headers = new ArrayList<>();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = ((String) headerNames.nextElement()).toLowerCase();
//            String value = request.getHeader(name);
//            if (isValidHeader(name)) {
//                headers.add(new BasicHeader(name, value));
//            }
//        }
//
//        Map<String, String> zuulRequestHeaders = RequestContext.getCurrentContext().getZuulRequestHeaders();
//        for (String it : zuulRequestHeaders.keySet()) {
//            String name = it.toLowerCase();
//            BasicHeader h = null;
//            for (BasicHeader he : headers) {
//                if (name.equals(he.getName())) {
//                    h = he;
//                    break;
//                }
//            }
//            if (h != null) {
//                headers.remove(h);
//            }
//            headers.add(new BasicHeader(it, zuulRequestHeaders.get(it)));
//        }
//
//        if (RequestContext.getCurrentContext().getResponseGZipped()) {
//            headers.add(new BasicHeader("accept-encoding", "deflate, gzip"));
//        }
//
//        return (Header[]) headers.toArray();
//    }
//
//    String getVerb(HttpServletRequest request) {
//        return getVerb(request.getMethod().toUpperCase());
//    }
//
//    String getVerb(String sMethod) {
//        if (sMethod == null) {
//            return "GET";
//        }
//
//        sMethod = sMethod.toLowerCase();
//        if (sMethod.equalsIgnoreCase("post")) {
//            return "POST";
//        }
//        if (sMethod.equalsIgnoreCase("put")) {
//            return "PUT";
//        }
//        if (sMethod.equalsIgnoreCase("delete")) {
//            return "DELETE";
//        }
//        if (sMethod.equalsIgnoreCase("options")) {
//            return "OPTIONS";
//        }
//        if (sMethod.equalsIgnoreCase("head")) {
//            return "HEAD";
//        }
//        return "GET";
//    }
//
//    void setResponse(HttpResponse response) throws UnsupportedOperationException, IOException {
//        RequestContext context = RequestContext.getCurrentContext();
//
//        context.set("hostZuulResponse", response);
//        context.setResponseStatusCode(response.getStatusLine().getStatusCode());
//        context.setResponseDataStream(ObjectUtils.isEmpty(response)
//                ? (ObjectUtils.isEmpty(response.getEntity()) ? response.getEntity().getContent() : null)
//                : null);
//
//        boolean isOriginResponseGzipped = false;
//        for (Header h : response.getHeaders(CONTENT_ENCODING)) {
//            if (HTTPRequestUtils.getInstance().isGzipped(h.getValue())) {
//                isOriginResponseGzipped = true;
//                break;
//            }
//        }
//        context.setResponseGZipped(isOriginResponseGzipped);
//
//        if (response.getAllHeaders() != null) {
//            for (Header header : response.getAllHeaders()) {
//                RequestContext ctx = context;
//                ctx.addOriginResponseHeader(header.getName(), header.getValue());
//
//                if (header.getName().equalsIgnoreCase("content-length"))
//                    ctx.setOriginContentLength(header.getValue());
//
//                if (isValidHeader(header)) {
//                    ctx.addZuulResponseHeader(header.getName(), header.getValue());
//                }
//            }
//        }
//    }
//
//    boolean isValidHeader(Header header) {
//        switch (header.getName().toLowerCase()) {
//        case "connection":
//        case "content-length":
//        case "content-encoding":
//        case "server":
//        case "transfer-encoding":
//            return false;
//        default:
//            return true;
//        }
//    }

}
