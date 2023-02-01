package com.newland.lanhe.service;

import com.newland.lanhe.model.OssFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 * Author: leell
 * Date: 2023/1/12 20:33:47
 */
public interface IFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    OssFile uploadFile(MultipartFile file);

    /**
     * 获取真实地址
     * @param objectName
     * @return
     */
    String getFileUrl(String objectName);
}
