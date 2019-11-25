package com.zovvo.tastaz.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zovvo.tastaz.Activities.CartActivity;
import com.zovvo.tastaz.Adapter.MenuAdapter;
import com.zovvo.tastaz.Adapter.ProductAdapter;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.Model.favourite;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.utils.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {
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
        Typeface pick_text = Typeface.createFromAsset(getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);


        recyclerView = (RecyclerView) view.findViewById(R.id.favourite_recycler_view);
        pAdapter = new MenuAdapter(menus, getContext());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        populatePizzaDetails();
        pAdapter.notifyDataSetChanged();
        return view;
    }
   private void populatePizzaDetails() {
       favourite pizza = new favourite("34","Chicken Lollipop"," juicy",R.drawable.foodeight);
        menus.add(pizza);
        pizza = new favourite("56","veg biryani","vegetables",R.drawable.foodone);
        menus.add(pizza);
        pizza = new favourite("45","Chicken delight","chicken",R.drawable.foodseven);
        menus.add(pizza);
    }

}
