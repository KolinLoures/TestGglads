package com.example.kolin.testgglads.presentation.list;

import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;

import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public interface ListView {

    void showCategories(List<String> categoryList);

    void showPostsCategories(List<Post> postList);
}
