package com.example.kolin.testgglads.data.entities;

import com.example.kolin.testgglads.domain.model.Post;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public class ListPostEntity {

    @SerializedName("posts")
    private List<PostEntity> posts = null;

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
}
