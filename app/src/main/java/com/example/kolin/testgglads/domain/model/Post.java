package com.example.kolin.testgglads.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public class Post implements Parcelable {

    private Integer categoryId;
    private Integer id;
    private String name;
    private String tagLine;
    private String createdAt;
    private String redirectUrl;
    private Integer votesCount;
    private String thumbnailUrl;
    private List<String> screenshotsUrl;


    public Post() {
    }

    protected Post(Parcel in) {
        name = in.readString();
        tagLine = in.readString();
        createdAt = in.readString();
        redirectUrl = in.readString();
        thumbnailUrl = in.readString();
        screenshotsUrl = in.createStringArrayList();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getScreenshotsUrl() {
        return screenshotsUrl;
    }

    public void setScreenshotsUrl(List<String> screenshotsUrl) {
        this.screenshotsUrl = screenshotsUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tagLine);
        dest.writeString(createdAt);
        dest.writeString(redirectUrl);
        dest.writeString(thumbnailUrl);
        dest.writeStringList(screenshotsUrl);
    }
}
