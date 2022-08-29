package com.example.qgame.helpers.services.files;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileInfo {
    private String name;
    private String url;
}
