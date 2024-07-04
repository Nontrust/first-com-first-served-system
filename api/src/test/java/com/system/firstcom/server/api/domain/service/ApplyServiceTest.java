package com.system.firstcom.server.api.domain.service;

import com.system.firstcom.server.api.domain.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    private final long TEST_USER_ID = 1L;

    @BeforeEach
    public void 테스트_전(){
        // TODO
    }

    @AfterEach
    public void 테스트_후(){
        /* 테스트간 간섭이 없도록 테스트 데이터를 지웁니다. */
        couponRepository.deleteAll();
    }
    
    @Test
    @Timeout(3)
    public void 한번_응모(){
        applyService.apply(TEST_USER_ID);
        long count = couponRepository.count();

        assertEquals(count, 1);

    }

    @Test
    @Timeout(8)
    @Transactional
    public void 백번_응모() throws InterruptedException {

        ConcurrentLinkedQueue<Runnable> runnables = LongStream
                .rangeClosed(1L,100L)
                .mapToObj(userId ->
                        (Runnable) ()
                                -> applyService.apply(userId)
                )
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));



        공통_서비스_테스트_멀티_스레드에서(runnables);


        long count = couponRepository.count();

        assertEquals(count, 100);

    }



    @Transactional
    protected void 공통_서비스_테스트_멀티_스레드에서(ConcurrentLinkedQueue<Runnable> runnables) throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        IntStream.range(0, threadCount)
                .forEach( i -> {
                    executorService.execute(() -> {
                        if(!runnables.isEmpty()){
                            log.warn("현재 스레드 {}", Thread.currentThread().getId());
                            runnables.poll().run();
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