package com.bppleman.entity;

public class ProblemRatio {
    private Integer id;
    private IDParam idParam;
    private Integer submitTime;
    private Integer acTime;
    private Double ratioValue;

    public ProblemRatio() {
        this(new IDParam(null, null, null, null));
    }

    public ProblemRatio(IDParam idParam) {
        this.idParam = idParam;
        submitTime = 0;
        acTime = 0;
        ratioValue = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return idParam.getUserId();
    }

    public void setUserId(Integer userId) {
        this.idParam.setUserId(userId);
    }

    public Integer getProblemId() {
        return idParam.getProblemId();
    }

    public void setProblemId(Integer problemId) {
        this.idParam.setProblemId(problemId);
    }

    public Integer getContestId() {
        return idParam.getContestId();
    }

    public void setContestId(Integer contestId) {
        this.idParam.setContestId(contestId);
    }

    public Integer getExamId() {
        return idParam.getExamId();
    }

    public void setExamId(Integer examId) {
        this.idParam.setExamId(examId);
    }

    public Integer getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Integer submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getAcTime() {
        return acTime;
    }

    public void setAcTime(Integer acTime) {
        this.acTime = acTime;
    }

    public Double getRatioValue() {
        return ratioValue;
    }

    public void setRatioValue(Double ratioValue) {
        this.ratioValue = ratioValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemRatio that = (ProblemRatio) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idParam != null ? !idParam.equals(that.idParam) : that.idParam != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (acTime != null ? !acTime.equals(that.acTime) : that.acTime != null) return false;
        return ratioValue != null ? ratioValue.equals(that.ratioValue) : that.ratioValue == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idParam != null ? idParam.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (acTime != null ? acTime.hashCode() : 0);
        result = 31 * result + (ratioValue != null ? ratioValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProblemRatio{" +
                "id=" + id +
                ", idParam=" + idParam +
                ", submitTime=" + submitTime +
                ", acTime=" + acTime +
                ", ratioValue=" + ratioValue +
                "}\n";
    }
}
