package com.bppleman.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
public class Contest implements Serializable{
    private int id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private long duration;
    private String status;
    private String type;
    private String author;
    private String password;
    private String note;
    private List<Problem> problems;

    public static class Type {
        public static String PUBLIC = "public";
        public static String PASSWORD = "password";
        public static String CLASS = "class";
    }

    public static class Status {
        public static String RUNNING = "running";
        public static String READY = "ready";
        public static String END = "end";
    }

    public static class Note {
        public static String TEACHER = "teacher";
        public static String DIY = "diy";
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + "\'" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", status='" + status + "\'" +
                ", type='" + type + "\'" +
                ", author='" + author + "\'" +
                ", password='" + password + "\'" +
                ", note='" + note + "\'" +
                ", problems=" + problems +
                "}\n";
    }
}
