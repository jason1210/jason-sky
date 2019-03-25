package com.jason.sky.entity;

import java.util.Date;

public class FileConf {
    private Integer id;

    private String bizType;

    private String fileTypeLimit;

    private String fileSizeLimit;

    private String path;

    private String description;

    private String resourceRealm;

    private Byte enabled;

    private Date creatTime;

    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getFileTypeLimit() {
        return fileTypeLimit;
    }

    public void setFileTypeLimit(String fileTypeLimit) {
        this.fileTypeLimit = fileTypeLimit == null ? null : fileTypeLimit.trim();
    }

    public String getFileSizeLimit() {
        return fileSizeLimit;
    }

    public void setFileSizeLimit(String fileSizeLimit) {
        this.fileSizeLimit = fileSizeLimit == null ? null : fileSizeLimit.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getResourceRealm() {
        return resourceRealm;
    }

    public void setResourceRealm(String resourceRealm) {
        this.resourceRealm = resourceRealm == null ? null : resourceRealm.trim();
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}