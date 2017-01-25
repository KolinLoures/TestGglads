package com.example.kolin.testgglads.data;

import com.example.kolin.testgglads.data.entities.CategoryEntity;
import com.example.kolin.testgglads.data.entities.PostEntity;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.presentation.list.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public class DataMapper {

    private static Category transformToCategory(CategoryEntity categoryEntity){
        Category category = null;
        if (categoryEntity != null) {
            category = new Category();
            category.setName(categoryEntity.getName());
            category.setColor(categoryEntity.getColor());
            category.setId(categoryEntity.getId());
            category.setItemName(categoryEntity.getItemName());
            category.setSlug(categoryEntity.getSlug());
        }
        return category;
    }

    private static Post transfromToPost(PostEntity postEntity){
        Post post = null;
        if (postEntity != null){
            post = new Post();
            post.setCategoryId(postEntity.getCategoryId());
            post.setId(postEntity.getId());
            post.setName(postEntity.getName());
            post.setTagLine(postEntity.getTagline());
            post.setCreatedAt(postEntity.getCreatedAt());
            post.setRedirectUrl(postEntity.getRedirectUrl());
            post.setVotesCount(postEntity.getVotesCount());
            post.setThumbnailUrl(postEntity.getThumbnail().getImageUrl());
            List<String> urls = new ArrayList<>();
            urls.add(postEntity.getScreenshotUrl().get300px());
            urls.add(postEntity.getScreenshotUrl().get850px());
            post.setScreenshotsUrl(urls);
        }
        return post;
    }


    public static List<Category> mapToCategory(List<CategoryEntity> categoryEntityList){
        List<Category> categories = null;
        if (categoryEntityList != null && !categoryEntityList.isEmpty()){
            categories = new ArrayList<>();
            for (CategoryEntity c: categoryEntityList){
                categories.add(transformToCategory(c));
            }
        }

        return categories;
    }

    public static List<Post> mapToPost(List<PostEntity> postEntities){
        List<Post> postList = null;
        if (postEntities != null && !postEntities.isEmpty()){
            postList = new ArrayList<>();
            for (PostEntity p: postEntities){
                postList.add(transfromToPost(p));
            }
        }
        return postList;
    }
}
