package org.franc1s.provider.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.franc1s.api.IUserService;
import org.franc1s.commons.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@RestController
public class HelloController implements IUserService {
    @Value("${server.port}")
    Integer port;

    @Override
    @RateLimiter(name = "rlA")
    public String hello() {
        String s="开始重试";
        System.out.println(s);
        return "hello" + port;
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "hello" + new Date() + ":" + port;
    }

    @Override
    public String hello2(String name) {
        return "hello2" + name;
    }

    @Override
    public String hello3(@RequestHeader String name) throws UnsupportedEncodingException {
        return URLEncoder.encode(name, "UTF-8");
    }

    @PostMapping("/add")
    public User addUser(User user) {
        return user;
    }

    @Override
    public User addUser1(@RequestBody User user) {
        return user;
    }

    @PutMapping("/update")
    public void updateUser(User user) {
        System.out.println(user);
    }

    @PutMapping("/update1")
    public void updateUser1(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(Integer id) {
        System.out.println(id);
    }

    @Override
    public void deleteUser1(@PathVariable Integer id) {
        System.out.println(id);
    }

}
