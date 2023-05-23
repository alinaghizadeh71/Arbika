package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by malihe on 5/25/2018.
 */

public class Offline_Recycler_View_Adapter extends RecyclerView.Adapter<Offline_Recycler_View_Adapter.PostsViewHolder>{

    private Context context;
    private List<Posts> posts;
    private ArrayList<Posts> list;
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    public static final int ITEM_TYPE_NORMAL = 0;
    public static final String ITEM_TYPE_HEADER="all" ;
    public static final ArrayList<Posts> all= new ArrayList<Posts>();
    public static final ArrayList<String> listdel= new ArrayList<String>();
     int type;
   // ArrayList<String> mylist;
     ArrayList mylist;
     mydatabasehandler db;

    String listcheckedtile;
int i;
    public Offline_Recycler_View_Adapter(Context context, List<Posts> posts, ArrayList<Posts> mylist,int type){
        this.context = context;
        this.posts = posts;
        this.type=type;
        this.list=mylist;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        db=new mydatabasehandler
                (context);
        mylist= new ArrayList<String>();

        mylist=db.gettilelist();
        View view;
        if(type==0)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_offline_card, parent, false);
            return new PostsViewHolder(view);
        }
        else if(type==1)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_managetile_card, parent, false);
            return new PostsViewHolder(view);
        }

        return  null;
    }

    @Override
    public void onBindViewHolder(final PostsViewHolder holder, final int position) {

       // holder.getItemViewType=getItemViewType(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");

        holder.titleTextView.setTypeface (typefacetext);

        final Posts post= posts.get(position);

        holder.titleTextView.setText(post.gettitle());


        int tile_counts = db.getTilesCount();
       // holder.titleTextView.setText(String.valueOf(tile_counts));

        post.setposition(String.valueOf(position));
        if(list==null)
        {

            all.add(post);
            holder.ck.setChecked(true);
             if(all.size()==getItemCount())
            {
                filldb(all);
            }

       }else if(list.size()==getItemCount())
        {
            holder.ck.setChecked(true);
        }



       else
        {


                 gettitledb();
                        if (!(mylist.contains(holder.titleTextView.getText()))) {
                            if (type == 1) {
                                holder.ck.setChecked(false);

                            }
                            }
                            else {
                                holder.ck.setChecked(true);
                            }

        }



        //add(post.gettitle());

        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ck.isChecked())
                {
                    holder.ck.setChecked(false);

                  int i=del(holder.titleTextView.getText().toString());
                 //   holder.titleTextView.setText(String.valueOf(mylist.size()+"////"+i));

                }else
                {
                    holder.ck.setChecked(true);

                    mylist.add(holder.titleTextView.getText().toString());
                    add(post);
                  //  holder.titleTextView.setText(String.valueOf(mylist.size()));
                }
            }
        });
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ck.isChecked())
                {
                    mylist.add(holder.titleTextView.getText().toString());
                   add(post);
                    //  holder.titleTextView.setText(String.valueOf(mylist.size()));

                    }else
                {

                    int i=del(holder.titleTextView.getText().toString());
                    //   holder.titleTextView.setText(String.valueOf(mylist.size()+"////"+i));


                }
            }

        });

    }

    public int del(String item)
    {
        db.delmanage_tile(item);

        return 1;
        /*for (int i=0;i<=list.size();i++) {
            if (list.get(i).toString().equals(item)) {
                list.remove(i);

            }

        }*/
     //   return 0;
    }
    public int add(Posts post)
    {
        db.addmanage_tile(post.getid().toString(),post.getposition().toString(),post.getImage().toString(),post.getnotif().toString(),post.gettitle().toString());

        return 0;
    }
    public void filldb(ArrayList<Posts> all)
    {

        mylist=all;
        for(int i=0;i<all.size();i++)
        {
            db.addmanage_tile(all.get(i).getid().toString(),all.get(i).getposition().toString(),all.get(i).getImage().toString(),all.get(i).getnotif().toString(),all.get(i).gettitle().toString());


        }
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }
    public ArrayList<String> getlistdel() {
        return listdel;
    }
    public ArrayList<Posts> getmylist() {
        return db.gettilelist();
    }
    public void gettitledb()
    {
           for(int i=0;i<list.size();i++)
           {
           mylist.add(list.get(i).gettitle());
           }
    }
    public class PostsViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public CheckBox ck;


        public PostsViewHolder(View itemView) {
            super(itemView);
            titleTextView= (TextView)itemView.findViewById(R.id.title);
            ck=(CheckBox)itemView.findViewById(R.id.ck);
        }

    }
}
