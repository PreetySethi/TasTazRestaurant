package com.zovvo.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView title_password, textpassword;
    Button passwordrequest;
    EditText et_forgotemail;
    ProgressBar loading;
    private static String reset_url = "http://dashboard.tas-taz.com/api/forget";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        title_password = (TextView) findViewById(R.id.titlepassword);
        Typeface text_title = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        title_password.setTypeface(text_title);

        textpassword = (TextView) findViewById(R.id.textpassword);
        Typeface text_password = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        textpassword.setTypeface(text_password);

        passwordrequest = (Button) findViewById(R.id.password_request);
        Typeface password_req = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        passwordrequest.setTypeface(password_req);

        et_forgotemail = (EditText) findViewById(R.id.et_forgot_email);
        Typeface forgot_password = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        et_forgotemail.setTypeface(forgot_password);

        loading =(ProgressBar) findViewById(R.id.progressBar);

        passwordrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                resetpassword();
            }
        });

    }

    private void resetpassword() {

        final String email = this.et_forgotemail.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, reset_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                   Toast.makeText(ForgotPasswordActivity.this, response, Toast.LENGTH_LONG).show();
                   Toast.makeText(ForgotPasswordActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                    parseData(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("checkresponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
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


        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {

            data.putOpt("email", et_forgotemail.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("message").equals("Success")) {
            Toast.makeText(ForgotPasswordActivity.this, "Email sent Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

            Toast.makeText(ForgotPasswordActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}
