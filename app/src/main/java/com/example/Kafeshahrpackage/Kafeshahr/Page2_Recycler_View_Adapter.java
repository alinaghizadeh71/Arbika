package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

/**
 * Created by malihe on 5/25/2018.
 */

public class Page2_Recycler_View_Adapter extends RecyclerView.Adapter<Page2_Recycler_View_Adapter.PostsViewHolder>{

    private Context context;
    private List<Posts> posts;
    String id_category;
    String title_category;
String back;
    String myback;
    boolean ispin;

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_VIDEO = 1;
    public static final int ITEM_TYPE_Image = 2;
    private int lastPosition = -1;
     int i;

    public Page2_Recycler_View_Adapter(Context context, List<Posts> posts,String id,String title_cat,boolean ispin,String back){
        this.context = context;
        this.posts = posts;
        this.id_category=id;
        this.title_category=title_cat;

this.back=back;

    }
    public void updateData(Context context, List<Posts> newposts,String id,String title_cat,boolean ispin,String back) {

        this.context = context;

        posts.addAll(newposts);
        this.title_category=title_cat;
      //  notifyDataSetChanged();
        notifyItemRangeInserted(getItemCount(),newposts.size());


        //notifyItemInserted(size-1);
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
       /* view = LayoutInflater.from(context).inflate(R.layout.page2_recycle_items, parent, false);
        return new PostsViewHolder(view);*/

        switch (viewType) {
            case ITEM_TYPE_VIDEO:
                view = LayoutInflater.from(context).inflate(R.layout.page2_video, parent, false);
                return new PostsViewHolder(view);
            case ITEM_TYPE_Image:
                view = LayoutInflater.from(context).inflate(R.layout.page2_image, parent, false);
                return new PostsViewHolder(view);
            case ITEM_TYPE_NORMAL:

                //Posts post= posts.get(position);
                view = LayoutInflater.from(context).inflate(R.layout.page2_recycle_items, parent, false);
                return new PostsViewHolder(view);
        }
        return null;
    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        Posts post= posts.get(position);

        //String id=post.getid();
        if(id_category.equals("14")) return ITEM_TYPE_Image;
       Log.i("key",id_category);
        String isvideo=post.getisvideo();
        if (isvideo=="false") return ITEM_TYPE_NORMAL;
        else if  (isvideo=="true") return ITEM_TYPE_VIDEO;
        return ITEM_TYPE_NORMAL;
    }
    @Override
    public void onBindViewHolder(final PostsViewHolder holder, final int position) {

        if (back.equals("finish"))
        {
            myback="finish";

        }else if (back.equals("page2"))
        {
            myback="page2";
        }

       // holder.getItemViewType=getItemViewType(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        holder.description.setTypeface (typefacetext);
        holder.titleTextView.setTypeface (typefacetext);
        holder.date.setTypeface (typefacenum);
        holder.time.setTypeface (typefacenum);
        holder.txtvisit.setTypeface (typefacenum);
        holder.txtpic.setTypeface (typefacenum);
        holder.txtnumpic.setTypeface (typefacenum);
        holder.txtcomment.setTypeface (typefacenum);
       // setAnimation(holder.itemView, position);
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
       // holder.itemView.startAnimation(animation);
        //lastPosition = position;
/*if(ispin==true)
{
    holder.img_pin.setVisibility(View.VISIBLE);
}*/
        Posts post= posts.get(position);
        if(post.getispin()=="true")
        {
            holder.img_pin.setVisibility(View.VISIBLE);
        }
        holder.titleTextView.setText(post.gettitle());
        holder.description.setText(post.getdes());
        holder.time.setText(post.gettime());
        holder.date.setText(post.getdate());
        holder.txtvisit.setText(post.getvisit());
        holder.txtcomment.setText(post.getcomment_count());
        String temp = post.getImage().replaceAll(" ", "%20");
        Picasso.with(context).load(temp).into(holder.coverImageView);


       if(post.getIsgallery().equals("true"))
       {
           /*Posts post1= posts.get(position);
           String numimage=String.valueOf(post1.getimages_count());
           Log.i("numimage",numimage);*/
           holder.txtnumpic.setText(post.getimages_count());

       }
       else
           holder.viewImagelayout.setVisibility(View.GONE);

        //get count image for tile picture
        //JSONArray Array_image = post.getJSONArray("images");

        mydatabasehandler db=new mydatabasehandler(context);
        String check=db.checkuser(post.getid());
        if(check=="true")
        { holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmarkred));}
        else { holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_s));}

        /*تبلیغات*/
        if(position==1)
        {
            holder.img.setVisibility(View.VISIBLE);
        }

        holder.description.setOnClickListener(new View.OnClickListener() {
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
                    intent.putExtra("id_cat",id_category);
                    intent.putExtra("title_cat",title_category);
                    intent.putExtra("back",myback);
                    context. startActivity(intent);
                }
                else if  (isvideo=="true")
                {
                    Intent intent=new Intent(context, page3_video.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat",id_category);
                    intent.putExtra("title_cat",title_category);
                    intent.putExtra("back",myback);
                    context. startActivity(intent);
                }


               /* if (position==0)
                {
                    Intent intent=new Intent(context, page3_video.class);
                    // intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(context, page3.class);
                    // intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }*/

            }
        });
        holder.coverImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_));
                Posts post= posts.get(position);
                String isvideo=post.getisvideo();

                final String id = String.valueOf(post.getid());

                if (isvideo=="false")
                {
                    Intent intent=new Intent(context, page3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("back",myback);
                    intent.putExtra("id_cat",id_category);
                    intent.putExtra("title_cat",title_category);
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }
                else if  (isvideo=="true")
                {
                    Intent intent=new Intent(context, page3_video.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("back",myback);
                    intent.putExtra("id_cat",id_category);
                    intent.putExtra("title_cat",title_category);
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }
               /* if (position==0)
                {
                    Intent intent=new Intent(context, page3_video.class);
                    // intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(context, page3.class);
                    // intent.putExtra("url",results.get(position).getUrl().toString());
                    context. startActivity(intent);
                }*/

            }
        });
        holder.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.i("iiiiiiiiiiiii","hhhhhhhiiii");
                Posts post = posts.get(position);
                mydatabasehandler db=new mydatabasehandler(context);
                if (i==1)
                {
                    Log.i("iiiiiiiiiiiii","111111111111");
                    holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_s));
                    db.deluser(post.getid());
                    i=0;
                    //Toast.makeText(getContext().getClass(),String.valueOf("del"), Toast.LENGTH_LONG).show();
                }
                else {
                    Log.i("iiiiiiiiiiiii","00000");
                    Posts item = new Posts();
                    //db = new mydatabasehandler(context);

                    item.setid(String.valueOf(post.getid()));
                     item.setcategory(id_category);
                    item.settitle(String.valueOf(post.gettitle()));
                    item.setdes(String.valueOf(post.getdes()));
                    item.setImage(String.valueOf(post.getImage()));
                    item.setdate(String.valueOf(post.getdate()));
                    item.settime(String.valueOf(post.gettime()));
                     item.setisvideo(String.valueOf(post.getisvideo()));

                       item.setcomment_count(String.valueOf(post.getcomment_count()));
                    item.setvisit(String.valueOf(post.getvisit()));
                  item.setIsgallery(String.valueOf(post.getIsgallery()));
                    item.setimages_count(String.valueOf(post.getimages_count()));
                    //not found user
                    String check=db.checkuser(post.getid());
                    if(check=="false")
                    {
                       long s=db.additem(item);
                        //holder.titleTextView.setText(String.valueOf(s));
                        Log.d("myTag", "This is my message");
                        Log.i("insertttttttt","ooooooook");
                        i = 1;
                        holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmarkred));
                     }
                }

            }
        });
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
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);

            lastPosition = position;
        }
    }
    public class PostsViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView description;
        public TextView date;
        public TextView time;
        public TextView txtvisit;
        public TextView txtpic;
        public TextView txtnumpic;
        public TextView txtcomment;
        public ImageView coverImageView;
        public ImageView img;
        public ImageView img_pin;
        public ToggleButton likeImageView;

        public RelativeLayout viewBackground ;
        public LinearLayout viewForeground,viewImagelayout;



        public PostsViewHolder(View itemView) {
            super(itemView);
            titleTextView= (TextView)itemView.findViewById(R.id.titleTextView);
            description= (TextView)itemView.findViewById(R.id.description);
            date= (TextView)itemView.findViewById(R.id.date);
            time= (TextView)itemView.findViewById(R.id.time);
            txtvisit= (TextView)itemView.findViewById(R.id.txtvisit);
            txtcomment= (TextView)itemView.findViewById(R.id.txtcomment);
            coverImageView= (ImageView)itemView.findViewById(R.id.coverImageView);
            img= (ImageView)itemView.findViewById(R.id.img);
            img_pin= (ImageView)itemView.findViewById(R.id.pin);
            likeImageView= (ToggleButton) itemView.findViewById(R.id.likeImageView);
            viewBackground = (RelativeLayout) itemView.findViewById(R.id.view_background);
            viewForeground = (LinearLayout) itemView.findViewById(R.id.view_foreground);
            viewImagelayout = (LinearLayout) itemView.findViewById(R.id.li_image);

            txtpic= (TextView)itemView.findViewById(R.id.txtpic);
            txtnumpic= (TextView)itemView.findViewById(R.id.txtnumpic);
        }

    }
}
