package org.franc1s.provider1.controller;

import org.franc1s.commons.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "hello" + port;
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "hello" + new Date() + ":" + port;
    }

    @PostMapping("/add")
    public User addUser(User user) {
        return user;
    }

    @PostMapping("/add1")
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

    @DeleteMapping("/delete1/{id}")
    public void deleteUser1(@PathVariable Integer id) {
        System.out.println(id);
    }
}
