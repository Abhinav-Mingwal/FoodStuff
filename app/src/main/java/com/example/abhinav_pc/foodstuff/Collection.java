package com.example.abhinav_pc.foodstuff;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 16-Oct-16.
 */
public class Collection {
    @SerializedName("collection")
    CollectionF collection;

    public class CollectionF{
        @SerializedName("collection_id")
        int collection_id;
        @SerializedName("url")
        String url;
        @SerializedName("image_url")
        String image_url;
        @SerializedName("title")
        String title;
        @SerializedName("description")
        String description;
    }
}
