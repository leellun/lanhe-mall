package com.newland.lanhe.service.impl;

import com.newland.lanhe.exception.BusinessException;
import com.newland.lanhe.model.OssFile;
import com.newland.lanhe.service.IFileService;
import com.newland.lanhe.utils.FileUtils;
import com.newland.lanhe.utils.MinioClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: leell
 * Date: 2023/1/12 20:34:08
 */
@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private MinioClientUtils minioClientUtils;

    @Override
    public OssFile uploadFile(MultipartFile file) {
        try {
            String objectFile = minioClientUtils.putObject(FileUtils.getGenerateDateName(file.getName()), file);
            OssFile ossFile = new OssFile();
            ossFile.setObjectName(objectFile);
            ossFile.setUrl(minioClientUtils.getObjectURL(objectFile));
            return ossFile;
        } catch (Exception e) {
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public String getFileUrl(String objectName) {
        try {
            return minioClientUtils.getObjectURL(objectName);
        } catch (Exception e) {
            throw new BusinessException("文件解析失败");
        }
    }

    @Override
    public void deleteFile(String objectName) {
        try {
            minioClientUtils.removeObject(objectName);
        } catch (Exception e) {
            throw new BusinessException("文件删除失败");
        }
    }
}
