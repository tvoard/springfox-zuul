package com.example.api.gateway.controllers;

//@Component
//@Primary
//@EnableAutoConfiguration
public class SwaggerController {
    /*
     * public class SwaggerController implements SwaggerResourcesProvider {
     * 
     * private final RouteLocator routeLocator;
     * 
     * public SwaggerController(RouteLocator routeLocator) { this.routeLocator =
     * routeLocator; }
     * 
     * @Override public List<SwaggerResource> get() { List<SwaggerResource>
     * resources = new LinkedList<>(); SwaggerResource sr = new SwaggerResource();
     * sr.setName("/"); sr.setLocation("/");
     * sr.setSwaggerVersion(SWAGGER_2.getVersion()); resources.add(sr);
     * 
     * resources.addAll(routeLocator.getRoutes().stream().distinct().map(route -> {
     * SwaggerResource swaggerResource = new SwaggerResource();
     * swaggerResource.setName(route.getLocation());
     * swaggerResource.setLocation(route.getFullPath().replace("**", "api-docs"));
     * swaggerResource.setSwaggerVersion(SWAGGER_2.getVersion()); return
     * swaggerResource; }).collect(Collectors.toList()));
     * 
     * return resources; }
     */
}