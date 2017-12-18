package br.com.dbc.proof.dto;

import java.time.LocalTime;

public class Session {

    private String sessionTitle;
    private Integer sessionDuration;
    private LocalTime sessionStartTime;

    public Session(String sessionTitle, Integer sessionDuration) {
        this.sessionTitle = sessionTitle;
        this.sessionDuration = sessionDuration;
    }

    public Session(String sessionTitle, Integer sessionDuration, LocalTime sessionStartTime) {
        this.sessionTitle = sessionTitle;
        this.sessionDuration = sessionDuration;
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public Integer getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(Integer sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String toStringLineOutput(){
        return sessionStartTime.toString() + " " + sessionTitle;
    }
}
