package com.zovvo.tastaz.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zovvo.tastaz.Adapter.HistoryAdapter;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.Model.History;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {
    List<History> historyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter hAdapter;


    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_popular, container, false);
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rview_historyorder);
        hAdapter = new HistoryAdapter(historyList, getContext());


        // Create grids with 2 items in a row
        // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hAdapter);

        populatePizzaDetails();
        hAdapter.notifyDataSetChanged();
        return view;
    }
    private void populatePizzaDetails() {
        History history = new History("Order ID: Or4565","Date : 25/02/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45ff65","Date : 18/10/2019");
        historyList.add(history);
        history = new History("Order ID: Or-456455","Date : 19/08/2019");
        historyList.add(history);
        history = new History("Order ID: Or-4777565","Date : 31/05/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45hjgj65","Date : 11/06/2019");
        historyList.add(history);
        history = new History("Order ID: Or-4523g65","Date : 13/02/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45g4565","Date : 22/05/2019");
        historyList.add(history);
        history = new History("Order ID: Or-45645335","Date : 52/02/2019");
        historyList.add(history);
    }


}
