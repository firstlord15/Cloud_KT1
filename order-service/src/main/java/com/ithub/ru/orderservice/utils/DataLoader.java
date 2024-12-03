package com.ithub.ru.orderservice.utils;

import com.ithub.ru.orderservice.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(OrderService orderService) {
        return args -> {
            orderService.CreateTestOrders();
        };
    }
}
