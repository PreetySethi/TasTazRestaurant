package com.simplistq.tastaz.loginModule.Fragment.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


public class SharedPref {
    SharedPreferences sharedPreferences;

    private static final String APP_KEY = "app_name";
    private static final String access_token = "access_token";
    private static final String id = "id";
    private static final String name ="name";
    private static final String contact = "contact";
    private static final String email="email";
    private static final String lat="lat";
    private static final String lon="lon";
    private static final String invoice_prefix="invoice_prefix";
    private static final String branch_id="branch_id";
    private static final String model="model";
    private static final String product_id="product_id";
    private static final String shipping_id="shipping_address_id";
    private static final String price="price";
    private static final String total="total";
    private SharedPreferences app_prefs;
    private final String INTRO = "intro";


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


    public static void Saveemail(String cemail, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(email, cemail);
        editor.apply();
    }

    public static String getemail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(email, "");
    }

    public static void Savelat(String clat, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(lat, clat);
        editor.apply();
    }

    public static String getlat(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(lat, "");
    }

    public static void Savelon(String clon, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(lon, clon);
        editor.apply();
    }

    public static String getlon(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(lon, "");
    }

    public static void Saveinvoice_prefix(String cinvoice_prefix, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(invoice_prefix, cinvoice_prefix);
        editor.apply();
    }

    public static String getinvoice_prefix(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(invoice_prefix, "");
    }


    public static void Savebranch_id(String cbranch_id, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(branch_id, cbranch_id);
        editor.apply();
    }

    public static String getbranch_id(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(branch_id, "");
    }

    public static void Savemodel(String cmodel, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(model, cmodel);
        editor.apply();
    }

    public static String getmodel(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(model, "");
    }

    public static void Saveproduct_id(String cproduct_id, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(product_id, cproduct_id);
        editor.apply();
    }

    public static String getproduct_id(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(product_id, "");
    }
    public static void Saveshipping_id(String cshipping_id, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(shipping_id, cshipping_id);
        editor.apply();
    }

    public static String getshipping_id(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(shipping_id, "");
    }

    public static void Saveprice(String cprice, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(price, cprice);
        editor.apply();
    }

    public static String getprice(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(price, "");
    }

    public static void Savetotal(String ctotal, Context context) {

        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(total, ctotal);
        editor.apply();
    }

    public static String gettotal(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, MODE_PRIVATE);
        return preferences.getString(total, "");
    }
    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, true);
    }




}
