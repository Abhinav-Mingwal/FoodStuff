package com.example.abhinav_pc.foodstuff.Network;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 25-Oct-16.
 */
public class LocationResponse {


    public class LocationSuggestion
    {
        public String entity_type;
        public int entity_id;
        public String title;
        public double latitude;
        public double longitude;
        public int city_id;
        public String city_name;
        public int country_id;
        public String country_name;
    }

    public ArrayList<LocationSuggestion> getLocation_suggestions() {
        return location_suggestions;
    }

    public void setLocation_suggestions(ArrayList<LocationSuggestion> location_suggestions) {
        this.location_suggestions = location_suggestions;
    }

    public ArrayList<LocationSuggestion> location_suggestions;

}
