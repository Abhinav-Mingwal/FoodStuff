package com.example.abhinav_pc.foodstuff.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by AbHiNav-PC on 13-Oct-16.
 */
public interface ApiServices {
//https://developers.zomato.com/api/v2.1/search?entity_type=city&lat=28.6329&lon=77.2195&sort=rating

    @Headers({"Accept: application/json","user-key: YOUR KEY HERE"})
    @GET("collections")
    Call<collectionResponce> getCollection(@Query("city_id") int city_id);


    @Headers({"Accept: application/json","user-key: YOUR KEY HERE"})
    @GET("categories")
    Call<CategoryResponce> getCategory();

    @Headers({"Accept: application/json","user-key: YOUR KEY HERE"})
    @GET("search")
    Call<RestaurantResponce> getRestraunts(@Query("entity_type") String city,@Query("lat") double lat,@Query("lon") double lon,@Query("sort") String sort);

    @Headers({"Accept: application/json","user-key: YOUR KEY HERE"})
    @GET("locations")
    Call<LocationResponse> getLocation(@Query("query") String place);

    @Headers({"Accept: application/json","user-key: YOUR KEY HERE"})
    @GET("restaurant")
    Call<RestaurantDetailsOnClick> getRestaurantDetails(@Query("res_id") String res_id);
}
