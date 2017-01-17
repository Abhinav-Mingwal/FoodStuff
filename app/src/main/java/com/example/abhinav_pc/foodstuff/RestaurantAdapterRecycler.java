package com.example.abhinav_pc.foodstuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 13-Nov-16.
 */

public class RestaurantAdapterRecycler extends RecyclerView.Adapter<RestaurantAdapterRecycler.myViewHolder> {
    Context mContext;
    ArrayList<restaurants> mRestaurants;

    public RestaurantAdapterRecycler(Context context, ArrayList<restaurants> objects) {
        mContext = context;
        mRestaurants = objects;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_layout, parent, false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        final restaurants currentRestaurant = mRestaurants.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ABCD", "   " + position);
                Log.i("ABCD", "    " + currentRestaurant.detail.restraunt_id);
                Intent intent = new Intent(mContext, RestaurantScreen.class);
                intent.putExtra("res_id", currentRestaurant.detail.restraunt_id);
                mContext.startActivity(intent);

            }
        });
        holder.restraunt_name.setText(currentRestaurant.detail.restraunt_name);
        if (!currentRestaurant.detail.restaurant_thumbID.isEmpty()) {
            Picasso.with(mContext).load(currentRestaurant.detail.restaurant_thumbID).resize(1080, 450).into(holder.restaurant_thumbID);
        }
        holder.restaurant_Locality.setText("Near " + currentRestaurant.detail.location.restaurant_Locality);
        holder.VisitUS.setText("Visit Us");
        holder.VisitUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebLinkView.class);
                intent.putExtra("link", currentRestaurant.detail.Zomato_Link);
                mContext.startActivity(intent);
            }
        });
        holder.Address.setText("Address : " + currentRestaurant.detail.location.restaurant_address);
        holder.City.setText("City : " + currentRestaurant.detail.location.restaurant_City);
        holder.cuisines.setText("Cuisines : " + currentRestaurant.detail.restaurant_cuisines);
        holder.votes.setText("Rating : " + currentRestaurant.detail.user_rating.aggregate);


    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView restraunt_name, restaurant_Locality, Address, City, cuisines, votes;
        ImageView restaurant_thumbID;
        Button VisitUS;


        public myViewHolder(View itemView) {
            super(itemView);
            restraunt_name = (TextView) itemView.findViewById(R.id.restraunt_name);
            restaurant_thumbID = (ImageView) itemView.findViewById(R.id.restaurant_thumbID);
            restaurant_Locality = (TextView) itemView.findViewById(R.id.restaurant_Locality);
            VisitUS = (Button) itemView.findViewById(R.id.Zomato_Link);
            Address = (TextView) itemView.findViewById(R.id.restaurant_address);
            City = (TextView) itemView.findViewById(R.id.restaurant_City);
            cuisines = (TextView) itemView.findViewById(R.id.restaurant_cuisines);
            votes = (TextView) itemView.findViewById(R.id.votes);

        }
    }


}
