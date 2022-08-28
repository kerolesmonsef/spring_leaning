package com.example.qgame.helpers.services;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Data
@Accessors(chain = true)
public class AssetFileUploader {

    private static String mainDirectory = "static";
    private final Path root = Paths.get("uploads");

    private String filePath = mainDirectory;

    private MultipartFile file;

    private boolean randomName = false;

    public AssetFileUploader setFilePath(String filePath) {
        this.filePath = mainDirectory + "/" + filePath;
        return this;
    }


    public void upload() throws IOException {

        if (file.isEmpty()) {
            return;
        }

        System.out.println(file);

//        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(filePath, file.getOriginalFilename());
//
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes());
        Path p = ResourceUtils.getFile("classpath:ss").toPath();
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }

        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));


    }
}
