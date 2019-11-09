package com.zovvo.tastaz.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zovvo.tastaz.R;

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

        TextView name = (TextView) view.findViewById(R.id.pro_account);
        Typeface pick_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);

        TextView addres = (TextView) view.findViewById(R.id.pro_address);
        Typeface addres_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        addres.setTypeface(addres_text);

        TextView payment = (TextView) view.findViewById(R.id.pro_payment);
        Typeface payment_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        payment.setTypeface(payment_text);

        TextView reward = (TextView) view.findViewById(R.id.pro_reward);
        Typeface reward_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        reward.setTypeface(reward_text);

        TextView setting = (TextView) view.findViewById(R.id.pro_settings);
        Typeface setting_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        setting.setTypeface(setting_text);
        return view;
    }

}
