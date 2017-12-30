package com.exiox.ponnanikkaran.model.Sigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by priyesh on 29/12/17.
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class SigninResult {

    @JsonProperty("result")
    private List<SignInResultModel> result;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

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

    public List<SignInResultModel> getResult() {
        return result;
    }

    public void setResult(List<SignInResultModel> result) {
        this.result = result;
    }
}
