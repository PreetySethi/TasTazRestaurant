package com.zovvo.tastaz.guestModule;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestDefaultFragment extends Fragment {
    private boolean mSearchViewAdded = false;

    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    GuestPopularFragment popularFragment;
    GuestBookTableFragment guestBookTableFragment;
    GuestroomdiningFragment guestroomdiningFragment;



    private final List<Fragment> mFragmentList = new ArrayList<>();
    //Title List
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    String myData = "";


    public GuestDefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_guest_default, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.guestviewpager);
        //categoryurl();

        tabLayout = (TabLayout) view.findViewById(R.id.guesttablayout);
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

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        popularFragment=new GuestPopularFragment();
        guestBookTableFragment= new GuestBookTableFragment();
        guestroomdiningFragment = new GuestroomdiningFragment();

        adapter.addFragment(popularFragment,"POPULAR");
        adapter.addFragment(guestBookTableFragment,"BOOK A TABLE");
        adapter.addFragment(guestroomdiningFragment,"IN ROOM DINING");

        viewPager.setAdapter(adapter);
    }

}
