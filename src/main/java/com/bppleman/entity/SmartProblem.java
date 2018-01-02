package com.bppleman.entity;

public class SmartProblem {
    private String type;
    private String label;
    private Integer startLevel;
    private Integer endLevel;
    private Integer problemCount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(Integer startLevel) {
        this.startLevel = startLevel;
    }

    public Integer getEndLevel() {
        return endLevel;
    }

    public void setEndLevel(Integer endLevel) {
        this.endLevel = endLevel;
    }

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    @Override
    public String toString() {
        return "SmartProblem{" +
                "type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", startLevel=" + startLevel +
                ", endLevel=" + endLevel +
                ", problemCount=" + problemCount +
                '}';
    }
}
