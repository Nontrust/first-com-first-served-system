package com.system.firstcom.server.api.domain.service;

import com.system.firstcom.server.api.domain.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
@Execution(ExecutionMode.SAME_THREAD)
@SpringBootTest
class ApplyServiceTest {
    @Autowired
    private ApplyServiceImpl applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final long TEST_USER_ID = 1L;

    @BeforeEach
    public void 테스트_전(){
    }

    @AfterEach
    public void 테스트_후(){
        /* 테스트간 간섭이 없도록 테스트 데이터를 지웁니다. */
        couponRepository.deleteAll();
        redisTemplate.delete("coupon:count:1");
    }
    
    @Test
    @Timeout(3)
    public void 한번_응모(){
        applyService.apply(TEST_USER_ID);
        long count = couponRepository.count();

        assertEquals(count, 1);

    }

    @Test
    @Timeout(10)
    // TODO : race Condition!!
    public void 백번_응모() throws InterruptedException {
        Consumer<Long> consumer = (Long l) -> applyService.apply(l);
        공통_서비스_테스트_멀티_스레드에서(consumer, 100);

        long count = couponRepository.count();

        assertEquals(count, 100);

    }

    protected void 공통_서비스_테스트_멀티_스레드에서(Consumer<Long> consumer, int threadCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        LongStream.range(0, threadCount)
                .forEach( i -> {
                    executorService.execute(() -> {
                        try {
                            log.warn("현재 스레드 {}", Thread.currentThread().getId());
                            log.warn("현재 count {}", i);
                            consumer.accept(i);
                        } finally {
                            latch.countDown();  // latch count를 감소시킵니다.
                        }
                    });
                });
        latch.await();
        executorService.shutdown();
    }

    private void 공통_서비스_테스트 (List<Runnable> runnables) {
        runnables.forEach(Runnable::run);
    }

    private void 공통_서비스_테스트 (Runnable runnable) {
       runnable.run();
    }

}