package com.example.abhinav_pc.foodstuff.Network;

import com.example.abhinav_pc.foodstuff.restaurants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 13-Oct-16.
 */
public class RestaurantResponce {


    public ArrayList<restaurants> getRestraunts() {
        return restraunts;
    }

    public void setRestraunts(ArrayList<restaurants> restraunts) {
        this.restraunts = restraunts;
    }

    @SerializedName("restaurants")
    ArrayList<restaurants> restraunts;
}
