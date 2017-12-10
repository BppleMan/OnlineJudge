package com.bppleman.entity;

public class ProblemRatio {
    private int id;
    private IDParam idParam;
    private int submitTime;
    private int acTime;
    private double ratioValue;

    public ProblemRatio() {
        this(new IDParam(null, null, null, null));
    }

    public ProblemRatio(IDParam idParam) {
        this.idParam = idParam;
        submitTime = 0;
        acTime = 0;
        ratioValue = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IDParam getIdParam() {
        return idParam;
    }

    public void setIdParam(IDParam idParam) {
        this.idParam = idParam;
    }

    public int getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(int submitTime) {
        this.submitTime = submitTime;
    }

    public int getAcTime() {
        return acTime;
    }

    public void setAcTime(int acTime) {
        this.acTime = acTime;
    }

    public double getRatioValue() {
        return ratioValue;
    }

    public void setRatioValue(double ratioValue) {
        this.ratioValue = ratioValue;
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
