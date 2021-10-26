package org.franc1s.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 在这个方法中，我们将调用一个远程调用，去调用provider中提供的hello接口
     * <p>
     * 但是这个调用可能会失败。
     * <p>
     * 我们在这个方法上添加这个注解,配置fallbackMethod属性,这个属性表示该方法调用失败时的临时替代方法
     * <p>
     * 需要忽略某个异常只抛出不显示:ignoreExceptions=xxx.class
     */
    @HystrixCommand(fallbackMethod = "error")
    public String hello() {
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    /**
     * 方法名字要和fallbackmethod名字一致
     * 返回值也要和对应的方法一致
     * <p>
     * Throwable 异常处理
     */
    public String error(Throwable t) {
        return "error:" + t.getMessage();
    }

    @HystrixCommand(fallbackMethod = "error")
    public Future<String> hello1() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://provider/hello", String.class);
            }
        };
    }

    @HystrixCommand(fallbackMethod = "error2")
    @CacheResult//这个注解表示该方法的请求结果会被缓存,默认情况下，缓存的key就是方法的参数,缓存的value就是方法的返回值.
    public String hello2() {
        return restTemplate.getForObject("http://provider/hello2", String.class);
    }

    public String error2(){
        return "error2";
    }
}
