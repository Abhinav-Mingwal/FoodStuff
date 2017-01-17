package com.example.abhinav_pc.foodstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.abhinav_pc.foodstuff.Network.ApiServices;
import com.example.abhinav_pc.foodstuff.Network.RestaurantResponce;
import com.example.abhinav_pc.foodstuff.Network.URLconstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationRestaurant extends AppCompatActivity {
    RestaurantAdapterRecycler restaurantAdapter;
    ArrayList<restaurants> restrauntList;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.RestaurantRecyclerView);
        restrauntList=new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(restaurantAdapter);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLconstants.Base_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices service = retrofit.create(ApiServices.class);
        Intent intent=getIntent();
        double[] location=intent.getDoubleArrayExtra("Location");
        Call<RestaurantResponce> call=service.getRestraunts("city",location[0],location[1],"rating");
        call.enqueue(new Callback<RestaurantResponce>() {
            @Override
            public void onResponse(Call<RestaurantResponce> call, Response<RestaurantResponce> response) {
                ArrayList<restaurants> Temp=response.body().getRestraunts();
                if(Temp==null){
                    return;
                }
                else {
                    for(restaurants i :Temp){
                        LocationRestaurant.this.restrauntList.add(i);
                    }
                    restaurantAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponce> call, Throwable t) {
            }
        });
        restaurantAdapter= new RestaurantAdapterRecycler(this,restrauntList);
        mRecyclerView.setAdapter(restaurantAdapter);

    }


}
