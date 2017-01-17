package com.example.abhinav_pc.foodstuff;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AbHiNav-PC on 16-Oct-16.
 */
public class Category {

    @SerializedName("categories")
    categoriesInside category;

    public class categoriesInside{
        @SerializedName("id")
        String category_id;
        @SerializedName("name")
        String category_name;
    }
}
