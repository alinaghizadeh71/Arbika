package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maliheh on 06/12/2018.
 */

public class Related_Recycler_View_Adapter extends RecyclerView.Adapter<Related_Recycler_View_Adapter.ViewHolder> {


   // List<Main_Data> list = Collections.emptyList();
    private List<Posts> posts;
    Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView title;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.country_name);
            imageView = (ImageView) itemView.findViewById(R.id.country_photo);

        }

    }
   /* public Related_Recycler_View_Adapter(List<Main_Data> list, Context context) {
        this.list = list;
        this.context = context;
    }*/
   public Related_Recycler_View_Adapter(Context context, List<Posts> posts){
       this.context = context;
       this.posts = posts;
   }
    @Override
    public Related_Recycler_View_Adapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final  int viewType) {



        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_card, parent, false);
        // Main_View_Holder holder = new Main_View_Holder(v);
        Related_Recycler_View_Adapter.ViewHolder holder = new Related_Recycler_View_Adapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Related_Recycler_View_Adapter.ViewHolder holder,final int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        holder.title.setTypeface (typefacetext);
        Posts post= posts.get(position);
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
      /*  holder.title.setText(posts.get(position).title);*/

        holder.title.setText(post.gettitle());
        Picasso.with(context).load(post.getImage()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
            }
        });
       /* holder.imageView.setImageResource(list.get(position).imageId);

        String item = String.valueOf(list.get(position).Ribbonid);*/



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
    public void insert(int position, Posts post) {
        posts.add(position, post);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove( Posts post) {
        int position = posts.indexOf(post);
        posts.remove(position);
        notifyItemRemoved(position);
    }

}
