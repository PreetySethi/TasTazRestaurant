package com.zovvo.tastaz.guestModule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zovvo.tastaz.R;
import com.zovvo.tastaz.loginModule.Fragment.Activities.CartActivity;
import com.zovvo.tastaz.loginModule.Fragment.Model.Menu;
import com.zovvo.tastaz.loginModule.Fragment.Model.ProductImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class guestProductAdapter extends RecyclerView.Adapter<guestProductAdapter.ViewHolder> {


    public static ArrayList<Menu> menus;
    public static ArrayList<ProductImage> cartModels = new ArrayList<ProductImage>();
    public static ProductImage cartModel;
    Context context;
    Picasso picasso;
    public CallBackUs mCallBackus;
    public HomeCallBack homeCallBack;

    public guestProductAdapter(ArrayList<Menu> menus, Context context, HomeCallBack mCallBackus) {
        this.menus = menus;
        this.context = context;
        this.homeCallBack = mCallBackus;
    }


    @NonNull
    @Override
    public guestProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_card_view, viewGroup, false);

        TextView name = (TextView) itemView.findViewById(R.id.pizzaName);
        Typeface pick_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        name.setTypeface(pick_text);

        /** TextView desc = (TextView) itemView.findViewById(R.id.pizzaDesc);
         Typeface desc_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
         desc.setTypeface(desc_text);*/

        TextView pric = (TextView) itemView.findViewById(R.id.pizzaPrice);
        Typeface pric_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        pric.setTypeface(pric_text);

        TextView price = (TextView) itemView.findViewById(R.id.Price);
        Typeface price_text = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/Sansation_Regular.ttf");
        price.setTypeface(price_text);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final guestProductAdapter.ViewHolder holder, int position) {


        Menu m = menus.get(position);
        holder.price.setText(m.getPrice());
        holder.name.setText(m.getName());
      //  holder.desc.setText(m.getDesc());
        Picasso.get().load(menus.get(position).getImage()).into(holder.image);



       holder.quantity.setText(String.valueOf(0));
                final int[] cartCounter = {1};//
        // {(arrayListImage.get(position).getStocks())};
        holder.viewCartDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        context.startActivity(new Intent(context, CartActivity.class));
                    }
                });

        holder.updateQtyDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // from these line of code we add items in cart


                        Toast toast = Toast.makeText(context,  "  Sign up to continue  ", Toast.LENGTH_SHORT);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast);
                        TextView text = view.findViewById(android.R.id.message);
                        text.setTextColor(Color.parseColor("#FFFFFF"));
                        toast.show();

                    }

                });
    }

    @Override
    public int getItemCount()
    {
        return menus.size();
    }

    public interface CallBackUs {
        void addCartItemView();
    }
    // this interface creates for call the invalidateoptionmenu() for refresh the menu item
    public interface HomeCallBack {
        void updateCartCount(Context context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc,price,order,updateQtyDialog,viewCartDialog,quantity;
        public ImageView image, menu,cartDecrement,cartIncrement;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pizzaName);
          //  desc= (TextView) itemView.findViewById(R.id.pizzaDesc);
            price = (TextView) itemView.findViewById(R.id.pizzaPrice);
            image = (ImageView) itemView.findViewById(R.id.pizzaImage);
            order =(TextView) itemView.findViewById(R.id.update_quantity_dialog);

             cartDecrement = (ImageView)itemView.findViewById(R.id.cart_decrement);
             cartIncrement =(ImageView) itemView.findViewById(R.id.cart_increment);

             updateQtyDialog = (TextView)itemView.findViewById(R.id.update_quantity_dialog);
             viewCartDialog = (TextView)itemView.findViewById(R.id.view_cart_button_dialog);
             quantity = (TextView)itemView.findViewById(R.id.cart_product_quantity_tv);
        }
    }

}
