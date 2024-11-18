package com.example.projectsale.order.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OrderDischargeEvent extends ApplicationEvent {

    private String orderId;

    public OrderDischargeEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }
}
