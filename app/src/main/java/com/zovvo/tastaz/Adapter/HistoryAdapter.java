package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.zovvo.tastaz.Activities.ViewOrderActivity;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.Model.History;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.CustomViewHolder> {

    private Context mContext;
    private List<History> historyList;


    public HistoryAdapter(List<History> historyList, Context mContext) {
        this.mContext = mContext;
        this.historyList = historyList;
    }

    @Override
    public HistoryAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderhistory_cardview, parent, false);

        TextView name = (TextView) itemView.findViewById(R.id.tv_date);
        Typeface pick_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/OpenSans-Semibold.ttf");
        name.setTypeface(pick_text);

        TextView desc = (TextView) itemView.findViewById(R.id.tv_orderid);
        Typeface desc_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        desc.setTypeface(desc_text);

        TextView or = (TextView) itemView.findViewById(R.id.orderid);
        Typeface or_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        or.setTypeface(or_text);

        TextView date = (TextView) itemView.findViewById(R.id.date);
        Typeface date_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        date.setTypeface(date_text);

       /* TextView view = (TextView) itemView.findViewById(R.id.order_view);
        Typeface view_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        view.setTypeface(view_text);*/

       /* TextView deliveryboy = (TextView) itemView.findViewById(R.id.tv_deliveryboy);
        Typeface deliveryboy_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        deliveryboy.setTypeface(deliveryboy_text);*/

       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onButtonShowPopupWindowClick(view);
               // openDialog(view);
               // v.getContext().startActivity(ViewOrderActivity);

                Intent myactivity = new Intent(mContext.getApplicationContext(), ViewOrderActivity.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(myactivity);
            }
        });*/

        return new HistoryAdapter.CustomViewHolder(itemView);
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.order_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();

                return true;
            }
        });
    }


   /* public void openDialog( View view) {
        final Dialog dialog = new Dialog(view.getContext()); // Context, this, etc.
        dialog.setContentView(R.layout.order_popup);
        dialog.setTitle("order");
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }*/


    @Override
    public void onBindViewHolder(final HistoryAdapter.CustomViewHolder holder, int position) {
        History pizza = historyList.get(position);
        holder.oid.setText(pizza.getOrderid());
        holder.date.setText(pizza.getDate());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView oid,date,deliveryboy;

        public CustomViewHolder(View itemView) {
            super(itemView);
            oid= (TextView) itemView.findViewById(R.id.tv_orderid);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            //deliveryboy=(TextView) itemView.findViewById(R.id.tv_deliveryboy);
        }
    }

}
