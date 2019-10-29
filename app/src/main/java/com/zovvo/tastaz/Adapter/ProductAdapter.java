package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zovvo.tastaz.Activities.CartActivity;
import com.zovvo.tastaz.Fragment.PopularFragment;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.Model.ProductImage;
import com.zovvo.tastaz.R;

import java.util.ArrayList;
import java.util.List;

import static com.zovvo.tastaz.Fragment.PopularFragment.cart_count;
import static com.zovvo.tastaz.Fragment.PopularFragment.menus;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public static ArrayList<ProductImage> cartModels = new ArrayList<ProductImage>();
    public static ProductImage cartModel;
    Context context;
    Toast toast;
    LinearLayout ln;
    private CallBackUs mCallBackus;
    private HomeCallBack homeCallBack;
    public static ArrayList<Menu> menus;
    public ProductAdapter( Context context, HomeCallBack mCallBackus,ArrayList<Menu> menus) {
        this.context = context;
        this.homeCallBack = mCallBackus;
        this.menus = menus;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_card_view, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewHolder holder,  int position) {
        Menu pizza = menus.get(position);
        holder.name.setText(pizza.getName());
        holder.desc.setText(pizza.getDesc());
        holder.price.setText(pizza.getPrice());
        holder.image.setImageResource(pizza.getImageResource());



        holder.quantity.setText(String.valueOf(0));
                final int[] cartCounter = {0};//{(arrayListImage.get(position).getStocks())};
        holder.cartDecrement.setEnabled(false);
        holder.cartDecrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cartCounter[0] == 1) {
                            Toast.makeText(context, "cant add less than 0", Toast.LENGTH_SHORT).show();
                        } else {
                            cartCounter[0] -= 1;
                            holder.quantity.setText(String.valueOf(cartCounter[0]));
                        }

                    }
                });
        holder.cartIncrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.cartDecrement.setEnabled(true);
                        cartCounter[0] += 1;
                        holder.quantity.setText(String.valueOf(cartCounter[0]));


                    }
                });
        holder.viewCartDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        context.startActivity(new Intent(context, CartActivity.class));
                    }
                });

        holder.updateQtyDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=0; i < 2; i++)
                        {
                            Toast toast = Toast.makeText(context, String.valueOf(cartCounter[0])  +"  Items added to cart", Toast.LENGTH_SHORT);
                            View view = toast.getView();
                            view.setBackgroundResource(R.drawable.toast);
                            TextView text = view.findViewById(android.R.id.message);
                            text.setTextColor(Color.parseColor("#FFFFFF"));
                            toast.show();

                        }


                        // from these line of code we add items in cart
                        cartModel = new ProductImage();
                        cartModel.setProductQuantity((cartCounter[0]));
                        cartModel.setProductPrice(menus.get(position).getPrice());
                        cartModel.setProductImage(menus.get(position).getImageResource());
                        cartModel.setTotalCash(cartCounter[0] *
                                Integer.parseInt(menus.get(position).getPrice()));
                        Log.d("pos", String.valueOf(position));

                        cartModels.add(cartModel);

//
                        // from these lines of code we update badge count value
                        PopularFragment.cart_count = 0;
                        for (int i = 0; i < cartModels.size(); i++) {
                            for (int j = i + 1; j < cartModels.size(); j++) {
                                if (cartModels.get(i).getProductImage().equals(cartModels.get(j).getProductImage())) {
                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                                    //          cartModels.get(i).setImageIdSlide(cartModels.get(j).getImageIdSlide());
                                    cartModels.remove(j);
                                    j--;
                                    Log.d("remove", String.valueOf(cartModels.size()));

                                }
                            }
                        }
                        PopularFragment.cart_count = cartModels.size();

                        // from this interface method calling we show the updated value of cart cout in badge
                        homeCallBack.updateCartCount(context);
                    }

                });



    }

    @Override
    public int getItemCount() {
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
            desc= (TextView) itemView.findViewById(R.id.pizzaDesc);
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
