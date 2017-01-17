package com.example.abhinav_pc.foodstuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.abhinav_pc.foodstuff.Network.ApiServices;
import com.example.abhinav_pc.foodstuff.Network.GPSTracker;
import com.example.abhinav_pc.foodstuff.Network.RestaurantResponce;
import com.example.abhinav_pc.foodstuff.Network.URLconstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NearByFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    RecyclerView mRecyclerView;
    RestaurantAdapterRecycler restaurantAdapter;
    ArrayList<restaurants> restrauntList;

    public NearByFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NearByFragment newInstance(int param1) {
        NearByFragment fragment = new NearByFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nearby,container,false);
        mRecyclerView=(RecyclerView) view.findViewById(R.id.NearbyRestaurantList);
        restrauntList=new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(restaurantAdapter);
        GPSTracker gpsTracker = new GPSTracker(getContext());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLconstants.Base_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices service = retrofit.create(ApiServices.class);
        Call<RestaurantResponce> call=service.getRestraunts("city",gpsTracker.getLatitude(), gpsTracker.getLongitude(),"rating");
        call.enqueue(new Callback<RestaurantResponce>() {
            @Override
            public void onResponse(Call<RestaurantResponce> call, Response<RestaurantResponce> response) {
                ArrayList<restaurants> Temp=response.body().getRestraunts();
                if(Temp==null){
                    return;
                }
                else {
                    for(restaurants i :Temp){
                        NearByFragment.this.restrauntList.add(i);
                    }
                    restaurantAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponce> call, Throwable t) {
            }
        });
        restaurantAdapter= new RestaurantAdapterRecycler(getActivity(),restrauntList);
        mRecyclerView.setAdapter(restaurantAdapter);



        return  view;
    }
}
