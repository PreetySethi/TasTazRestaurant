package com.simplistq.tastaz.guestModule;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplistq.tastaz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestNotifyFragment extends Fragment {


    public GuestNotifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_notify, container, false);
    }

}
