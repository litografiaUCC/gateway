package com.litografiaartesplanchas.application.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	private int clientsPort = 8080;
	private int inventoryPort = 8081;
	private int employeePort = 8082;
	
	private String basePath = "http://localhost";
	
	
	
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    	
        return builder.routes()
                .route("clients", p -> p
                        .path("/api/v1/clients/**")
                        .filters(f -> f.addRequestHeader("Content-Type", "application/json"))
                        .uri(String.format("%s:%d", basePath, clientsPort)))
                .route("inventory", p -> p
                        .path("/api/v1/inventory/**")
                        .filters(f -> f.addRequestHeader("Content-Type", "application/json"))
                        .uri(String.format("%s:%d", basePath, inventoryPort)))
                .route("employee", p -> p
                        .path("/api/v1/employee/**")
                        .filters(f -> f.addRequestHeader("Content-Type", "application/json"))
                        .uri(String.format("%s:%d", basePath, employeePort)))
                    .build();
    }
}