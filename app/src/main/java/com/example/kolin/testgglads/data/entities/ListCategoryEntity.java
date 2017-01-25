package com.example.kolin.testgglads.data.entities;

import com.example.kolin.testgglads.domain.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public class ListCategoryEntity {

    @SerializedName("categories")
    private List<CategoryEntity> categories = null;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
