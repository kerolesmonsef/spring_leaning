package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Option;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OptionValueDTO {
    private String optionName;
    private String value;

    @JsonIgnore
    private Option option;

    public OptionValueDTO() {
    }

    public OptionValueDTO(String optionName, String value) {
        this.optionName = optionName;
        this.value = value;
    }
}
