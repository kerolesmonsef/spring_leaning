package com.example.qgame.helpers.services.files;

import com.example.qgame.helpers.StringHelper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Data
@Accessors(chain = true)
public class AssetFileManager {

    private String filePath = "";

    private MultipartFile file;

    private boolean randomName = true;

    public FileInfo upload() {

        Path root = fullPath();

        if (file.isEmpty()) {
            return new FileInfo();
        }

        String fileName = newFileName(file);

        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            Files.copy(file.getInputStream(), root.resolve(fileName));
        } catch (IOException e) {

        }

        return new FileInfo().setName(fileName);
    }


    public void remove(String fileName) {

        try {
            Files.delete(fullPath(fileName));
        } catch (IOException e) {

        }
    }


    private Path fullPath() {
        return Path.of(FileHelper.ASSET_PATH + filePath);
    }

    private Path fullPath(String fileName) {
        return Path.of(FileHelper.ASSET_PATH + filePath + fileName);
    }

    private String newFileName(MultipartFile file) {

        if (randomName) {
            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            return StringHelper.random(10) + "." + fileExtension;
        }

        return file.getOriginalFilename();
    }
}
