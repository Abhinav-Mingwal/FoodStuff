package com.example.abhinav_pc.foodstuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 13-Nov-16.
 */

public class CollectionAdapterRecycler extends RecyclerView.Adapter<CollectionAdapterRecycler.MyViewHolder> {
    Context mContext;
    ArrayList<Collection> mCollection;

    CollectionAdapterRecycler(Context context,ArrayList<Collection> object){
        this.mContext=context;
        this.mCollection=object;
        Log.i("ABCD","   "+"constructor");

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("ABCD","   "+"onBind");
        final Collection currentCollection =mCollection.get(position);
        Picasso.with(mContext).load(currentCollection.collection.image_url).resize(1080,450).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,WebLinkView.class);
                intent.putExtra("link",currentCollection.collection.url);
                mContext.startActivity(intent);
            }
        });
        holder.title.setText(currentCollection.collection.title);
        holder.description.setText(currentCollection.collection.description);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("ABCD","   "+"myViewHolder");
            image =(ImageView)itemView.findViewById(R.id.collection_image);
            title = (TextView)itemView.findViewById(R.id.collection_title);
            description = (TextView)itemView.findViewById(R.id.collection_description);

        }
    }

}
