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
        try {
            //kick and block user
            UserAccountService newUserAccountService = new UserAccountService();
            newUserAccountService.updateantitimestamp(playerID); //7b9e5f745e130536
            // newConnectURL.kickUser(playerID);
            // Specify the directory where you want to save the uploaded files
            String uploadDir;
            if (isSuspicious) {
                newUserAccountService.freezeUserAccount(playerID);
                uploadDir = FIX_LOCATION_SUSPICIOUS + File.separator + playerID + File.separator + folderName;
            } else {
                uploadDir = FIX_LOCATION + File.separator + playerID + File.separator + folderName;
            }

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                //System.out.println("making dir for " + uploadDir);
                dir.mkdirs();
                //System.out.println("making dir result,isMakeDirSuccess:  " + isMakeDirSuccess);
            }

            // Create a new file in the specified directory
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            //FileOutputStream fos = new FileOutputStream(serverFile);
            //StreamUtils.copy(file.getInputStream(), fos);

          /*  try (FileOutputStream fos = new FileOutputStream(serverFile)) {
                StreamUtils.copy(file.getInputStream(), fos);
            }*/
            Path filepath = Paths.get(dir.getAbsolutePath() + File.separator, file.getOriginalFilename());
            file.transferTo(filepath);
            // fos.close();
            // Optionally, you can return a success message in the response
            response.setStatus(HttpServletResponse.SC_OK);
            // response.getWriter().print("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
