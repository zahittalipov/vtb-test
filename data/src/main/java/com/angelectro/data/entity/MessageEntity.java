package com.angelectro.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MessageEntity {

    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("subject")
    private String subject;
    @SerializedName("startDateTime")
    private Date startDateTime;
    @SerializedName("endDateTime")
    private Date endDateTime;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

}
