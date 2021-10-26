package org.franc1s.api;

import org.franc1s.commons.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

public interface IUserService {
    @GetMapping("/hello")
    String hello();  //这里的方法名无所谓

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @GetMapping("/hello3")
    String hello3(@RequestHeader("name") String name) throws UnsupportedEncodingException;

    @PostMapping("/add1")
    User addUser1(@RequestBody User user);

    @DeleteMapping("/delete1/{id}")
    void deleteUser1(@PathVariable("id") Integer id);
}
