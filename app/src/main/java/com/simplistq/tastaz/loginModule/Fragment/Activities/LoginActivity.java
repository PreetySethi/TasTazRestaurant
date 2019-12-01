package com.simplistq.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.simplistq.tastaz.guestModule.MainGuestActivity;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.VolleySingleton;
import com.simplistq.tastaz.MyLocationUsingLocationAPI;
import com.simplistq.tastaz.R;
import com.simplistq.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String login_url = "http://dashboard.tas-taz.com/app/login";
    EditText user, password;
    private SharedPref sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* if(sharedpref.getIsLogin()){
            Intent intent = new Intent(LoginActivity.this,MyLocationUsingLocationAPI.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }*/


         user = (EditText) findViewById(R.id.et_username);
        Typeface user_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        user.setTypeface(user_font);

         password = (EditText) findViewById(R.id.et_password);
        Typeface password_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        password.setTypeface(password_font);

        TextView loginuser = (TextView)findViewById(R.id.textlogin);
        Typeface custom_font_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        loginuser.setTypeface(custom_font_text);

        TextView forgot = (TextView)findViewById(R.id.textlogin);
        Typeface forgot_font_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        forgot.setTypeface(forgot_font_text);

        TextView txguest = (TextView)findViewById(R.id.guestlogin);
        Typeface custom_font_gtext = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        txguest.setTypeface(custom_font_gtext);

        Button login = (Button) findViewById(R.id.btn_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        login.setTypeface(custom_font);

        Button guest = (Button) findViewById(R.id.btn_guest);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        guest.setTypeface(custom_font_guest);

        txguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainGuestActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

 /*private void Loginh(){
         String email = user.getText().toString().trim();
         String passwords = password .getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  rQueue.getCache().clear();
                Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                try {
                    parseData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("checkLogin", "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.e("checkLoginError", "onErrorResponse: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",passwords);

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return getLoginData().toString().getBytes();
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                String authid = (String) SharedPref.getUSER_auth(CustomerLoginActivity.this);
                headers.put("Content-Type", "application/json");
                headers.put("auth_token", authid);
                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();
        try {
            data.putOpt("email",user .getText().toString());
            data.putOpt("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }



    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")){
            Toast.makeText(LoginActivity.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MyLocationUsingLocationAPI.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }else {

            // Toast.makeText(CustomerLoginActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
        }
    }*/




    private void Login(){
        final String password = this.password.getText().toString();
        final String email = this.user.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  rQueue.getCache().clear();
                //JSONObject jsonObject = null;

                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    // Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                    //Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                    JSONObject jsonObject = new JSONObject(response);
                    String token = jsonObject.optString("access_token");


                    if (!token.isEmpty()) {

                        SharedPref.Saveaccess_token(token, LoginActivity.this);

                    }
                    //sharedpref.putIsLogin(true);
                    Toast.makeText(LoginActivity.this, "Loggedin Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MyLocationUsingLocationAPI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                }catch (Exception e) {
                    e.printStackTrace();
                }
                //Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                // Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();


                Log.i("checkLogin", "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.e("checkLoginError", "onErrorResponse: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return getLoginData().toString().getBytes();
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                String access_token = (String) SharedPref.getaccess_token(LoginActivity.this);

                headers.put("Content-Type", "application/json");
                //headers.put("access_token", access_token);
                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {
            data.putOpt("email",user .getText().toString());
            data.putOpt("password", password.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}
