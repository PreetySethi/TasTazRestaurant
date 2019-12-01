package com.simplistq.tastaz.loginModule.Fragment.Fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplistq.tastaz.loginModule.Fragment.Adapter.MenuAdapter;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.ProductAdapter;
import com.simplistq.tastaz.loginModule.Fragment.Model.favourite;
import com.simplistq.tastaz.R;

import java.util.ArrayList;
import java.util.List;

import static com.simplistq.tastaz.loginModule.Fragment.Activities.CartActivity.temparraylist;
import static com.simplistq.tastaz.loginModule.Fragment.Adapter.ProductAdapter.fav;
import static com.simplistq.tastaz.loginModule.Fragment.Adapter.ProductAdapter.favs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment   {
    List<favourite> menus = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter pAdapter;


    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        TextView name = (TextView) view.findViewById(R.id.title_favourite);
        Typeface pick_text = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(pick_text);


        recyclerView = (RecyclerView) view.findViewById(R.id.favourite_recycler_view);
//        temparraylist.addAll(cartModels);
        pAdapter = new MenuAdapter(favs, getContext());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        pAdapter.notifyDataSetChanged();
        for (int i = 0; i < favs.size(); i++) {
            for (int j = i + 1; j < favs.size(); j++) {
                if (favs.get(i).getName()==(favs.get(j).getName())) {
                    favs.remove(j);
                    j--;
                    Log.d("remove", String.valueOf(favs.size()));

                }
            }

        }

        return view;




    }

}
