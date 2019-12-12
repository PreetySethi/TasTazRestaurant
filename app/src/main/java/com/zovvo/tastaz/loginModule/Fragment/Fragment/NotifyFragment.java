package com.zovvo.tastaz.loginModule.Fragment.Fragment;


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
public class NotifyFragment extends Fragment {


    public NotifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notify, container, false);

        TextView name = (TextView) view.findViewById(R.id.title_notify);
        Typeface pick_text = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(pick_text);

        return view;

    }

}
