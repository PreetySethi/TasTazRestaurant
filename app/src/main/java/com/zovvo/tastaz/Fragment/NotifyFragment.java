package com.zovvo.tastaz.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zovvo.tastaz.Adapter.NotifyAdapter;
import com.zovvo.tastaz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifyFragment extends Fragment {


    public NotifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false);



        private void retrieveJSON() {

            showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("strrrrr", ">>" + response);

                            try {

                                JSONObject obj = new JSONObject(response);
                                if(obj.optString("status").equals("true")){

                                    dataModelArrayList = new ArrayList<>();
                                    JSONArray dataArray  = obj.getJSONArray("data");

                                    for (int i = 0; i < dataArray.length(); i++) {

                                        DataModel playerModel = new DataModel();
                                        JSONObject dataobj = dataArray.getJSONObject(i);

                                        playerModel.setName(dataobj.getString("name"));
                                        playerModel.setCountry(dataobj.getString("country"));
                                        playerModel.setCity(dataobj.getString("city"));
                                        //  playerModel.setImgURL(dataobj.getString("imgURL"));

                                        dataModelArrayList.add(playerModel);

                                    }

                                    setupListview();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            // request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            requestQueue.add(stringRequest);


        }

        private void setupListview(){
            removeSimpleProgressDialog();  //will remove progress dialog
            listAdapter = new NotifyAdapter(this, dataModelArrayList);
            listView.setAdapter(listAdapter);
        }
    }

}
