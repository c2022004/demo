package com.example.projectsale.order.handle;

import com.example.projectsale.order.event.OrderDischargeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderServiceHandle {

    @Async("asyncTaskExecutor")
    @EventListener
    public void handle(OrderDischargeEvent event) {

        log.info("Handle event order: {}", event.getOrderId());
    }
}
