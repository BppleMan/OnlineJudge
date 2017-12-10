package com.bppleman.entity;

/**
 * Created by BppleMan on 2017/11/21.
 */
public class Data {
    private int id;
    private int problemId;
    private String input;
    private String answer;
    private String shellName;
    private String shellValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        return "Data{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", input='" + input + "\'" +
                ", answer='" + answer + "\'" +
                ", shellName='" + shellName + "\'" +
                ", shellValue='" + shellValue + "\'" +
                "}\n";
    }
}
