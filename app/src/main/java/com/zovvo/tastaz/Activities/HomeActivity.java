package com.zovvo.tastaz.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.zovvo.tastaz.LocationUtil.PermissionUtils;
import com.zovvo.tastaz.MyLocationUsingHelper;
import com.zovvo.tastaz.MyLocationUsingLocationAPI;
import com.zovvo.tastaz.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.btnLocation)
    Button btnProceed;
    @BindView(R.id.txt_loc)
    TextView loc;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.rlPickLocation)
    RelativeLayout rlPick;

    SharedPreferences pref;

    // LogCat tag
    private static final String TAG = MyLocationUsingHelper.class.getSimpleName();

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;

    private Location mLastLocation;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;

    double latitude;
    double longitude;

    // list of permissions

    ArrayList<String> permissions=new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tx = (TextView)findViewById(R.id.tx_name);
        Typeface custom_font_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        tx.setTypeface(custom_font_text);

        /*    For showing the name in other activty*/

        pref = getSharedPreferences("Registration", 0);
        // retrieving value from Registration
        String name = pref.getString("name", null);
        // Now set these value into textview of second activity
        tx.setText("Hii... "+name);
        /*    For showing the name in other activty*/


        TextView pick = (TextView) findViewById(R.id.textView);
        Typeface pick_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        pick.setTypeface(pick_text);


        TextView here = (TextView) findViewById(R.id.tvEmpty);
        Typeface here_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        here.setTypeface(here_text);


        TextView address= (TextView) findViewById(R.id.tvAddress);
        Typeface address_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        address.setTypeface(address_text);

        TextView loc= (TextView) findViewById(R.id.txt_loc);
        Typeface loc_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        loc.setTypeface(loc_text);



        Button guest = (Button) findViewById(R.id.btnLocation);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        guest.setTypeface(custom_font_guest);




    }



}
