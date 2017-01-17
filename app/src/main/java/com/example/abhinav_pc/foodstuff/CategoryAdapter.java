package com.example.abhinav_pc.foodstuff;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by AbHiNav-PC on 16-Oct-16.
 */
public class CategoryAdapter extends ArrayAdapter<Category>{

    Context mContext;
    ArrayList<Category> mCategories;


    public CategoryAdapter(Context context, ArrayList<Category> objects) {
        super(context, 0, objects);
        this.mContext=context;
        this.mCategories=objects;
    }
    public static class ViewHolder{
        TextView category_name;
        TextView category_id;
        ViewHolder(TextView category_name,TextView category_id){
        this.category_name=category_name;
            this.category_id=category_id;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.category_list_item,null);
            TextView category_name=(TextView)convertView.findViewById(R.id.Category_Name);
            TextView category_id = (TextView)convertView.findViewById(R.id.category_id);
            ViewHolder vh = new ViewHolder(category_name,category_id);
            convertView.setTag(vh);
        }
        ViewHolder vh=(ViewHolder)convertView.getTag();
        Category currentCollection =mCategories.get(position);
        vh.category_name.setText(currentCollection.category.category_name);
        vh.category_id.setText(currentCollection.category.category_id);
        return convertView;
    }

}
