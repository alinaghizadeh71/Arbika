package com.example.Kafeshahrpackage.Kafeshahr;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_offline extends AppCompatActivity {
    ProgressBar pd;
    String device_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_offline);
        Typeface typefacenum = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb.ttf");

         pd=(ProgressBar)findViewById(R.id.pd);
        final TextView title=(TextView)findViewById(R.id.title);
        final TextView text=(TextView)findViewById(R.id.numnews1);
        final TextView text1=(TextView)findViewById(R.id.numnews);
        final SeekBar seekbar=(SeekBar)findViewById(R.id.sbBar);
        title.setTypeface (typefacetext);
        text.setTypeface (typefacenum);
        text1.setTypeface (typefacenum);

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            device_id= b.getString("device_id");
            // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }
        //text.setText(seekbar.getProgress() + "/" + seekbar.getMax());
        text.setText("0" + " خبر");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText(pval + " خبر");
            }
        });








        String url="http://api.arbika.ir/v1/categories";
        APISendingTile apiSendingData= new APISendingTile(news_offline.this,url);
        JSONObject requestJsonObject= new JSONObject();
        try {


            requestJsonObject.put("device_id",device_id);
            apiSendingData.signUp(requestJsonObject, new APISendingTile.OnSignupComplate() {

                @Override
                public void onSignUp(boolean success, JSONArray array_result,JSONArray array_resultmeta) {
                    if(success == true){


                        RecyclerView recyclerView;
                        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                        Offline_Recycler_View_Adapter adapter;
                        adapter = new Offline_Recycler_View_Adapter( getApplication(),ParsingPostJSON(array_result), (ArrayList<Posts>) ParsingPostJSON(array_result),0);
//  glm.setSpanSizeLookup(ssl);
                        recyclerView.setLayoutManager(new LinearLayoutManager(news_offline.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(adapter);
                        pd.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(news_offline.this, "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
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
}

