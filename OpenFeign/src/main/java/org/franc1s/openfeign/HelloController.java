package org.franc1s.openfeign;

import org.franc1s.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }
    @GetMapping("/hello2")
    String hello2(String name){
        return helloService.hello2(name);
    }

    @GetMapping("/hello3")
    String hello3(String name) throws UnsupportedEncodingException {
        return helloService.hello3(URLEncoder.encode(name,"UTF-8"));
    }

    @PostMapping("/add1")
    User addUser(){
        User user = new User();
        user.setId(99);
        user.setUsername("123");
        user.setPassword("1234");
        return helloService.addUser1(user);
    }

    @DeleteMapping("/delete1")
    void deleteUser(){
        helloService.deleteUser1(99);
    }
}
