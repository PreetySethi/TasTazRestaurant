package com.zovvo.tastaz.Activities;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.zovvo.tastaz.Fragment.DefaultFragment;
import com.zovvo.tastaz.Fragment.FavouriteFragment;
import com.zovvo.tastaz.Fragment.NotifyFragment;
import com.zovvo.tastaz.Fragment.OrderHistoryFragment;

import com.zovvo.tastaz.Fragment.ProfileFragment;
import com.zovvo.tastaz.interfaces.onSearchListener;
import com.zovvo.tastaz.interfaces.onSimpleSearchActionsListener;
import com.zovvo.tastaz.utils.Util;
import com.zovvo.tastaz.utils.Utils;

import com.zovvo.tastaz.R;
import com.zovvo.tastaz.widgets.MaterialSearchView;


public class MainActivity extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener {

    private TabLayout tabLayout;
    private Toolbar Toolbar;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    BottomNavigationView bottomNavigationView;
    FrameLayout viewlayout;


    FavouriteFragment favouriteFragment = new FavouriteFragment();
    NotifyFragment notifyFragment = new NotifyFragment();
    OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    DefaultFragment defaultFragment= new DefaultFragment();


    private boolean mSearchViewAdded = false;
    private MaterialSearchView mSearchView;
    private WindowManager mWindowManager;
    private Toolbar mToolbar;
    private MenuItem searchItem;
    private boolean searchActive = false;
    private  FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(mToolbar);

       // fab = (FloatingActionButton) findViewById(R.id.fab);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mSearchView = new MaterialSearchView(this);
        mSearchView.setOnSearchListener(this);
        mSearchView.setSearchResultsListener(this);
        mSearchView.setHintText("Search");

        if (mToolbar != null) {
            // Delay adding SearchView until Toolbar has finished loading
            mToolbar.post(new Runnable() {
                @Override
                public void run() {
                    if (!mSearchViewAdded && mWindowManager != null) {
                        mWindowManager.addView(mSearchView,
                                MaterialSearchView.getSearchViewLayoutParams(MainActivity.this));
                        mSearchViewAdded = true;
                    }
                }
            });
        }



        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        setupNavigationView();


        Toolbar = (Toolbar) findViewById(R.id.toolbar);
       // getSupportActionBar().setTitle("Home");
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
                displayFragment(DefaultFragment.class);
                break;
            case R.id.action_info:
                displayFragment(FavouriteFragment.class);
                break;
            case R.id.action_msg:
                displayFragment(NotifyFragment.class);
                break;
            case R.id.action_profile:
                displayFragment(ProfileFragment.class);
                break;
            case R.id.action_history:
                displayFragment(OrderHistoryFragment.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        searchItem = menu.findItem(R.id.search);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mSearchView.display();
                openKeyboard();
                return true;
            }
        });
        if(searchActive)
            mSearchView.display();
        return true;

    }
    private void openKeyboard(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
            }
        }, 200);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearch(String query) {

    }

    @Override
    public void searchViewOpened() {

    }

    @Override
    public void searchViewClosed() {

    }

    @Override
    public void onCancelSearch() {

    }

    @Override
    public void onItemClicked(String item) {

    }

    @Override
    public void onScroll() {

    }

    @Override
    public void error(String localizedMessage) {

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
