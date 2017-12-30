package com.exiox.ponnanikkaran.model.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by priyesh on 30/12/17.
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class PostResult {

    @JsonProperty("result")
    private List<PostsResultsModel> result;

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

    public List<PostsResultsModel> getResult() {
        return result;
    }

    public void setResult(List<PostsResultsModel> result) {
        this.result = result;
    }
}
