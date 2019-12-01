package com.simplistq.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.CartAdapter;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.VolleySingleton;
import com.simplistq.tastaz.loginModule.Fragment.Fragment.PopularFragment;
import com.simplistq.tastaz.loginModule.Fragment.Model.ProductImage;
import com.simplistq.tastaz.R;
import com.simplistq.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.simplistq.tastaz.loginModule.Fragment.Adapter.ProductAdapter.cartModels;

public class CartActivity extends AppCompatActivity {
    public static TextView grandTotal;
    public static int grandTotalplus;
    // create a temp list and add cartitem list
    public static ArrayList<ProductImage> temparraylist;
    RecyclerView cartRecyclerView;
    TextView lon, lat, price, email, name, contact,model,productid,title;
    EditText address1,address2,city;
    CartAdapter cartAdapter;
    LinearLayout proceedToBook;
    Context context;
    private Toolbar mToolbar;
    SharedPreferences.Editor editor;
    private static String cart_url = "http://192.168.8.108/tas-taz/public/api/order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        SharedPreferences pref;
        SharedPreferences.Editor editor;

        pref = getSharedPreferences("Registration", 0);
        // retrieving value from Registration
        String prices = pref.getString("price", null);
        String clon= pref.getString("lon", null);
        String clat= pref.getString("lat", null);
        // Now set these value into textview of second activity

         address1 = (EditText) findViewById(R.id.addressone);
        Typeface pick_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        address1.setTypeface(pick_text);

        title = (TextView) findViewById(R.id.title_cart);
        Typeface title_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        title.setTypeface(title_text);

         address2 = (EditText) findViewById(R.id.addressone);
        Typeface pick_text2 = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        address2.setTypeface(pick_text2);

        city = (EditText) findViewById(R.id.txt_city);
        Typeface pick_city = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        city.setTypeface(pick_city);


        String cname = (String) SharedPref.getname(CartActivity.this);
        String ccontact = (String) SharedPref.getcontact(CartActivity.this);
        String cemail = (String) SharedPref.getemail(CartActivity.this);
        String cmodel = (String) SharedPref.getmodel(CartActivity.this);
        String cproductid = (String) SharedPref.getproduct_id(CartActivity.this);

        context = this;
        temparraylist = new ArrayList<>();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        proceedToBook = findViewById(R.id.proceed_to_book);
        grandTotal = findViewById(R.id.grand_total_cart);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");

