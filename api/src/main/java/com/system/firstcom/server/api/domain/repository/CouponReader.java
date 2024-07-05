package com.system.firstcom.server.api.domain.repository;

import com.system.firstcom.server.api.domain.entity.Coupon;

import java.util.Optional;
public interface CouponReader {
    Optional<Coupon> findById(Long id);
    long count(Long id);
    Coupon save(Coupon coupon);
}
