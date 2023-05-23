package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maliheh on 06/07/2018.
 */

class CustomPagerAdapterpage2 extends PagerAdapter {
  /*  int[] mResources = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image1,
            R.drawable.image4,
            R.drawable.image2};*/

  //  private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private int i;
    private String id_cat;
    private List<Posts> posts;
    private Context context;


    public CustomPagerAdapterpage2(Context context, List<Posts> posts) {
        this.context = context;
        this.posts = posts;
     //   this.IMAGES=IMAGES;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view,final int position) {
       final View imageLayout;
        final ImageView imageView;
        final TextView title;



        imageLayout = inflater.inflate(R.layout.pager_item_page2, view, false);
        assert imageLayout != null;
        imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        title=(TextView)imageLayout
                .findViewById(R.id.txtt);

        // imageView.setImageResource(IMAGES.get(position));
        Posts post= posts.get(position);
        String temp = post.getImage().replaceAll(" ", "%20");
        Picasso.with(context).load(temp).into(imageView);
        title.setText(post.getdes());
        final String id = String.valueOf(post.getid());
        view.addView(imageLayout, 0);
        Typeface typefacenum = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        title.setTypeface (typefacetext,Typeface.BOLD);
        imageLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(context, page3.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("url","http://api.arbika.ir/v1/news/"+id+"?include=tags,related");
                intent.putExtra("url_comment","http://api.arbika.ir/v1/news/"+id+"/comments?include=replies");
                //intent.putExtra("url",results.get(position).getUrl().toString());
                context. startActivity(intent);
            }
        });

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
