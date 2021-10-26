package org.franc1s.consumer;

import org.franc1s.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UseHelloController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getHello")
    public String getHello() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance instance = provider.get(0);
        String s = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/hello", String.class);
        return s;
    }
    @GetMapping("/getHello1")
    public String getHello1() {
        String s = restTemplate.getForObject("http://provider/hello", String.class);
        return s;
    }
    @GetMapping("/updateUser")
    public void updateUser() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "javaboy");
        map.add("password", "123");
        map.add("id", "1");
        User user = restTemplate.postForObject("http://provider/add", map, User.class);
        System.out.println(user);
        user.setId(98);
        user = restTemplate.postForObject("http://provider/add1", user, User.class);
        System.out.println(user);
    }

    @GetMapping("/update")
    public void putHello(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "javaboy");
        map.add("password", "123");
        map.add("id", "1");
        restTemplate.put("http://provider/update",map,User.class);
        User user = new User();
        user.setId(2);
        user.setUsername("javaboy2");
        user.setPassword("123");
        restTemplate.put("http://provider/update1",user);
    }

    @GetMapping("/delete")
    public void deleteHello(){
        restTemplate.delete("http://provider/delete?id={1}",99);
        restTemplate.delete("http://provider/delete1/{1}",99);

    }

}
