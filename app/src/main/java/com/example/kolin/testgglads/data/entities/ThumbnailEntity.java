package com.example.kolin.testgglads.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 25.01.2017.
 */

public class ThumbnailEntity {

    @SerializedName("id")
    private Integer id;
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("image_url")
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
