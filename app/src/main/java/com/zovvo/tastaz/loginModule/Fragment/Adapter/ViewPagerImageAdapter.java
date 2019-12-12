package com.zovvo.tastaz.loginModule.Fragment.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zovvo.tastaz.R;


public class ViewPagerImageAdapter extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images ={R.drawable.foodeight, R.drawable.bir, R.drawable.foodfg, R.drawable.foode};

    public ViewPagerImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    Toast.makeText(context, "slide 1 clicked", Toast.LENGTH_LONG).show();
                } else if (position == 1) {
                    Toast.makeText(context, "slide 2 clicked", Toast.LENGTH_LONG).show();
                } else if (position == 2){
                    Toast.makeText(context, "slide 3 clicked", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "slide 4 clicked", Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view= (View) object;
        vp.removeView(view);
    }
}
