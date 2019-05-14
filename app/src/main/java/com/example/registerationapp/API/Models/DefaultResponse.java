package com.example.registerationapp.API.Models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    public DefaultResponse(boolean mError, String mMessage) {
        this.mError = mError;
        this.mMessage = mMessage;
    }

    @SerializedName("error")
    private boolean mError;

    @SerializedName("message")
    private String mMessage;

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
}
