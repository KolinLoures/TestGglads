package com.example.kolin.testgglads.data.entities;

import com.example.kolin.testgglads.domain.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kolin on 25.01.2017.
 */

public class CategoryEntity {

    @SerializedName("categories")
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
