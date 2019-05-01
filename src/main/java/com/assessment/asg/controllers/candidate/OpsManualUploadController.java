package com.assessment.asg.controllers.candidate;

import com.assessment.asg.handlers.StorageFileNotFoundException;
import com.assessment.asg.services.StorageService;
import com.assessment.asg.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class OpsManualUploadController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final StorageService storageService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public OpsManualUploadController(final StorageService storageService, final UserDetailsServiceImpl userDetailsService) {
        this.storageService = storageService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(final @PathVariable String filename) {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has been served the operators manual template");
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadOperatorsManual")
    public String handleFileUpload(final @RequestParam("file") MultipartFile file) {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted an operators manual");
        storageService.store(file);
        return "redirect:/dashboard";
    }

    @GetMapping("/downloadOpsManual")
    public void downloadFile(final HttpServletResponse response) throws IOException {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has requested the operators manual template");
        File file = new ClassPathResource("/static/download/OperatorsManualTemplate.pdf").getFile();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inputStream.close();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }

}
