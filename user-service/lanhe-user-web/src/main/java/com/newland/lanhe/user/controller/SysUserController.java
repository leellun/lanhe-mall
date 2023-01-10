package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.model.dto.UserCenterDTO;
import com.newland.lanhe.user.model.dto.UserPassVO;
import com.newland.lanhe.user.model.dto.UserQueryDTO;
import com.newland.lanhe.user.model.dto.UserResetPassDTO;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("hasAuthority('user:select')")
    public RestResponse list(@RequestBody UserQueryDTO userQueryDTO) {
        return RestResponse.success();
    }

    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysUser sysUser) {
        return RestResponse.success();
    }

    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysUser sysUser) {
        return RestResponse.success();
    }

    @ApiOperation("修改用户：个人中心")
    @PutMapping(value = "center")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse center(@RequestBody UserCenterDTO userCenterDTO) {
        return RestResponse.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("hasAuthority('user:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        return RestResponse.success();
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse updatePass(@RequestBody UserPassVO userPassVO) {
        return RestResponse.success();
    }

    @ApiOperation("重置密码")
    @PutMapping(value = "/resetPass")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse resetPass(@RequestBody UserResetPassDTO userResetPassDTO) {
        return RestResponse.success();
    }

    @ApiOperation("上传头像")
    @PostMapping(value = "/updateAvatar")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse<String> updateAvatar(@RequestParam MultipartFile avatar) {
        return RestResponse.success();
    }

    @ApiOperation("修改邮箱")
    @PostMapping(value = "/updateEmail/{code}")
    public RestResponse updateEmail(@PathVariable String code, @RequestBody SysUser user) throws Exception {
        return RestResponse.success();
    }
}

