package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zovvo.tastaz.Model.ProductImage;
import com.zovvo.tastaz.R;

import com.zovvo.tastaz.utils.SharedPref;


import java.util.ArrayList;

import static com.zovvo.tastaz.Activities.CartActivity.grandTotal;
import static com.zovvo.tastaz.Activities.CartActivity.grandTotalplus;
import static com.zovvo.tastaz.Activities.CartActivity.temparraylist;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<ProductImage> cartModelArrayList;
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public CartAdapter(ArrayList<ProductImage> cartModelArrayList, Context context) {
        this.context = (Context) context;
        this.cartModelArrayList = cartModelArrayList;
    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        // holder.productCartImage.setImageResource(R.drawable.burger);
        holder.productCartPrice.setText(String.valueOf(cartModelArrayList.get(position).getTotalCash()));
        holder.productCartName.setText(cartModelArrayList.get(position).getProductName());
        holder.productPrice.setText(cartModelArrayList.get(position).getProductPrice());
        holder.productCartQuantity.setText(String.valueOf(cartModelArrayList.get(position).getProductQuantity()));

       /* editor = pref.edit();
        editor.putString("price", String.valueOf(holder.productCartPrice));
        editor.commit();*/

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.foodone);
        requestOptions.error(R.drawable.foodone);
      /*  Log.d("imageurl", String.valueOf(cartModelArrayList.get(position).getProductImage()));
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(cartModelArrayList.get(position).getProductImage()).into(holder.productCartImage);*/

        //for remove single item in cart and update the total value and list
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartModelArrayList.size() == 1) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0;
                    grandTotal.setText(String.valueOf(grandTotalplus));
                }

                if (cartModelArrayList.size() > 0) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0;
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                    }

                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                    grandTotal.setText(String.valueOf(grandTotalplus));

                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // increment quantity and update quamtity and total cash
        holder.cartIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;\
                Log.d("posthegun", String.valueOf(cartModelArrayList.get(position).getStocks()));

                grandTotalplus = 0;
                holder.cartDecrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getProductQuantity());
                Log.d("counterthegun", String.valueOf(cartModelArrayList.get(position).getProductQuantity()));

                holder.cartIncrement.setEnabled(true);
                cartUpdateCounter += 1;

                cartModelArrayList.get(position).setProductQuantity((cartUpdateCounter));
                int cash = (Integer.parseInt(cartModelArrayList.get(position).getProductPrice()) * (cartModelArrayList.get(position).getProductQuantity()));

                holder.productCartQuantity.setText(String.valueOf(cartModelArrayList.get(position).getProductQuantity()));

                cartModelArrayList.get(position).setTotalCash(cash);
                holder.productCartPrice.setText(String.valueOf(cash));


                for (int i = 0; i < temparraylist.size(); i++) {
                    grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                }
                Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                grandTotal.setText(String.valueOf(grandTotalplus));

            }

        });

        // decrement quantity and update quamtity and total cash
        holder.cartDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;
                grandTotalplus = 0;
                holder.cartIncrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getProductQuantity());
                Log.d("counterthegun", String.valueOf(cartModelArrayList.get(position).getProductQuantity()));


                if (cartUpdateCounter == 0) {
                    holder.cartDecrement.setEnabled(false);
                    Toast.makeText(context, "quantity can't be zero", Toast.LENGTH_SHORT).show();
                } else {
                    holder.cartDecrement.setEnabled(true);
                    cartUpdateCounter -= 1;
                    cartModelArrayList.get(position).setProductQuantity((cartUpdateCounter));
                    holder.productCartQuantity.setText(String.valueOf(cartModelArrayList.get(position).getProductQuantity()));
                    int cash = (Integer.parseInt(cartModelArrayList.get(position).getProductPrice()) * (cartModelArrayList.get(position).getProductQuantity()));

                    cartModelArrayList.get(position).setTotalCash(cash);
                    holder.productCartPrice.setText(String.valueOf(cash));
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                    }

                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                    grandTotal.setText(String.valueOf(grandTotalplus));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("sizecart", String.valueOf(cartModelArrayList.size()));
        return cartModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView  cartIncrement, cartDecrement, deleteItem;
        TextView productCartName, productCartPrice, productCartQuantity, productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteItem = itemView.findViewById(R.id.delete_item_from_cart);
            productCartName = itemView.findViewById(R.id.product_cart_code);
            productPrice = itemView.findViewById(R.id.product_price);
            productCartPrice = itemView.findViewById(R.id.product_cart_price);
            productCartQuantity = itemView.findViewById(R.id.cart_product_quantity_tv);
            cartDecrement = itemView.findViewById(R.id.cart_decrement);
            cartIncrement = itemView.findViewById(R.id.cart_increment);


        }
    }


}
