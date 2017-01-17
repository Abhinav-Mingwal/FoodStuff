package com.example.abhinav_pc.foodstuff.Network;

import com.example.abhinav_pc.foodstuff.Category;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 16-Oct-16.
 */
public class CategoryResponce {
    @SerializedName("categories")
    ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
