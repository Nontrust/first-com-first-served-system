package com.system.firstcom.server.consumer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FailedEvent {
    @Id
    private Long id;
    private Long userId;
    private String message;

    public static FailedEvent of(Long userId){
        String message = "쿠폰 발급에 실패했습니다.";
        return FailedEvent.of(userId, message);
    }

    public static FailedEvent of(Long userId, String message){
        return FailedEvent.builder()
                .userId(userId)
                .message(message)
                .build();
    }
}
