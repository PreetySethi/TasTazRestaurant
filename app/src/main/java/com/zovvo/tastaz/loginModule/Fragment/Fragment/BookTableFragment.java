package com.zovvo.tastaz.loginModule.Fragment.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zovvo.tastaz.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookTableFragment extends Fragment {
    LinearLayout ln;


    public BookTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bookatable, container, false);


        TextView name = (TextView) view.findViewById(R.id.comsoon);
        Typeface name_text = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(name_text);
        return  view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


}
