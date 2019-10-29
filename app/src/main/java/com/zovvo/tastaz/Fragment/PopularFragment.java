package com.zovvo.tastaz.Fragment;



import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.zovvo.tastaz.Activities.CartActivity;
import com.zovvo.tastaz.Adapter.ProductAdapter;
import com.zovvo.tastaz.utils.Converter;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.R;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment implements ProductAdapter.CallBackUs, ProductAdapter.HomeCallBack {
    //List<Menu> menus = new ArrayList<>();
    public static ArrayList<Menu> menus = new ArrayList<>();
    public static int cart_count = 0;
    ProductAdapter productAdapter;
    RecyclerView productRecyclerView;

   /* private RecyclerView precyclerView;
    private MenuAdapter pAdapter;*/


    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);


        populatePizzaDetails();
        productAdapter = new ProductAdapter(getContext(),this,menus);
        productRecyclerView = view.findViewById(R.id.product_recycler_view);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        Switch pick = (Switch) view.findViewById(R.id.switchveg);
        pick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // Log.v("Switch State=", " only Vegeterian items will show"+isChecked);
                if (isChecked) {
                    pick.setText("Only Vegeterian items");  //To change the text near to switch
                    Log.d("You are :", "Checked");
                } else {
                    pick.setText("Vegeterian and Non-vegeterian items");  //To change the text near to switch
                    Log.d("You are :", "Checked");
                }
            }

        });


        Typeface pick_text = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        pick.setTypeface(pick_text);
         return view;

    }


    private void populatePizzaDetails() {
        Menu pizza = new Menu("Deluxe Veggie","fresh vegetables",R.drawable.lollipop,"24");
        menus.add(pizza);
        pizza = new Menu("Farm House","spicy spices",R.drawable.foodtwo,"18");
        menus.add(pizza);
        pizza = new Menu("Peppy Paneer","sorted begies",R.drawable.foodthre,"25");
        menus.add(pizza);
        pizza = new Menu("Mexican  Wave","mushroom slices",R.drawable.imagefour,"16");
        menus.add(pizza);
        pizza = new Menu("Chicken Fiesta","boiled and spicy",R.drawable.bbq,"30");
        menus.add(pizza);
        pizza = new Menu("Chicken Delight","pieces and golden",R.drawable.salmonfish,"20");
        menus.add(pizza);
        pizza = new Menu("Chicken Dominator","veggies",R.drawable.foodseven,"25");
        menus.add(pizza);
        pizza = new Menu("Non-Veg Supreme","vegetable",R.drawable.foodeight,"55");
        menus.add(pizza);
    }

    @Override
    public void addCartItemView() {
        //addItemToCartMethod();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(getActivity(), cart_count, R.drawable.ic_shopping_cart_white_24dp));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        switch (id) {
            case R.id.cart_action:
                if (cart_count < 1) {
                    Toast.makeText(getActivity(), "there is no item in cart", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), CartActivity.class));
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void updateCartCount(Context context) {
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().invalidateOptionsMenu();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ProductAdapter.menus.clear();
    }

}
