package com.angelectro.domain.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class MessageModel implements Parcelable {

    private String id;
    private String text;
    private String subject;
    private Date startDateTime;
    private Date endDateTime;

    public MessageModel(String id, String text, String subject, Date startDateTime, Date endDateTime) {
        this.id = id;
        this.text = text;
        this.subject = subject;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    protected MessageModel(Parcel in) {
        id = in.readString();
        text = in.readString();
        subject = in.readString();
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(text);
        parcel.writeString(subject);
    }
}
