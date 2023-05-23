package com.example.Kafeshahrpackage.Kafeshahr;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Shahr extends AppCompatActivity {
    RecyclerView recyclerView;
    Shahr_Recycler_View_Adapter adapter;
    private Toolbar toolbar;
    String url="http://api.arbika.ir/v1/cities";
    String title;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shahr);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();
            title = b.getString("title");
            id=b.getString("id");
        }
        setTitle(title);

        // Toast.makeText(Shahr.this, "hello baby", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

     recyclerView.setNestedScrollingEnabled(true);

        APISendingTile APISending = new APISendingTile(Shahr.this, url);
        JSONObject requestJsonObject = new JSONObject();
        try {
            APISending.signUp(requestJsonObject, new APISendingTile.OnSignupComplate() {
                @Override
                public void onSignUp(boolean success, JSONArray array_result,JSONArray array_resultmeta) {
                    if (success == true) {

                            //Toast.makeText(Shahr.this, "ok", Toast.LENGTH_SHORT).show();
                            adapter = new Shahr_Recycler_View_Adapter(ParsingPostJSON(array_result), Shahr.this);
                        recyclerView.setLayoutManager(new GridLayoutManager(Shahr.this, 3));
                        //recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(Shahr.this, "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static List<Posts> ParsingPostJSON(JSONArray response) {

        try {
            List<Posts> posts = new ArrayList<>();


            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();
                post.setid(o.optString("cat_id", ""));
                post.settitle(o.optString("name", ""));

                posts.add(post);

            }

            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
