package org.franc1s.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private static final Logger logger= LoggerFactory.getLogger(HelloService.class);

    @Async
    public String backgroundFun(){
        logger.info("back");
        return "hello4";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void sche1(){
        logger.info("start:");
        backgroundFun();
        logger.info("end:");
    }
}
