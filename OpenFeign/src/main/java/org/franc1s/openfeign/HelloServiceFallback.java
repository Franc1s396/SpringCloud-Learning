package org.franc1s.openfeign;

import org.franc1s.commons.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("/javaboy")//防止请求地址重复
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return "error";
    }

    @Override
    public String hello3(String name) throws UnsupportedEncodingException {
        return "error";
    }

    @Override
    public User addUser1(User user) {
        return null;
    }

    @Override
    public void deleteUser1(Integer id) {
        System.out.println("error");
    }
}
