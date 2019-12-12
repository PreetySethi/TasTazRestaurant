package com.zovvo.tastaz.guestModule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zovvo.tastaz.R;

 public class GuestBookTableFragment extends Fragment {
     LinearLayout ln;


    public GuestBookTableFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest_book_table, container, false);

        ln = view.findViewById(R.id.booktable);
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
