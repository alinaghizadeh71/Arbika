package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

import com.android.volley.Cache;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class maintest extends AppCompatActivity {

    RecyclerView recyclerView;
    Main_Recycler_View_Adapter adapter;
JSONObject jsoncache;
    JSONArray arraycache,arrayStatusmeta;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    TextView titleTextView;
    String android_id;
    Handler h;
     ArrayList<Posts> mylist;
    ArrayList listdel;
    mydatabasehandler db;
    String url="http://api.arbika.ir/v1/categories";
    List<String> pos = new ArrayList<>();
     GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_rec);
          /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //btn0= (ImageButton) findViewById(R.id.btn1);
        //btn1= (ImageButton) findViewById(R.id.btn2);
       // btn2= (ImageButton) findViewById(R.id.btn3);
        //btn3= (ImageButton) findViewById(R.id.btn4);

         android_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);

      //  Toast.makeText(maintest.this,android_id,Toast.LENGTH_LONG).show();


        titleTextView= (TextView) findViewById(R.id.titleTextView);
        Typeface typefacetext = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
        titleTextView.setTypeface (typefacetext);


         db=new mydatabasehandler(this);
        mylist = new ArrayList<Posts>();
        mylist=db.gettilelist();
if(!(mylist==null)) {
   // Toast.makeText(this, String.valueOf(mylist.size()), Toast.LENGTH_SHORT).show();
}
      /*  for(int i=0;i<mylist.size();i++) {

           // Toast.makeText(this, String.valueOf(mylist.get(i)), Toast.LENGTH_SHORT).show();
        }*/
        Bundle b = getIntent().getExtras();
        if (b != null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            listdel = b.getStringArrayList("listdel");


        }
         recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        GridLayoutManager.SpanSizeLookup ssl = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {


               // if (position == 1 || position ==2)  return 2;
                for (int i = 0; i < pos.size(); i++)
                {/*Toast.makeText(getBaseContext(), String.valueOf(i)+"\n"+String.valueOf(pos.get(i)), Toast.LENGTH_SHORT).show();*/
                    if(i==position)
                    {
                        if(pos.get(i).equals("1")) return 1;
                        if(pos.get(i).equals("2")) return 2;
                        if(pos.get(i).equals("3")) return 3;

                    }
                }



                return 1;
            }
        };
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
       gridLayoutManager = (GridLayoutManager) layoutManager;
        gridLayoutManager.setSpanSizeLookup(ssl);


        isNetworkAvailable(this);





     /*   String url_category = "http://api.arbika.ir/v1/categories";
        APIGettingPosts apiGettingPosts = new APIGettingPosts(maintest.this,url_category,"category");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(maintest.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();

                }else {



                    adapter = new Main_Recycler_View_Adapter(posts, getApplication());
//  glm.setSpanSizeLookup(ssl);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);



                }
            }
        });
*/



        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

       // Main_Recycler_View_Adapter adapter = new Main_Recycler_View_Adapter(data, getApplication());


       // recyclerView.setHasFixedSize(true);
      //  gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);


     /*   GridLayoutManager glm = new GridLayoutManager(this, 3);
        GridLayoutManager.SpanSizeLookup ssl = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                //if (position == 0) return 3;
                if (position == 0 || position ==3) return 2;
                return 1;

               *//* if (position == 0) return 3;
                if (position == 1 || position ==4) return 2;
                return 1;*//*
            }
        };*/




      /*  if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

             glm = new GridLayoutManager(this, 3);
        }
        else{
            glm = new GridLayoutManager(this, 5);
        }*/



       /* ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        flipper.startFlipping();
        flipper.setFocusable(true);
        flipper.requestFocus();*/

        String url_slideshow = "http://api.arbika.ir/v1/selectedNews";
        APIGettingPosts apiGettingPosts1 = new APIGettingPosts(maintest.this,url_slideshow,"slideshow");
        apiGettingPosts1.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(maintest.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();

                }else {
                    AdapterViewFlipper adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.adapterViewFlipper); // get the reference of AdapterViewFlipper
// Custom Adapter for setting the data in Views
                    adapterViewFlipper.setFlipInterval(3000);
                    adapterViewFlipper.startFlipping();

// set auto start for flipping between views
                    adapterViewFlipper.setAutoStart(true);
                    YourFlipperAdapter yourFlipperAdapter = new YourFlipperAdapter(maintest.this, (ArrayList<Posts>) posts);
                    adapterViewFlipper.setAdapter(yourFlipperAdapter); // set adapter for AdapterViewFlipper
