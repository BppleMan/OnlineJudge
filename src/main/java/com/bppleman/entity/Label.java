package com.bppleman.entity;

/**
 * Created by BppleMan on 2017/11/24.
 */
public class Label {
    private int id;
    private String labelType;
    private String labelValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", labelType='" + labelType + "\'" +
                ", labelValue='" + labelValue + "\'" +
                "}\n";
    }
}
