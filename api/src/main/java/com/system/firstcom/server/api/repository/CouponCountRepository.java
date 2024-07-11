package com.system.firstcom.server.api.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CouponCountRepository {
    private final RedisTemplate<String, String> redisRestTemplate;
    private final String PREFIX = "coupon:count";

    public Long increment(Long  couponId){
        String key = String.format("%s:%d", PREFIX, couponId);
        return redisRestTemplate
                .opsForValue()
                .increment(key);
    }

}
