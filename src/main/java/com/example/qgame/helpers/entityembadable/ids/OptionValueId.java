package com.example.qgame.helpers.entityembadable.ids;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OptionValueId implements Serializable {
    private Long option_id;
    private String value;

    public OptionValueId(Long option_id, String value) {
        this.option_id = option_id;
        this.value = value;
    }
}
