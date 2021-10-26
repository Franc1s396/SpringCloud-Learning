package org.franc1s.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class Resilience4jTest {
    @Test
    public void test1(){
        //获取一个CircuitBreakerRegistry实例 ofDefaults获取*默认*的断路器
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();

        //也可以自己配置断路器的属性
        CircuitBreakerConfig config= CircuitBreakerConfig.custom()
                //故障率阈值百分比,超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                //断路器保持打开的时间，在到达设置的时间之后，断路器会进入到half open状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                //当断路器处于half open 状态时，环形缓冲区的大小
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindow(2,2, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();
        CircuitBreakerRegistry registry1 = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry1.circuitBreaker("javaboy");
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
            return "hello";
        });
        Try<String> results = Try.of(supplier)
                .map(v -> v + "hello123");
        System.out.println(results.isSuccess());
        System.out.println(results.get());
    }

    @Test
    public void test2(){
        //也可以自己配置断路器的属性
        CircuitBreakerConfig config= CircuitBreakerConfig.custom()
                //故障率阈值百分比,超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                //断路器保持打开的时间，在到达设置的时间之后，断路器会进入到half open状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                //当断路器处于half open 状态时，环形缓冲区的大小
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindow(2,2, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();
        CircuitBreakerRegistry registry1 = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry1.circuitBreaker("javaboy");
    }

    @Test
    public void test3(){
        //Resilience4j 限流
        RateLimiterConfig config= RateLimiterConfig.custom()
                //阈值刷新时间
                .limitRefreshPeriod(Duration.ofMillis(1000))
                //阈值刷新频次(在设置的时间内限定的请求次数)
                .limitForPeriod(2)
                //限流后冷却时间
                .timeoutDuration(Duration.ofMillis(1000))
                .build();
        RateLimiter rateLimiter = RateLimiter.of("rateLimiter", config);
        CheckedRunnable runnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println(new Date());
        });
        Try.run(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .onFailure(t-> System.out.println(t.getMessage()));
    }

    @Test
    public void test4(){
        RetryConfig config = RetryConfig.custom()
                //重试的次数
                .maxAttempts(5)
                //重试的间隔时间
                .waitDuration(Duration.ofMillis(500))
                //重试异常
                .retryExceptions(RuntimeException.class)
                .build();
        Retry retry = Retry.of("retry", config);
        Retry.decorateRunnable(retry, new Runnable() {
            int count=0;
            //开启了重试功能之后，run方法执行时，如果抛出异常，会自动触发重试功能
            @Override
            public void run() {
                if (count++ <3){
                    throw new RuntimeException();
                }
            }
        });
    }
}
