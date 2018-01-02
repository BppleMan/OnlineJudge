package com.bppleman.entity;

import java.sql.Timestamp;

/**
 * Created by BppleMan on 2017/11/20.
 */
public class Status {
    private Integer id;
    private IDParam idParam;
    private Integer codeId;
    private Code code;
    private String statusValue;
    private Integer time;
    private Integer memory;
    private String compileInfo;
    private Timestamp date;

    public static String Judging = "Judging";
    public static String Wrong_Answer = "Wrong Answer";
    public static String Accepted = "Accepted";
    public static String RUNTIME_ERROR = "Runtime Error";
    public static String Time_Limit_Exceeded = "Time Limit Exceeded";
    public static String Memory_Limit_Exceede = "Memory Limit Exceede";
    public static String Output_Limit_Exceeded = "Output Limit Exceeded";
    public static String Compilation_Error = "Compilation Error";
    public static String Presentation_Error = "Presentation Error";
    public static String System_Error = "System Error";
    public static String Queuing = "Queuing";

    public Status() {
        this(new IDParam());
    }

    public Status(IDParam param) {
        idParam = param;
        time = -1;
        memory = -1;
        compileInfo = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IDParam getIdParam() {
        return idParam;
    }

    public void setIdParam(IDParam idParam) {
        this.idParam = idParam;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
        this.codeId = code.getId();
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (id != null ? !id.equals(status.id) : status.id != null) return false;
        if (idParam != null ? !idParam.equals(status.idParam) : status.idParam != null) return false;
        if (codeId != null ? !codeId.equals(status.codeId) : status.codeId != null) return false;
        if (code != null ? !code.equals(status.code) : status.code != null) return false;
        if (statusValue != null ? !statusValue.equals(status.statusValue) : status.statusValue != null) return false;
        if (time != null ? !time.equals(status.time) : status.time != null) return false;
        if (memory != null ? !memory.equals(status.memory) : status.memory != null) return false;
        if (compileInfo != null ? !compileInfo.equals(status.compileInfo) : status.compileInfo != null) return false;
        return date != null ? date.equals(status.date) : status.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idParam != null ? idParam.hashCode() : 0);
        result = 31 * result + (codeId != null ? codeId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (statusValue != null ? statusValue.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (memory != null ? memory.hashCode() : 0);
        result = 31 * result + (compileInfo != null ? compileInfo.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", idParam=" + idParam +
                ", codeId=" + codeId +
                ", code=" + code +
                ", statusValue='" + statusValue + '\'' +
                ", time=" + time +
                ", memory=" + memory +
                ", compileInfo='" + compileInfo + '\'' +
                ", date=" + date +
                '}';
    }
}
