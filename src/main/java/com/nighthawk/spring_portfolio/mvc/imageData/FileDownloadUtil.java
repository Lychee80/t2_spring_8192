package com.nighthawk.spring_portfolio.mvc.imageData;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownloadUtil {
    private Path foundFile;

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

    public ArrayList<String> getFilePaths() throws IOException {
        Path dirPath = Paths.get("Files-Upload");
        ArrayList<String> filePaths = new ArrayList<String>();

        Files.list(dirPath).forEach(file -> {
            filePaths.add(file.getFileName().toString());
        });

        return filePaths;
    }

    public static void main(String args[]) {
        FileDownloadUtil myUtil = new FileDownloadUtil();
        try {
            ArrayList<String> myList = myUtil.getFilePaths();
            for (int i = 0; i < myList.size(); i++) {
                System.out.println(myList.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}