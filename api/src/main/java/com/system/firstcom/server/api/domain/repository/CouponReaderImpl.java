package com.system.firstcom.server.api.domain.repository;

import com.system.firstcom.server.api.domain.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CouponReaderImpl implements CouponReader {
    private final CouponRepository repository;

    @Override
    public Optional<Coupon> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Coupon save(Coupon coupon) {
        return repository.save(coupon);
    }
}
