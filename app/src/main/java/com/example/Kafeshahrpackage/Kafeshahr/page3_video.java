package com.example.Kafeshahrpackage.Kafeshahr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;


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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bg.devlabs.fullscreenvideoview.FullscreenVideoView;
import pub.devrel.easypermissions.EasyPermissions;


import static android.widget.RelativeLayout.CENTER_IN_PARENT;


public class page3_video extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
String sizevideo;
    VideoView videoView;
    double length;
    private volatile byte[] videoBuffer;
    TextView txtc;
     String temp;
    int isgallery;
    String picture,lable;
    private Toolbar toolbar;
    String url,url_comment;
    ArrayList<String> images = new ArrayList<>();
    long max;
    private ProgressDialog mProgressDialog;
    // Progress Dialog
    String modifiedFileSize;
     long file_size;
    String heapsize;
    TextView descText,txttitle,news,comment;
    Button show, hide;
    String titlenews;
    String id;
    RelativeLayout layoutR;
    ProgressBar Progress,Progressc;
    String title,id_category,title_category;

    RecyclerView recyclerView,recyclerView1,recyclerViewtag;
    GridLayoutManager glm;
    TextView txtcom,txtvisit;
    ImageButton btnplay;
    FullscreenVideoView fullscreenVideoView;
    String urlvideo,back;
    JSONObject obj;
    ToggleButton likeImageView;
    int i;
    String fontnews;
    String fontcomment;
    String fontstyle;
    Typeface customfont;
    SharedPreferences sharedpreferences,sharedpreferencesheap;
    private static final int WRITE_REQUEST_CODE = 300;
       int ii=1;
    private static final String TAG = MainActivity.class.getSimpleName();
    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3_video);

          /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        txtcom= (TextView)findViewById(R.id.txtcom);
        txtvisit= (TextView)findViewById(R.id.txtvisit);
        btnplay=(ImageButton)findViewById(R.id.btnplay);
        likeImageView= (ToggleButton)findViewById(R.id.likeImageView);
        Typeface typefacenum = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");

        txtcom.setTypeface (typefacenum);
        txtvisit.setTypeface (typefacenum);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /*   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle("");


        if (i==1) btnplay.setVisibility(View.GONE);
        fullscreenVideoView = (FullscreenVideoView) findViewById(R.id.fullscreenVideoView);
        fullscreenVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplication(), String.valueOf(ii), Toast.LENGTH_LONG).show();
               //if (ii==1) {

                   Intent i = new Intent(page3_video.this, pagegallery.class);
                   if (isgallery == 1) {
                       i.putStringArrayListExtra("images", images);
                       //i.putStringArrayListExtra("title",title);
                       i.putExtra("label", lable);
                       i.putExtra("isgallery", "gallery");
                   } else {
                       i.putExtra("picture", picture);
                       i.putExtra("label", lable);
                       i.putExtra("isgallery", "picture");
                   }

                   startActivity(i);
               //}
               // ii++;
            }
        });
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
               // fullscreenVideoView.onCancelPendingInputEvents();
                loadvideo(urlvideo);
                btnplay.setVisibility(View.GONE);
            }
        });
//relative
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //tag
        recyclerViewtag = (RecyclerView) findViewById(R.id.recyclertag);


        Progress = (ProgressBar)findViewById(R.id.pd);
        Progressc = (ProgressBar)findViewById(R.id.pdComment);
        layoutR = (RelativeLayout)findViewById(R.id.Rl);

        getSupportActionBar().setWindowTitle("ff");
        descText = (TextView) findViewById(R.id.description_text);
        txttitle = (TextView) findViewById(R.id.txttitle);
        news = (TextView) findViewById(R.id.news);
        comment = (TextView) findViewById(R.id.comment);
       // Typeface typefacenum = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb.ttf");

        txttitle.setTypeface(typefacetext,Typeface.BOLD);
        descText.setTypeface(typefacetext);
        comment.setTypeface(typefacetext);
        news.setTypeface(typefacetext);

        sharedpreferencesheap = getSharedPreferences("heap",
                Context.MODE_PRIVATE);

        if (sharedpreferencesheap.contains("heapsize")) {
                heapsize=sharedpreferencesheap.getString("heapsize", "");
//Toast.makeText(this,sharedpreferencesheap.getString("heapsize", ""),Toast.LENGTH_LONG).show();
        }


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
           // Toast.makeText(page3_video.this,url, Toast.LENGTH_LONG).show();

        }

        new LoadViewTask().execute();
        likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] id = new String[1];
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //get data from one user
                                String data="";
                                try {
                                    List<Posts> tag = new ArrayList<>();
                                    obj = response.getJSONObject("result");

                                    //Toast.makeText(page3.this, obj.getString("post_title"), Toast.LENGTH_SHORT).show();

                                    String tmpHtml = obj.getString("post_content_full");
                                    String htmlTextStr = Html.fromHtml(tmpHtml).toString();

                                    id[0] =obj.getString("post_id").toString();
                                    //not found user
                                    mydatabasehandler db=new mydatabasehandler(page3_video.this);
                                    String check=db.checkuser(id[0]);
                                    if(check=="false")
                                    {  Posts item = new Posts();
                                        //db = new mydatabasehandler(context);

                                        item.setid(id[0]);
                                        item.settitle(obj.getString("source_title").toString());
                                        String tmpHtml1 = obj.getString("post_title");
                                        String htmlTextStr1 = Html.fromHtml(tmpHtml1).toString();
                                        item.setdes(htmlTextStr1);
                                        item.setImage(obj.getString("picture"));
                                        item.setdate(obj.getString("short_date"));
                                        item.settime(obj.getString("short_time"));
                                        item.setimages_count(obj.optString("images_count",""));
                                        item.setvisit(obj.getString("hits"));
                                        item.setcomment_count(obj.getString("comments_count"));
                                        item.setisvideo(obj.getString("is_video"));
                                        item.setIsgallery(obj.getString("is_gallery"));
                                        item.setcategory(id_category);

                                        db.additem(item);

                                        i = 1;
                                        likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3_video.this, R.drawable.ic_bookmarkred));
                                    }
                                    else
                                    {
                                        likeImageView.setBackgroundDrawable(ContextCompat                                                                .getDrawable(page3_video.this, R.drawable.ic_s));
                                        db.deluser(id[0]);
                                      //  Toast.makeText(page3_video.this,String.valueOf(id[0]), Toast                                                          .LENGTH_LONG).show();
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
                RequestQueue requestQueue= Volley.newRequestQueue(page3_video.this);
                requestQueue.add(jsonObjReq);






            }
        });













        String fullScreen = getIntent().getStringExtra("fullScreenInd");
        if ("y".equals(fullScreen)) {
            /*FrameLayout f=(FrameLayout)findViewById(R.id.frame);
            f.setMinimumHeight(600);*/
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            //  params.addRule(ALIGN_PARENT_BOTTOM);
            params.addRule(CENTER_IN_PARENT);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            videoView.setLayoutParams(params);
            // getSupportActionBar().hide();

        }




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
        fab.setTransitionName("reveal");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enter Your Comment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(),comment.class);
                //Toast.makeText(page3.this,String.valueOf((titlenews)), Toast.LENGTH_LONG).show();
                intent.putExtra("title_news",String.valueOf((titlenews)));
                intent.putExtra("id",String.valueOf((id)));

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(page3_video.this,fab,"reveal");
                ActivityCompat.startActivity(page3_video.this,intent, activityOptionsCompat.toBundle());
            }
        });



       /* List<Main_Data> data = fill_with_data();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Related_Recycler_View_Adapter adapter = new Related_Recycler_View_Adapter(data, getApplication());
        recyclerView.setHasFixedSize(true);
        if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            glm = new GridLayoutManager(this, 2);
        }
        else{
            glm = new GridLayoutManager(this, 2);
        }

        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);*/


       /* ArrayList<Posts> posts= new ArrayList();
        posts.add(new Posts(Posts.comment_card));
        posts.add(new Posts(Posts.commenttocomment_card));
        posts.add(new Posts(Posts.comment_card));
        posts.add(new Posts(Posts.commenttocomment_card));

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
        adapter1 = new MultiViewTypeAdapter(posts,page3_video.this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(page3_video.this, LinearLayoutManager.VERTICAL, false));
        recyclerView1.setAdapter(adapter1);*/







    }

