package com.zovvo.tastaz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zovvo.tastaz.R;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);


        Button ok = (Button) findViewById(R.id.ok_button);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        ok.setTypeface(custom_font_guest);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
