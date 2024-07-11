package com.system.firstcom.server.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor(access = PROTECTED)
@Entity
public class Coupon {
    @Id @GeneratedValue
    private Long id;
    private Long userId;

    public static Coupon of(Long userId){
        return Coupon.builder().userId(userId).build();
    }

}

