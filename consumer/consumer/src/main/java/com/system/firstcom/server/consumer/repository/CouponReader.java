package com.system.firstcom.server.consumer.repository;

import com.system.firstcom.server.consumer.entity.Coupon;
import com.system.firstcom.server.consumer.entity.FailedEvent;

public interface CouponReader {
    Coupon findById(Long id);
    Coupon save(Coupon coupon);

    FailedEvent save(FailedEvent failedEvent);
}
