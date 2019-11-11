package com.zovvo.tastaz.Activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.zovvo.tastaz.Adapter.VolleySingleton;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    AutocompleteSupportFragment autocompleteFragment;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    TextView lon, lat, active_location, resutText, name, contact;
    private static final int REQUEST_LOCATION = 1;
    List<Place.Field> placeField = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
    private static String update_url = "http://192.168.8.180/tas-taz/public/api/user";
    //String id ="";



    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        TextView tx = (TextView) findViewById(R.id.txt_cus_name);
        TextView txt = (TextView) findViewById(R.id.txt_cus_contact);

        pref = getSharedPreferences("Registration", 0);

        String cname = pref.getString("name", null);
        String ccontact = pref.getString("contact", null);

        // Now set these value into textview of second activity
        tx.setText(cname);
        txt.setText(ccontact);

        // requestPermission();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        configureCameraIdle();
        fetchLastLocation();

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        initSearchList();


        resutText = (TextView) findViewById(R.id.dragg_result);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button save = (Button) findViewById(R.id.save_add);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/NevisBold-KGwl.ttf");
        save.setTypeface(custom_font);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterLocWithVolley();
            }
        });

        EditText address = (EditText) findViewById(R.id.et_address);
        Typeface address_font = Typeface.createFromAsset(getAssets(), "fonts/NevisBold-KGwl.ttf");
        address.setTypeface(address_font);

        TextView result = (TextView) findViewById(R.id.dragg_result);
        Typeface loc_text = Typeface.createFromAsset(getAssets(), "fonts/NevisBold-KGwl.ttf");
        result.setTypeface(loc_text);
        lat = (TextView) findViewById(R.id.txt_cus_latitude);
        lon = (TextView) findViewById(R.id.txt_cus_longitude);
        active_location = (TextView) findViewById(R.id.txt_cus_location);
        name = (TextView) findViewById(R.id.txt_cus_name);
        contact = (TextView) findViewById(R.id.txt_cus_contact);

    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(MapsActivity.this, currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       // LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
       /* MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I AM Here");
        //  googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        mMap.setOnCameraIdleListener(onCameraIdleListener);*/


    }

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(MapsActivity.this);

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String city = addressList.get(0).getAdminArea();
                        //String area = addressList.get(0).getSubAdminArea();
                        //String country = addressList.get(0).setCountryName();
                        if (!locality.isEmpty())
                            resutText.setText(locality);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private void initSearchList() {
        //Initialize Places. For simplicity, the API key is hard-coded.
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getResources().getString(R.string.places_api_key));
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        ImageView searchIcon = (ImageView) ((LinearLayout) autocompleteFragment.getView()).getChildAt(0);
        // Set the desired icon
        searchIcon.setImageDrawable(getResources().getDrawable(R.drawable.search));


        autocompleteFragment.getView().setBackgroundResource(R.drawable.map_bg);
        autocompleteFragment.setHint("Search Here");
        autocompleteFragment.setCountry("QA");
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsActivity.this, "TYPE YOUR DESIRED LOCATION", Toast.LENGTH_SHORT).show();
            }
        });

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                final LatLng latLng = place.getLatLng();
                // mMap.addMarker(new MarkerOptions().position(place.getLatLng()));
                //  mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                if (latLng != null) {
                    // TODO: Get info about the selected place.
                    Log.i("placeApi", "Place: " + place.getName() + ", " + place.getId());
                    // Toast.makeText(CustomerRegisterActivity.this, "" + place.getName()+","+place.getId()+","+place.getLatLng(), Toast.LENGTH_SHORT).show();
                    lat.setText(""+latLng.latitude);
                    lon.setText(""+latLng.longitude);
//                    active_location.setText(place.getName());

                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("placeApi", "An error occurred: " + status);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                    configureCameraIdle();
                }
                break;
        }
    }

    //For Autocomplete Fragment//
    private void RegisterLocWithVolley() {

        final String lat = this.lat.getText().toString();
        final String lon = this.lon.getText().toString();
        final String activelocation = this.active_location.getText().toString();
        final String name = this.name.getText().toString();
        final String contact = this.contact.getText().toString();
        SharedPreferences prefs = getSharedPreferences("id", Context.MODE_PRIVATE);
        String id  = prefs.getString("id", null);
        String urlID = update_url+"/"+id;


        StringRequest request = new StringRequest(Request.Method.PUT, urlID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(MapsActivity.this, response, Toast.LENGTH_LONG).show();
                    Toast.makeText(MapsActivity.this, response.toString(), Toast.LENGTH_LONG).show();

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
                Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("lat", lat);
                params.put("lon", lon);
                params.put("name", name);
                params.put("contact", contact);
                params.put("language", "en");
                params.put("address", "");
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
                String authid = (String) SharedPref.getUSER_auth(MapsActivity.this);
               // headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Accept", "application/json");
              // headers.put("access_token", authid);
                headers.put("Authorization", "Bearer " +authid);
                return headers;
            }


        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();
        try {

            data.putOpt("language", "en");
            data.putOpt("lat", lat);
            data.putOpt("lon",lon);
            data.putOpt("name",name);
            data.putOpt("contact",contact);
            data.putOpt("address", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {
            Toast.makeText(MapsActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

            Toast.makeText(MapsActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
        }
    }

}
