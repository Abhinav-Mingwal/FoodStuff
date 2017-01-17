package com.example.abhinav_pc.foodstuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.abhinav_pc.foodstuff.Network.ApiServices;
import com.example.abhinav_pc.foodstuff.Network.URLconstants;
import java.util.ArrayList;
import com.example.abhinav_pc.foodstuff.Network.collectionResponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Collection_fragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    RecyclerView mRecyclerView;
    CollectionAdapterRecycler collectionAdapter;
    ArrayList<Collection> collectionArrayList;


    public Collection_fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Collection_fragment newInstance(int param1) {
        Collection_fragment fragment = new Collection_fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collection_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.Collection_RecyclerView);
        collectionArrayList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(collectionAdapter);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLconstants.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices service = retrofit.create(ApiServices.class);
        Call<collectionResponce> call = service.getCollection(1);
        call.enqueue(new Callback<collectionResponce>() {
            @Override
            public void onResponse(Call<collectionResponce> call, Response<collectionResponce> response) {
                ArrayList<Collection> collection= response.body().getCollections();
                if(collection==null){
                    return;
                }
                for(Collection b:collection){
                    collectionArrayList.add(b);
                }
                collectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<collectionResponce> call, Throwable t) {

            }
        });
        collectionAdapter = new CollectionAdapterRecycler(getActivity(), collectionArrayList);
        mRecyclerView.setAdapter(collectionAdapter);

        return v;

    }


}
