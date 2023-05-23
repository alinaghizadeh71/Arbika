package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
EditText edit;
    private Toolbar toolbar;
    ImageButton send;
    RecyclerView recyclerView;
    ProgressBar Progress;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String txt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("صفحه جستجو");


        Typeface typeface = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRAN.ttf");
        edit=(EditText)findViewById(R.id.edit);
        edit.setTypeface (typeface);
        Progress = (ProgressBar)findViewById(R.id.pd);
        recyclerView = (RecyclerView) findViewById(R.id.cardView);

        send=(ImageButton)findViewById(R.id.btnsearch);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            // Toast.makeText(Search.this,String.valueOf((b.getString("key"))), Toast.LENGTH_LONG).show();
            edit.setText(String.valueOf(b.getString("key")));
           new LoadViewTask().execute();

        }



        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();

            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark);





        txt_search=edit.getText().toString();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadViewTask().execute();
            }
        });



    }
    private List<Posts> ParsingPostJSON(JSONArray response) {

        try {
            List<Posts> posts = new ArrayList<>();


            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();
                post.setid(o.optString("post_id", ""));
                post.settitle(o.optString("source_title", ""));
                post.setdes(o.optString("post_title", ""));
                post.setImage(o.optString("picture", ""));
                post.setdate(o.optString("short_date", ""));
                post.settime(o.optString("short_time", ""));
                post.setisvideo(o.optString("is_video",""));
                post.setIsgallery(o.optString("is_gallery",""));
                post.setimages_count(o.optString("images_count",""));
                post.setvisit(o.optString("hits",""));
                    posts.add(post);

            }

            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    private class LoadViewTask extends AsyncTask<Void, Void,Void>
    {String finaltxt;
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            Progress.setVisibility(View.VISIBLE);
             finaltxt=String.valueOf(edit.getText().toString());
        }

        @Override
        protected Void doInBackground(Void... params) {
           //
            APISendingData apiSendingData= new APISendingData(Search.this,"");
            JSONObject requestJsonObject= new JSONObject();
            try {


                requestJsonObject.put("search_term",finaltxt);
                apiSendingData.signUp(requestJsonObject, new APISendingData.OnSignupComplate() {
                    @Override
                    public void onSignUp(boolean success, JSONArray array_result) {
                        if(success == true){
                            //Toast.makeText(Search.this, "hiiii", Toast.LENGTH_SHORT).show();
                            Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(Search.this, ParsingPostJSON(array_result),"","",false,"finish");
                            recyclerView = (RecyclerView) findViewById(R.id.cardView1);
                            recyclerView.setAdapter(page2RecyclerViewAdapter);
                             recyclerView.setLayoutManager(new LinearLayoutManager(Search.this, LinearLayoutManager.VERTICAL, false));
                            // recyclerView.setAdapter(page2RecyclerViewAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            Progress.setVisibility(View.GONE);


                        }else{
                            Toast.makeText(Search.this, "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPostExecute(Void result)
        {


        }

    }


    public void loadData() {
     /*   recyclerView = (RecyclerView) findViewById(R.id.cardView);
                    Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(Search.this, posts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(page2RecyclerViewAdapter);
     mSwipeRefreshLayout.setRefreshing(false);*/
     //   new LoadViewTask().execute();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == android.R.id.home)
        {
//            Intent i=new Intent(Search.this,maintest.class);
//            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
