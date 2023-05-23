package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malihe on 5/25/2018.
 */

class MultiViewTypeAdapter  extends RecyclerView.Adapter{
    /*RecyclerView.Adapter<Page2_Comment_Recycler_View_Adapter.PostsViewHolder>*/
    private Context context;
    private List<Posts> posts;
    int statuslike;
    int statusdislike;
    int statuslike_sub;
    int statusdislike_sub;
    public MultiViewTypeAdapter (ArrayList<Posts>posts, Context context){
        this.context = context;
        this.posts = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        switch (viewType) {

            case Posts.comment_card:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
                return new PostsViewHolder(view);
            case Posts.commenttocomment_card:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commenttocomment_card, parent, false);
                return new PostsViewHolderr(view);
             }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");

        Posts object = posts.get(position);
        if (object != null) {
            switch (object.type) {
                case Posts.comment_card:
                    ((PostsViewHolder) holder).title.setTypeface (typefacetext);

                    ((PostsViewHolder) holder).commentTextView.setTypeface (typefacetext);
                    ((PostsViewHolder) holder).date.setTypeface (typefacenum);
                    ((PostsViewHolder) holder).time.setTypeface (typefacenum);
                    ((PostsViewHolder) holder).txtdislike.setTypeface (typefacenum);
                    ((PostsViewHolder) holder).txtlike.setTypeface (typefacenum);
                    ((PostsViewHolder) holder).error.setTypeface (typefacetext);
                    ((PostsViewHolder) holder).title.setText(object.gettitle());
                    // holder.intro.setText(post.getIntro());
                    //holder.date.setText(post.getDate());
                  //  Picasso.with(context).load(object.getImage()).into( ((PostsViewHolder) holder).coverImageView);
                    ((PostsViewHolder) holder).reply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(context, comment.class);
                            intent.putExtra("comment", ((PostsViewHolder) holder).commentTextView.getText().toString());
                            context. startActivity(intent);

                        }
                    });
                    ((PostsViewHolder) holder).like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lblike= ((PostsViewHolder) holder).txtlike.getText().toString();
                            if(statuslike==0)
                            {
                                statuslike = 1;
                                ((PostsViewHolder) holder).txtlike.setText(String.valueOf(Integer.parseInt(lblike) + 1));
                            }
                            else if(statuslike==1)
                            {
                                statuslike=0;
                                ((PostsViewHolder) holder).txtlike.setText(String.valueOf(Integer.parseInt(lblike)-1));
                            }else
                            {
                                ((PostsViewHolder) holder).txtlike.setText(String.valueOf(Integer.parseInt(lblike) + 1));
                            }
                        }
                    });
                    ((PostsViewHolder) holder).dislike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lbdislike= ((PostsViewHolder) holder).txtdislike.getText().toString();


                            if(statusdislike==0)
                            {
                                statusdislike = 1;
                                ((PostsViewHolder) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)+1));
                            }
                            else if(statusdislike==1)
                            {
                                statusdislike=0;
                                ((PostsViewHolder) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)-1));
                            }else
                            {
                                ((PostsViewHolder) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)+1));
                            }


                        }
                    });
                    break;
                case Posts.commenttocomment_card:
                    ((PostsViewHolderr) holder).txtdislike.setTypeface (typefacenum);
                    ((PostsViewHolderr) holder).txtlike1.setTypeface (typefacenum);
                    ((PostsViewHolderr) holder).date.setTypeface (typefacenum);
                    ((PostsViewHolderr) holder).time.setTypeface (typefacenum);
                    ((PostsViewHolderr) holder).error.setTypeface (typefacetext);
                    ((PostsViewHolderr) holder).title.setTypeface (typefacetext);
                    ((PostsViewHolderr) holder).commentTextView.setTypeface (typefacetext);
                   // ((PostsViewHolderr) holder).title.setText(object.getfirstname());
                    ((PostsViewHolderr) holder).like1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lblike= ((PostsViewHolderr) holder).txtlike1.getText().toString();


                            if(statuslike_sub==0)
                            {
                                statuslike_sub = 1;
                                ((PostsViewHolderr) holder).txtlike1.setText(String.valueOf(Integer.parseInt(lblike)+1));
                            }
                            else if(statuslike_sub==1)
                            {
                                statuslike_sub=0;
                                ((PostsViewHolderr) holder).txtlike1.setText(String.valueOf(Integer.parseInt(lblike)-1));
                            }else
                            {
                                ((PostsViewHolderr) holder).txtlike1.setText(String.valueOf(Integer.parseInt(lblike)+1));
                            }

                        }
                    });
                    ((PostsViewHolderr) holder).dislike1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lbdislike= ((PostsViewHolderr) holder).txtdislike.getText().toString();



                            if(statusdislike_sub==0)
                            {
                                statusdislike_sub = 1;
                                ((PostsViewHolderr) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)+1));
                            }
                            else if(statusdislike_sub==1)
                            {
                                statusdislike_sub=0;
                                ((PostsViewHolderr) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)-1));
                            }else
                            {
                                ((PostsViewHolderr) holder).txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)+1));
                            }

                        }
                    });
                    // holder.intro.setText(post.getIntro());
                    //holder.date.setText(post.getDate());
                   // Picasso.with(context).load(object.getImage()).into( ((PostsViewHolderr) holder).coverImageView);

                    break;


            }
        }
    }

    /*@Override
    public void onBindViewHolder(final PostsViewHolder holder, final int position) {


      *//*  Posts post= posts.get(position);
        holder.title.setText(post.getfirstname());
       // holder.intro.setText(post.getIntro());
        //holder.date.setText(post.getDate());
        Picasso.with(context).load(post.getImage()).into(holder.coverImageView);

        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, comment.class);
                 intent.putExtra("comment",holder.commentTextView.getText().toString());
                context. startActivity(intent);

            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lblike=holder.txtlike.getText().toString();

                holder.txtlike.setText(String.valueOf(Integer.parseInt(lblike)+1));

            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lbdislike=holder.txtdislike.getText().toString();

                holder.txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)+1));



            }
        });*//*
    }*/

    @Override
    public int getItemCount() {
        return posts.size();
    }
    @Override
    public int getItemViewType(int position) {

        switch (posts.get(position).type) {
            case 0:
                return Posts.comment_card;
            case 1:
                return Posts.commenttocomment_card;

            default:
                return -1;
        }
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public com.makeramen.roundedimageview.RoundedImageView coverImageView;
       public TextView commentTextView;
        public ImageButton reply;
        public ImageButton like;
        public ImageButton dislike;
        public TextView txtlike;
        public TextView date;
        public TextView time;
        public TextView error;
        public TextView txtdislike;
        public ImageView shareImageView;
        public ImageView likeImageView;


        public PostsViewHolder(View itemView) {
            super(itemView);
            title= (TextView)itemView.findViewById(R.id.titleTextView);
            commentTextView= (TextView)itemView.findViewById(R.id.txtcontent);
            coverImageView= (com.makeramen.roundedimageview.RoundedImageView)itemView.findViewById(R.id.country_photo);
            reply= (ImageButton)itemView.findViewById(R.id.rep);
            like= (ImageButton)itemView.findViewById(R.id.like);
            dislike= (ImageButton)itemView.findViewById(R.id.dislike);

            date= (TextView)itemView.findViewById(R.id.date);
            time= (TextView)itemView.findViewById(R.id.time);
            error= (TextView)itemView.findViewById(R.id.txterror);
            txtlike= (TextView)itemView.findViewById(R.id.txtlike);
            txtdislike= (TextView)itemView.findViewById(R.id.txtdislike);
        }
    }

    public class PostsViewHolderr extends RecyclerView.ViewHolder{
        public TextView title;
        public com.makeramen.roundedimageview.RoundedImageView coverImageView;
        public TextView commentTextView;
        public ImageButton like1;
        public ImageButton dislike1;
        public TextView txtlike1;
        public TextView txtdislike;
        public TextView date;
        public TextView time;
        public TextView error;


        public PostsViewHolderr(View itemView) {
            super(itemView);
            title= (TextView)itemView.findViewById(R.id.titleTextView);
          commentTextView= (TextView)itemView.findViewById(R.id.txtcontent);
          //  coverImageView= (com.makeramen.roundedimageview.RoundedImageView)itemView.findViewById(R.id.country_photo);

            like1= (ImageButton)itemView.findViewById(R.id.like);
            dislike1= (ImageButton)itemView.findViewById(R.id.dislike);
            txtlike1= (TextView)itemView.findViewById(R.id.txtlike);
            txtdislike= (TextView)itemView.findViewById(R.id.txtdislike);

            date= (TextView)itemView.findViewById(R.id.date);
            time= (TextView)itemView.findViewById(R.id.time);
            error= (TextView)itemView.findViewById(R.id.txterror);
        }
    }
}
