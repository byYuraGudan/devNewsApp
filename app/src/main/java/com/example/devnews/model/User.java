package com.example.devnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;
    @SerializedName("github_username")
    @Expose
    private String githubUsername;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("profile_image_90")
    @Expose
    private String profileImage90;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImage90() {
        return profileImage90;
    }

    public void setProfileImage90(String profileImage90) {
        this.profileImage90 = profileImage90;
    }

}