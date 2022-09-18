package com.example.qgame.helpers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OptionValuesDto {
    private final String optionName;
    private final List<String> values;
}
