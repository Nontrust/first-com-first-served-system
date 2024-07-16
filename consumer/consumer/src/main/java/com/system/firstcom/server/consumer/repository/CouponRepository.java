package com.system.firstcom.server.consumer.repository;

import com.system.firstcom.server.consumer.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {}