        name = (TextView) findViewById(R.id.txtcus_name);
        contact = (TextView) findViewById(R.id.txtcus_contact);
        email= (TextView) findViewById(R.id.txtcus_email);
        price=(TextView) findViewById(R.id.txtprice);
        lat= (TextView)findViewById(R.id.txtlat);
        lon=(TextView) findViewById(R.id.txtlon);
        model=(TextView)findViewById(R.id.txtmodel);
        productid=(TextView)findViewById(R.id.txtproductid);
        name.setText(cname);
        contact.setText(ccontact);
        email.setText(cemail);
        price.setText(prices);
        lat.setText(clat);
        lon.setText(clon);
        model.setText(cmodel);
        productid.setText(cproductid);


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
                if (cartModels.get(i).getProductName()==(cartModels.get(j).getProductName())) {
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
        grandTotal.setText( String.valueOf(grandTotalplus));

       // final String total = this.grandTotal.getText().toString();
      //  SharedPref.Savetotal(total, CartActivity.this);


        cartRecyclerView = findViewById(R.id.recycler_view_cart);
        cartAdapter = new CartAdapter(temparraylist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setAdapter(cartAdapter);


        proceedToBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(CartActivity.this, "order placed", Toast.LENGTH_SHORT).show();
                //bookMyOrder();
                CartItems();
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
        //cartModels.clear();
    }


  /* private String getItems()
    {
        JSONObject dataObj = new JSONObject();
        try
        {
            dataObj.putOpt("price", "");
            dataObj.putOpt("name","");
            dataObj.putOpt("model", "");
            dataObj.putOpt( "quantity", "");
            dataObj.putOpt( "product_id", "");

            JSONArray cartItemsArray = new JSONArray();
            JSONObject cartItemsObjedct;
            for (int i = 0; i < temparraylist.size(); i++)
            {
                cartItemsObjedct = new JSONObject();
                cartItemsObjedct.putOpt("price", cartModels.get(i).getProductPrice());
                cartItemsObjedct.putOpt("name",cartModels.get(i).getProductName());
                cartItemsObjedct.putOpt("model",model.getText().toString());
                cartItemsObjedct.putOpt("quantity",cartModels.get(i).getProductName());
                cartItemsObjedct.putOpt("product_id",productid.getText().toString());
                cartItemsArray.put(cartItemsObjedct);
            }

            dataObj.put("cart", cartItemsArray);

        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataObj.toString();
    }*/


   /* private void CartItems() {

        /* String invoice_prefix = this.lat.getText().toString();
         String branch_id = this.lon.getText().toString();


        String cname = (String) SharedPref.getname(CartActivity.this);
        String ccontact = (String) SharedPref.getcontact(CartActivity.this);
        String email = (String) SharedPref.getcontact(CartActivity.this);

        String address1 = (String) SharedPref.getcontact(CartActivity.this);
        String address2 = (String) SharedPref.getcontact(CartActivity.this);
        String city = (String) SharedPref.getcontact(CartActivity.this);


        String lat = (String) SharedPref.getcontact(CartActivity.this);
        String lon = (String) SharedPref.getcontact(CartActivity.this);
        String total = (String) SharedPref.getcontact(CartActivity.this);

        String id  = (String) SharedPref.getid(CartActivity.this);
        String access_token = (String) SharedPref.getaccess_token(CartActivity.this);
        String accesstoken =access_token;



        StringRequest request = new StringRequest(Request.Method.POST, cart_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(CartActivity.this, response, Toast.LENGTH_LONG).show();
                    Toast.makeText(CartActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                    parseData(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("checkresponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(CartActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("customer_name", "");
                params.put("contact", "");
                params.put("invoice_prefix", "");
                params.put("branch_id", "");
                params.put("shipping_address_id", "0");
                params.put("city", "");
                params.put("email", "");
                params.put("address1", "");
                params.put("address2","");
                params.put("lat","");
                params.put("lon","");
                params.put("total","");
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return getLoginData().toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization","Bearer " + accesstoken);
                return headers;

            }


        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {

            data.putOpt("customer_name","Preety");
            data.putOpt("contact","54565456");
            data.putOpt("invoice_prefix", "1");
            data.putOpt("branch_id", "1");
            data.putOpt("city","Doha");
            data.putOpt("email","5445@gmail.com");
            data.putOpt("address1","hiii");
            data.putOpt("address2","hiiii");
            data.putOpt("lon","45.09");
            data.putOpt("lat", "89.09");
            data.putOpt("total", "56");
            data.putOpt("shipping_address_id", "0");


            JSONArray cartItemsArray = new JSONArray();
            JSONObject cartItemsObjedct;
            for (int i = 0; i < temparraylist.size(); i++)
            {
                cartItemsObjedct = new JSONObject();
                cartItemsObjedct.putOpt("price", "55");
                cartItemsObjedct.putOpt("name","Keema Chapati");
                cartItemsObjedct.putOpt("model","KC-001");
                cartItemsObjedct.putOpt("quantity","2");
                cartItemsObjedct.putOpt("product_id","16");
                cartItemsArray.put(cartItemsObjedct);
            }

            data.put("cart", cartItemsArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {
            Toast.makeText(CartActivity.this, "Order Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

            Toast.makeText(CartActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
        }
    }*/


    private void CartItems() {

         String invoice_prefix = this.lat.getText().toString();
         String branch_id = this.lon.getText().toString();


        String cname = (String) SharedPref.getname(CartActivity.this);
        String ccontact = (String) SharedPref.getcontact(CartActivity.this);
        String cemail = (String) SharedPref.getemail(CartActivity.this);
        String cmodel = (String) SharedPref.getmodel(CartActivity.this);
        String cproductid = (String) SharedPref.getproduct_id(CartActivity.this);

        String address1 = this.address1.getText().toString();
        String address2 = this.address2.getText().toString();
        String city = this.city.getText().toString();


        String lat = (String)SharedPref.getlat(CartActivity.this);
        String lon = (String)SharedPref.getlon(CartActivity.this);
        String total = this.grandTotal.getText().toString();

        String id  = (String) SharedPref.getid(CartActivity.this);
        String access_token = (String) SharedPref.getaccess_token(CartActivity.this);
        String accesstoken =access_token;



        StringRequest request = new StringRequest(Request.Method.POST, cart_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                   // Toast.makeText(CartActivity.this, response, Toast.LENGTH_LONG).show();
                   // Toast.makeText(CartActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                    parseData(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("checkresponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(CartActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("customer_name", cname);
                params.put("contact", ccontact);
                params.put("address1", address1);
                params.put("address2",address2);
                params.put("city", city);
                params.put("invoice_prefix", "2019_");
                params.put("branch_id", "1");
                params.put("shipping_address_id", "0");
                params.put("email", cemail);
                params.put("lat",lat);
                params.put("lon",lon);
                params.put("total",total);
                params.put("model",cmodel);
                params.put("product_id",cproductid);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return getLoginData().toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization","Bearer " + accesstoken);
                return headers;

            }


        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {

            data.putOpt("customer_name",name.getText().toString());
            data.putOpt("contact",contact.getText().toString());
            data.putOpt("city",city.getText().toString());
            data.putOpt("address1",address1.getText().toString());
            data.putOpt("address2",address2.getText().toString());
            data.putOpt("email",email.getText().toString());
            data.putOpt("total", grandTotal.getText().toString());
            data.putOpt("invoice_prefix", "2019-");
            data.putOpt("branch_id", "1");
            data.putOpt("lon",lon.getText().toString());
            data.putOpt("lat", lat.getText().toString());
            data.putOpt("shipping_address_id", "0");


            JSONArray cartItemsArray = new JSONArray();
            JSONObject cartItemsObjedct;
            for (int i = 0; i < temparraylist.size(); i++)
            {
                cartItemsObjedct = new JSONObject();
                cartItemsObjedct.putOpt("price", temparraylist.get(i).getProductPrice());
                cartItemsObjedct.putOpt("name",temparraylist.get(i).getProductName());
                cartItemsObjedct.putOpt("model",model.getText().toString());
                cartItemsObjedct.putOpt("quantity",temparraylist.get(i).getProductName());
                cartItemsObjedct.putOpt("product_id",productid.getText().toString());
                cartItemsArray.put(cartItemsObjedct);
                cartItemsArray.put(cartItemsObjedct);
            }

            data.put("cart", cartItemsArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {
            Toast.makeText(CartActivity.this, "Ordered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this, OrderConfirmationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

           // Toast.makeText(CartActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
        }
    }
}
