package com.bppleman.entity;

public class ProblemLabel {
    private Integer id;
    private Integer problemId;
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemLabel that = (ProblemLabel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (problemId != null ? !problemId.equals(that.problemId) : that.problemId != null) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (problemId != null ? problemId.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProblemLabel{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", label='" + label + '\'' +
                '}';
    }
}
