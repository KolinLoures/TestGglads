package com.example.kolin.testgglads.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 25.01.2017.
 */

public class ScreenShotsEntity {

    @SerializedName("300px")
    private String size300px;
    @SerializedName("850px")
    private String size850px;

    public String get300px() {
        return size300px;
    }

    public void set300px(String _300px) {
        this.size300px = _300px;
    }

    public String get850px() {
        return size850px;
    }

    public void set850px(String _850px) {
        this.size850px = _850px;
    }
}
