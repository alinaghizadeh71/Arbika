package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by malihe on 5/25/2018.
 */

public class Comment_Recycler_View_Adapter extends RecyclerView.Adapter<Comment_Recycler_View_Adapter.PostsViewHolder>{

    private Context context;
    private List<Posts> posts;
    private  String idnews;
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    public static final int ITEM_TYPE_comment = 0;
    public static final int ITEM_TYPE_reply = 1;
    int statuslike;
    int statusdislike;
    int statuslike_sub;
    int statusdislike_sub;
    int checklike;
    int checkdislike;
    String fontsize;
    public Comment_Recycler_View_Adapter(Context context, List<Posts> posts,String idnews,String fontsize){
        this.context = context;
        this.posts = posts;
        this.idnews=idnews;
        this.fontsize=fontsize;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
       /* view = LayoutInflater.from(context).inflate(R.layout.page2_recycle_items, parent, false);
        return new PostsViewHolder(view);*/
        switch (viewType) {
            case ITEM_TYPE_comment:
                view = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
                return new PostsViewHolder(view);
            case ITEM_TYPE_reply:

                //Posts post= posts.get(position);
                view = LayoutInflater.from(context).inflate(R.layout.commenttocomment_card, parent, false);
                return new PostsViewHolder(view);
        }
        return null;

    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        Posts post= posts.get(position);
        String isReply=post.getisReply();

        if (isReply=="true") return ITEM_TYPE_comment;
        else if  (isReply=="false")
        {
        if(post.getreplies()==null)
            return ITEM_TYPE_reply;
        }
        else
            return ITEM_TYPE_comment;

        return ITEM_TYPE_comment;
    }
    @Override
    public void onBindViewHolder(final PostsViewHolder holder, final int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");

        Posts object = posts.get(position);
        if (object != null) {

            holder.title.setTypeface(typefacetext);

            holder.commentTextView.setTypeface(typefacetext);
           holder.date.setTypeface(typefacenum);
            holder.time.setTypeface(typefacenum);
             holder.txtdislike.setTypeface(typefacenum);
            holder.txtlike.setTypeface(typefacenum);
            holder.error.setTypeface(typefacetext);

            holder.title.setText(object.gettitle());
            holder.commentTextView.setText(object.getdes());
            if(fontsize!=null) {
                holder.commentTextView.setTextSize(Integer.parseInt(fontsize));
            }

            holder.date.setText(object.getdate());
            holder.time.setText("");
            String isReply=object.getisReply();
            if (isReply=="false")
            {
                if(object.getreplies()==null)
                    holder.reply.setVisibility(View.GONE);
                      holder.time.setVisibility(View.GONE);
                     holder.date.setText(object.getdate());
            }
        else{
                holder.reply.setVisibility(View.VISIBLE);

            }
          holder.reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Posts object = posts.get(position);
                    Intent intent=new Intent(context, comment.class);
                    intent.putExtra("title_news", object.getdes().toString());
                    intent.putExtra("id_parent", object.getid().toString());
                    intent.putExtra("id", idnews);
                    context. startActivity(intent);

                }
            });
             holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  /*  if(checkdislike==1)
                    {String lbdislike = holder.txtdislike.getText().toString();
                        if(Integer.parseInt(lbdislike)>0)
                        {holder.txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike)-1));}
                        checkdislike=2;
                    }*/

                    String lblike= holder.txtlike.getText().toString();

                    //karbar like karde
                    checklike=1;
                    //

                    if(statuslike==0)
                    {
                        statuslike = 1;
                        holder.txtlike.setText(String.valueOf(Integer.parseInt(lblike) + 1));
                    }
                    else if(statuslike==1)
                    {
                        statuslike=0;
                        holder.txtlike.setText(String.valueOf(Integer.parseInt(lblike)-1));
                    }else
                    {
                        holder.txtlike.setText(String.valueOf(Integer.parseInt(lblike) + 1));
                    }
                }
            });
           holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* if(checklike==1)
                    { String lblike= holder.txtlike.getText().toString();
                        if(Integer.parseInt(lblike)>0)
                            holder.txtlike.setText(String.valueOf(Integer.parseInt(lblike)-1));
                        checklike=2;
                    }*/


                    String lbdislike = holder.txtdislike.getText().toString();
                    checkdislike=1;



                    if (statusdislike == 0) {
                        statusdislike = 1;
                        holder.txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike) + 1));
                    } else if (statusdislike == 1) {
                        statusdislike = 0;
                        holder.txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike) - 1));
                    } else {
                        holder.txtdislike.setText(String.valueOf(Integer.parseInt(lbdislike) + 1));
                    }
                }
           });
                }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public void removeItem(int position) {


        posts.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Posts item, int position) {
        posts.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
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
}
