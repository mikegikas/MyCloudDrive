package com.personal.MyCloudDrive.controller;

import com.personal.MyCloudDrive.model.File;
import com.personal.MyCloudDrive.services.FileService;
import com.personal.MyCloudDrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;


    @GetMapping("/delete-file/{fileid}")
    public String deleteFile(@PathVariable("fileid") int fileid, Model model) {
        String error = null;

        try {
            fileService.deleteFile(fileid);
        } catch (Exception e) {
            error = e.toString();
        }

        displayMessage(model, "actionDelete", "actionFailed", "Your file", error);

        return "result";
    }

    // https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service#answer-60517672
    @GetMapping("/download-file/{fileid}")
    public ResponseEntity downloadFile(@PathVariable("fileid") int fileid) {
        File file = fileService.getFileById(fileid);

        if (file != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
                    .body(file.getFileData());
        }
        return null;
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model) {
        String error = null;

        if (multipartFile.isEmpty()) {
            error = "You dont have any file selected!";
        } else if (multipartFile.getSize() > 104857600) {
            return "/errorFile";
        } else {
            if (fileService.isFileUnique(multipartFile.getOriginalFilename(), UserService.loggedinuser.getUserId())) {
                try {
                    fileService.addFile(multipartFile, UserService.loggedinuser.getUserId());
                } catch (IOException e) {
                    error = e.toString();
                }
            } else {
                error = "You already have the same filename!";
            }
        }

        displayMessage(model, "actionSuccess", "actionFailed", "Your file", error);

        return "result";
    }

    public void displayMessage(Model model, String actionSuccess, String actionFail, String sectionName, String error) {
        if (error == null) {
            model.addAttribute(actionSuccess, sectionName);
        } else {
            model.addAttribute(actionFail, error);
        }
    }
}
