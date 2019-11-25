package com.zovvo.tastaz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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


import com.squareup.picasso.Picasso;
import com.zovvo.tastaz.Activities.CartActivity;
import com.zovvo.tastaz.Fragment.PopularFragment;
import com.zovvo.tastaz.Model.Menu;
import com.zovvo.tastaz.Model.ProductImage;
import com.zovvo.tastaz.R;


import java.util.ArrayList;
import java.util.List;

import static com.zovvo.tastaz.Fragment.PopularFragment.menus;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    public static ArrayList<Menu> menus;
    public static ArrayList<ProductImage> cartModels = new ArrayList<ProductImage>();
    public static ProductImage cartModel;
    Context context;
    Picasso picasso;
    public CallBackUs mCallBackus;
    public HomeCallBack homeCallBack;

    public ProductAdapter(ArrayList<Menu> menus, Context context, HomeCallBack mCallBackus) {
        this.menus = menus;
        this.context = context;
        this.homeCallBack = mCallBackus;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_card_view, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewHolder holder,  int position) {


        Menu m = menus.get(position);
        holder.price.setText(m.getPrice());
        holder.name.setText(m.getName());
        holder.desc.setText(m.getDesc());
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
                        cartModel = new ProductImage();
                        cartModel.setProductQuantity((cartCounter[0]));
                        cartModel.setProductName(menus.get(position).getName());
                        cartModel.setProductPrice(menus.get(position).getPrice());
                        cartModel.setTotalCash(cartCounter[0] *
                                Integer.parseInt(menus.get(position).getPrice()));
                        Log.d("pos", String.valueOf(position));

                        cartModels.add(cartModel);

                        Toast toast = Toast.makeText(context,  "                                        Added to cart                                                  ", Toast.LENGTH_SHORT);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast);
                        TextView text = view.findViewById(android.R.id.message);
                        text.setTextColor(Color.parseColor("#FFFFFF"));
                        toast.show();

//
                        // from these lines of code we update badge count value
                      /* PopularFragment.cart_count = 0;
                        for (int i = 0; i < cartModels.size(); i++) {
                            for (int j = i + 1; j < cartModels.size(); j++) {
                                if (cartModels.get(i).getProductImage()==(cartModels.get(j).getProductImage())) {
                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                                    cartModels.remove(j);
                                    j--;
                                    Log.d("remove", String.valueOf(cartModels.size()));

                                }
                            }
                        }*/
                        PopularFragment.cart_count = cartModels.size();
                        homeCallBack.updateCartCount(context);
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
