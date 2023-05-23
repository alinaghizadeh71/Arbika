package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Maliheh on 09/03/2018.
 */

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Uri> imageUrls;
    private SparseBooleanArray mSparseBooleanArray;//Variable to store selected Images
    private DisplayImageOptions options;
    private boolean isCustomGalleryActivity;//Variable to check if gridview is to setup for Custom Gallery or not

    public MyAdapter(Context context, ArrayList<Uri> imageUrls, boolean isCustomGalleryActivity) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.isCustomGalleryActivity = isCustomGalleryActivity;
        mSparseBooleanArray = new SparseBooleanArray();


        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .resetViewBeforeLoading(true).cacheOnDisk(true)
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    //Method to return selected Images
    public ArrayList<Uri> getCheckedItems() {
        ArrayList<Uri> mTempArry = new ArrayList<Uri>();

        for (int i = 0; i < imageUrls.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                mTempArry.add(imageUrls.get(i));
            }
        }

        return mTempArry;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int i) {
        return imageUrls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.custom_gallery_item, viewGroup, false);//Inflate layout

        ImageButton mCheckBox = (ImageButton) view.findViewById(R.id.chkImage);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imgThumb);
        imageView.setImageURI(imageUrls.get(position));

        //If Context is MainActivity then hide checkbox
        if (!isCustomGalleryActivity)
            mCheckBox.setVisibility(View.GONE);

      //  ImageLoader.getInstance().displayImage("file://" + imageUrls.get(position), imageView, options);

        //Load Images over ImageView

        mCheckBox.setTag(position);//Set Tag for CheckBox
      //  mCheckBox.setChecked(mSparseBooleanArray.get(position));
     //   mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
        mCheckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                imageUrls.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        return view;
    }
    public void AddItem(Uri item, int position) {
        imageUrls.add(position, item);
        // notify item added by position
       notifyDataSetInvalidated();

    }

}