package com.system.firstcom.server.api.repository;

import com.system.firstcom.server.api.entity.Coupon;

import java.util.Optional;
public interface CouponReader {
    Optional<Coupon> findById(Long id);
    long count(Long id);
    Coupon save(Coupon coupon);
}
