package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver;
import com.beloo.widget.chipslayoutmanager.layouter.breaker.IRowBreaker;
import com.codesgood.views.JustifiedTextView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class page3 extends AppCompatActivity {
    private Toolbar toolbar;
    JustifiedTextView descText;

    RecyclerView recyclerView1;
    TextView titleTextView,time,date,news,comment;
    TextView txtc,txtcom,txtvisit;
    Button show, hide;
    ImageButton image;
    ImageButton btng;
    RecyclerView recyclerView,recyclerViewtag;
    GridLayoutManager glm;
    TextView txttitle;
    ToggleButton likeImageView;

    String url_comment;
    String url;
    ProgressBar Progress;
    ProgressBar Progressc;
    RelativeLayout layoutR;
    String titlenews;
    String id;

    JSONObject obj;
    String back;
    /*ArrayList<String> title = new ArrayList<>();*/
    ArrayList<String> images = new ArrayList<>();
    int i;
    int isgallery;
    String picture,lable,id_category,title_category;
    String fontnews;
    String fontcomment;
    String fontstyle;
    Typeface customfont;
    SharedPreferences sharedpreferences;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

          /* for support version api19*/
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //progress
        Progress = (ProgressBar)findViewById(R.id.pd);
        Progressc = (ProgressBar)findViewById(R.id.pdComment);

       // setTitle("ورزشی");
        btng=(ImageButton) findViewById(R.id.btngallery);
        txttitle= (TextView)findViewById(R.id.txttitle);
        txtcom= (TextView)findViewById(R.id.txtcom);
        txtvisit= (TextView)findViewById(R.id.txtvisit);

        likeImageView= (ToggleButton)findViewById(R.id.likeImageView);
        titleTextView= (TextView)findViewById(R.id.titleTextView);
        descText = (JustifiedTextView) findViewById(R.id.description_text);

        date= (TextView)findViewById(R.id.date);
        time= (TextView)findViewById(R.id.time);
        news= (TextView)findViewById(R.id.news);
        comment= (TextView)findViewById(R.id.comment);
        Typeface typeface = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb.ttf");

       // txttitle.setTypeface (typefacetext);
        txttitle.setTypeface(typefacetext,Typeface.BOLD);
       // txttitle.setTextAppearance();
        descText.setTypeface (typefacetext);
        titleTextView.setTypeface (typefacetext);
       // txtc.setTypeface (typefacetext);
        txtcom.setTypeface (typefacenum);
        txtvisit.setTypeface (typefacenum);
        date.setTypeface (typefacenum);
        time.setTypeface (typefacenum);
       // btng.setTypeface (typefacetext);
        news.setTypeface (typefacetext);
        comment.setTypeface (typefacetext);

                //get font size
        sharedpreferences = getSharedPreferences("font",
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains("fontnews")) {
            fontnews =sharedpreferences.getString("fontnews", "");

        }
        if (sharedpreferences.contains("fontcomment")) {

            fontcomment =sharedpreferences.getString("fontcomment", "");
        }
        if (sharedpreferences.contains("fontstyle")) {

            fontstyle =sharedpreferences.getString("fontstyle", "");
            customfont= Typeface.createFromAsset(getApplication().getAssets(), "fonts/"+fontstyle);
            descText.setTypeface (customfont);
            txttitle.setTypeface (customfont);
        }
      // Toast.makeText(page3.this,String.valueOf((fontnews))+"\n"+String.valueOf((fontcomment)), Toast.LENGTH_LONG).show();

         layoutR = (RelativeLayout)findViewById(R.id.Rl);

        btng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // list_image=new ArrayList<Posts>();
                Intent i=new Intent(page3.this,pagegallery.class);
                i.putStringArrayListExtra("images",images);
               // i.putStringArrayListExtra("title",title);
                i.putExtra("label",lable);
                i.putExtra("isgallery","gallery");
                startActivity(i);
            }
        });
        image=(ImageButton) findViewById(R.id.newsimage);
