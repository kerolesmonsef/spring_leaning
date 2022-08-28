package com.example.qgame.helpers.services;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Data
@Accessors(chain = true)
public class AssetFileUploader {

    private static String mainDirectory = "static";

    private String filePath;

    private MultipartFile file;

    private boolean randomName = false;


    public AssetFileUploader setFilePath(String filePath) {
        this.filePath = mainDirectory + "/" + filePath;
        return this;
    }


    public void Upload() throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(filePath, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
    }
}
