package com.newland.lanhe.user.agent;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@FeignClient("user-service")
public interface SysUserApiAgent {

    @PostMapping(value = "/user/login")
    RestResponse<LoginUser> login(@RequestBody LoginDTO loginDTO);

}

