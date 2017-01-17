package com.example.abhinav_pc.foodstuff.Network;

/**
 * Created by AbHiNav-PC on 16-Nov-16.
 */

public class RestaurantDetailsOnClick {


    public String apikey;
    public String id;
    public String name;
    public String url;
    public Location location;

    public class Location
    {
        public String address;
        public String locality;
        public String city;
        public int city_id;
        public String latitude;
        public String longitude;
        public String zipcode;
        public int country_id;
    }

    public class UserRating
    {
        public String aggregate_rating;
        public String rating_text;
        public String rating_color;
        public String votes;
    }

    public String cuisines;
    public int average_cost_for_two;
    public int price_range;
    public String currency;
    public String thumb;
    public UserRating user_rating;
    public String photos_url;
    public String menu_url;
    public String featured_image;
    public int has_online_delivery;
    public int is_delivering_now;
    public String deeplink;
    public int has_table_booking;
    public String book_url;
    public String events_url;
}
