package org.franc1s.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello(){
        myChannel.output().send(MessageBuilder.withPayload("hello SpringCloud Stream!").build());
    }
}
