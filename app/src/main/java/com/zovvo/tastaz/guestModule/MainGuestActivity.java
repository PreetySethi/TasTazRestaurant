package com.zovvo.tastaz.guestModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.utils.Utils;

public class MainGuestActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private androidx.appcompat.widget.Toolbar Toolbar;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    BottomNavigationView bottomNavigationView;
    FrameLayout viewlayout;


    GuestFavouriteFragment favouriteFragment = new GuestFavouriteFragment();
    GuestNotifyFragment notifyFragment = new GuestNotifyFragment();
    GuestDefaultFragment defaultFragment= new GuestDefaultFragment();


    private boolean mSearchViewAdded = false;

    private Toolbar mToolbar;
    private MenuItem searchItem;
    private boolean searchActive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guest);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(mToolbar);

        // fab = (FloatingActionButton) findViewById(R.id.fab);




        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        setupNavigationView();


        Toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    private void setupNavigationView() {
        BottomNavigationView mNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        if (mNavigationView != null) {

            // By Default : First item is to be selected,displaying item respective fragment
            Menu menu = mNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            mNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }


    // Perform action when any item is selected.
    protected void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.action_home:
                displayFragment(GuestDefaultFragment.class);
                break;
            case R.id.action_info:
                displayFragment(GuestFavouriteFragment.class);
                break;
            case R.id.action_msg:
                displayFragment(GuestNotifyFragment.class);
                break;
            case R.id.action_profile:
                displayFragment(GuestProfileFragment.class);
                break;
            case R.id.action_history:
                displayFragment(GuestOrderHistoryFragment.class);
                break;

        }
    }


    public void displayFragment(Class fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = Utils.getFragment(fm, fragment);
        if (!frag.isAdded()) {
            ft.add(R.id.viewlayout, frag, fragment.getSimpleName());
        } else {
            ft.replace(R.id.viewlayout, Utils.getFragment(fm, fragment));
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
