package com.nighthawk.spring_portfolio.mvc.imageData;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.status(null).build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    /* 
    @GetMapping("/downloadFiles")
    public String downloadFiles() {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        String[] filePaths = null;
        ArrayList<String> myArray = new ArrayList<String>();
        try {
            myArray = downloadUtil.getFiles();
            filePaths = new String[myArray.size()];
            for (int i = 0; i < myArray.size(); i++) {
                filePaths[i] = myArray.get(i);
            }            

        } catch (IOException e) {
            ex.printStackTrace();
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }    
    */
}