package com.zovvo.tastaz.guestModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Activities.CartActivity;
import com.zovvo.tastaz.loginModule.Fragment.Model.Menu;
import com.zovvo.tastaz.loginModule.Fragment.utils.Converter;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuestPopularFragment extends Fragment implements guestProductAdapter.CallBackUs, guestProductAdapter.HomeCallBack {
    public static ArrayList<Menu> menus = new ArrayList<>();
    public static int cart_count = 0;
    guestProductAdapter productAdapter;

    // private ArrayList<Menu> menus = new ArrayList<>();
    RecyclerView recyclerView;

    public GuestPopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest_popular, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.product_recycler_view);



        //populatePizzaDetails();
        // productAdapter = new ProductAdapter(menus,getContext(),this);
        recyclerView.setAdapter(productAdapter);
        // productAdapter.notifyDataSetChanged();

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        parse(view);


        return view;
    }

    public void parse(View view) {
        String url = "http://dashboard.tas-taz.com/api/product";
        String base="http://dashboard.tas-taz.com/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray xc = object.getJSONArray("data");  // data array

                    for (int i = 0; i < xc.length(); i++) {
                        JSONObject gg = xc.getJSONObject(i);
                        String pricety = gg.getString("price_type");
                        String price = gg.getString("price");
                        String model=gg.getString("model");
                        if (!model.isEmpty() ) {
                            SharedPref.Savemodel(model, getContext());
                        }
                        String veg =gg.getString("veg");
                        JSONObject jsonObject = xc.getJSONObject(i);
                        JSONArray files = jsonObject.getJSONArray("info"); // info array
                        JSONObject Jsonfilename = files.getJSONObject(0);
                        String name = Jsonfilename.getString("name");
                        String desc =Jsonfilename.getString("description");
                        String product_id =Jsonfilename.getString("product_id");
                        if (!product_id.isEmpty() ) {
                            SharedPref.Saveproduct_id(product_id, getContext());
                        }
                        JSONObject json = xc.getJSONObject(i);
                        JSONArray imagearray = json.getJSONArray("image");// image array
                        JSONObject Jsonimagename = imagearray.getJSONObject(0);
                        String imageurl = Jsonimagename.getString("src");
                        String imageset = (base + imageurl);
                        String imageccheck=imageset;
                        Menu n= new Menu(price,name,desc,pricety,imageccheck);
                        menus.add(n);
                        parseadapter();
                        recyclerView.setAdapter(productAdapter);


                    }
                } catch (Exception e) {
                    //  Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "please make sure internet is available", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);

    }
    public void parseadapter()
    {
        productAdapter = new guestProductAdapter(menus,getContext(),this);
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
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
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
                    Toast.makeText(getActivity(), "Please Sign Up to continue", Toast.LENGTH_SHORT).show();

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
        guestProductAdapter.menus.clear();
    }
}
