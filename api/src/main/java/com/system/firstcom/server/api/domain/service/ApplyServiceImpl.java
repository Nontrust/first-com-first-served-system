package com.system.firstcom.server.api.domain.service;

import com.system.firstcom.server.api.domain.entity.Coupon;
import com.system.firstcom.server.api.domain.repository.CouponReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplyServiceImpl implements ApplyService {
    private final CouponReader couponReader;
    private final Long TARGETED_COUPON_ID = 1L;

    @Override
    @Transactional
    public void apply(Long userId) {
        long count = couponReader.count(TARGETED_COUPON_ID);
        if(count > 100){
            return;
        }

        Coupon coupon = Coupon.of(userId);
        couponReader.save(coupon);
    }
}
