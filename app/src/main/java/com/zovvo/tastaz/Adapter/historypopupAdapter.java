package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.zovvo.tastaz.Model.historypopup;
import com.zovvo.tastaz.R;

import java.util.List;


public class historypopupAdapter extends RecyclerView.Adapter<historypopupAdapter.CustomViewHolder> {

    private List<historypopup> historypopups;
    private Context mContext;

    public historypopupAdapter(List<historypopup> history, Context mContext ){
        this.mContext = mContext;
        this.historypopups = history;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_popup, parent, false);

        return new historypopupAdapter.CustomViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final historypopupAdapter.CustomViewHolder holder, int position) {

        historypopup history = historypopups.get(position);
        holder.name.setText(history.getName());
        holder.deliver.setText(history.getDeliveredby());
        holder.date.setText(history.getDate());
        holder.pay.setText(history.getPayment());
        holder.oid.setText(history.getOrderid());
       // holder.image.setImage(history.getImage());
    }


    @Override
    public int getItemCount() {
        return historypopups.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView name, deliver,date,oid,pay;
        public ImageView image;

        public CustomViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txproductName);
            deliver = (TextView) itemView.findViewById(R.id.or_deliver);
            date = (TextView) itemView.findViewById(R.id.or_date);
            image = (ImageView) itemView.findViewById(R.id.pizzaImage);
            pay = (TextView) itemView.findViewById(R.id.or_payment);
            oid= (TextView) itemView.findViewById(R.id.txt_orderid);

        }
    }

}
