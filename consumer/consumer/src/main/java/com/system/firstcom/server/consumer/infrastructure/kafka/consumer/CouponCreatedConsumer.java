package com.system.firstcom.server.consumer.infrastructure.kafka.consumer;

import com.system.firstcom.server.consumer.entity.Coupon;
import com.system.firstcom.server.consumer.entity.FailedEvent;
import com.system.firstcom.server.consumer.repository.CouponReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CouponCreatedConsumer {
    private final CouponReader couponReader;


    @KafkaListener(topics = "coupon.create", groupId = "group_1")
    public void listener(Long userId){
        try{
            Coupon coupon = Coupon.of(userId);
            couponReader.save(coupon);
        } catch (Exception e){
            log.error("failed to create :: {}", userId);
            FailedEvent failedEvent = FailedEvent.of(userId);
            couponReader.save(failedEvent);
        }

    }
}
