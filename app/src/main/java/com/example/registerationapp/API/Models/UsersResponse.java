package com.example.registerationapp.API.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    public UsersResponse(boolean mError, List<User> mUsers) {
        this.mError = mError;
        this.mUsers = mUsers;
    }

    @SerializedName("error")
    private boolean mError;

    @SerializedName("users")
    private List <User> mUsers;

    public boolean ismError() {
        return mError;
    }

    public void setmError(boolean mError) {
        this.mError = mError;
    }

    public List<User> getmUsers() {
        return mUsers;
    }

    public void setmUsers(List<User> mUsers) {
        this.mUsers = mUsers;
    }
}
