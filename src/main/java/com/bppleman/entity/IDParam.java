package com.bppleman.entity;

public class IDParam {
    private Integer userId;
    private Integer problemId;
    private Integer contestId;
    private Integer examId;

    public IDParam() {
        this(-1, -1, -1, -1);
    }

    public IDParam(Integer userId, Integer problemId, Integer contestId, Integer examId) {
        this.userId = userId;
        this.problemId = problemId;
        this.contestId = contestId;
        this.examId = examId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String toPath() {
        StringBuffer sb = new StringBuffer();
        if (userId != -1)
            sb.append("&userId=" + userId);
        if (problemId != -1)
            sb.append("&problemId=" + problemId);
        if (contestId != -1)
            sb.append("&contestId=" + contestId);
        if (examId != -1)
            sb.append("&examId=" + examId);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IDParam idParam = (IDParam) o;

        if (userId != null ? !userId.equals(idParam.userId) : idParam.userId != null) return false;
        if (problemId != null ? !problemId.equals(idParam.problemId) : idParam.problemId != null) return false;
        if (contestId != null ? !contestId.equals(idParam.contestId) : idParam.contestId != null) return false;
        return examId != null ? examId.equals(idParam.examId) : idParam.examId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (problemId != null ? problemId.hashCode() : 0);
        result = 31 * result + (contestId != null ? contestId.hashCode() : 0);
        result = 31 * result + (examId != null ? examId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IDParam{" +
                "userId=" + userId +
                ", problemId=" + problemId +
                ", contestId=" + contestId +
                ", examId=" + examId +
                '}';
    }
}
