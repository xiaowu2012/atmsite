package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.service.UserAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    private static final String FIX_LOCATION = "C:\\droiyanplayers";
    private static final String FIX_LOCATION_SUSPICIOUS = "C:\\suspiciousPlayers";

    @PostMapping("/upload")
    public void handleFileUpload(@RequestPart("file") MultipartFile file,
                                 @RequestParam("playerID") String playerID,
                                 @RequestParam("folderName") String folderName,
                                 @RequestParam("isSuspicious") Boolean isSuspicious, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
