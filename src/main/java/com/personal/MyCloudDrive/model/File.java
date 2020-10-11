package com.personal.MyCloudDrive.model;

public class File {
    private Integer fileId;
    private String filename;
    private String contentType;
    private Long filesize;
    private Integer userId;
    private byte[] fileData;

    public File(Integer fileId, String filename, String contentType, Long filesize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.filesize = filesize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
