package com.system.firstcom.server.api.repository;

import com.system.firstcom.server.api.entity.Coupon;
import com.system.firstcom.server.api.infrastructure.kafka.producer.CouponCreateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CouponReaderImpl implements CouponReader {
    private final CouponRepository repository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    @Override
    public Optional<Coupon> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public long count(Long id) {
        return couponCountRepository.increment(id);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return repository.save(coupon);
    }

    @Override
    public void create(Long id) {
        couponCreateProducer.create(id);
    }
}
