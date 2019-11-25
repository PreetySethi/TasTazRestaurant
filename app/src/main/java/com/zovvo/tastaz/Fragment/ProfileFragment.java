package com.zovvo.tastaz.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zovvo.tastaz.Activities.PaymentActivity;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.utils.SharedPref;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


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
        Typeface pick_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);
        name.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        TextView addres = (TextView) view.findViewById(R.id.pro_address);
        Typeface addres_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        addres.setTypeface(addres_text);

        TextView payment = (TextView) view.findViewById(R.id.pro_payment);
        Typeface payment_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        payment.setTypeface(payment_text);

        TextView profile = (TextView) view.findViewById(R.id.title_profile);
        Typeface profilet_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        profile.setTypeface(profilet_text);

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




        TextView reward = (TextView) view.findViewById(R.id.pro_reward);
        Typeface reward_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        reward.setTypeface(reward_text);
        reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), PaymentActivity.class);
                // startActivity(intent);
            }
        });

        TextView setting = (TextView) view.findViewById(R.id.pro_settings);
        Typeface setting_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        setting.setTypeface(setting_text);


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), PaymentActivity.class);
                // startActivity(intent);
            }
        });

        return view;

    }



}
