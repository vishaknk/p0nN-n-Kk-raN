package com.exiox.ponnanikkaran.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by priyesh on 27/12/17.
 */

public class SignUpResultModel {


    @JsonProperty("result")
    private List<SignUpModel> result;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<SignUpModel> getResult() {
        return result;
    }

    public void setResult(List<SignUpModel> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
