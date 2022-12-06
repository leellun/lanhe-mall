package com.newland.lanhe.user.agent;


import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient("uua-service")
public interface SysUserApiAgent {

    @PostMapping(value = "/sysUser/login")
    public LoginUser login(@RequestBody LoginDTO loginDTO);

}