image.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(page3.this,pagegallery.class);
        if(isgallery==1)
        {
            i.putStringArrayListExtra("images",images);
            //i.putStringArrayListExtra("title",title);
            i.putExtra("label",lable);
            i.putExtra("isgallery","gallery");
        }
       else
        {
            i.putExtra("picture",picture);
            i.putExtra("label",lable);
            i.putExtra("isgallery","picture");
        }

        startActivity(i);
       // Toast.makeText(page3.this,String.valueOf(("hoooo")), Toast.LENGTH_LONG).show();
    }
});




        show = (Button) findViewById(R.id.show);
        show.setTypeface (typefacetext);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide = (Button) findViewById(R.id.hide);
        hide.setTypeface (typefacetext);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setTransitionName("reveal");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Enter Your Comment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
             */   Intent intent = new Intent(getBaseContext(),comment.class);
                //Toast.makeText(page3.this,String.valueOf((titlenews)), Toast.LENGTH_LONG).show();
                intent.putExtra("title_news",String.valueOf((titlenews)));
                intent.putExtra("id",String.valueOf((id)));
/*
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(page3.this,fab,"reveal");
                ActivityCompat.startActivity(page3.this,intent, activityOptionsCompat.toBundle());
       */     }
        });


        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();
           // image.setImageResource(b.getInt("key"));
            url=b.getString("url");
            url_comment=b.getString("url_comment");
            id_category=b.getString("id_cat");
            title_category=b.getString("title_cat");
            back=b.getString("back");

        }
        //relative
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //tag
        recyclerViewtag = (RecyclerView) findViewById(R.id.recyclertag);


       new LoadViewTask().execute();

        likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] id = new String[1];
                try {
                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                            url, null,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //get data from one user
                                    String data = "";
                                    try {
                                        List<Posts> tag = new ArrayList<>();
                                        obj = response.getJSONObject("result");

                                        //Toast.makeText(page3.this, obj.getString("post_title"), Toast.LENGTH_SHORT).show();

                                        String tmpHtml = obj.getString("post_content_full");
                                        String htmlTextStr = Html.fromHtml(tmpHtml).toString();

                                        id[0] = obj.getString("post_id").toString();
                                        //not found user
                                        mydatabasehandler db = new mydatabasehandler(page3.this);
                                        String check = db.checkuser(id[0]);
                                        if (check == "false")

                                        {
                                            i = 1;
                                            likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_bookmarkred));

                                            Posts item = new Posts();
                                            //db = new mydatabasehandler(context);

                                            item.setid(id[0]);
                                            item.settitle(obj.getString("source_title").toString());
                                            String tmpHtml1 = obj.getString("post_title");
                                            String htmlTextStr1 = Html.fromHtml(tmpHtml1).toString();
                                            item.setdes(htmlTextStr1);
                                            item.setImage(obj.getString("picture"));
                                            item.setdate(obj.getString("short_date"));
                                            item.settime(obj.getString("short_time"));
                                            item.setimages_count(obj.optString("images_count", ""));
                                            item.setvisit(obj.getString("hits"));
                                            item.setcomment_count(obj.getString("comments_count"));
                                            item.setisvideo(obj.getString("is_video"));
                                            item.setIsgallery(obj.getString("is_gallery"));
                                            item.setcategory(id_category);

                                            db.additem(item);

                                        } else {
                                            likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_s));
                                            db.deluser(id[0]);
                                            //  Toast.makeText(page3.this,String.valueOf(id[0]), Toast                                                          .LENGTH_LONG).show();
                                        }


                                    }
                                    // Try and catch are included to handle any errors due to JSON
                                    catch (JSONException e) {
                                        // If an error occurs, this prints the error to the log
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // t.setText("Error: " + error.getMessage());

                        }
                    });
                    jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(3000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue requestQueue = Volley.newRequestQueue(page3.this);
                    requestQueue.add(jsonObjReq);


                }
                catch (Exception e)
                {
                    Toast.makeText(page3.this, "خطا", Toast.LENGTH_SHORT).show();
                }

            }
        });

       }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // this.menu = menu;
        getMenuInflater().inflate(R.menu.page3image, menu);
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
            Intent i=new Intent(page3.this,Search.class);

            startActivity(i);
            return true;
        } /*else if (id == R.id.fav) {
            Intent i=new Intent(page3.this,Bookmark.class);
            startActivity(i);
            return true;
        }*/ else if (id == R.id.ref) {
            Intent i=new Intent(page3.this,page3.class);
            i.putExtra("url",url);
            startActivity(i);
            return true;
        }else if (id == R.id.share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            //String shareBody = getResources().getString(R.string.content);
            String shareBody =String.valueOf(titlenews);
            shareBody = shareBody + "\n \n"+url+"\n\n";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        }else if (id == android.R.id.home)
        {
            if(back.equals("bookmark"))
            {
               // Toast.makeText(page3.this,String.valueOf(id_category), Toast.LENGTH_LONG).show();
               //
               /* Intent i=new Intent(page3.this,Bookmark.class);
                i.putExtra("idcategory",id_category);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);*/
                finish();
            }else  if(back.equals("finish"))
            {
                // Toast.makeText(page3.this,String.valueOf("boooooo"), Toast.LENGTH_LONG).show();
                 finish();

            }
            else if(back.equals("page2"))
            {

                Intent i = new Intent(page3.this, page2.class);
                i.putExtra("url", "http://api.arbika.ir/v1/category/" + id_category + "/news");
                i.putExtra("title", "hiii");
                i.putExtra("id", id_category);
                i.putExtra("title", title_category);
                // Toast.makeText(page3.this,String.valueOf(url)+"\n"+url_comment, Toast.LENGTH_LONG).show();
               finish();
                startActivity(i);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    private void ParsingPostJSONimage(JSONArray response) {
  /*      if (key.equals("category")) {*/
        try {
           // ArrayList<Posts> posts = new ArrayList<>();

            if(response.length()>=0) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject o = response.getJSONObject(i);
                    /*Posts post = new Posts();
                    // post.setid(o.optString("post_id", ""));
                    post.settitle(o.optString("title", ""));
                     post.setImage(o.optString("path", ""));

                    posts.add(post);*/
                    String temp = o.optString("path", "").replaceAll(" ", "%20");
                    images.add(i,temp);
                    //title.add(i,o.optString("title", ""));

                }

            }
           // return posts;

        } catch (JSONException e) {
            e.printStackTrace();
           // return null;
        }

    }
    private  List<Posts> ParsingPostJSONTag(JSONArray response) {
  /*      if (key.equals("category")) {*/
        try {
            List<Posts> posts = new ArrayList<>();

            if(response.length()>=0) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject o = response.getJSONObject(i);
                    Posts post = new Posts();
                    // post.setid(o.optString("post_id", ""));
                    post.settitle(o.optString("tag_title", ""));
                    // post.setdes(o.optString("post_content_full", ""));

                    posts.add(post);
                    Newstag_Recycler_View_Adapter adapter = new Newstag_Recycler_View_Adapter((ArrayList<Posts>) posts, page3.this);

                    ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getBaseContext())
                            .setChildGravity(Gravity.RIGHT)
                            //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL

                            //whether RecyclerView can scroll. TRUE by default
                            .setScrollingEnabled(true)
                            //set maximum views count in a particular row
                            //.setMaxViewsInRow(2)
                            //set gravity resolver where you can determine gravity for item in position.
                            //This method have priority over previous one
                            .setGravityResolver(new IChildGravityResolver() {
                                @Override
                                public int getItemGravity(int position) {
                                    return Gravity.RIGHT;
                                }
                            })
                            //you are able to break row due to your conditions. Row breaker should return true for that views
                            .setRowBreaker(new IRowBreaker() {
                                @Override
                                public boolean isItemBreakRow(@IntRange(from = 0) int position) {
                                    return position == 6 || position == 11 || position == 2;
                                }
                            })
                            //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                            .setOrientation(ChipsLayoutManager.HORIZONTAL)
                            // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                            //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                            .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                            // whether strategy is applied to last row. FALSE by default

                            .withLastRow(true)
                            .build();

                    recyclerViewtag.setLayoutManager(chipsLayoutManager);
                    recyclerViewtag.setAdapter(adapter);
                    // recyclerViewtag.smoothScrollToPosition(0);
                    recyclerViewtag.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                    recyclerViewtag.setNestedScrollingEnabled(false);


                }
            }
            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    private  List<Posts> ParsingPostJSONRelative(JSONArray response) {
  /*      if (key.equals("category")) {*/
        try {
            List<Posts> posts = new ArrayList<>();

            if(response.length()>=0) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject o = response.getJSONObject(i);
                    Posts post = new Posts();
                     post.setid(o.optString("post_id", ""));
                    post.settitle(o.optString("post_title", ""));
                     post.setImage(o.optString("picture", ""));
                    post.setisvideo(o.optString("is_video", ""));
                    posts.add(post);


                            Related_Recycler_View_Adapter adapter = new Related_Recycler_View_Adapter(getApplication(),posts);
                            recyclerView.setHasFixedSize(true);
                            if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                                glm = new GridLayoutManager(page3.this, 2);
                            }
                            else{
                                glm = new GridLayoutManager(page3.this, 2);
                            }

                            recyclerView.setLayoutManager(glm);
                            recyclerView.setAdapter(adapter);



                }
            }
            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    private void GetComment(final String url_comment) {

                                    APIGettingPosts apiGettingPosts = new APIGettingPosts(page3.this,url_comment,"comment");
                                    apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

                                        @Override
                                        public void onReceived(List<Posts> posts) {

                                            if(posts == null || posts.isEmpty()){
                                               // Toast.makeText(page3.this, "خطا در دریافت اطلاعات نظرات", LENGTH_SHORT).show();
                                                 //Toast.makeText(page3.this, url_comment, Toast.LENGTH_SHORT).show();
                                             txtc=(TextView)findViewById(R.id.txtcomnon);
                                                txtc.setVisibility(View.VISIBLE);
                                                Progressc.setVisibility(View.GONE);
                                            }else {
                                                recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
                                                                Comment_Recycler_View_Adapter adapter1 = new Comment_Recycler_View_Adapter(page3.this,(ArrayList<Posts>) posts,id,fontcomment);
                                                                recyclerView1.setLayoutManager(new LinearLayoutManager(page3.this, LinearLayoutManager.VERTICAL, false));
                                                                recyclerView1.setAdapter(adapter1);
                                                                            Progressc.setVisibility(View.GONE);
                                                                                                                                                                 /*   ArrayList<Posts> list= new ArrayList();
                                                                                                                                                                    list.add(new Posts(Posts.comment_TYPE,"Hello. This is the Text-only View Type. Nice to meet you"));
                                                                                                                                                                    list.add(new Posts(Posts.commenttocomment_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.wt));
                                                                                                                                                                    list.add(new Posts(Posts.comment_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.raw.sound));
                                                                                                                                                                    list.add(new Posts(Posts.commenttocomment_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.snow));

                                                                                                                                                                    Page2_Comment_Recycler_View_Adapter adapter1 = new Page2_Comment_Recycler_View_Adapter(this,list);
                                                                                                                                                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);*/




                                            }
                                        }
                                    }
                                    );

        }


    private class LoadViewTask extends AsyncTask<Void, Void,Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {

            // recyclerView.setVisibility(View.GONE);
            //recyclerViewtag.setVisibility(View.GONE);
            //Progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //get data from one user

                                try {

                                    obj = response.getJSONObject("result");
                                     String tmpHtml = obj.getString("post_content_full");
                                    String htmlTextStr = Html.fromHtml(tmpHtml).toString();
                                    titlenews = obj.getString("post_title").toString();
                                    id = obj.getString("post_id").toString();
                                    if (fontnews != null) {
                                        txttitle.setTextSize(Integer.parseInt(fontnews),Typeface.BOLD);
                                        descText.setTextSize(Integer.parseInt(fontnews));
                                    }
                                    txttitle.setText(obj.getString("post_title"));
                                    lable = obj.getString("post_title");
                                    // descText.setText(tmpHtml);

                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                        descText.setText(Html.fromHtml(tmpHtml, Html.FROM_HTML_MODE_LEGACY));
                                    } else {
                                        descText.setText(Html.fromHtml(tmpHtml));
                                    }
                                    //
                                    String temp = obj.getString("picture").replaceAll(" ", "%20");
                                    Picasso.with(getBaseContext()).load(temp).into(image);
                                    txtcom.setText(obj.getString("comments_count"));
                                    txtvisit.setText(obj.getString("hits"));
                                    titleTextView.setText(obj.getString("source_title"));
                                    date.setText(obj.getString("short_date"));
                                    time.setText(obj.getString("short_time"));
                                    String is_gallery = obj.getString("is_gallery");

                                    mydatabasehandler db = new mydatabasehandler(page3.this);
                                    String check = db.checkuser(obj.getString("post_id"));
                                    if (check == "true") {
                                        likeImageView.setChecked(true);
                                        likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_bookmarkred));
                                    } else {
                                        likeImageView.setChecked(false);
                                        likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_s));
                                    }

                                    JSONArray Array_Tag = obj.getJSONArray("tags");
                                    JSONArray Array_NewsRelative = obj.getJSONArray("related");
                                    Progress.setVisibility(View.GONE);
                                    layoutR.setVisibility(View.VISIBLE);



                            /*تگ*/
                                    ParsingPostJSONTag(Array_Tag);

                            /*اخبار مرتبط*/
                                    ParsingPostJSONRelative(Array_NewsRelative);

                                    if (is_gallery.equals("true")) {
                           /* گالری*/
                                        JSONArray Array_image = obj.getJSONArray("images");
                                        ParsingPostJSONimage(Array_image);
                                        isgallery = 1;
                                        btng.setVisibility(View.VISIBLE);
                                    } else {
                                        picture = obj.getString("picture");
                                        //lable=obj.getString("post_title").toString();
                                    }

                            /*نظرات*/
                                    GetComment(url_comment);


                                    // show.setVisibility(View.VISIBLE);


                                }
                                // Try and catch are included to handle any errors due to JSON
                                catch (JSONException e) {
                                    // If an error occurs, this prints the error to the log
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // t.setText("Error: " + error.getMessage());

                    }
                })
                {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        try {
                            Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                            if (cacheEntry == null) {
                                cacheEntry = new Cache.Entry();
                            }
                            final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                            final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                            long now = System.currentTimeMillis();
                            final long softExpire = now + cacheHitButRefreshed;
                            final long ttl = now + cacheExpired;
                            cacheEntry.data = response.data;
                            cacheEntry.softTtl = softExpire;
                            cacheEntry.ttl = ttl;
                            String headerValue;
                            headerValue = response.headers.get("Date");
                            if (headerValue != null) {
                                cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                            }
                            headerValue = response.headers.get("Last-Modified");
                            if (headerValue != null) {
                                cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                            }
                            cacheEntry.responseHeaders = response.headers;
                            final String jsonString = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers));

                            return Response.success(new JSONObject(jsonString), cacheEntry);

                        } catch (UnsupportedEncodingException | JSONException e) {
                            return Response.error(new ParseError(e));
                        }
                    }

                    @Override
                    protected void deliverResponse(JSONObject response) {
                        super.deliverResponse(response);
                    }

                    @Override
                    public void deliverError(VolleyError error) {
                        super.deliverError(error);
                    }

                    @Override
                    protected VolleyError parseNetworkError(VolleyError volleyError) {
                        return super.parseNetworkError(volleyError);
                    }
                };

                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(3000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(page3.this);
                requestQueue.add(jsonObjReq);

            }
            catch (Exception e) {
                // If an error occurs, this prints the error to the log
                Toast.makeText(page3.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            return null;


        }


        @Override
        protected void onPostExecute(Void result)
        {


        }
    }


   /* private List<Posts> ParsingPostJSON(JSONArray response) {
  *//*      if (key.equals("category")) {*//*
        try {
            List<Posts> posts = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();



                    post.setid(o.optString("comment_id", ""));
                    post.settitle(o.optString("commenter", ""));
                    post.setdes(o.optString("comment_text", ""));

                    post.setreplies(o.getJSONArray("replies"));
                    if(post.getreplies().length()>0)

                    { post.setisReply("true");
                        posts.add(post);
                        for (int ii = 0; ii < post.getreplies().length(); ii++) {
                            JSONObject o1 = post.getreplies().getJSONObject(ii);
                            post.setid(o1.optString("comment_id", ""));
                            post.settitle(o1.optString("commenter", ""));
                            post.setdes(o1.optString("comment_text", ""));
                            post.setisReply("false");
                            posts.add(post);
                        }
                    }

            }

            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }*/
}
