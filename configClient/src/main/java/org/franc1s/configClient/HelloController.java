package org.franc1s.configClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {
    @Value("${javaboy}")
    String javaboy;
    @GetMapping("/hello")
    public String hello(){
        return ""+javaboy;
    }
}
