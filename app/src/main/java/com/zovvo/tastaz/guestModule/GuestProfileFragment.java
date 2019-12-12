package com.zovvo.tastaz.guestModule;


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
public class GuestProfileFragment extends Fragment {


    public GuestProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_guest_profile, container, false);

        TextView name = (TextView) view.findViewById(R.id.textview);
        Typeface pick_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(pick_text);

        TextView viiew = (TextView) view.findViewById(R.id.textprofile);
        Typeface viiew_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        viiew.setTypeface(viiew_text);
        return view;
    }

}
