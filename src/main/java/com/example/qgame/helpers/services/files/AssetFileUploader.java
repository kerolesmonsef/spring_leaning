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
public class AssetFileUploader {

    private String filePath = "";

    private MultipartFile file;

    private boolean randomName = true;

    public FileInfo upload() throws IOException {

        Path root = Path.of(FileHelper.ASSET_PATH + filePath);

        if (file.isEmpty()) {
            return new FileInfo();
        }

        String fileName = getFileName(file);

        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }

        Files.copy(file.getInputStream(), root.resolve(fileName));

        return new FileInfo().setName(fileName);
    }

    private String getFileName(MultipartFile file) {

        if (randomName) {
            String filename = file.getOriginalFilename();
            String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
            return StringHelper.random(10) + "." + fileExtension;
        }

        return file.getOriginalFilename();
    }
}
