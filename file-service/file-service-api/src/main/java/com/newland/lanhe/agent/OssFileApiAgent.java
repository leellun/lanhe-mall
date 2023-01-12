package com.newland.lanhe.agent;

import com.newland.lanhe.model.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * oss文件代理
 * Author: leell
 * Date: 2023/1/13 00:53:19
 */
@FeignClient("file-service")
public interface OssFileApiAgent {
    @GetMapping("/object/{objectName}")
    public RestResponse getRealUrl(@PathVariable String objectName);
}
