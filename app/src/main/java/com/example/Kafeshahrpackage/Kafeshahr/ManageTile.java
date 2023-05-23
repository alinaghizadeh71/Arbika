package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageTile extends AppCompatActivity {
    ProgressBar pd;
    String device_id;
    Offline_Recycler_View_Adapter adapter;
    Toolbar toolbar;

    mydatabasehandler db;
    ArrayList<Posts> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        //getSupportActionBar().setIcon(R.drawable.ic_back);
        setTitle("مدیریت کاشی ها");

        Typeface typefacenum = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");


        pd = (ProgressBar) findViewById(R.id.pd);
      //  final TextView title = (TextView) findViewById(R.id.title);
        //title.setTypeface(typefacenum);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            device_id = b.getString("device_id");
            // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }

        db=new mydatabasehandler(this);
        mylist = new ArrayList<Posts>();
        mylist=db.gettilelist();

        String url = "http://api.arbika.ir/v1/categories";
        APISendingTile apiSendingData = new APISendingTile(ManageTile.this, url);
        JSONObject requestJsonObject = new JSONObject();
        try {


            requestJsonObject.put("device_id", device_id);
            apiSendingData.signUp(requestJsonObject, new APISendingTile.OnSignupComplate() {

                @Override
                public void onSignUp(boolean success, JSONArray array_result,JSONArray array_resultmeta) {
                    if (success == true) {


                        RecyclerView recyclerView;
                        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

                        adapter = new Offline_Recycler_View_Adapter(getApplication(), ParsingPostJSON(array_result),mylist, 1);
//  glm.setSpanSizeLookup(ssl);

                        recyclerView.setLayoutManager(new LinearLayoutManager(ManageTile.this, LinearLayoutManager.VERTICAL, false));

                        recyclerView.setAdapter(adapter);
                        pd.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(ManageTile.this, "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private List<Posts> ParsingPostJSON(JSONArray response) {

        try {
            List<Posts> posts = new ArrayList<>();


            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();
                post.setid(o.optString("cat_id", ""));
                post.settitle(o.optString("name", ""));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // this.menu = menu;
        getMenuInflater().inflate(R.menu.managetile, menu);
        //hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send) {

            Intent i=new Intent(ManageTile.this,maintest.class);
            startActivity(i);



            }
        if (id == R.id.sendtext) {

            Intent i=new Intent(ManageTile.this,maintest.class);
            startActivity(i);



        }
        return true;

        }


}
