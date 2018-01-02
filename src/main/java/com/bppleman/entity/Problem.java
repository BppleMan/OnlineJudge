package com.bppleman.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/8.
 */
public class Problem implements Serializable{
    private Integer id;
    private String title;
    private String description;
    private String input;
    private String output;
    private String sampleInput;
    private String sampleOutput;
    private String hints;
    private String author;
    private Date createTime;
    private ProblemRatio problemRatio;
    private List<ProblemLabel> problemLabels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ProblemRatio getProblemRatio() {
        return problemRatio;
    }

    public void setProblemRatio(ProblemRatio problemRatio) {
        this.problemRatio = problemRatio;
    }

    public List<ProblemLabel> getProblemLabels() {
        return problemLabels;
    }

    public void setProblemLabels(List<ProblemLabel> problemLabels) {
        this.problemLabels = problemLabels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        if (id != null ? !id.equals(problem.id) : problem.id != null) return false;
        if (title != null ? !title.equals(problem.title) : problem.title != null) return false;
        if (description != null ? !description.equals(problem.description) : problem.description != null) return false;
        if (input != null ? !input.equals(problem.input) : problem.input != null) return false;
        if (output != null ? !output.equals(problem.output) : problem.output != null) return false;
        if (sampleInput != null ? !sampleInput.equals(problem.sampleInput) : problem.sampleInput != null) return false;
        if (sampleOutput != null ? !sampleOutput.equals(problem.sampleOutput) : problem.sampleOutput != null)
            return false;
        if (hints != null ? !hints.equals(problem.hints) : problem.hints != null) return false;
        if (author != null ? !author.equals(problem.author) : problem.author != null) return false;
        if (createTime != null ? !createTime.equals(problem.createTime) : problem.createTime != null) return false;
        if (problemRatio != null ? !problemRatio.equals(problem.problemRatio) : problem.problemRatio != null)
            return false;
        return problemLabels != null ? problemLabels.equals(problem.problemLabels) : problem.problemLabels == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + (output != null ? output.hashCode() : 0);
        result = 31 * result + (sampleInput != null ? sampleInput.hashCode() : 0);
        result = 31 * result + (sampleOutput != null ? sampleOutput.hashCode() : 0);
        result = 31 * result + (hints != null ? hints.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (problemRatio != null ? problemRatio.hashCode() : 0);
        result = 31 * result + (problemLabels != null ? problemLabels.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", sampleInput='" + sampleInput + '\'' +
                ", sampleOutput='" + sampleOutput + '\'' +
                ", hints='" + hints + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", problemRatio=" + problemRatio +
                ", problemLabels=" + problemLabels +
                '}';
    }
}
