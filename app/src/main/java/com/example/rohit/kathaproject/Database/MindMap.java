package com.example.rohit.kathaproject.Database;

/**
 * Created by Rohit on 13-07-2017.
 */

public class MindMap {
    private String issueName;
    private String startTime;
    private String stopTime;
    private String isMainTopic;

    public MindMap(String issueName, String startTime, String stopTime, String isMainTopic) {
        this.issueName = issueName;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.isMainTopic = isMainTopic;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getIsMainTopic() {
        return isMainTopic;
    }

    public void setIsMainTopic(String isMainTopic) {
        this.isMainTopic = isMainTopic;
    }
}
