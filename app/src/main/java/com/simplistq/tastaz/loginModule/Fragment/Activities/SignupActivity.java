   package com.simplistq.tastaz.loginModule.Fragment.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
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
import com.mikhaellopez.circularimageview.CircularImageView;
import com.simplistq.tastaz.loginModule.Fragment.Adapter.VolleySingleton;
import com.simplistq.tastaz.MyLocationUsingLocationAPI;
import com.simplistq.tastaz.R;
import com.simplistq.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

   public class SignupActivity extends AppCompatActivity {

       private CircularImageView btn_photo_upload;
       EditText name,email,password, con_password,contact;
       TextView title;
       String getId;
       private static String register_url = "http://dashboard.tas-taz.com/app/user";
       private static String login_url = "http://dashboard.tas-taz.com/app/login";
       private Bitmap bitmap;
       SharedPreferences pref;
       SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

                 /*Shared Preference with storing and showing data*/

        /*    For showing the name in other activty*/
                            pref = getSharedPreferences("Registration", 0);
                            // get editor to edit in file
                            editor = pref.edit();

                            SharedPreferences.Editor editor = pref.edit();
                            editor.commit();
        /*    For showing the name in other activty*/


        title = (TextView) findViewById(R.id.Title);
        Typeface texttitle = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        title.setTypeface(texttitle);

        name = (EditText) findViewById(R.id.et_user);
        Typeface text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(text);

         email = (EditText) findViewById(R.id.et_email);
        Typeface text_email = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        email.setTypeface(text_email);

         password = (EditText) findViewById(R.id.et_password);
        Typeface text_password = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        password.setTypeface(text_password);

        con_password=(EditText) findViewById(R.id.et_con_password);
        Typeface text_con = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        con_password.setTypeface(text_con);

         contact = (EditText) findViewById(R.id.et_mob);
        Typeface text_mob = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        contact.setTypeface(text_mob);

        Button guest = (Button) findViewById(R.id.submit_request);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        guest.setTypeface(custom_font_guest);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();
                validate();
                RegisterWithVolley();
            }
        });


        btn_photo_upload = (CircularImageView) findViewById(R.id.img_profile);


        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
    }

       @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);

           if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
               Uri filePath = data.getData();
               try {

                   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                   btn_photo_upload.setImageBitmap(bitmap);

               } catch (IOException e) {
                   e.printStackTrace();
               }

               UploadPicture(getId, getStringImage(bitmap));

           }
       }

       private void UploadPicture(final String id, final String photo) {

           final ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Uploading...");
           progressDialog.show();

          /* StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                   new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           progressDialog.dismiss();
                           Log.i("", response.toString());
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               String success = jsonObject.getString("success");

                               if (success.equals("1")){
                                   Toast.makeText(SignupActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                               }

                           } catch (JSONException e) {
                               e.printStackTrace();
                               progressDialog.dismiss();
                               Toast.makeText(SignupActivity.this, "Try Again!"+e.toString(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   },
                   new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           progressDialog.dismiss();
                           Toast.makeText(SignupActivity.this, "Try Again!" + error.toString(), Toast.LENGTH_SHORT).show();
                       }
                   })
           {
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> params = new HashMap<>();
                   params.put(" cus_id", id);
                   params.put("photo", photo);

                   return params;
               }
           };

           RequestQueue requestQueue = Volley.newRequestQueue(this);
           requestQueue.add(stringRequest);*/


       }

       public String getStringImage(Bitmap bitmap){

           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

           byte[] imageByteArray = byteArrayOutputStream.toByteArray();
           String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

           return encodedImage;
       }
       private void chooseFile(){
           Intent intent = new Intent();
           intent.setType("image/*");
           intent.setAction(Intent.ACTION_GET_CONTENT);
           startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
       }


       private void inputValidation() {
           String str_name = name.getText().toString();
           String str_email = email.getText().toString();
           String str_contact = contact.getText().toString();
           String str_password = password.getText().toString();
           String str_conpassword = con_password.getText().toString();



           if (TextUtils.isEmpty(str_name)) {
               name.setError("Please enter your Name");
               name.requestFocus();
               return;
           }

           if (TextUtils.isEmpty(str_email)) {
               email.setError("Please enter your email");
               email.requestFocus();
               return;
           }
           if (TextUtils.isEmpty(str_contact)) {
               contact.setError("Please enter your contact no");
               contact.requestFocus();
               return;
           }
           if (TextUtils.isEmpty(str_password)) {
               password.setError("Please enter a password");
               password.requestFocus();
               return;
           }
           if (str_password.length()<8) {
               password.setError("password should be 8 characters");
               password.requestFocus();
               return;
           }


           if(TextUtils.isEmpty(str_conpassword))
           {
               con_password.setError("Please confirm password");
               con_password.requestFocus();


           }
       }
       private boolean validate() {
           boolean temp = true;
           String pass = password.getText().toString();
           String cpass = con_password.getText().toString();
           if (!pass.equals(cpass)) {
               Toast.makeText(SignupActivity.this, "Password Not matching", Toast.LENGTH_SHORT).show();
               temp = false;
           }
           return temp;
       }


       private void RegisterWithVolley(){


           final String name = this.name.getText().toString();
           final String password = this.email.getText().toString();
           final String email = this.password.getText().toString();
           final String contact = this.contact.getText().toString();

           editor = pref.edit();
           editor.putString("name", name);
           editor.putString("contact", contact);
           editor.putString("email",email);
           editor.commit();


           StringRequest request = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {
                       Log.d("Success", response.toString());
                       VolleyLog.v("Response:%n %s", response.toString());
                      Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                       Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                       parseData(response);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   Log.i("checkresponse", "onResponse: "+response);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   VolleyLog.e("Error: ", error.getMessage());
                   Toast.makeText(SignupActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                   Log.e("checkerror", "onErrorResponse: " + error.getMessage());
               }
           }) {
               @Override
               protected Map<String,String> getParams(){
                   Map<String,String> params = new HashMap<String, String>();
                   params.put("name",name);
                   params.put("email",email);
                   params.put("password",password);
                   params.put("contact",contact);
                   return params;
               }
               @Override
               public String getBodyContentType() {
                   return "application/json; charset=utf-8";
               }
               @Override
               public byte[] getBody() throws AuthFailureError {
                   return getSignupData().toString().getBytes();
               }


           };
           VolleySingleton.getInstance(this).addToRequestQueue(request);
       }
       private JSONObject getSignupData() {
           JSONObject data = new JSONObject();
           try {
               data.putOpt("name", name.getText().toString());
               data.putOpt("password", con_password.getText().toString());
               data.putOpt("email", email.getText().toString());
               data.putOpt("contact", contact.getText().toString());


              /* SharedPreferences pref = getSharedPreferences("id", Context.MODE_PRIVATE);
               SharedPreferences.Editor mEditor=pref.edit();
               mEditor.putString("id", "");
               mEditor.commit();*/

           } catch (JSONException e) {
               e.printStackTrace();
           }
           return data;
       }
       private void parseData(String response) throws JSONException {

           JSONObject jsonObject = new JSONObject(response);
           if (jsonObject.optString("status").equals("true")){
               String cusid =jsonObject.optJSONObject("data").optString("id");
               String cusname =jsonObject.optJSONObject("data").optString("name");
               String cuscontact =jsonObject.optJSONObject("data").optString("contact");
               String cusemail =jsonObject.optJSONObject("data").optString("email");


               if (!cusid.isEmpty() && !cusname.isEmpty() && !cuscontact.isEmpty() &&!cusemail.isEmpty()) {

                   SharedPref.Saveid(cusid, SignupActivity.this);
                   SharedPref.Savename(cusname, SignupActivity.this);
                   SharedPref.Savecontact(cuscontact, SignupActivity.this);
                   SharedPref.Saveemail(cuscontact, SignupActivity.this);

               }
               Login();
               Toast.makeText(SignupActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(SignupActivity.this,MyLocationUsingLocationAPI.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               this.finish();
           }else {

               Toast.makeText(SignupActivity.this, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
           }
       }

       private void Login(){
           final String email = this.email.getText().toString();
           final String password = this.password.getText().toString();

           StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   //  rQueue.getCache().clear();
                   //JSONObject jsonObject = null;

                   try {
                       Log.d("Success", response.toString());
                       VolleyLog.v("Response:%n %s", response.toString());
                        Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                       Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                       JSONObject jsonObject = new JSONObject(response);
                           String token = jsonObject.optString("access_token");


                           if (!token.isEmpty()) {

                               SharedPref.Saveaccess_token(token, SignupActivity.this);

                           }


                   }catch (Exception e) {
                       e.printStackTrace();
                   }
                   Toast.makeText(SignupActivity.this,response,Toast.LENGTH_LONG).show();
                        Toast.makeText(SignupActivity.this,response.toString(),Toast.LENGTH_LONG).show();


                   Log.i("checkLogin", "onResponse: "+response);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Toast.makeText(SignupActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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
                   String access_token = (String) SharedPref.getaccess_token(SignupActivity.this);

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
               data.putOpt("email",email .getText().toString());
               data.putOpt("password", password.getText().toString());

           } catch (JSONException e) {
               e.printStackTrace();
           }
           return data;
       }


          /* else
           {
               loadingBar.setTitle("Creating New Account");
               loadingBar.setMessage("Please wait while we are creating account for you.");
               loadingBar.show();

               String email = null;
               String password = null;

               mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
               {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task)
                   {
                       if(task.isSuccessful())
                       {
                           Toast.makeText(Signup.this, "You have successfully signed up", Toast.LENGTH_SHORT).show();
                           Intent mainIntent = new Intent(Signup.this, MainActivity.class);
                           mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(mainIntent);
                           finish();
                       }
                       else
                       {
                           Toast.makeText(Signup.this, "Error occured, please try again.", Toast.LENGTH_SHORT).show();
                       }
                       loadingBar.dismiss();
                   }
               });
           }
       }**/



     /**  JSONObject parameters = new JSONObject();
    try {
           parameters.put("key", "value");
       } catch (Exception e) {
       }
       JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, yourUrl, parameters,new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               Log.i("onResponse", response.toString());
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("onErrorResponse", error.toString());
           }
       }) {
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String, String> headers = new HashMap<>();
               // Basic Authentication
               //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

               headers.put("Authorization", "Bearer " + accesstoken);
               return headers;
           }
       };
    queue.add(request);*/



}