//
/*public List<Main_Data> fill_with_data() {

    List<Main_Data> data = new ArrayList<>();


   *//* data.add(new Main_Data("0","لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee));
    data.add(new Main_Data("0", "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee1));
    data.add(new Main_Data("0", "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee2));
    data.add(new Main_Data("0", "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee3));
    data.add(new Main_Data("0", "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee4));
    data.add(new Main_Data("0", "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است", R.drawable.imagee5));
   *//* return data;
}*/

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.page3video, menu);
        menu.findItem(R.id.down).setTitle("Your Text");

      /*  MenuItem item = menu.add(Menu.NONE, R.id.down, 1, "info");
        item.setTitle(sizevideo);
        item.setIcon(R.drawable.ic_download);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS & MenuItem.SHOW_AS_ACTION_WITH_TEXT);
*/
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
           // String shareBody = getResources().getString(R.string.content);
            String shareBody =String.valueOf(titlenews);
            shareBody = shareBody + "\n \n https://play.google.com/store/apps/details?id=the.package.id \n\n";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

            return true;
        } /*else if (id == R.id.action_info) {
            return true;
        }*/
        else if (id == R.id.ref) {
            Intent i=new Intent(page3_video.this,page3_video.class);
            i.putExtra("url",url);
            startActivity(i);
            return true;
        }
        /* else if (id == R.id.filter) {
            {

                View menuItemView = findViewById(R.id.filter);
                //showFilterPopup(menuItemView);

                return true;
            }
        } */else if (id == android.R.id.home) {
            if(back.equals("bookmark"))
            {
                // Toast.makeText(page3.this,String.valueOf("boooooo"), Toast.LENGTH_LONG).show();
                //
                Intent i=new Intent(page3_video.this,Bookmark.class);
                i.putExtra("idcategory","all");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
                finish();
            }else  if(back.equals("finish"))
            {
                // Toast.makeText(page3.this,String.valueOf("boooooo"), Toast.LENGTH_LONG).show();
                finish();

            }
            else if(back.equals("page2"))
            {
                Intent i = new Intent(page3_video.this, page2.class);
                i.putExtra("url", "http://api.arbika.ir/v1/category/" + id_category + "/news");
                i.putExtra("title", "hiii");
                i.putExtra("id", id_category);
                i.putExtra("title", title_category);
                // Toast.makeText(page3.this,String.valueOf(url)+"\n"+url_comment, Toast.LENGTH_LONG).show();
                startActivity(i);
            }
            return true;
        } else if (id == R.id.down) {
            //try {
            //String url = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";

            showDialog(0);

            return true;
        } if (id == R.id.search) {
            Intent i=new Intent(page3_video.this,Search.class);

            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:


                return new AlertDialog.Builder(this)
                        //.setIcon(R.drawable.ic_launcher)
                        .setTitle("دانلود")

                        .setMessage("آِیا مایل به دریافت فایل به حجم "+sizevideo+" مگابایت هستید؟")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                     //   Toast.makeText(getApplicationContext(), urlvideo, Toast.LENGTH_LONG).show();
                                        //new DownloadFileFromURL().execute();
                                        if (isConnectingToInternet()) {
                                            if (CheckForSDCard.isSDCardPresent()) {

                                                //check if app has permission to write to the external storage.
                                                if (EasyPermissions.hasPermissions(page3_video.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                                    //Get the URL entered

                                                    if(checksize())
                                                    {
                                                         new DownloadFile().execute(urlvideo);
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(getApplicationContext(), "فضای حافظه برای دانلود کافی نیست,برای تعییر به بخش مدیریت حافظه مراجعه کنید.", Toast.LENGTH_LONG).show();
                                                    }

                                                } else {
                                                    //If permission is not present request for the same.
                                                    EasyPermissions.requestPermissions(page3_video.this, "write", WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                                                }


                                            } else {
                                                Toast.makeText(getApplicationContext(),
                                                        "SD Card not found", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),
                                                    "ارتباط خودرا با اینترنت برقرار کنید.", Toast.LENGTH_LONG).show();

                                        }

                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        openDownloadedFolder();
                                        Toast.makeText(getBaseContext(),
                                                "Cancel clicked!", Toast.LENGTH_LONG).show();
                                    }
                                }
                        )
                        .create();




            default:
                return null;
        }
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
    private List<Posts> ParsingPostJSONTag(JSONArray response) {
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
                    Newstag_Recycler_View_Adapter adapter = new Newstag_Recycler_View_Adapter((ArrayList<Posts>) posts, page3_video.this);

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
    private List<Posts> ParsingPostJSONRelative(JSONArray response) {
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

                    posts.add(post);


                    Related_Recycler_View_Adapter adapter = new Related_Recycler_View_Adapter(getApplication(),posts);
                    recyclerView.setHasFixedSize(true);
                    if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                        glm = new GridLayoutManager(page3_video.this, 2);
                    }
                    else{
                        glm = new GridLayoutManager(page3_video.this, 2);
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













        APIGettingPosts apiGettingPosts = new APIGettingPosts(page3_video.this,url_comment,"comment");
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

                                                                                                                     /*//نظرات*/
                                            // ArrayList<Posts> posts= new ArrayList();
                                            //  posts.add(new Posts(Posts.comment_card));
                                                                                                                                       /* posts.add(new Posts(Posts.commenttocomment_card));
                                                                                                                                        posts.add(new Posts(Posts.comment_card));
                                                                                                                                        posts.add(new Posts(Posts.commenttocomment_card));
                                                                                                                    */
                                            recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
                                            Comment_Recycler_View_Adapter adapter1 = new Comment_Recycler_View_Adapter(page3_video.this,(ArrayList<Posts>) posts,id,fontcomment);
                                            recyclerView1.setLayoutManager(new LinearLayoutManager(page3_video.this, LinearLayoutManager.VERTICAL, false));
                                            recyclerView1.setAdapter(adapter1);
                                            Progressc.setVisibility(View.GONE);
                                                                                                                                  /*  ArrayList<Posts> list= new ArrayList();
                                                                                                                                      list.add(new Posts(Posts.comment_TYPE,"Hello. This is the Text-only View Type. Nice to meet you"));
                                                                                                                                        list.add(new Posts(Posts.commenttocomment_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.wt));
                                                                                                                                        list.add(new Posts(Posts.comment_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.raw.sound));
                                                                                                                                        list.add(new Posts(Posts.commenttocomment_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.snow));

                                                                                                                                        Page2_Comment_Recycler_View_Adapter adapter1 = new Page2_Comment_Recycler_View_Adapter(this,list);
                                                                                                                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
                                                                                                                                    */



                                        }
                                    }
                                }
        );

    }

    public void loadvideo(String videoUrl )
{
    Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);




             //"http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";
    fullscreenVideoView.videoUrl(videoUrl)
            .canPause(true)
            .canSeekBackward(true)
            .canSeekForward(true)
            .enableAutoStart();
             /**/
               /* .playDrawable(R.drawable.play)
                .pauseDrawable(R.drawable.pause)
                .fastForwardDrawable(R.drawable.f)
                .rewindDrawable(R.drawable.rewind)
                .enterFullscreenDrawable(R.drawable.fullscreenbutton)
                .exitFullscreenDrawable(R.drawable.fullscreenexit)

                .progressBarColor(R.color.colorAccent)
                .portraitOrientation(PortraitOrientation.DEFAULT)
                .landscapeOrientation(LandscapeOrientation.DEFAULT);*/

               /* .landscapeOrientation(LandscapeOrientation.DEFAULT)*/
