package org.franc1s.openfeign;

import org.franc1s.api.IUserService;
import org.franc1s.commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider",fallback = HelloServiceFallback.class)
public interface HelloService extends IUserService {
}
