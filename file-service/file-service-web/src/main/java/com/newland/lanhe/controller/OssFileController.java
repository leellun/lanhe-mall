package com.newland.lanhe.controller;

import com.newland.lanhe.model.OssFile;
import com.newland.lanhe.service.IFileService;
import com.newland.lanhe.model.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 * Author: leell
 * Date: 2023/1/12 20:33:11
 */
@Api(tags = "文件：文件上传下载")
@Log4j2
@RestController
@RequestMapping("/oss")
public class OssFileController {
    @Autowired
    private IFileService fileService;

    /**
     * 文件上传请求
     */
    @ApiOperation("文件上传")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件", required = true,
            dataType = "file")})
    @PostMapping("/upload")
    public RestResponse upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            OssFile ossFile = fileService.uploadFile(file);
            return RestResponse.success(ossFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        return RestResponse.error("上传文件失败");
    }

    @ApiOperation("文件地址查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "objectName", value = "对象名", required = true,
            dataType = "String")})
    @GetMapping("/object/{objectName}")
    public RestResponse getRealUrl(@PathVariable("objectName") String objectName){
        return RestResponse.success(fileService.getFileUrl(objectName));
    }
}
