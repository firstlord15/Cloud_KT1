package com.ithub.ru.eurekaclient.circuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "orderService", fallbackMethod = "fallback")
    public String getOrderDetails(String orderId) {
        String url = "http://order-service/api/order/" + orderId;
        return restTemplate.getForObject(url, String.class);
    }

    public String fallback(String orderId, Throwable t) {
        log.error("Failed to fetch order details for id: {}. Error: {}", orderId, t.getMessage());
        return "Fallback response: Unable to fetch order details for " + orderId;
    }
}