package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malihe on 5/25/2018.
 */
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder> {


    private Context mContext;
    private List<Posts> studentList;
    String source;
    String idcat;

    public SwipeRecyclerViewAdapter(Context context, List<Posts> objects,String source) {
        this.mContext = context;
        this.studentList = objects;
        this.source=source;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_recycle_items, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        Typeface typefacenum = Typeface.createFromAsset(mContext.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(mContext.getAssets(), "fonts/IRANSansWeb.ttf");
        viewHolder.titleTextView.setTypeface(typefacenum);
        viewHolder.txtdes.setTypeface(typefacenum);
        viewHolder.date.setTypeface(typefacenum);
        viewHolder.time.setTypeface(typefacenum);
        viewHolder.txtvisit.setTypeface(typefacenum);
        viewHolder.txtcomment.setTypeface(typefacenum);
        final Posts item = studentList.get(position);
        if(source.equals("page2"))
        {
            idcat=item.getcategory();
        }else if(source.equals("maintest"))
        {
            idcat="all";
        }
  viewHolder.titleTextView.setText(item.gettitle() );
        viewHolder.txtdes.setText(item.getdes() );
        // holder.intro.setText(post.getIntro());
        viewHolder.date.setText(item.getdate());
        viewHolder.time.setText(item.gettime());
        viewHolder.txtvisit.setText(item.getvisit());
        viewHolder.txtcomment.setText(item.getcomment_count());
        String temp = item.getImage().replaceAll(" ", "%20");
       Picasso.with(mContext).load(temp).into(viewHolder.coverImageView);
        final String isvideo=item.getisvideo();

        final String id = String.valueOf(item.getid());

        viewHolder.txtdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("videoo",isvideo);
               // Posts post = studentList.get(position);
                if (isvideo=="") {
                    Intent intent1=new Intent(mContext, maintest.class);
                    mContext. startActivity(intent1);
                }

                if (isvideo.equals("false"))
                {
                    Intent intent=new Intent(mContext, page3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat",idcat);
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","bookmark");
                    mContext. startActivity(intent);
                }
                else if  (isvideo.equals("true"))
                {
                    Intent intent=new Intent(mContext, page3_video.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat",idcat);
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","bookmark");
                    mContext. startActivity(intent);
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
        viewHolder.coverImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // holder.likeImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_));
               // Posts post = studentList.get(position);
               // String isvideo=item.getisvideo();

                //final String id = String.valueOf(item.getid());

                if (isvideo.equals("false"))
                {
                    Intent intent=new Intent(mContext, page3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat",idcat);
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","bookmark");
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    mContext. startActivity(intent);
                }
                else if  (isvideo.equals("true"))
                {
                    Intent intent=new Intent(mContext, page3_video.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                    intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                    intent.putExtra("id_cat",idcat);
                    intent.putExtra("title_cat","");
                    intent.putExtra("back","bookmark");
                    //intent.putExtra("url",results.get(position).getUrl().toString());
                    mContext. startActivity(intent);
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
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
        /*viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));*/

        // Drag From Right
        viewHolder.swipeLayout.addDrag
                (SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));


        // Handling different events when swiping
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

      /*  viewHolder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, " onClick : ", Toast.LENGTH_SHORT).show();
                if ((((SwipeLayout) v).getOpenStatus() == SwipeLayout.Status.Close)) {
                    //Start your activity

                    Toast.makeText(mContext, " onClick : ", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, " onClick : " + item.getfirstname() + " \n" + item.getlastname(), Toast.LENGTH_SHORT).show();
            }
        });

viewHolder.rightarrow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       viewHolder.bottom_wrapper.setVisibility(View.GONE);
       /* Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = mContext.getResources().getString(R.string.content);
        shareBody = shareBody + "\n \n https://play.google.com/store/apps/details?id=the.package.id \n\n";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        mContext.startActivity(sharingIntent);
*/
    }
});
        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(v.getContext(), "Clicked on Map " + viewHolder.titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = mContext.getResources().getString(R.string.content);
                shareBody = shareBody + "\n \n https://play.google.com/store/apps/details?id=the.package.id \n\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mContext.startActivity(sharingIntent);

            }
        });


        /*viewHolder.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Share " + viewHolder.titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

     /*   viewHolder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Posts item = studentList.get(position);

                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                studentList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, studentList.size());
                mItemManger.closeAllItems();


                mydatabasehandler db=new mydatabasehandler(mContext);
                db.deluser(item.getid());

                Toast.makeText(view.getContext(), "Deleted " + viewHolder.titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        // mItemManger is member in RecyclerSwipeAdapter Class
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    //  ViewHolder Class

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView tvName;
        TextView tvEmailId;
        ImageButton btnDelete,rightarrow;
        TextView tvEdit;

        ImageButton btnLocation;
        public TextView titleTextView;
        public TextView txtdes,txtvisit,txtcomment;
        public TextView date;
        public TextView time;
        public ImageView coverImageView;
        LinearLayout   bottom_wrapper;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
          /*  titleTextView = (TextView) itemView.findViewById(R.id.tvName);
            tvEmailId = (TextView) itemView.findViewById(R.id.tvEmailId);*/

            titleTextView= (TextView)itemView.findViewById(R.id.titleTextView);
            txtdes= (TextView)itemView.findViewById(R.id.txtdes);
            date= (TextView)itemView.findViewById(R.id.date);
            time= (TextView)itemView.findViewById(R.id.time);
            txtvisit= (TextView)itemView.findViewById(R.id.txtvisit);
            txtcomment= (TextView)itemView.findViewById(R.id.txtcomment);
            coverImageView= (ImageView)itemView.findViewById(R.id.coverImageView);

            btnDelete = (ImageButton) itemView.findViewById(R.id.btndelete);
            rightarrow = (ImageButton) itemView.findViewById(R.id.imageundo);
          //  tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
          //  tvShare = (TextView) itemView.findViewById(R.id.tvShare);
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnshare);
            bottom_wrapper=(LinearLayout)itemView.findViewById(R.id.bottom_wrapper);

        }
    }
}