package com.example.qgame.Models;

import com.example.qgame.helpers.converters.IJsonConverter;
import com.example.qgame.helpers.entityembadable.FilesList;
import com.example.qgame.helpers.entityembadable.ITestInter;
import lombok.Data;

import javax.persistence.*;

@Table(name = "tests")
@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @Convert(converter = IJsonConverter.class)
    @Column(columnDefinition = "json")
    private ITestInter images;
}
