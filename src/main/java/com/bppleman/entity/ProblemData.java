package com.bppleman.entity;

/**
 * Created by BppleMan on 2017/11/21.
 */
public class ProblemData {
    private Integer id;
    private Integer problemId;
    private String inputData;
    private String outputData;
    private String shellName;
    private String shellValue;

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

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getShellName() {
        return shellName;
    }

    public void setShellName(String shellName) {
        this.shellName = shellName;
    }

    public String getShellValue() {
        return shellValue;
    }

    public void setShellValue(String shellValue) {
        this.shellValue = shellValue;
    }

    @Override
    public String toString() {
        return "ProblemData{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", inputData='" + inputData + "\'" +
                ", outputData='" + outputData + "\'" +
                ", shellName='" + shellName + "\'" +
                ", shellValue='" + shellValue + "\'" +
                "}\n";
    }
}
