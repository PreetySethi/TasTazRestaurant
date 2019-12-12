package com.zovvo.tastaz.loginModule.Fragment.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Utils {

    public static Fragment getFragment(FragmentManager manager, Class fragmentclass) {
        Fragment fragment = manager.findFragmentByTag(fragmentclass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = (Fragment) fragmentclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

}
