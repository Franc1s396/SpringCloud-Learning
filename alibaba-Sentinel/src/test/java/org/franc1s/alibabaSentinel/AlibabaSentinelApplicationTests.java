package org.franc1s.alibabaSentinel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class AlibabaSentinelApplicationTests {

	@Autowired
	RestTemplate restTemplate;
	@Test
	void contextLoads() {
		for (int i = 0; i < 15; i++) {
			String s = restTemplate.getForObject("http://localhost:8081/hello", String.class);
			System.out.println(s+":"+new Date());
		}
	}

}
