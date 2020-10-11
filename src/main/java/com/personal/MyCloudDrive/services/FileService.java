package com.personal.MyCloudDrive.services;

import com.personal.MyCloudDrive.mapper.FileMapper;
import com.personal.MyCloudDrive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public void addFile(MultipartFile multipartFile, int userId) throws IOException {
        File file = new File(null, multipartFile.getOriginalFilename(), multipartFile.getContentType(),
                multipartFile.getSize(), userId, multipartFile.getBytes());
        fileMapper.addFile(file);
    }

    public boolean isFileUnique(String filename, int userId) {
        return fileMapper.getFile(filename, userId) == null;
    }

    public List<File> getFileByUser(int userId) {
        return fileMapper.findAllFileByUserId(userId);
    }

    public File getFileById(int fileId) {
        return fileMapper.getFileById(fileId);
    }

    public void deleteFile(int fileId) {
        fileMapper.deleteFileByID(fileId);
    }
}
