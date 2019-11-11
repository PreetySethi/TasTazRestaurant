package com.zovvo.tastaz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;


public class SharedPref {
    SharedPreferences sharedPreferences;
    public static final String NAME = "NAME";

    private static final String APP_KEY = "app_name";
    private static final String USER_auth = "access_token";


    private static final String id = "id";

    public static void SaveUSER_auth(String auth, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_auth, auth);
        editor.apply();
    }

    public static String getUSER_auth(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, Activity.MODE_PRIVATE);
        return preferences.getString(USER_auth, "");
    }


    public static void Saveid(String auth, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(id, id);
        editor.apply();
    }


}
