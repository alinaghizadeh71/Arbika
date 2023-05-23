package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


/**
 * Created by Maliheh on 06/07/2018.
 */

class CustomPagerAdapterpage3 extends PagerAdapter {
  /*  int[] mResources = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image1,
            R.drawable.image4,
            R.drawable.image2};*/

    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private int i;
    private Context context;


    public CustomPagerAdapterpage3(Context context, ArrayList<String> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
      
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view,final int position) {
       final View imageLayout;
        final com.github.chrisbanes.photoview.PhotoView imageView;



        imageLayout = inflater.inflate(R.layout.pager_item_page3, view, false);
        assert imageLayout != null;
        imageView = (com.github.chrisbanes.photoview.PhotoView) imageLayout
                .findViewById(R.id.image);
        // imageView.setImageResource(IMAGES.get(position));
        String temp = IMAGES.get(position).replaceAll(" ", "%20");
        Picasso.with(context).load(temp).into(imageView);

        view.addView(imageLayout, 0);

        imageLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //this will log the page number that was click
                // Toast.makeText(context,String.valueOf(position), Toast.LENGTH_LONG).show();
               /* Intent i=new Intent(context,page3.class);
                i.putExtra("key",IMAGES.get(position));

                context.startActivity(i);*/

            }
        });

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
