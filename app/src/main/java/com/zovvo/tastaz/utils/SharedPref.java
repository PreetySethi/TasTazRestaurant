package com.zovvo.tastaz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class SharedPref {
    SharedPreferences sharedPreferences;

    private static final String APP_KEY = "app_name";
    private static final String access_token = "access_token";
    private static final String id = "id";
    private static final String name ="name";
    private static final String contact = "contact";




    public static void Saveaccess_token(String ctoken, Context context) {

        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(access_token, ctoken);
        editor.apply();
    }

    public static String getaccess_token(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(access_token, "");
    }


    public static void Saveid(String cid, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(id, cid);
        editor.apply();
    }

    public static String getid(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(id, "");
    }


    public static void Savename(String cname, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, cname);
        editor.apply();
    }

    public static String getname(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(name, "");
    }


    public static void Savecontact(String ccontact, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(contact, ccontact);
        editor.apply();
    }

    public static String getcontact(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(contact, "");
    }


}
