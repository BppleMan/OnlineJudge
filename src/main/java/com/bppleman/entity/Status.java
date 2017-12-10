package com.bppleman.entity;

import java.sql.Timestamp;

/**
 * Created by BppleMan on 2017/11/20.
 */
public class Status {
    private int id;
    private IDParam idParam;
    private int codeId;
    private Code code;
    private String statusValue;
    private int time;
    private int memory;
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
        time = -1;
        memory = -1;
        compileInfo = null;
        idParam = new IDParam(null, null, null, null);
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

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
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
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", idParam=" + idParam +
                ", codeId=" + codeId +
                ", statusValue='" + statusValue + "\'" +
                ", time=" + time +
                ", memory=" + memory +
                ", compileInfo='" + compileInfo + "\'" +
                ", date=" + date +
                "}\n";
    }
}
