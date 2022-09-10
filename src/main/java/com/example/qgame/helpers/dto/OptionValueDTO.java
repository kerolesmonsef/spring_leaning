package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Option;
import lombok.Data;

@Data
public class OptionValueDTO {
    private String optionName;
    private String value;
    private Option option;

    public OptionValueDTO() {
    }

    public OptionValueDTO(String optionName, String value) {
        this.optionName = optionName;
        this.value = value;
    }
}
