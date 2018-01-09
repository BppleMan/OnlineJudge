package com.bppleman.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
public class Contest implements Serializable{
    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String status;
    private String type;
    private String username;
    private Integer userId;
    private String password;
    private Long day;
    private Long hour;
    private Long minute;
    private Long second;
    private List<Integer> problemIds;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUsername() {
        return username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getProblemIds() {
        return problemIds;
    }

    public void setProblemIds(List<Integer> problemIds) {
        this.problemIds = problemIds;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Long getMinute() {
        return minute;
    }

    public void setMinute(Long minute) {
        this.minute = minute;
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }

    public void updateFrom(Contest contest) {
        this.name = contest.name;
        this.endTime = contest.endTime;
        this.day = contest.day;
        this.hour = contest.hour;
        this.minute = contest.minute;
        this.second = contest.second;
        if (startTime != null) {
            this.startTime = contest.startTime;
            long duration = (day * 24 * 3600 + hour * 3600 + minute * 60 + second) * 1000;
            this.endTime = new Timestamp(startTime.getTime() + duration);
        }
    }

    @Override
    public Contest clone() throws CloneNotSupportedException {
        Contest contest = new Contest();
        contest.problemIds = new ArrayList<>(this.problemIds);
        contest.name = new String(name);
        contest.status = new String(status);
        contest.type = new String(type);
        contest.password = password;
        return contest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contest contest = (Contest) o;

        if (id != null ? !id.equals(contest.id) : contest.id != null) return false;
        if (name != null ? !name.equals(contest.name) : contest.name != null) return false;
        if (startTime != null ? !startTime.equals(contest.startTime) : contest.startTime != null) return false;
        if (endTime != null ? !endTime.equals(contest.endTime) : contest.endTime != null) return false;
        if (status != null ? !status.equals(contest.status) : contest.status != null) return false;
        if (type != null ? !type.equals(contest.type) : contest.type != null) return false;
        if (username != null ? !username.equals(contest.username) : contest.username != null) return false;
        if (userId != null ? !userId.equals(contest.userId) : contest.userId != null) return false;
        if (password != null ? !password.equals(contest.password) : contest.password != null) return false;
        if (day != null ? !day.equals(contest.day) : contest.day != null) return false;
        if (hour != null ? !hour.equals(contest.hour) : contest.hour != null) return false;
        if (minute != null ? !minute.equals(contest.minute) : contest.minute != null) return false;
        if (second != null ? !second.equals(contest.second) : contest.second != null) return false;
        return problemIds != null ? problemIds.equals(contest.problemIds) : contest.problemIds == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (second != null ? second.hashCode() : 0);
        result = 31 * result + (problemIds != null ? problemIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", problemIds=" + problemIds +
                '}';
    }
}
