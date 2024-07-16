package com.system.firstcom.server.consumer.repository;

import com.system.firstcom.server.consumer.entity.Coupon;
import com.system.firstcom.server.consumer.entity.FailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CouponReaderImpl implements CouponReader {
    private final CouponRepository couponRepository;
    private final FailedEventRepository failedEventRepository;


    @Override
    public Coupon findById(Long id){
        return couponRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public FailedEvent save(FailedEvent failedEvent) {
        return failedEventRepository.save(failedEvent);
    }

}
