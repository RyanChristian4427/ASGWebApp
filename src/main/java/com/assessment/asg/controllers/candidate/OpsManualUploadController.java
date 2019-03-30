package com.assessment.asg.controllers.candidate;

import com.assessment.asg.handlers.StorageFileNotFoundException;
import com.assessment.asg.services.interfaces.StorageService;
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

    private final StorageService storageService;

    @Autowired
    public OpsManualUploadController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(final @PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadOperatorsManual")
    public String handleFileUpload(final @RequestParam("file") MultipartFile file) {
        storageService.store(file);

        return "redirect:/dashboard";
    }

    @GetMapping("/downloadOpsManual")
    public void downloadFile(final HttpServletResponse response) throws IOException {
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
