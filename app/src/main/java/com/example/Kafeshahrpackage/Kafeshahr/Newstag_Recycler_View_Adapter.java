package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maliheh on 06/12/2018.
 */

public class Newstag_Recycler_View_Adapter extends RecyclerView.Adapter<Newstag_Recycler_View_Adapter.ViewHolder> {

    String key;
    List<Posts> posts = Collections.emptyList();
    Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView title;


        public ViewHolder(View view) {
            super(view);
            cv = (CardView) itemView.findViewById(R.id.card_view_tg);
            title = (TextView) itemView.findViewById(R.id.txttag);


        }

    }
    public Newstag_Recycler_View_Adapter(List<Posts> list, Context context) {
        this.posts = list;
        this.context = context;
    }

    @Override
    public Newstag_Recycler_View_Adapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final  int viewType) {



        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newstag_card, parent, false);
        // Main_View_Holder holder = new Main_View_Holder(v);
        Newstag_Recycler_View_Adapter.ViewHolder holder = new Newstag_Recycler_View_Adapter.ViewHolder(v);




        return holder;
    }

    @Override
    public void onBindViewHolder(Newstag_Recycler_View_Adapter.ViewHolder holder, final int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        holder.title.setTypeface (typefacetext);
        holder.cv.setBackgroundResource(R.drawable.newstag_background);
        holder.cv.setPadding(10,10,10,10);
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        Posts post= posts.get(position);
          holder.title.setText(post.gettitle());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, Search.class);
                Posts post= posts.get(position);
                intent.putExtra("key",post.gettitle());
                context. startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return posts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Posts data) {
        posts.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Posts data) {
        int position = posts.indexOf(data);
        posts.remove(position);
        notifyItemRemoved(position);
    }

}
