package com.example.projectsale.order.service;

import com.example.projectsale.order.event.OrderDischargeEvent;
import com.example.projectsale.utils.AbsServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl extends AbsServiceUtil implements OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void demo(String search) {
        log.info("========================== Demo =====================");
        applicationEventPublisher.publishEvent(new OrderDischargeEvent(this, search));
    }
}