/*.enableAutoStart()*/
}




    //Open downloaded folder
    private void openDownloadedFolder() {
        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {

            //Get Download Directory File
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + "Kafeshahr Downloads");

            //If file is not present then display Toast
            if (!apkStorage.exists())
                Toast.makeText(page3_video.this, "Right now there is no directory. Please download some file first.", Toast.LENGTH_SHORT).show();

            else {

                //If directory is present Open Folder

                /** Note: Directory will open only if there is a app to open directory like File Manager, etc.  **/

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/" + "Kafeshahr Downloads");
                intent.setDataAndType(uri, "file/*");
                startActivity(Intent.createChooser(intent, "Open Download Folder"));
            }

        } else
            Toast.makeText(page3_video.this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

    }

    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, page3_video.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
        //url = editTextUrl.getText().toString();
        new DownloadFile().execute(urlvideo);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }


    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(page3_video.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = timestamp + "_" + fileName;

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "Kafeshahr/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "دانلود ناموفق";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();
        }
    }
    private float writeToSD() throws IOException {
        String folder = Environment.getExternalStorageDirectory() + File.separator + "Kafeshahr/";

        File directory = new File(folder);

        if (!directory.exists()) {
            directory.mkdirs();
        }   long size = 0;
        size= getFileFolderSize(directory);
        float sizeMB = (float) size / 1024 / 1024;
        String s = " MB";

       /* lb.setText("سایز فعلی پوشه تصاویر:"+String.valueOf(String.format("%.2f", sizeMB))+"مگابایت");
        if (sizeMB < 1) {
            sizeMB = (float) size / 1024;
            s = " KB";

            lb.setText("سایز فعلی پوشه تصاویر:"+String.valueOf(String.format("%.2f", sizeMB))+"کیلوبایت");
        }*/
       return sizeMB;
    }
    public static long getFileFolderSize(File dir) {
        long size = 0;
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else
                    size += getFileFolderSize(file);
            }
        } else if (dir.isFile()) {
            size += dir.length();
        }
        return size;
    }




    private class getsizevideofromurl extends AsyncTask<Void,Void,Void>{
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Void... params) {

            try {
              //  Toast.makeText(getApplicationContext(), urlvideo, Toast.LENGTH_LONG).show();
           URL url = new URL(urlvideo);
           /*  URLConnection connection = url.openConnection();
            connection.connect();*/
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept-Encoding", "identity"); // <--- Add this line
               // int length = connection.getContentLength();
                // getting file length
                long lengthOfFile=0;
                if(connection.getContentLength()>0 )
                {
                     lengthOfFile = connection.getContentLength();
                    float sizeMB = 0;

                    sizeMB = (float) lengthOfFile / 1024 / 1024;

                    String s = " MB";
                    Log.d("siiiiiiiiiiiiiiiiize",String.valueOf(String.format("%.2f", sizeMB)));
                    sizevideo=String.format("%.2f", sizeMB);
                  //  Toast.makeText(page3_video.this,String.valueOf(sizeMB), Toast.LENGTH_LONG).show();
                   // setTitle(String.valueOf(String.format("%.2f", sizeMB)));
                   //setTitle(""+String.format("%.2f", sizeMB));

                }


        }

        catch (IOException e) {
            Toast.makeText(getApplicationContext(), "خطا", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {

          //  Toast.makeText(page3_video.this,sizevideo, Toast.LENGTH_LONG).show();
        }
    }
            private boolean checksize()
            {
                try {
                    float size=writeToSD();
                    size=size+Float.parseFloat(sizevideo);
                    if(size<=Float.parseFloat(heapsize))
                    {
                       /* Toast.makeText(getApplicationContext(), String.valueOf(size+"\n"+heapsize),       Toast.LENGTH_LONG).show();*/
                        return true;
                    }
                    else
                    {
                        return  false;
                    }
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
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
                                String data="";
                                try {
                                    List<Posts> tag = new ArrayList<>();
                                    obj = response.getJSONObject("result");

                                    //Toast.makeText(page3.this, obj.getString("post_title"), Toast.LENGTH_SHORT).show();

                                    String tmpHtml = obj.getString("post_content_full");
                                    String htmlTextStr = Html.fromHtml(tmpHtml).toString();
                                    titlenews=obj.getString("post_title").toString();
                                    id=obj.getString("post_id").toString();
                                    txtcom.setText(obj.getString("comments_count").toString());
                                    txtvisit.setText(obj.getString("hits").toString());
                                    lable = obj.getString("post_title");
                                    String is_gallery = obj.getString("is_gallery");
                                    if (fontnews != null) {
                                        txttitle.setTextSize(Integer.parseInt(fontnews),Typeface.BOLD);
                                        descText.setTextSize(Integer.parseInt(fontnews));
                                    }

                                    txttitle.setText(obj.getString("post_title"));
                                    // descText.setText(htmlTextStr);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        descText.setText(Html.fromHtml(tmpHtml,Html.FROM_HTML_MODE_LEGACY));
                                    } else {
                                        descText.setText(Html.fromHtml(tmpHtml));
                                    }

                                    urlvideo=obj.getString("video_file_name");


                                    new   getsizevideofromurl().execute();

                                  /*  Toast.makeText(page3_video.this, String.valueOf("size film: \n"+getFileSize(urlvideo)), Toast.LENGTH_LONG).show();*/

                                    //size_url=getFileSize(urlvideo);

                                    mydatabasehandler db=new mydatabasehandler(page3_video.this);
                                    String check=db.checkuser(obj.getString("post_id"));
                                    if(check=="true")
                                    {likeImageView.setChecked(true);
                                /*likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_bookmarkred));*/}
                                    else {
                                        likeImageView.setChecked(false);
                                /* likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(page3.this, R.drawable.ic_s));*/}


                                    JSONArray Array_Tag = obj.getJSONArray("tags");
                                    JSONArray Array_NewsRelative = obj.getJSONArray("related");
                                    Progress.setVisibility(View.GONE);
                                    layoutR.setVisibility(View.VISIBLE);

                                    if (is_gallery.equals("true")) {
                           /* گالری*/
                                        JSONArray Array_image = obj.getJSONArray("images");
                                        ParsingPostJSONimage(Array_image);
                                        isgallery = 1;

                                    } else {
                                        picture = obj.getString("picture");
                                        //lable=obj.getString("post_title").toString();
                                    }

                            /*تگ*/
                                    ParsingPostJSONTag(Array_Tag);

                            /*اخبار مرتبط*/
                                    ParsingPostJSONRelative(Array_NewsRelative);



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
                RequestQueue requestQueue= Volley.newRequestQueue(page3_video.this);
                requestQueue.add(jsonObjReq);

            } catch (Exception e) {
                // If an error occurs, this prints the error to the log
                Toast.makeText(page3_video.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            return null;


        }


        @Override
        protected void onPostExecute(Void result)
        {


        }
    }
}



