package com.jason.sky.controller;

import com.jason.sky.entity.FileInfo;
import com.jason.sky.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author: jason
 * @Date: 2019-03-25
 */
@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 文件上传接口
     *
     * @param request
     * @param bizType 业务类型(必传)
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public List<FileInfo> uploadFile(MultipartHttpServletRequest request, String bizType) {
        List<FileInfo> result = this.fileUploadService.uploadFile(request.getMultiFileMap().get("fileData"), bizType);
        return result;
    }

    @GetMapping("/getFiles")
    public List<FileInfo> getFiles(Long userId) {
        List<FileInfo> result = this.fileUploadService.getFiles(userId);
        return result;
    }

}
