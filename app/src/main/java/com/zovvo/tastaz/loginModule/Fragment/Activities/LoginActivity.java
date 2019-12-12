package com.zovvo.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.zovvo.tastaz.guestModule.MainGuestActivity;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.VolleySingleton;
import com.zovvo.tastaz.MyLocationUsingLocationAPI;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String login_url = "http://dashboard.tas-taz.com/app/login";
    String user_url = "http://dashboard.tas-taz.com/api/user";

    EditText email, password;
    private SharedPref sharedpref;
    ProgressBar loading;
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


         email = (EditText) findViewById(R.id.et_username);
        Typeface user_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        email.setTypeface(user_font);

         password = (EditText) findViewById(R.id.et_password);
        Typeface password_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        password.setTypeface(password_font);

        TextView loginuser = (TextView)findViewById(R.id.textlogin);
        Typeface custom_font_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        loginuser.setTypeface(custom_font_text);

        /*TextView forgot = (TextView)findViewById(R.id.textlogin);
        Typeface forgot_font_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        forgot.setTypeface(forgot_font_text);*/

        TextView txguest = (TextView)findViewById(R.id.guestlogin);
        Typeface custom_font_gtext = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        txguest.setTypeface(custom_font_gtext);

        Button login = (Button) findViewById(R.id.btn_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        login.setTypeface(custom_font);

        Button guest = (Button) findViewById(R.id.btn_guest);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        guest.setTypeface(custom_font_guest);
        loading =(ProgressBar) findViewById(R.id.progressBar);

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
                loading.setVisibility(View.VISIBLE);
                Login();
            }
        });

    }






    private void Login(){
        final String email = this.email.getText().toString();
        final String password = this.password.getText().toString();
        //String id  = (String) SharedPref.getid(LoginActivity.this);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  rQueue.getCache().clear();
                //JSONObject jsonObject = null;

                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                   // Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                   // Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                    JSONObject jsonObject = new JSONObject(response);
                    String token = jsonObject.optString("access_token");


                    if (!token.isEmpty()) {

                        SharedPref.Saveaccess_token(token, LoginActivity.this);


                    }


                    parseData(response);

                }catch (Exception e) {
                    e.printStackTrace();
                }
              //  Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
               // Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();


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
                headers.put("Accept" ,"application/json");
                //headers.put("access_token", access_token);
                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void user(){
        StringRequest request = new StringRequest(Request.Method.GET, user_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  rQueue.getCache().clear();
                //JSONObject jsonObject = null;

                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                    Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                    JSONObject jsonObject = new JSONObject(response);
                    String cid = jsonObject.optString("id");


                    if (!cid.isEmpty()) {

                        SharedPref.Saveid(cid, LoginActivity.this);

                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }
                // Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                //    Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();


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
                headers.put("Accept" ,"application/json");
                headers.put("access_token", access_token);
                return headers;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {
            data.putOpt("email",email .getText().toString());
            data.putOpt("password", password.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("access_token").equals("false")){

            Toast.makeText(LoginActivity.this, "Please check your credentials !", Toast.LENGTH_SHORT).show();

        }else {
            //Toast.makeText(LoginActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MyLocationUsingLocationAPI.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
    }

}
