package com.example.abhinav_pc.foodstuff;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.abhinav_pc.foodstuff.Network.ApiServices;
import com.example.abhinav_pc.foodstuff.Network.CategoryResponce;
import com.example.abhinav_pc.foodstuff.Network.URLconstants;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Category_fragment extends Fragment {



    ListView listView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;

//    private OnFragmentInteractionListener mListener;

    public Category_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Category_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Category_fragment newInstance(int param1) {
        Category_fragment fragment = new Category_fragment();
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
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        listView = (ListView) v.findViewById(R.id.Category_list_view);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLconstants.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices service = retrofit.create(ApiServices.class);
        Call<CategoryResponce> call = service.getCategory();
        call.enqueue(new Callback<CategoryResponce>() {
            @Override
            public void onResponse(Call<CategoryResponce> call, Response<CategoryResponce> response) {
                ArrayList<Category> category = response.body().getCategories();
                if (category == null)
                    return;
                for (Category b : category) {
                    Category_fragment.this.categoryList.add(b);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoryResponce> call, Throwable t) {

            }
        });
        listView.setAdapter(categoryAdapter);
        return v;

    }

}
