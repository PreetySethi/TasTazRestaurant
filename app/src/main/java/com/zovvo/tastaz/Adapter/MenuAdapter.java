package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.CustomViewHolder>{

    private Context mContext;
    private List<Menu> menus;

    public MenuAdapter(List<Menu> pizzas, Context mContext) {
        this.mContext = mContext;
        this.menus = pizzas;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_card_view, parent, false);

        TextView name = (TextView) itemView.findViewById(R.id.pizzaName);
        Typeface pick_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        name.setTypeface(pick_text);

        TextView desc = (TextView) itemView.findViewById(R.id.pizzaDesc);
        Typeface desc_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        desc.setTypeface(desc_text);

        TextView pric = (TextView) itemView.findViewById(R.id.pizzaPrice);
        Typeface pric_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        pric.setTypeface(pric_text);

        TextView price = (TextView) itemView.findViewById(R.id.Price);
        Typeface price_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/NevisBold-KGwl.ttf");
        price.setTypeface(price_text);


        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MenuAdapter.CustomViewHolder holder, int position) {
        Menu pizza = menus.get(position);
        holder.name.setText(pizza.getName());
        holder.desc.setText(pizza.getDesc());
        holder.price.setText(pizza.getPrice());
        holder.image.setImageResource(pizza.getImageResource());
    }


    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc,price;
        public ImageView image, menu;

        /**
         * Constructor to initialize the Views
         *
         * @param itemView
         */
        public CustomViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pizzaName);
            desc= (TextView) itemView.findViewById(R.id.pizzaDesc);
            price = (TextView) itemView.findViewById(R.id.pizzaPrice);
            image = (ImageView) itemView.findViewById(R.id.pizzaImage);

        }
    }
}

