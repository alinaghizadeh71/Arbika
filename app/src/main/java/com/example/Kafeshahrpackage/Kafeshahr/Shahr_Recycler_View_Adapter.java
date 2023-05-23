package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malihe on 6/3/2018.
 */

public class Shahr_Recycler_View_Adapter extends RecyclerView.Adapter<Shahr_Recycler_View_Adapter.ViewHolder> {

    private List<Posts> posts;
    Context context;
    String title;

    public static class ViewHolder extends RecyclerView.ViewHolder
            {
      CardView cv;

        TextView title;

       // RibbonLayout ribbonLayout;

        public ViewHolder(View view) {
            super(view);
            cv = (CardView) itemView.findViewById(R.id.cardmain);
            title = (TextView) itemView.findViewById(R.id.title);

             }

    }
    public Shahr_Recycler_View_Adapter(List<Posts> posts, Context context) {
        this.posts = posts;
        this.context = context;

    }

    @Override
    public ViewHolder  onCreateViewHolder(final ViewGroup parent,final  int viewType) {



        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shahrcard, parent, false);
       // Main_View_Holder holder = new Main_View_Holder(v);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder  holder, final int position) {

        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        holder.title.setTypeface (typefacetext);

        Posts post= posts.get(position);
        Log.d("title",post.gettitle());
        holder.title.setText(post.gettitle());


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cv_notif.setVisibility(View.GONE);

                Posts post= posts.get(position);
                title = String.valueOf(post.gettitle());

                final String id = String.valueOf(post.getid());
                Log.v("idddddddddddddddddddd",id);

                    Intent intent1 = new Intent(context, page2.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent1.putExtra("url", "http://api.arbika.ir/v1/category/" + id + "/news");
                    intent1.putExtra("title", title);
                    intent1.putExtra("id", id);

                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context.startActivity(intent1);

//Toast.makeText(Main_Recycler_View_Adapter.this.getClass(),String.valueOf(post.getid()),Toast.LENGTH_LONG).show();
                // Log.w("id", title);
            }
        });
       // String temp = post.getImage().replaceAll(" ", "%20");



    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return posts.size();
    }




}
