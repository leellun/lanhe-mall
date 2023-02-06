package com.newland.lanhe.member.controller;


import com.newland.lanhe.member.entity.MemberLevel;
import com.newland.lanhe.member.service.MemberLevelService;
import com.newland.lanhe.model.RestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/memberLevel")
public class MemberLevelController {
    @Autowired
    private MemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public RestResponse<List<MemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<MemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return RestResponse.ok(memberLevelList);
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public RestResponse<List<MemberLevel>> getAllMemberLevels() {
        List<MemberLevel> memberLevelList = memberLevelService.getAllMemberLevels();
        return RestResponse.ok(memberLevelList);
    }
}

