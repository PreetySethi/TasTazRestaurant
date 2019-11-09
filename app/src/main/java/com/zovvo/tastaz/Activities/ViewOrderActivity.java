package com.zovvo.tastaz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.zovvo.tastaz.Adapter.historypopupAdapter;
import com.zovvo.tastaz.Model.OrderPopup;
import com.zovvo.tastaz.R;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderActivity extends AppCompatActivity {

    List<OrderPopup> oDetails = new ArrayList<>();
    private RecyclerView recyclerView;
    private historypopupAdapter hOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        recyclerView = (RecyclerView) findViewById(R.id.rview_history);
        hOrderAdapter = new historypopupAdapter(oDetails,this);

        // Create grids with 2 items in a row
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hOrderAdapter);

        populateOrderDetails();
        hOrderAdapter.notifyDataSetChanged();

    }

    private void populateOrderDetails() {
        OrderPopup orderPopup = new OrderPopup("Chicken Fiesta","R.drawable.foodeight","7777 2222 2222","22/10/2019","11:09 AM", "ABDULLAH","CASH ON DELIVERY");
        oDetails.add(orderPopup);
    }
}
