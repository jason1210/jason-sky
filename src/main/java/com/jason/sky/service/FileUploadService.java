package com.jason.sky.service;

import com.jason.sky.entity.FileConf;
import com.jason.sky.entity.FileInfo;
import com.jason.sky.mapper.FileConfMapper;
import com.jason.sky.mapper.FileInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: jason
 * @Date: 2019-03-25
 */
@Service
public class FileUploadService {

    @Autowired
    private FileConfMapper fileConfMapper;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    protected static Logger log = LoggerFactory.getLogger(FileUploadService.class);

    /**
     * 文件上传
     *
     * @param mpfList 文件信息集
     * @param bizType 业务类型(必传)
     * @return
     */
    public List<FileInfo> uploadFile(List<MultipartFile> mpfList, String bizType) {
        // 验证数据begin
        // 获取对应业务文件配置信息
        FileConf fileConf = fileConfMapper.selectByBizType(bizType);
        if (fileConf == null) {
            log.info("file conf is null");  // 打印文件配置信息
            return null;
        }
        // 验证文件信息是否符合配置信息
        if (!validateFileInfo(mpfList, fileConf)) {
            // 验证失败
            log.info("fileInfo is error");  // 打印文件配置信息
            return null;
        }
        // 信息验证end

        List<FileInfo> files = new ArrayList<FileInfo>();
        FileInfo fileInfo = null;
        String path = fileConf.getPath();  // 文件存储的目录
        // 获取相对路径，由file_conf、额外路径
        String relativePath = fileConf.getResourceRealm();

        // 验证服务器存储路径是否存在，若不存在，则新建文件夹
        File serFile = new File(path + relativePath);
        if (!serFile.exists()) {
            serFile.mkdirs();
        }

        // 循环上传文件
        for (MultipartFile mpf : mpfList) {
            String originalFileName = mpf.getOriginalFilename(); // 获取源文件名
            // 生成新文件名
            String newFileName = "F" + UUID.randomUUID().toString().replace("-", "").toUpperCase()
                + originalFileName.substring(originalFileName.lastIndexOf("."));
            // 组装数据
            fileInfo = new FileInfo();
            fileInfo.setUserId(1L);
            fileInfo.setOriginalName(originalFileName);
            fileInfo.setFileSize(String.valueOf(mpf.getSize() / 1024)); // 单位（kb）
            fileInfo.setFileType(mpf.getContentType());     // 文件类型
            fileInfo.setNewName(newFileName);                        // 文件新名字
            fileInfo.setRelativePath(relativePath + newFileName);    // 文件相对路径
            fileInfo.setFilePath(path + relativePath + newFileName); // 文件物理路径
            fileInfo.setBizType(bizType);
            fileInfo.setDelFlag(false);
            fileInfo.setCreatTime(new Date());
            // 存储文件并记录到数据库
            try {
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(fileInfo.getFilePath()));
                fileInfoMapper.insertSelective(fileInfo);
            } catch (IOException e) {
                log.error("upload file error!", e);
                return null;
            }
            files.add(fileInfo);
        }

        return files;
    }

    private boolean validateFileInfo(List<MultipartFile> mpfList, FileConf fileConf) {
        if (mpfList == null || fileConf == null) { return false; }
        for (MultipartFile mpf : mpfList) {
            // 验证文件大小是否超出配置大小
            if (!StringUtils.isEmpty(fileConf.getFileSizeLimit()) && mpf.getSize() / 1024 > Integer.parseInt(
                fileConf.getFileSizeLimit())) {
                return false;
            }
            // 验证文件类型是否符合文件配置的要求
            if (!StringUtils.isEmpty(fileConf.getFileTypeLimit()) && fileConf.getFileTypeLimit().indexOf(
                mpf.getContentType()) < 0) {
                return false;
            }
        }
        return true;
    }

    public List<FileInfo> getFiles(Long userId){
        return fileInfoMapper.selectByUserId(userId);
    }
}
