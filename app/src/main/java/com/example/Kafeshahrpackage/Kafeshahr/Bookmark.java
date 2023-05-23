package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class Bookmark extends AppCompatActivity {
/*implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener*/
//private ArrayList<Posts> mDataSet;



     TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    String DB_PATH;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    private Toolbar toolbar;

    ProgressBar spinner;


    String url_bookmark = "http://api.arbika.ir/v1/categories";
    String id_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
       getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("علاقمندی ها");

        ImageButton share= (ImageButton) findViewById(R.id.btnshare);
    /*    setTitle("علاقمندی ها");
setTitleColor(Color.parseColor("#FFFFFF"));*/
                              //  recyclerView = (RecyclerView) findViewById(R.id.recyclerbook);
                                //coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
                               /* cartList = new ArrayList<>();*/
     //   mAdapter = new Page2_Recycler_View_Adapter(Bookmark.this, cartList);

      /*  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);*/



//setSwipeForRecyclerView();
       // toolbar = (Toolbar) findViewById(R.id.toolbar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
         spinner=(ProgressBar)findViewById(R.id.progressBar);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {

                                                           loadData();

                                                     }
                                                     });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark);

        try {
            mydatabasehandler db = new mydatabasehandler(getBaseContext());
            List<Posts> posts = new ArrayList<>();
            Bundle b = getIntent().getExtras();
            if (b != null) {
                // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

                id_category = b.getString("idcategory");
                // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
            }
            //  Toast.makeText(Bookmark.this,id_category, Toast.LENGTH_LONG).show();
            if (id_category.equals("all")) {
                posts = db.getfavoriteslist();

            } else {

                posts = db.getfavoriteslistcat(id_category);
            }


            mRecyclerView.setLayoutManager(new LinearLayoutManager(Bookmark.this));
            SwipeRecyclerViewAdapter mAdapter = new SwipeRecyclerViewAdapter(Bookmark.this, posts, get_source());

            ((SwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

            mRecyclerView.setAdapter(mAdapter);
            spinner.setVisibility(View.GONE);
        }
        catch (Exception e)
        {
            Toast.makeText(Bookmark.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
        }


        /* Scroll Listeners */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // this.menu = menu;
        getMenuInflater().inflate(R.menu.bookmark, menu);
        //  hideOption(R.id.action_info);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {


            /*File dbfile = new File("/sdcard/android/com.myapp/databases/mydatabase.db" );
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
           */
           /* Intent i=new Intent(Bookmark.this,Search.class);
            startActivity(i);*/
/*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                DB_PATH = getBaseContext().getFilesDir().getAbsolutePath().replace("files", "Kafeshahr") + File.separator;
            }
            else {
                DB_PATH = getBaseContext().getFilesDir().getPath() + getBaseContext().getPackageName() + "/Kafeshahr/";
            }
           // Toast.makeText(this,DB_PATH,Toast.LENGTH_LONG).show();
            try {
                writeToSD();
            } catch (IOException e) {
                Toast.makeText(this,"nno",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }*/
            return true;
        } if (id == R.id.ref) {
           /* Intent i=new Intent(Bookmark.this,Bookmark.class);
            startActivity(i);*/

            return true;
            }else if (id == android.R.id.home)
        {
            /*Intent i=new Intent(Bookmark.this,maintest.class);
            startActivity(i);*/
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    String source;
    public String get_source() {

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            source= b.getString("source");
             //Toast.makeText(Bookmark.this,"source:"+source, Toast.LENGTH_LONG).show();
        }
        return source;
    }

  private class LoadViewTask extends AsyncTask<Void, Void,Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
           spinner.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {

           /* APIGettingPosts apiGettingPosts = new APIGettingPosts(Bookmark.this,url_bookmark,"category_news");
            apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

                @Override
                public void onReceived(List<Posts> posts) {
                    // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                    if(posts == null || posts.isEmpty()){


                    }else {

                        Toast.makeText(getApplication(), "oooooook", Toast.LENGTH_SHORT).show();
                                   *//* mydatabasehandler db=new mydatabasehandler(getBaseContext());
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(Bookmark.this));

                                    SwipeRecyclerViewAdapter mAdapter = new SwipeRecyclerViewAdapter(Bookmark.this, db.getfavoriteslist());

                                    ((SwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

                                    mRecyclerView.setAdapter(mAdapter);
                                    spinner.setVisibility(View.GONE);*//*


                    }
                }
            });*/
            return null;

        }


        @Override
        protected void onPostExecute(Void result)
        {
            mSwipeRefreshLayout.setRefreshing(false);
           /* if(listdata == null || listdata.isEmpty()) {
                Toast.makeText(Bookmark.this, "خطا", Toast.LENGTH_SHORT).show();
            }*/
                //close the progress dialog
          //   spinner.setVisibility(View.GONE);
           // spinner.setVisibility(View.GONE);

            //initialize the View
            //  setContentView(R.layout.main);
        }
    }
    // load initial data
    public void loadData() {

      /*  for (int i = 0; i <= 20; i++) {
            mDataSet.add(new Student("Student " + i, "androidstudent" + i + "@gmail.com"));

        }*/

      /*  APIGettingPosts apiGettingPosts = new APIGettingPosts(Bookmark.this,url_bookmark,"category");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(Bookmark.this, "خطا در دریافت اطلاعات", LENGTH_SHORT).show();
                }else {


                    listdata=posts;


                    mRecyclerView.setLayoutManager(new LinearLayoutManager(Bookmark.this));

                    SwipeRecyclerViewAdapter mAdapter = new SwipeRecyclerViewAdapter(Bookmark.this, posts);

                    ((SwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

                    mRecyclerView.setAdapter(mAdapter);


                }
            }
        });*/
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
