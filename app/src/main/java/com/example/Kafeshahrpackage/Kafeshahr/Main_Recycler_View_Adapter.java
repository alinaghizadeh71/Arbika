package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malihe on 6/3/2018.
 */

public class Main_Recycler_View_Adapter extends RecyclerView.Adapter<Main_Recycler_View_Adapter.ViewHolder> {

    private List<Posts> posts;
    Context context;
    String title;
    String device_id;
    String notif;
   // final ArrayList mylist;
    public static class ViewHolder extends RecyclerView.ViewHolder
            {
      CardView cv;
        CardView cv_notif;
        TextView title,txtnum;
        ImageView imageView;
                LinearLayout li;
                FrameLayout framee;
       // RibbonLayout ribbonLayout;

        public ViewHolder(View view) {
            super(view);
            cv = (CardView) itemView.findViewById(R.id.cardmain);
            cv_notif = (CardView) itemView.findViewById(R.id.notif);
            title = (TextView) itemView.findViewById(R.id.country_name);
            imageView = (ImageView) itemView.findViewById(R.id.country_photo);
            txtnum= (TextView) itemView.findViewById(R.id.txtnum);
            li=(LinearLayout)itemView.findViewById(R.id.linearmain);
            framee=(FrameLayout)itemView.findViewById(R.id.framee);
          //  ribbonLayout = (RibbonLayout) itemView.findViewById(R.id.ribbonLayout);
        }

    }
    /*, ArrayList<String> mylist*/
    public Main_Recycler_View_Adapter(List<Posts> posts, String device_id, Context context) {
        this.posts = posts;
        this.context = context;
        this.device_id=device_id;
        //this.mylist=mylist;
    }

    @Override
    public ViewHolder  onCreateViewHolder(final ViewGroup parent,final  int viewType) {



        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card, parent, false);
       // Main_View_Holder holder = new Main_View_Holder(v);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder  holder, final int position) {

     /*   if (position==1)
       {
           // holder.imageView.setVisibility(View.INVISIBLE);
           holder.imageView.setMinimumWidth(10000);
           //Toast.makeText(context,String.valueOf(holder.imageView.getMinimumWidth()),Toast.LENGTH_LONG).show();
       }
        if (position==2) {
            holder.imageView.setMinimumWidth(10000);
        }*/


        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        holder.title.setTypeface (typefacetext);
        holder.txtnum.setTypeface (typefacenum);
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        Posts post= posts.get(position);

        holder.title.setText(post.gettitle());

      //  holder.title.setText(mylist.get(0).toString());
     /*                       if(!(mylist.get(0).toString()=="all"))
                            { //holder.title.setText(String.valueOf(mylist.size()));
                                *//*for (int i = 0; i < mylist.size(); i++) {
                                    if (!(mylist.get(i).toString().equals(post.gettitle()))) {
                                        holder.cv.setVisibility(View.GONE);


                                    }

                                }*//*
                                if (mylist.contains(post.gettitle())) {

                                }
                                else
                                {
                                    Log.d("pooooooooooooooooooos",String.valueOf(position));
                                  //  remove(position);

                                  //  holder.cv.setVisibility(View.GONE);
                                    //holder.li.setVisibility(View.GONE);
                                }
                            }*/


        String temp = post.getImage().replaceAll(" ", "%20");
        Picasso.with(context).load(temp).into(holder.imageView);
         notif=post.getnotif();
        if (Integer.parseInt(notif)<1)
        {
            holder.cv_notif.setVisibility(View.GONE);
        }
        else
            holder.txtnum.setText(notif);
       // holder.title.setText(list.get(position).title);
       // holder.imageView.setImageResource(list.get(position).imageId);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // cv_notif.setVisibility(View.GONE);
                holder.cv_notif.setVisibility(View.GONE);
                Posts post= posts.get(position);
                title = String.valueOf(post.gettitle());

                final String id = String.valueOf(post.getid());
                Log.v("idddddddddddddddddddd",id);
                if(id.equals("19"))
                {
                    Intent intent1=new Intent(context, Shahr.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent1.putExtra("title", title);
                    intent1.putExtra("id", id);
                    context.startActivity(intent1);
                }
                else {
                    Intent intent1 = new Intent(context, page2.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent1.putExtra("url", "http://api.arbika.ir/v1/category/" + id + "/news");
                    intent1.putExtra("title", title);
                    intent1.putExtra("id", id);
                    intent1.putExtra("device_id", device_id);
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context.startActivity(intent1);
                }
//Toast.makeText(Main_Recycler_View_Adapter.this.getClass(),String.valueOf(post.getid()),Toast.LENGTH_LONG).show();
               // Log.w("id", title);
            }
        });


        //holder.ribbonLayout.setShowBottom(false);
        //holder.ribbonLayout.setShowHeader(false);

        //if(item.equals("0")) //عادی
        //{
          /* holder.ribbonLayout.setShowBottom(false);
           holder.ribbonLayout.setShowHeader(false);
*/

//        }
  //      else  //جدید
    //    {
           /* holder.ribbonLayout.setShowBottom(true);
            holder.ribbonLayout.setShowBottom(true);
*/
          /*             holder.ribbonLayout.setShowBottom(false);


                        holder.ribbonLayout.setMinimumHeight(50);
                        holder.ribbonLayout.setHeaderRibbonColor(Color.parseColor("#B94948"));
                        holder.ribbonLayout.setHeaderTextColor(Color.parseColor("#FFFFFF"));
                        holder.ribbonLayout.setHeaderText(String.valueOf(list.get(position).Ribbonid));*/
           // holder.ribbonLayout.setBottomText(String.valueOf(list.get(position).Ribbonid));

      //  }

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
    public void remove(int position) {
      //  int position = posts.indexOf(data);
        posts.remove(position);
        notifyItemChanged(position);
       /* notifyItemRangeRemoved(position, 1);*/
      /*  posts.remove(position);
        notifyItemRemoved(Integer.parseInt(position));
        notifyItemRangeChanged(Integer.parseInt(position), posts.size());
        notifyItemChanged(Integer.parseInt(position));*/
        //notifyDataSetChanged();
    }

}
