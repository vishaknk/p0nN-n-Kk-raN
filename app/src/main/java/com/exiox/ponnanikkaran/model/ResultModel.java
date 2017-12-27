package com.exiox.ponnanikkaran.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by priyesh on 27/12/17.
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class ResultModel {
    @JsonProperty("result")
    private List<SignUpModel> loginResult;

    private List<SignUpModel> results;


    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<SignUpModel> getLoginResults() {
        return loginResult;
    }

    public void setLoginResult(List<SignUpModel> result) {
        this.loginResult = result;
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

