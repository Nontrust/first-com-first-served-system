package com.system.firstcom.server.api.domain.service;

public interface ApplyService {
    /**
     * 쿠폰을 발급한다
     * @param userId 유저 아이디
     */
    void apply(Long userId);
}
