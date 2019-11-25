package com.zovvo.tastaz.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zovvo.tastaz.Activities.MainActivity;
import com.zovvo.tastaz.Activities.MapsActivity;
import com.zovvo.tastaz.Adapter.HistoryAdapter;
import com.zovvo.tastaz.Adapter.VolleySingleton;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.Model.History;
import com.zovvo.tastaz.utils.SharedPref;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {
    List<History> historyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter hAdapter;


    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_popular, container, false);
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rview_historyorder);
        hAdapter = new HistoryAdapter(historyList, getContext());

        TextView name = (TextView) view.findViewById(R.id.title_history);
        Typeface pick_text = Typeface.createFromAsset(getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hAdapter);

        //populatePizzaDetails();
        parse(view);
        hAdapter.notifyDataSetChanged();
        return view;
    }



   /* private void parsehistory(View view) {

        String url = "http://192.168.8.180/tas-taz/public/api/order";

        String id  = (String) SharedPref.getid(getContext());
        String access_token = (String) SharedPref.getaccess_token(getContext());
        String accesstoken =access_token;



        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

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
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
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
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(request);
    }


    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {
            Toast.makeText(getContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }*/


    public void parse(View view) {
        String url = "http://dashboard.tas-taz.com/api/order";
        String id  = (String) SharedPref.getid(getContext());
        String access_token = (String) SharedPref.getaccess_token(getContext());
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

                        History n= new History(order_id,created_at);
                        historyList.add(n);
                        parseadapter();
                        recyclerView.setAdapter(hAdapter);


                    }
                } catch (Exception e) {
                   // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                      //  Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                       // Log.e("checkerror", "onErrorResponse: " + error.getMessage());
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
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);

    }
    public void parseadapter()
    {
        hAdapter = new HistoryAdapter(historyList,getContext());
    }



   /* private void populatePizzaDetails() {
        History history = new History("Order ID: Or4565","Date : 25/02/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45ff65","Date : 18/10/2019");
        historyList.add(history);
        history = new History("Or.
        der ID: Or-456455","Date : 19/08/2019");
        historyList.add(history);
        history = new History("Order ID: Or-4777565","Date : 31/05/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45hjgj65","Date : 11/06/2019");
        historyList.add(history);
        history = new History("Order ID: Or-4523g65","Date : 13/02/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45g4565","Date : 22/05/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45645335","Date : 52/02/2019");
        historyList.add(history);
    }*/

}
