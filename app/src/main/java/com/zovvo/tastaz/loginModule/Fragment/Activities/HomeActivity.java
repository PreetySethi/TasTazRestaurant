package com.zovvo.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.zovvo.tastaz.loginModule.Fragment.LocationUtil.PermissionUtils;
import com.zovvo.tastaz.MyLocationUsingHelper;
import com.zovvo.tastaz.R;

import java.util.ArrayList;

import butterknife.BindView;


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

    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}



