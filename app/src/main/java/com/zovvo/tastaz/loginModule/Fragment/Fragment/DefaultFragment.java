package com.zovvo.tastaz.loginModule.Fragment.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.ViewPagerAdapter;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.icu.util.Calendar.getInstance;
import static com.android.volley.VolleyLog.TAG;

public class DefaultFragment extends Fragment {
    private TabLayout tabayout;

    private ViewPager viewPager;
    PopularFragment popularFragment;
    BookTableFragment bookTableFragment;
    RoomDiningFragment roomDiningFragment;
    OrderHistoryFragment orderHistoryFragment;
    NotifyFragment notifyFragment;
    ProfileFragment profileFragment;
    SharedPref sharedpref;


    private boolean mSearchViewAdded = false;
   // private MaterialSearchView mSearchView;
    private WindowManager mWindowManager;
    private Toolbar mToolbar;
    private MenuItem searchItem;
    private boolean searchActive = false;
    private FloatingActionButton fab;


    private final List<Fragment> mFragmentList = new ArrayList<>();
    //Title List
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    String myData = "";


    public DefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);

        TextView name = (TextView) view.findViewById(R.id.cusname);
        Typeface name_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(name_text);

        TextView timenow = (TextView) view.findViewById(R.id.todaydate);
        Typeface timenow_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        timenow.setTypeface(timenow_text);

        TextView carttotal = (TextView) view.findViewById(R.id.txt_carttotal);
        Typeface carttotal_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        carttotal.setTypeface(carttotal_text);

        String totalprice = sharedpref.gettotal(getContext());
        carttotal.setText(totalprice);

       mToolbar = (Toolbar) view.findViewById(R.id.toolbar);




       // Date currentTime = Calendar.getInstance().getTime();
       // timenow.setText(currentTime.toString());





               // ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);




        //create multiple titles, but use OneFragment() for every new tab






        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        categoryurl();



        tabayout = (TabLayout) view.findViewById(R.id.tablayout);


        tabayout.setupWithViewPager(viewPager);
        // Tab ViewPager setting
       // viewPager.setOffscreenPageLimit(mFragmentList.size());
       // tabLayout.setupWithViewPager(viewPager);


        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
       // tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

       // tabLayout.setTabMode(com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE);
        tabayout.setSmoothScrollingEnabled(true);
        tabayout.setupWithViewPager(viewPager);




        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        setupViewPager(viewPager);
        return view;
    }


   /* public void setTablayoutItemsMode(TabLayout tabLayout, List<Category> categories) {
        if (categories.size() > 3)
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }

    }*/

    private void categoryurl() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "/public/api/category", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                         {
                            JSONArray dataArray = jsonObject.getJSONArray("");
                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject dataobj = dataArray.getJSONObject(i);
                                tabayout.addTab(tabayout.newTab().setText(dataobj.getString("name")));
                            }
                             setupViewPager(viewPager);
                             tabayout.setupWithViewPager(viewPager);
                             // Tab ViewPager setting
                             viewPager.setOffscreenPageLimit(3);
                             tabayout.setupWithViewPager(viewPager);

                             tabayout.setupWithViewPager(viewPager);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        popularFragment=new PopularFragment();
        bookTableFragment=new BookTableFragment();
        roomDiningFragment=new RoomDiningFragment();
        orderHistoryFragment = new OrderHistoryFragment();
        notifyFragment =new NotifyFragment();
        profileFragment =new ProfileFragment();
        adapter.addFragment(popularFragment,"POPULAR");
        adapter.addFragment(bookTableFragment,"BOOK A TABLE");
        adapter.addFragment(roomDiningFragment,"IN ROOM DINING");
       /* adapter.addFragment(orderHistoryFragment," History");
        adapter.addFragment(notifyFragment,"Notify");
        adapter.addFragment(profileFragment,"Profile");*/
        viewPager.setAdapter(adapter);
    }


}
