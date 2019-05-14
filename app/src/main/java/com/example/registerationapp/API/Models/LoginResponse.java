package com.example.registerationapp.API.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    public LoginResponse(boolean mError, String mMessage, User mUser) {
        this.mError = mError;
        this.mMessage = mMessage;
        this.mUser = mUser;
    }

    @SerializedName("error")
    private boolean mError;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("user")
    private User mUser;

    public boolean ismError() {
        return mError;
    }

    public void setmError(boolean mError) {
        this.mError = mError;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
