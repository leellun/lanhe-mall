package com.newland.lanhe.controller;

import com.newland.lanhe.model.OssFile;
import com.newland.lanhe.service.IFileService;
import com.newland.lanhe.model.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 * Author: leell
 * Date: 2023/1/12 20:33:11
 */
@Log4j2
@RestController
@RequestMapping("/oss")
public class OssFileController {
    @Autowired
    private IFileService fileService;

    /**
     * 文件上传请求
     */
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
    @GetMapping("/object/{objectName}")
    public RestResponse getRealUrl(@PathVariable("objectName") String objectName){
        return RestResponse.success(fileService.getFileUrl(objectName));
    }
}
