package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maliheh on 07/16/2018.
 */

public class YourFlipperAdapter extends BaseAdapter {
    Context context;

    LayoutInflater inflter;
    private List<Posts> posts;
    public YourFlipperAdapter(Context applicationContext, ArrayList<Posts> posts) {
        this.context = applicationContext;
        this.posts = posts;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.filiper_view_item, null);


        ImageButton testImage = (ImageButton) view.findViewById(R.id.mybtn);
        Posts post= posts.get(position);

        //holder.date.setText(post.getDate());
        String temp = post.getImage().replaceAll(" ", "%20");
        Picasso.with(context).load(temp).into(testImage);
       // testImage.setImageResource(testImages[position]);
        testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Posts post= posts.get(position);
                String isvideo=post.getisvideo();

                final String id = String.valueOf(post.getid());

                if (isvideo=="false")
                {
                    Intent intent=new Intent(context, page3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat","");
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","finish");
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }
                else if  (isvideo=="true")
                {
                    Intent intent=new Intent(context, page3_video.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat","");
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","finish");
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }

                  /*  Intent intent=new Intent(context, page2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/selectedNews/all");
                    context. startActivity(intent);*/

            }
        });
        return view;
    }
}