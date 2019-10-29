package com.zovvo.tastaz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zovvo.tastaz.MyLocationUsingLocationAPI;
import com.zovvo.tastaz.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText user = (EditText) findViewById(R.id.et_username);
        Typeface user_font = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Semibold.ttf");
        user.setTypeface(user_font);

        EditText password = (EditText) findViewById(R.id.et_password);
        Typeface password_font = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Semibold.ttf");
        password.setTypeface(password_font);

        TextView loginuser = (TextView)findViewById(R.id.textlogin);
        Typeface custom_font_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        loginuser.setTypeface(custom_font_text);

        TextView txguest = (TextView)findViewById(R.id.guestlogin);
        Typeface custom_font_gtext = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        txguest.setTypeface(custom_font_gtext);

        Button login = (Button) findViewById(R.id.btn_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        login.setTypeface(custom_font);

        Button guest = (Button) findViewById(R.id.btn_guest);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
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
                Intent intent = new Intent(LoginActivity.this, MyLocationUsingLocationAPI.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Verify login with userid and password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
