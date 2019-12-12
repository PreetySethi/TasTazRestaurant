package com.zovvo.tastaz.loginModule.Fragment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.utils.SharedPref;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);


        Button ok = (Button) findViewById(R.id.ok_button);
        Typeface custom_font_guest = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        ok.setTypeface(custom_font_guest);

        TextView delivery = (TextView) findViewById(R.id.delivery);
        Typeface pick_text = Typeface.createFromAsset(getAssets(),  "fonts/Sansation_Regular.ttf");
        delivery.setTypeface(pick_text);

        //TextView total = (TextView) findViewById(R.id.grandtotalprice);
       // Typeface total_text = Typeface.createFromAsset(getAssets(),  "fonts/NevisBold-KGwl.ttf");
        //total.setTypeface(total_text);

        String ctotal = (String) SharedPref.gettotal(OrderConfirmationActivity.this);
        //total.setText(ctotal);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
