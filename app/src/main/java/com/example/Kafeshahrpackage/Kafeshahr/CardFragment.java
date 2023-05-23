package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CardFragment extends Activity {

    ArrayList<Posts> listitems = new ArrayList<>();
    String[] names;
    String[] test;
    int len;
    String finallen;
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu","Petra","Taj Mahal","Colosseum"};
    /* R.drawable.machu_picchu,
             R.drawable.petra,
             R.drawable.taj_mahal,
             R.drawable.colosseum*/
   /* int  Images[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,

    };*/
    String url_bookmark = "http://api.arbika.ir/v1/categories";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Toast.makeText(CardFragment.this, "hi", Toast.LENGTH_SHORT).show();
      //  getActivity().setTitle("7 Wonders of the Modern World");

      //  initializeList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_card);





        APIGettingPosts apiGettingPosts = new APIGettingPosts(this,url_bookmark,"category");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
               // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(CardFragment.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CardFragment.this, "oooooook", Toast.LENGTH_SHORT).show();
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardView);
                    Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(CardFragment.this, posts,"id category","",false,"finish");
                    recyclerView.setLayoutManager(new LinearLayoutManager(CardFragment.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(page2RecyclerViewAdapter);
                }
            }
        });



    }



























/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
       *//* LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*//*
       *//* if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }*//*



        APIGettingPosts apiGettingPosts = new APIGettingPosts(getContext());
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {
            @Override
            public void onReceived(List<Posts> posts) {
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(getContext(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }else {
                   // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardView);
                    //MyAdapter postsAdapter = new MyAdapter(posts);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                  //  recyclerView.setAdapter(postsAdapter);
                    //MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
                    //MyRecyclerView.setHasFixedSize(true);
                    LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
                    MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    MyRecyclerView.setAdapter(new MyAdapter(listitems));
                    MyRecyclerView.setLayoutManager(MyLayoutManager);
                }


            }
        });
        return view;


    }*/

  /*  @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<Posts> list;
        private Context context;
       // private List<Posts> posts;

        public MyAdapter(List<Posts> Data) {
            this.context = context;
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            Posts post= list.get(position);
            holder.titleTextView.setText(list.get(position).getfirstname());
          *//*  holder.coverImageView.setImageResource(list.get(position).getImageResourceId());*//*
            Picasso.with(context).load(post.getImage()).into(holder.coverImageView);
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.likeImageView.setTag(R.drawable.ic_like);


        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);
            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);
                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                    }

                }
            });



            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

                }
            });



        }
    }

    public void initializeList() {
        //Toast.makeText(getContext(),"initializeList", Toast.LENGTH_SHORT).show();
        getdatafromserver();
        listitems.clear();




     *//*   for(int i =0;i<3;i++){

                        Toast.makeText(getContext(),"for", Toast.LENGTH_SHORT).show();
                        Posts item = new Posts();
                        item.setfirstname(test[i]);
                        item.setImageResourceId(Images[i]);
                        item.setIsfav(0);
                        item.setIsturned(0);
                        listitems.add(item);

                    }*//*

    }
    public void getdatafromserver()
    {
        Toast.makeText(getContext(),"getdatafromserver", Toast.LENGTH_SHORT).show();
        String urlone = "https://reqres.in/api/users/2";
        String urlmulti = "https://reqres.in/api/users?page=2";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlmulti, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // t.setText("object  :  "+response.toString());


                        //get data from multi user
                        try {
                            JSONArray arrayStatus = response.getJSONArray("data");

                            len = arrayStatus.length();
                            finallen=String.valueOf(arrayStatus.length());
                            names = new String[len];
                            for (int j = 0; j < len; j++) {
                                JSONObject o = arrayStatus.getJSONObject(j);
                                String statusSeverityDescription = o.optString("first_name", "");
                                //Toast.makeText(MainActivity.this,statusSeverityDescription, Toast.LENGTH_SHORT).show();
                                names[j]=statusSeverityDescription;
                                //Toast.makeText(getContext(),"len0"+String.valueOf(len), Toast.LENGTH_SHORT).show();
                            }
                           *//* for (int i = 0; i < len; i++) {
                                Toast.makeText(getContext(),names[i].toString(), Toast.LENGTH_SHORT).show();
                            }*//*
                            Toast.makeText(getContext(),"len1"+String.valueOf(len), Toast.LENGTH_SHORT).show();
                           // initializeList();
                        }
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(8000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjReq);

    }*/

}
