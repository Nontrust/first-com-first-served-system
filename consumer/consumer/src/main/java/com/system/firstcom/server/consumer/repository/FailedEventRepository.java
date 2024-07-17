package com.system.firstcom.server.consumer.repository;

import com.system.firstcom.server.consumer.entity.FailedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedEventRepository extends JpaRepository<FailedEvent, Long> {}
