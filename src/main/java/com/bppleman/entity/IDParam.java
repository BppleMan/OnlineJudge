package com.bppleman.entity;

public class IDParam {
    private int userId;
    private int problemId;
    private int contestId;
    private int examId;

    public IDParam() {
        this(null, null, null, null);
    }

    public IDParam(Integer userId, Integer problemId, Integer contestId, Integer examId) {
        if (userId != null)
            this.userId = userId;
        else this.userId = -1;
        if (problemId != null)
            this.problemId = problemId;
        else this.problemId = -1;
        if (contestId != null)
            this.contestId = contestId;
        else this.contestId = -1;
        if (examId != null)
            this.examId = examId;
        else this.examId = -1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Override
    public String toString() {
        return "IDParam{" +
                "userId=" + userId +
                ", problemId=" + problemId +
                ", contestId=" + contestId +
                ", examId=" + examId +
                "}\n";
    }

    public String toPath() {
        return "?1=1" +
                (userId  != -1 ? "&userId=" + userId : "") +
                (problemId != -1 ? "&problemId=" + problemId : "") +
                (contestId != -1 ? "&contestId=" + contestId : "") +
                (examId != -1 ? "&examId=" + examId : "");
    }
}
