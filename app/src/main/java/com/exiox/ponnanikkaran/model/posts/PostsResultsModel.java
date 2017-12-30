package com.exiox.ponnanikkaran.model.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by priyesh on 30/12/17.
 */

@JsonIgnoreProperties( ignoreUnknown = true )
public class PostsResultsModel {

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userImage")
    private String userImage;

    @JsonProperty("postHeading")
    private String postHeading;

    @JsonProperty("postDetails")
    private String postDetails;

    @JsonProperty("postDt")
    private String postDt;

    @JsonProperty("postUpdatedDate")
    private String postUpdatedDate;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPostHeading() {
        return postHeading;
    }

    public void setPostHeading(String postHeading) {
        this.postHeading = postHeading;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }

    public String getPostDt() {
        return postDt;
    }

    public void setPostDt(String postDt) {
        this.postDt = postDt;
    }

    public String getPostUpdatedDate() {
        return postUpdatedDate;
    }

    public void setPostUpdatedDate(String postUpdatedDate) {
        this.postUpdatedDate = postUpdatedDate;
    }
}
