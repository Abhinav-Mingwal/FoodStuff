package com.example.abhinav_pc.foodstuff.Network;

import com.example.abhinav_pc.foodstuff.Collection;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 16-Oct-16.
 */
public class collectionResponce {

    @SerializedName("collections")
    ArrayList<Collection> collections;

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
    }
}
