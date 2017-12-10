package com.bppleman.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/8.
 */
public class Problem implements Serializable{
    private int id;
    private String title;
    private String description;
    private String input;
    private String output;
    private String sampleInput;
    private String sampleOutput;
    private String hints;
    private String author;
    private Date createTime;
    private List<String> labels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + "\'" +
                ", description='" + description + "\'" +
                ", input='" + input + "\'" +
                ", output='" + output + "\'" +
                ", sampleInput='" + sampleInput + "\'" +
                ", sampleOutput='" + sampleOutput + "\'" +
                ", hints='" + hints + "\'" +
                ", author='" + author + "\'" +
                ", createTime=" + createTime +
                ", labels=" + labels +
                "}\n";
    }
}
