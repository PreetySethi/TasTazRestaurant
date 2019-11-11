package com.zovvo.tastaz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zovvo.tastaz.Adapter.CartAdapter;
import com.zovvo.tastaz.Fragment.PopularFragment;
import com.zovvo.tastaz.Model.ProductImage;
import com.zovvo.tastaz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.zovvo.tastaz.Adapter.ProductAdapter.cartModels;

public class CartActivity extends AppCompatActivity {
    public static TextView grandTotal;
    public static int grandTotalplus;
    // create a temp list and add cartitem list
    public static ArrayList<ProductImage> temparraylist;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    LinearLayout proceedToBook;
    Context context;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        context = this;
        temparraylist = new ArrayList<>();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        proceedToBook = findViewById(R.id.proceed_to_book);
        grandTotal = findViewById(R.id.grand_total_cart);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");

        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // these lines of code for show the same  cart for future refrence
                grandTotalplus = 0;
                for (int i = 0; i < temparraylist.size(); i++) {

                }
                cartModels.addAll(temparraylist);
                PopularFragment.cart_count = (temparraylist.size());
//                addItemInCart.clear();
                finish();
            }
        });
        PopularFragment.cart_count = 0;

        //addInCart();

        Log.d("sizecart_1", String.valueOf(temparraylist.size()));
        Log.d("sizecart_2", String.valueOf(cartModels.size()));

        // from these lines of code we remove the duplicacy of cart and set last added quantity in cart
        // for replace same item
        for (int i = 0; i < cartModels.size(); i++) {
            for (int j = i + 1; j < cartModels.size(); j++) {
                if (cartModels.get(i).getProductImage().equals(cartModels.get(j).getProductImage())) {
                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                    cartModels.remove(j);
                    j--;
                    Log.d("remove", String.valueOf(cartModels.size()));

                }
            }

        }
        temparraylist.addAll(cartModels);
        cartModels.clear();
        Log.d("sizecart_11", String.valueOf(temparraylist.size()));
        Log.d("sizecart_22", String.valueOf(cartModels.size()));
        // this code is for get total cash
        for (int i = 0; i < temparraylist.size(); i++) {
            grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
        }
        grandTotal .setText(String.valueOf(grandTotalplus) );
        cartRecyclerView = findViewById(R.id.recycler_view_cart);
        cartAdapter = new CartAdapter(temparraylist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setAdapter(cartAdapter);


        proceedToBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "order placed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartActivity.this, OrderConfirmationActivity.class);
                startActivity(intent);
                //bookMyOrder();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        grandTotalplus = 0;
        for (int i = 0; i < temparraylist.size(); i++) {
            PopularFragment.cart_count = (temparraylist.size());

        }
        cartModels.addAll(temparraylist);
        cartModels.clear();
    }


   /* private String getItems()
    {
        JSONObject dataObj = new JSONObject();
        try
        {
            dataObj.putOpt("lat", "");
            dataObj.putOpt("long","");
            dataObj.putOpt("deviceID", "");
            dataObj.putOpt( "date", "");

            JSONArray cartItemsArray = new JSONArray();
            JSONObject cartItemsObjedct;
            for (int i = 0; i < temparraylist.size(); i++)
            {
                cartItemsObjedct = new JSONObject();
                cartItemsObjedct.putOpt("ProductID", temparraylist.get(i)
                        .getId);
                cartItemsObjedct.putOpt("ProductQuantity",temparraylist.get(i).getProductQuantity);
                cartItemsArray.put(cartItemsObjedct);
            }

            dataObj.put("cartItems", cartItemsArray);

        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataObj.toString();
    }*/

}
