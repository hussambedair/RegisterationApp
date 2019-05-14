package com.example.registerationapp.API.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    public User(int mId, String mEmail, String mName, String mSchool) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mName = mName;
        this.mSchool = mSchool;
    }

    @SerializedName("id")
    private int mId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("name")
    private String mName;

    @SerializedName("school")
    private String mSchool;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }
}
