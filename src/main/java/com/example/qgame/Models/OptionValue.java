package com.example.qgame.Models;

import com.example.qgame.helpers.entityembadable.ids.OptionValueId;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "option_value")
public class OptionValue implements Serializable {

//    @EmbeddedId
//    private OptionValueId id;

    @Id
    private Long option_id;

    @Id
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;
}
