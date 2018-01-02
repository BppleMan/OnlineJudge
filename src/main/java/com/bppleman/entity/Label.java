package com.bppleman.entity;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/24.
 */
public class Label {
    private Integer id;
    private String type;
    private List<String> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", type='" + type + "\'" +
                ", values=" + values +
                "}\n";
    }
}
