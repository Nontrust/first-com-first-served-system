package com.system.firstcom.server.api.service;

import com.system.firstcom.server.api.entity.Coupon;
import com.system.firstcom.server.api.repository.CouponReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyServiceImpl implements ApplyService {
    private final CouponReader couponReader;
    private final Long TARGETED_COUPON_ID = 1L;

    @Override
    public void apply(Long userId) {
        Long apply = couponReader.apply(userId);
        if(apply != 1){
            return;
        }

        long count = couponReader.count(TARGETED_COUPON_ID);
        if(count > 100){
            return;
        }

        Coupon coupon = Coupon.of(userId);
        couponReader.create(userId);
    }
}
