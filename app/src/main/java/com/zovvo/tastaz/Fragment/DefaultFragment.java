package com.zovvo.tastaz.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.Adapter.ViewPagerAdapter;
import com.zovvo.tastaz.interfaces.onSearchListener;
import com.zovvo.tastaz.interfaces.onSimpleSearchActionsListener;
import com.zovvo.tastaz.utils.Util;
import com.zovvo.tastaz.widgets.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    public DefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
       // ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);







        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        tabLayout.setTabMode(com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE);
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
                             ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), jsonObject);
                             viewPager.setAdapter(adapter);

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


    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/NevisBold-KGwl.ttf"));
                }
            }
        }
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
        adapter.addFragment(recommendedFragment,"Recommended");
        adapter.addFragment(topreviewFragment,"Top review");
        adapter.addFragment(orderHistoryFragment,"Tab one");
        adapter.addFragment(notifyFragment,"Tab two");
        adapter.addFragment(profileFragment,"Tab three");
        viewPager.setAdapter(adapter);
    }


}
