package com.simplistq.tastaz.loginModule.Fragment.Fragment;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.simplistq.tastaz.R;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.ViewPagerAdapter;
import com.simplistq.tastaz.loginModule.Fragment.RecommendedFragment;
import com.simplistq.tastaz.loginModule.Fragment.TopreviewFragment;
import com.simplistq.tastaz.loginModule.Fragment.widgets.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment {
    private TabLayout tabLayout;

    private ViewPager viewPager;
    PopularFragment popularFragment;
    RecommendedFragment recommendedFragment;
    TopreviewFragment topreviewFragment;
    OrderHistoryFragment orderHistoryFragment;
    NotifyFragment notifyFragment;
    ProfileFragment profileFragment;


    private boolean mSearchViewAdded = false;
    private MaterialSearchView mSearchView;
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

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
       // ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);




        //create multiple titles, but use OneFragment() for every new tab






        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        categoryurl();

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        // Tab ViewPager setting
       // viewPager.setOffscreenPageLimit(mFragmentList.size());
       // tabLayout.setupWithViewPager(viewPager);


        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
       // tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

       // tabLayout.setTabMode(com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE);
        tabLayout.setSmoothScrollingEnabled(true);
        tabLayout.setupWithViewPager(viewPager);



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
                                tabLayout.addTab(tabLayout.newTab().setText(dataobj.getString("name")));
                            }
                             setupViewPager(viewPager);
                             tabLayout.setupWithViewPager(viewPager);
                             // Tab ViewPager setting
                             viewPager.setOffscreenPageLimit(3);
                             tabLayout.setupWithViewPager(viewPager);

                             tabLayout.setupWithViewPager(viewPager);


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
        recommendedFragment=new RecommendedFragment();
        topreviewFragment=new TopreviewFragment();
        orderHistoryFragment = new OrderHistoryFragment();
        notifyFragment =new NotifyFragment();
        profileFragment =new ProfileFragment();
        adapter.addFragment(popularFragment,"Popular");
        //adapter.addFragment(recommendedFragment,"Recommended");
        //adapter.addFragment(topreviewFragment,"Top review");
        adapter.addFragment(orderHistoryFragment," History");
        adapter.addFragment(notifyFragment,"Notify");
        adapter.addFragment(profileFragment,"Profile");
        viewPager.setAdapter(adapter);
    }


}