package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Option;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class OptionValueDTO {
    @JsonProperty("id")
    private String optionName;
    @JsonProperty("name")
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
