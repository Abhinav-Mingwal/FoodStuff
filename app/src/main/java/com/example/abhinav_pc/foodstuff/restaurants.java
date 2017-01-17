package com.example.abhinav_pc.foodstuff;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AbHiNav-PC on 16-Nov-16.
 */

public class restaurants {
    @SerializedName("restaurant")
    restaurants_Detail detail;

    public class restaurants_Detail {
        @SerializedName("id")
        String restraunt_id;
        @SerializedName("name")
        String restraunt_name;
        @SerializedName("url")
        String Zomato_Link;
        @SerializedName("location")
        restaurant_Location location;

        public class restaurant_Location {

            @SerializedName("address")
            String restaurant_address;
            @SerializedName("locality")
            String restaurant_Locality;
            @SerializedName("city")
            String restaurant_City;
            @SerializedName("city_id")
            String City_ID;

        }

        @SerializedName("cuisines")
        String restaurant_cuisines;
        @SerializedName("average_cost_for_two")
        int restaurant_average_cost_for_two;
        @SerializedName("price_range")
        int restaurant_price_range;
        @SerializedName("thumb")
        String restaurant_thumbID;
        @SerializedName("user_rating")
        restaurant_User_Rating user_rating;

        public class restaurant_User_Rating {
            @SerializedName("aggregate_rating")
            Float aggregate;
            @SerializedName("rating_text")
            String rating_Text;
            @SerializedName("votes")
            String votes;

        }


    }
}
