package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.dto.LoginDTO;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.model.dto.*;
import com.newland.lanhe.user.service.SysUserService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.IntOptions;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "loginDTO", value = "登录信息", required = true,
            dataType = "LoginDTO", paramType = "body")})
    @PostMapping(value = "/login")
    public RestResponse<LoginUser> login(@RequestBody @Validated LoginDTO loginDTO) {
        return RestResponse.ok(sysUserService.login(loginDTO));
    }

    @ApiOperation("查询用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "userQueryDTO", value = "用户列表", required = true,
            dataType = "UserQueryDTO", paramType = "body")})
    @PostMapping("/list")
//    //@PreAuthorize("hasAuthority('user:select')")
    public RestResponse list(@RequestBody UserQueryDTO userQueryDTO) {
        return RestResponse.ok(sysUserService.getUsers(userQueryDTO));
    }

    @ApiOperation("查询用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "userQueryDTO", value = "用户列表", required = true,
            dataType = "UserQueryDTO", paramType = "body")})
    @GetMapping("/{id}")
//    //@PreAuthorize("hasAuthority('user:select')")
    public RestResponse list(@PathVariable Long id) {
        return RestResponse.ok(sysUserService.getUser(id));
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "sysUser", value = "用户", required = true,
            dataType = "SysUser", paramType = "body")})
    @PostMapping
    //@PreAuthorize("hasAuthority('user:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysUserDto sysUserDto) {
        sysUserService.addUser(sysUserDto);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "sysUser", value = "用户", required = true,
            dataType = "SysUser", paramType = "body")})
    @PutMapping
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysUserDto sysUserDto) {
        sysUserService.updateUser(sysUserDto);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("修改用户状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "enable", value = "状态", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/enable/{id}")
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse enable(@PathVariable("id") Long id, @RequestParam("enable") @Validated @IntOptions(options = {0, 1}, message = "状态不正确") Integer enable) {
        sysUserService.enableUser(id, enable);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("修改用户：个人中心")
    @ApiImplicitParams({@ApiImplicitParam(name = "userCenterDTO", value = "个人中心", required = true,
            dataType = "UserCenterDTO", paramType = "body")})
    @PutMapping(value = "center")
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse center(@RequestBody UserCenterDTO userCenterDTO) {
        sysUserService.updateCenter(userCenterDTO);
        return RestResponse.success();
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "用户id列表", required = true,
            dataType = "Set", paramType = "body")})
    @DeleteMapping
    //@PreAuthorize("hasAuthority('user:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysUserService.deleteUser(ids);
        return RestResponse.success("删除用户成功");
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "userPassVO", value = "用户密码", required = true,
            dataType = "UserPassVO", paramType = "body")})
    @PostMapping(value = "/updatePass")
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse updatePass(@RequestBody UserPassVO userPassVO) {
        sysUserService.updatePass(userPassVO);
        return RestResponse.success("密码修改成功");
    }

    @ApiOperation("重置密码")
    @PutMapping(value = "/resetPass/{id}")
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse resetPass(@PathVariable Long id) {
        sysUserService.resetPass(id);
        return RestResponse.success("密码重置成功");
    }

    @ApiOperation("上传头像")
    @PostMapping(value = "/updateAvatar")
    //@PreAuthorize("hasAuthority('user:update')")
    public RestResponse<String> updateAvatar(@RequestParam String avatar) {
        sysUserService.updateAvatar(avatar);
        return RestResponse.success("头像上传成功");
    }

    @ApiOperation("修改邮箱")
    @PostMapping(value = "/updateEmail/{code}")
    public RestResponse updateEmail(@PathVariable String code, @RequestBody SysUser user) throws Exception {
//        sysUserService.updateEmail(code,user);
        return RestResponse.success("邮箱修改成功");
    }
}

