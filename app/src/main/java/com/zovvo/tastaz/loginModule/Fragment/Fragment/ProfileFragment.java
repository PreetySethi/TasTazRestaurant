package com.zovvo.tastaz.loginModule.Fragment.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.zovvo.tastaz.loginModule.Fragment.Activities.LoginActivity;
import com.zovvo.tastaz.loginModule.Fragment.Activities.PaymentActivity;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Adapter.VolleySingleton;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static String URL_UPLOAD = "http://dashboard.tas-taz.com/api/avatar";
    private static String logout_url= "http://dashboard.tas-taz.com/api/logout";
    CircularImageView btn_photo_upload;
    private Bitmap bitmap;
    String getId;

    private final int GALLERY = 1;
    JSONObject jsonObject;
    RequestQueue rQueue;




    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String cname = (String) SharedPref.getname(getActivity());
         TextView namee = (TextView) view.findViewById(R.id.name);
        namee.setText(cname);

        TextView name = (TextView) view.findViewById(R.id.pro_account);
        Typeface pick_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(pick_text);
        name.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        TextView addres = (TextView) view.findViewById(R.id.pro_address);
        Typeface addres_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        addres.setTypeface(addres_text);

        TextView payment = (TextView) view.findViewById(R.id.pro_payment);
        Typeface payment_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        payment.setTypeface(payment_text);

        TextView profile = (TextView) view.findViewById(R.id.title_profile);
        Typeface profilet_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        profile.setTypeface(profilet_text);

        TextView logoutpro = (TextView) view.findViewById(R.id.pro_logout);
        Typeface logout_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        logoutpro.setTypeface(logout_text);

        addres.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        logoutpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parselog();
            }
        });

        TextView setting = (TextView) view.findViewById(R.id.pro_settings);
        Typeface setting_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        setting.setTypeface(setting_text);


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), PaymentActivity.class);
                // startActivity(intent);
            }
        });



        btn_photo_upload = (CircularImageView) view.findViewById(R.id.img_profile);
        requestMultiplePermissions();


        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // chooseFile();


                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, GALLERY);

            }
        });


        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    btn_photo_upload.setImageBitmap(bitmap);
                    uploadImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                btn_photo_upload.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            UploadPicture(getId, getStringImage(bitmap));

        }
    }*/

  /*  private void UploadPicture(final String id, final String avatar) {


       // String access_token = (String) SharedPref.getaccess_token(getContext());
      //  String accesstoken =access_token;

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, URL_UPLOAD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

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
                // Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("avatar", avatar);
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
                headers.put("Accept" ,"application/json");
                headers.put("Content-Type","application/x-www-form-urlencoded");
                headers.put("Authorization","Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjEwMjVkMjU5NTBmMmRiNzk2YjNhMWU0Yzk4NTA1NDRlNDhjNjVlZGRiMzYxMDE0MWUxM2VmNGNlOTU1NDI3YzRmNzJhMjRiOTdhN2E5MTFlIn0.eyJhdWQiOiIyIiwianRpIjoiMTAyNWQyNTk1MGYyZGI3OTZiM2ExZTRjOTg1MDU0NGU0OGM2NWVkZGIzNjEwMTQxZTEzZWY0Y2U5NTU0MjdjNGY3MmEyNGI5N2E3YTkxMWUiLCJpYXQiOjE1NzU0NTIwOTYsIm5iZiI6MTU3NTQ1MjA5NiwiZXhwIjoxNjA3MDc0NDk2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.uB9cBEQ4JzBNUjvipTuASPBxWKPJHk_F71aU1tJc9UDwv1Yn-zTs9LhzEWI2OICL7ExoXYFBQ6eBHFJXbl0sGUDfIUGlQJKeub6Kv-BQDqWN_VoOAtxZdvEy4EdX2tphs0e8jrQ0cmLDdsNKgFKvz5TxVuOJQ1QWXC9xnqHjq6Uv5ydqtbn_Z99uMdznAzTmKkK8KgBZgNMnWLW5ZlZwKpK6Sa5l9MdSeZA_7KjqcaIaQmuPedDEtRizxc-ntIYgVpOe-memsF_n3AGhwQ2ihW46e2ufdZ87IvnGn0wZKpNSFg45IDpdNxwZmNXpp2JHSzOlNN-CK3KjYtrNDkXUBme9psKxUrbzowkt6kdAFXt6SAg39MfXMHRNz2aA-LAtsbWsBcBvx6IBYQFlTtPahtrgmwVCgbyDjvPhZ7spbhTB48MmwaOeYlFdhd56T0kq1-5fxnXFYVXVncsNf7_Djzzi_J6upzk_z_lUH7btaV6dFCLJgSs1-4T5hYr-7iTQae2qi3-gTZFms0R_5tsxodSH9Rf_P66XrtBCBTTvrBM8V9-yVLajlNlRmRQ8HQZS-2TMkufp-Xq7RSvfrApxevbEy1yb0GIDGI3gm9J2EQG1hDZnKDWifXD72VzFAKzg4VII0BNr5BW-f4_u8BzIc-CmacmDW02zZCp21YZX63A");
                return headers;

            }

       };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);


    }*/
    private JSONObject getLoginData() {
        JSONObject data = new JSONObject();

        try {
            data.putOpt("avatar", btn_photo_upload .getImageAlpha());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

   /* public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }*/
   /* private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }*/
    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);

            String imageid =jsonObject.optJSONObject("").optString("data");

            if (!imageid.isEmpty() ) {

                SharedPref.Savedata(imageid, getContext());


            }
            Toast.makeText(getContext(), "Uploaded Successfully!", Toast.LENGTH_SHORT).show();
           /* Intent intent = new Intent(getContext(),.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/

        }
  private void uploadImage(Bitmap bitmap){

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
       String avatar = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


     // data.putOpt("avatar", btn_photo_upload .getImageAlpha());

       try {
           jsonObject = new JSONObject();
           //String imgname = String.valueOf(Calendar.getInstance().getTimeInMillis());
           //jsonObject.put("name", imgname);
           //  Log.e("Image name", etxtUpload.getText().toString().trim());
           jsonObject.put("avatar", avatar);
           // jsonObject.put("aa", "aa");
       } catch (JSONException e) {
           Log.e("JSONObject Here", e.toString());
       }
      StringRequest request = new StringRequest(Request.Method.POST, URL_UPLOAD, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              try {
                  Log.d("Success", response.toString());
                  VolleyLog.v("Response:%n %s", response.toString());
                  Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                  Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

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
              // Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
              Log.e("checkerror", "onErrorResponse: " + error.getMessage());
          }
      }) {
          @Override
          protected Map<String, String> getParams() {
              Map<String, String> params = new HashMap<String, String>();
              params.put("avatar", avatar);
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
              headers.put("Accept" ,"application/json");
              headers.put("Content-Type","application/x-www-form-urlencoded");
              headers.put("Authorization","Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjEwMjVkMjU5NTBmMmRiNzk2YjNhMWU0Yzk4NTA1NDRlNDhjNjVlZGRiMzYxMDE0MWUxM2VmNGNlOTU1NDI3YzRmNzJhMjRiOTdhN2E5MTFlIn0.eyJhdWQiOiIyIiwianRpIjoiMTAyNWQyNTk1MGYyZGI3OTZiM2ExZTRjOTg1MDU0NGU0OGM2NWVkZGIzNjEwMTQxZTEzZWY0Y2U5NTU0MjdjNGY3MmEyNGI5N2E3YTkxMWUiLCJpYXQiOjE1NzU0NTIwOTYsIm5iZiI6MTU3NTQ1MjA5NiwiZXhwIjoxNjA3MDc0NDk2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.uB9cBEQ4JzBNUjvipTuASPBxWKPJHk_F71aU1tJc9UDwv1Yn-zTs9LhzEWI2OICL7ExoXYFBQ6eBHFJXbl0sGUDfIUGlQJKeub6Kv-BQDqWN_VoOAtxZdvEy4EdX2tphs0e8jrQ0cmLDdsNKgFKvz5TxVuOJQ1QWXC9xnqHjq6Uv5ydqtbn_Z99uMdznAzTmKkK8KgBZgNMnWLW5ZlZwKpK6Sa5l9MdSeZA_7KjqcaIaQmuPedDEtRizxc-ntIYgVpOe-memsF_n3AGhwQ2ihW46e2ufdZ87IvnGn0wZKpNSFg45IDpdNxwZmNXpp2JHSzOlNN-CK3KjYtrNDkXUBme9psKxUrbzowkt6kdAFXt6SAg39MfXMHRNz2aA-LAtsbWsBcBvx6IBYQFlTtPahtrgmwVCgbyDjvPhZ7spbhTB48MmwaOeYlFdhd56T0kq1-5fxnXFYVXVncsNf7_Djzzi_J6upzk_z_lUH7btaV6dFCLJgSs1-4T5hYr-7iTQae2qi3-gTZFms0R_5tsxodSH9Rf_P66XrtBCBTTvrBM8V9-yVLajlNlRmRQ8HQZS-2TMkufp-Xq7RSvfrApxevbEy1yb0GIDGI3gm9J2EQG1hDZnKDWifXD72VzFAKzg4VII0BNr5BW-f4_u8BzIc-CmacmDW02zZCp21YZX63A");
              return headers;

          }

      };

      RequestQueue requestQueue = Volley.newRequestQueue(getContext());
      requestQueue.add(request);

   }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(getActivity())
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getActivity().getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void logoutUser() {


        String access_token = (String) SharedPref.getaccess_token(getContext());
        String accesstoken =access_token;

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, logout_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Success", response.toString());
                    VolleyLog.v("Response:%n %s", response.toString());
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                    parselogout(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("checkresponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                // Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.e("checkerror", "onErrorResponse: " + error.getMessage());
            }
        }) {


            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Map<String, String> params = new HashMap<String, String>();
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept" ,"application/json");
                headers.put("Authorization","Bearer " + accesstoken);
                return headers;

            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
    }
    private void parselogout(String response) throws JSONException {

        Toast.makeText(getContext(), "Logged out  Successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

       /* JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")){
            Toast.makeText(getContext(), "Logged out  Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else {

            Toast.makeText(getContext(), "Please wait, credentials are wrong", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void parselog() {

        Toast.makeText(getContext(), "Logged out  Successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

       /* JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")){
            Toast.makeText(getContext(), "Logged out  Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else {

            Toast.makeText(getContext(), "Please wait, credentials are wrong", Toast.LENGTH_SHORT).show();
        }*/
    }

}
