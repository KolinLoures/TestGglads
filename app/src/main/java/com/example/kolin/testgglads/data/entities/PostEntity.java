package com.example.kolin.testgglads.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 25.01.2017.
 */

public class PostEntity {
    @SerializedName("category_id")
    private Integer categoryId;
    @SerializedName("day")
    private String day;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("product_state")
    private String productState;
    @SerializedName("tagline")
    private String tagLine;
    @SerializedName("ios_featured_at")
    private Object iosFeaturedAt;
    @SerializedName("comments_count")
    private Integer commentsCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("discussion_url")
    private String discussionUrl;
    @SerializedName("exclusive")
    private Object exclusive;
    @SerializedName("featured")
    private Boolean featured;
    @SerializedName("maker_inside")
    private Boolean makerInside;
    @SerializedName("redirect_url")
    private String redirectUrl;
    @SerializedName("screenshot_url")
    private ScreenShotsEntity screenshotUrl;
    @SerializedName("thumbnail")
    private ThumbnailEntity thumbnail;
    @SerializedName("votes_count")
    private Integer votesCount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getTagline() {
        return tagLine;
    }

    public void setTagline(String tagLine) {
        this.tagLine = tagLine;
    }

    public Object getIosFeaturedAt() {
        return iosFeaturedAt;
    }

    public void setIosFeaturedAt(Object iosFeaturedAt) {
        this.iosFeaturedAt = iosFeaturedAt;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDiscussionUrl() {
        return discussionUrl;
    }

    public void setDiscussionUrl(String discussionUrl) {
        this.discussionUrl = discussionUrl;
    }

    public Object getExclusive() {
        return exclusive;
    }

    public void setExclusive(Object exclusive) {
        this.exclusive = exclusive;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean getMakerInside() {
        return makerInside;
    }

    public void setMakerInside(Boolean makerInside) {
        this.makerInside = makerInside;
    }



    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public ScreenShotsEntity getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(ScreenShotsEntity screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }

    public ThumbnailEntity getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailEntity thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }


}
