package com.ys.app.model.validator.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by byun.ys on 4/28/2017.
 */
public class SearchForm {

    @NotEmpty
    private String key;
    @NotEmpty
    @Length(min=4)
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
