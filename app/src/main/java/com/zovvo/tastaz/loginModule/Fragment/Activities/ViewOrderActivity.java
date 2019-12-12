package com.zovvo.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.historypopupAdapter;
import com.zovvo.tastaz.loginModule.Fragment.Model.OrderPopup;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewOrderActivity extends AppCompatActivity {

    List<OrderPopup> oDetails = new ArrayList<>();
    private RecyclerView recyclerView;
    private historypopupAdapter hOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        recyclerView = (RecyclerView) findViewById(R.id.rview_history);
        hOrderAdapter = new historypopupAdapter(oDetails,this);

        // Create grids with 2 items in a row
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hOrderAdapter);

        populateOrderDetails();
        hOrderAdapter.notifyDataSetChanged();

    }

    private void populateOrderDetails() {
        OrderPopup orderPopup = new OrderPopup("Chicken Fiesta","R.drawable.foodeight","7777 2222 2222","22/10/2019","11:09 AM", "ABDULLAH","CASH ON DELIVERY");
        oDetails.add(orderPopup);
    }
    public void parse() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        String url = "http://dashboard.tas-taz.com/api/order";
        String id  = (String) SharedPref.getid(this);
        String access_token = (String) SharedPref.getaccess_token(this);
        String accesstoken =access_token;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray xc = object.getJSONArray("data");  // data array

                    for (int i = 0; i < xc.length(); i++) {
                        JSONObject jsonObject = xc.getJSONObject(i);
                        JSONArray files = jsonObject.getJSONArray("product"); // product array
                        JSONObject Jsonfilename = files.getJSONObject(0);
                        String order_id = Jsonfilename.getString("order_id");
                        String created_at =Jsonfilename.getString("created_at");
                        // JSONObject Jsondelname = xc.getJSONObject("0");//delivery object
                        //String deliveryboy = Jsondelname.getString("name");

                       // OrderPopup n= new OrderPopup(order_id,created_at);
                       // oDetails.add(n);
                        parseadapter();
                        recyclerView.setAdapter(hOrderAdapter);


                    }
                } catch (Exception e) {
                    Toast.makeText(ViewOrderActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                        Toast.makeText(ViewOrderActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("checkerror", "onErrorResponse: " + error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization","Bearer " + accesstoken);
                return headers;

            }
        };
        Volley.newRequestQueue(this.getApplicationContext()).add(stringRequest);

    }
    public void parseadapter()
    {
        hOrderAdapter = new historypopupAdapter(oDetails,this);
    }


}
