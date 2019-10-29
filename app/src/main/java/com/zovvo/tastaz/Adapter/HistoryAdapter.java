package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zovvo.tastaz.R;
import com.zovvo.tastaz.Model.History;

import java.util.List;

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
        Typeface pick_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);

        TextView desc = (TextView) itemView.findViewById(R.id.tv_orderid);
        Typeface desc_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        desc.setTypeface(desc_text);

        return new HistoryAdapter.CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final HistoryAdapter.CustomViewHolder holder, int position) {
        History pizza = historyList.get(position);
        holder.date.setText(pizza.getOrderid());
        holder.oid.setText(pizza.getDate());
    }


    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView date, oid;

        public CustomViewHolder(View itemView) {
            super(itemView);
            oid= (TextView) itemView.findViewById(R.id.tv_orderid);
            date = (TextView) itemView.findViewById(R.id.tv_date);


        }
    }

}
