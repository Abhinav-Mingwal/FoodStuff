package com.example.abhinav_pc.foodstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhinav_pc.foodstuff.Network.ApiServices;
import com.example.abhinav_pc.foodstuff.Network.RestaurantDetailsOnClick;
import com.example.abhinav_pc.foodstuff.Network.URLconstants;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantScreen extends AppCompatActivity {
    TextView address, locality, city, name, aggregate_rating, rating_text, rating_color, votes, cuisines,
            average_cost_for_two, price_range, user_rating, menu_url;
    ImageView thumb;
    Button BookTable, Visit_Us, photos_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_screen);
        final Intent intent = getIntent();
        final String res_id = intent.getStringExtra("res_id");
        Visit_Us = (Button) findViewById(R.id.Zomato_Link_screen);
        name = (TextView) findViewById(R.id.restraunt_name_screen);
        thumb = (ImageView) findViewById(R.id.restaurant_thumbID_screen);
        address = (TextView) findViewById(R.id.restaurant_address_screen);
        locality = (TextView) findViewById(R.id.restaurant_Locality_screen);
        city = (TextView) findViewById(R.id.restaurant_City_screen);
        aggregate_rating = (TextView) findViewById(R.id.aggregate_screen);
        rating_text = (TextView) findViewById(R.id.rating_Text_screen);
        votes = (TextView) findViewById(R.id.votes_screen);
        cuisines = (TextView) findViewById(R.id.restaurant_cuisines_screen);
        average_cost_for_two = (TextView) findViewById(R.id.restaurant_average_cost_for_two_screen);
        price_range = (TextView) findViewById(R.id.restaurant_price_range_screen);
        menu_url = (Button) findViewById(R.id.Menu_URL);
        BookTable = (Button) findViewById(R.id.BookButton);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLconstants.Base_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices service = retrofit.create(ApiServices.class);
        Call<RestaurantDetailsOnClick> call = service.getRestaurantDetails(res_id);
        call.enqueue(new Callback<RestaurantDetailsOnClick>() {
            @Override
            public void onResponse(Call<RestaurantDetailsOnClick> call, Response<RestaurantDetailsOnClick> response) {
                final RestaurantDetailsOnClick currentRestaurantDetailsOnClick = response.body();
                Log.i("ABCD", response.code() + " COde");
                name.setText(currentRestaurantDetailsOnClick.name);
                if (currentRestaurantDetailsOnClick.thumb != null)
                    Picasso.with(RestaurantScreen.this).load(currentRestaurantDetailsOnClick.thumb).resize(1080, 450).into(thumb);
                address.setText("Address : "+currentRestaurantDetailsOnClick.location.address);
                locality.setText("Locality : "+currentRestaurantDetailsOnClick.location.locality);
                city.setText("City : "+currentRestaurantDetailsOnClick.location.city);
                aggregate_rating.setText("Aggregate : "+currentRestaurantDetailsOnClick.user_rating.aggregate_rating);
                rating_text.setText("Rating  : "+currentRestaurantDetailsOnClick.user_rating.rating_text);
                votes.setText("Votes : "+currentRestaurantDetailsOnClick.user_rating.votes);
                cuisines.setText("Cuisines : "+currentRestaurantDetailsOnClick.cuisines);
                average_cost_for_two.setText("Average cost for two : "+currentRestaurantDetailsOnClick.average_cost_for_two+"");
                price_range.setText("Price Range : "+currentRestaurantDetailsOnClick.price_range+"");
                menu_url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(RestaurantScreen.this,WebLinkView.class);
                        intent1.putExtra("link",currentRestaurantDetailsOnClick.menu_url);
                        startActivity(intent1);
                    }
                });
                BookTable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(RestaurantScreen.this,WebLinkView.class);
                        intent1.putExtra("link",currentRestaurantDetailsOnClick.book_url);
                        startActivity(intent1);
                    }
                });
                Visit_Us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(RestaurantScreen.this,WebLinkView.class);
                        intent1.putExtra("link",currentRestaurantDetailsOnClick.url);
                        startActivity(intent1);
                    }
                });


            }

            @Override
            public void onFailure(Call<RestaurantDetailsOnClick> call, Throwable t) {

            }
        });
    }
}
