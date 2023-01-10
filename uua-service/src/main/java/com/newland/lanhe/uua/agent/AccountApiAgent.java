package com.newland.lanhe.uua.agent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "-account-service")
public interface AccountApiAgent {

//    @PostMapping(value = "/l/accounts/session")
//    RestResponse<AccountDTO> login(@RequestBody AccountLoginDTO accountLoginDTO);
}