// set interval time for flipping between views


                }
            }
        });















        //draw menu
        IProfile profile = new ProfileDrawerItem().withName("کافه شهر").withEmail("www.Arbika.ir").withIcon(R.drawable.usercom);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true).withHeaderBackground(R.color.colorAccent)
                .addProfiles(
                        profile,
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Gmail Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .withTextColor(Color.WHITE)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("ویرایش اطلاعات کاربری").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("اخبار آفلاین").withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(3),

                        new PrimaryDrawerItem().withName("مدیریت کاشی ها").withIcon(FontAwesome.Icon.faw_eye)
 .withIdentifier(4),
                        new PrimaryDrawerItem().withName("مدیریت حافظه").withIcon(FontAwesome.Icon.faw_eye).withIdentifier(2),
                        new PrimaryDrawerItem().withName("مدیریت فونت").withIcon(FontAwesome.Icon.faw_eye)
                                .withIdentifier(5),
                        new PrimaryDrawerItem().withName("ارسال خبر").withIcon(FontAwesome.Icon.faw_eye)
                                .withIdentifier(6),

                        new SectionDrawerItem().withName("سایت"),
                        new SecondaryDrawerItem().withName("سایت آخرین خبر").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("معرفی نرم افزار").withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName("آخرین تعییرات").withIcon(FontAwesome.Icon.faw_github),
                        new SectionDrawerItem().withName("بیشتر"),
                        new SecondaryDrawerItem().withName("تماس با ما").withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem.getIdentifier() == 2) {
                            Intent i=new Intent(maintest.this,ManageSD.class);
                            i.putExtra("device_id",android_id);
                            startActivity(i);
                        }if (drawerItem != null && drawerItem.getIdentifier() == 3) {
                            Intent i=new Intent(maintest.this,news_offline.class);
                            i.putExtra("device_id",android_id);
                            startActivity(i);
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 4) {
                            Intent i=new Intent(maintest.this,ManageTile.class);
                            i.putExtra("device_id",android_id);
                            startActivity(i);
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 5) {
                            Intent i=new Intent(maintest.this,Manage_Font.class);
                            //i.putExtra("device_id",android_id);
                            startActivity(i);
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 6) {
                            Intent i=new Intent(maintest.this,AddNews.class);
                            //i.putExtra("device_id",android_id);
                            startActivity(i);
                        }if (drawerItem instanceof Nameable) {
                            // toolbar.setTitle(((Nameable) drawerItem).getName().getText(page1.this));
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withDrawerGravity(Gravity.RIGHT)
                .build();
//end draw menu
        TextView txt = (TextView)findViewById(R.id.action_bar_text);
        txt.setTypeface (typefacetext);
        txt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                result.openDrawer();
            }
        });
        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                result.openDrawer();
            }
        });
        ImageButton share = (ImageButton)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "دانلود اپلیکیشن آربیکا از بازار";
                shareBody = shareBody + "\n \n https://play.google.com/store/apps/details?id=the.package.id \n\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });
        ImageButton search = (ImageButton)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                Intent i=new Intent(maintest.this,Search.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
            }
        });
        ImageButton bookmark = (ImageButton)findViewById(R.id.fav);
        bookmark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                Intent i=new Intent(maintest.this,Bookmark.class);
                i.putExtra("idcategory","all");
                i.putExtra("source","maintest");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(i);
            }
        });

    }
    private void checkarray(List<Posts> posts, List<Posts> mylist) {


                for (int i = 0; i < posts.size(); i++) {
                    poststitle.add(posts.get(i).gettitle());
              /*  if((posts.get(i).gettitle().equals(mylist.get(ii).gettime())))
                     Toast.makeText(maintest.this, String.valueOf(mylist.get(ii).gettitle()),
                            Toast.LENGTH_LONG).show();*/
                }
       /* Toast.makeText(maintest.this, String.valueOf(poststitle.size()+"\n"+mylist.size()),
                Toast.LENGTH_LONG).show();*/
        for (int ii = 0; ii < mylist.size(); ii++) {
            if(!(poststitle.contains(mylist.get(ii).gettitle())))
                    {
                        /*Toast.makeText(maintest.this, String.valueOf(mylist.get(ii).gettitle()),
                                Toast.LENGTH_LONG).show();*/
                        mylist.remove(ii);
                    }

        }
    }
    final List<String> poststitle=new ArrayList<>();
  private static List<Posts> ParsingPostJSON(JSONArray response) {

        try {
            List<Posts> posts = new ArrayList<>();


            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();
                post.setid(o.optString("cat_id", ""));
                post.settitle(o.optString("name", ""));


                post.setposition(o.optString("", ""));
                post.setImage(o.optString("picture", ""));
                post.setnotif(o.optString("notif", ""));
                posts.add(post);

            }

            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void ParsingPostJSONmeta(JSONArray response) {

        try {

            for (int i = 0; i < response.length(); i++) {
                /*Toast.makeText(this, String.valueOf(response.get(i)),
                        Toast.LENGTH_LONG).show();*/
                pos.add(String.valueOf(response.get(i)));

            }



        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

  
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "لطفا برای خروج دوباره کلید خروج را فشار دهید",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }



    public boolean isNetworkAvailable(Activity c) {
        boolean state;
        ConnectivityManager cmg = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cmg.getActiveNetworkInfo();
        state = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (state) {
            //Toast.makeText(maintest.this, "ok net", Toast.LENGTH_SHORT).show();

             volleyserverRequest(url);

            return true;
        } else {
            //NO interntet
          //  Toast.makeText(maintest.this, "no net", Toast.LENGTH_SHORT).show();
            volleyCacheRequest(url);

            return false;
        }
    }


    public void volleyCacheRequest(String url){

        Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache();
        Cache.Entry reqEntry = cache.get(url);
        if(reqEntry != null){
            try {
                final long cacheHitButRefreshed = 1 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
               // final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                long now = System.currentTimeMillis();
                final long softExpire = now + cacheHitButRefreshed;
               // final long ttl = now + cacheExpired;

                reqEntry.softTtl = cacheHitButRefreshed;
               // reqEntry.ttl = ttl;

                String data = new String(reqEntry.data, "UTF-8");

                JSONObject cacheObj = new JSONObject(data);
                arraycache = cacheObj.getJSONArray("result");
                arrayStatusmeta = cacheObj.getJSONArray("meta");

                List<Posts> post=ParsingPostJSON(arraycache);
                if(mylist==null)
                { adapter = new Main_Recycler_View_Adapter(post, android_id,getApplication());}
                else
                {
                    checkarray(post,mylist);
                    adapter = new Main_Recycler_View_Adapter(mylist, android_id,getApplication());
                }
                ParsingPostJSONmeta(arrayStatusmeta);
                //  glm.setSpanSizeLookup(ssl);
                recyclerView.setLayoutManager(gridLayoutManager);

                //   checkarray(post,mylist);

                if(!(listdel==null)) {
                    for (int i = 0; i < listdel.size(); i++) {
                        // Toast.makeText(maintest.this,String.valueOf(listdel.get(i)), Toast.LENGTH_LONG).show();
                        adapter.remove(Integer.parseInt((String) listdel.get(i)));
                    }
                }

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{

            //Request Not present in cache, launch a network request instead.
        }
    }

    public void volleyInvalidateCache(String url){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url, true);
    }

    public void volleyDeleteCache(String url){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
    }

    public void volleyClearCache(){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }
    public void volleyserverRequest(String url)
    {
        APISendingTile apiSendingData= new APISendingTile(maintest.this,url);
        JSONObject requestJsonObject= new JSONObject();
        try {


            requestJsonObject.put("device_id",android_id);
            apiSendingData.signUp(requestJsonObject, new APISendingTile.OnSignupComplate() {

                @Override
                public void onSignUp(boolean success, JSONArray array_result,JSONArray array_resultmeta) {
                    if(success == true){
                        List<Posts> post=ParsingPostJSON(array_result);
                        if(mylist==null)
                        { adapter = new Main_Recycler_View_Adapter(post, android_id,getApplication());}
                        else
                        {
                            checkarray(post,mylist);
                            adapter = new Main_Recycler_View_Adapter(mylist, android_id,getApplication());
                        }
                        ParsingPostJSONmeta(array_resultmeta);
                        //  glm.setSpanSizeLookup(ssl);
                        recyclerView.setLayoutManager(gridLayoutManager);

                        //   checkarray(post,mylist);

                        if(!(listdel==null)) {
                            for (int i = 0; i < listdel.size(); i++) {
                                // Toast.makeText(maintest.this,String.valueOf(listdel.get(i)), Toast.LENGTH_LONG).show();
                                adapter.remove(Integer.parseInt((String) listdel.get(i)));
                            }
                        }

                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                    }else{
                        Toast.makeText(maintest.this, "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
