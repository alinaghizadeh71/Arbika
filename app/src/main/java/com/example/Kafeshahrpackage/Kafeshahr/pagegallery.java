package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class pagegallery extends AppCompatActivity {

ImageButton close,share;
   /* private static final Integer[] IMAGES= {
            R.drawable.imagee,
            R.drawable.imagee5,
            R.drawable.imagee4,
            R.drawable.imagee3,
            R.drawable.imagee2,
            R.drawable.imagee1};*/
 /*   private static final String[] images={};
    private static final String[] title={};*/
 ArrayList<String> images;

    String picture,lable;
    private static ViewPager mPager;

    private static int NUM_PAGES = 0;
    private  ArrayList<String> ImagesArray = new ArrayList<String>();
TextView titleTextView,txtnum,txtlbnum;
    int gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagegallery);
          /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Typeface typefacenum = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        titleTextView=(TextView)findViewById(R.id.titleTextView);
        txtnum=(TextView)findViewById(R.id.num);
        txtlbnum=(TextView)findViewById(R.id.numlb);
        titleTextView.setTypeface(typefacenum);
        txtlbnum.setTypeface(typefacenum);
        txtnum.setTypeface(typefacenum);

        close = (ImageButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent i=new Intent(pagegallery.this,page3.class);
                //startActivity(i);
                finish();
            }
        });
        share = (ImageButton) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = getResources().getString(R.string.content);
                shareBody = shareBody + "\n \n https://play.google.com/store/apps/details?id=the.package.id \n\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        init();
    }
    private  void init() {
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            if(getIntent().getExtras().getString("isgallery").equals("gallery"))
            {
                gallery=1;
                images = new ArrayList<String>();

                 //Toast.makeText(pagegallery.this,String.valueOf("gallery"), Toast.LENGTH_LONG).show();
                // image.setImageResource(b.getInt("key"));

                images = getIntent().getStringArrayListExtra("images");
                lable=getIntent().getExtras().getString("label");
               // title = getIntent().getStringArrayListExtra("title");
                titleTextView.setText(String.valueOf(lable));
               // Toast.makeText(pagegallery.this,String.valueOf(title.get(0)), Toast.LENGTH_LONG).show();

                for(int i=0;i<images.size();i++) {
                    ImagesArray.add(String.valueOf(images.get(i)));

                }
            }
            else  if(getIntent().getExtras().getString("isgallery").equals("picture"))
            {
                lable=getIntent().getExtras().getString("label");
                picture=getIntent().getExtras().getString("picture");
                ImagesArray.add(String.valueOf(picture));
                titleTextView.setText(String.valueOf(lable));
                //Toast.makeText(pagegallery.this,String.valueOf(picture), Toast.LENGTH_LONG).show();
            }

        }

        //Toast.makeText(pagegallery.this, String.valueOf("image"+images.size()), Toast.LENGTH_SHORT).show();
        //Toast.makeText(pagegallery.this, String.valueOf("title"+title.size()), Toast.LENGTH_SHORT).show();




    mPager = (ViewPager) findViewById(R.id.pager);


    mPager.setAdapter(new CustomPagerAdapterpage3(pagegallery.this, ImagesArray));

        if(gallery==1) {

          //  Toast.makeText(pagegallery.this,String.valueOf(title.get(0)), Toast.LENGTH_LONG).show();
   /* CirclePageIndicator indicator = (CirclePageIndicator)
            findViewById(R.id.indicator);

    indicator.setViewPager(mPager);
*/
    final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
    /*indicator.setRadius(5 * density);*/

    NUM_PAGES = ImagesArray.size();
            txtnum.setText(String.valueOf(1)+"/"+NUM_PAGES);
            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //Log.d(TAG, "my position is : " + position);
                    txtnum.setText(String.valueOf(position+1)+"/"+NUM_PAGES);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
    // Auto start of viewpager
  /*                  final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                                //  titleTextView.setText(String.valueOf(title.get(0)));
                            }
                            Integer c = currentPage++;
                            mPager.setCurrentItem(c, true);
                            titleTextView.setText(String.valueOf(title.get(currentPage++)));
                        }
                    };
                    Timer swipeTimer = new Timer();
                    swipeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 8000, 8000);*/

    // Pager listener over indicator
   /* indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            currentPage = position;

        }

        @Override
        public void onPageScrolled(int pos, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int pos) {

        }
    });*/

}
    }
   /* private  class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
           // Log.i(TAG, "page selected " + position);
            currentPage = position;
            txtnum.setText(String.valueOf(currentPage));

        }
    }*/
   /* public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            int i=mPager.getCurrentItem();
            txtnum.setText(String.valueOf(currentPage));
        }

        public final int getCurrentPage() {
            return currentPage;
        }
    }*/
}
