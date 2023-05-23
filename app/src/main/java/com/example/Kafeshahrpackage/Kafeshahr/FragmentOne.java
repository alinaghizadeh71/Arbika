package com.example.Kafeshahrpackage.Kafeshahr;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentOne extends Fragment {
    public FragmentOne() {
        // Required empty public constructor
    }



/*    private static final Integer[] IMAGES = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image1,
            R.drawable.image4,
            R.drawable.image2};*/
    RecyclerView recyclerView;
    JSONObject page;
    int pageing = 1;
    List<Posts> post;
    private Parcelable recyclerViewState;
    boolean endlist;
    boolean loaading = false;
    ProgressBar Progress,progresmore;
    Page2_Recycler_View_Adapter page2RecyclerViewAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
     View view;
    String key;

    String selectedtext;
    int idx;
    RadioButton r;
    RadioButton rb1,rb2,rb3;
    RadioGroup myRadioGroup;
    String url;
    String id_category,title,deviceid;
    String type,range;
    int totalItemCount,pastVisiblesItems;
    LinearLayoutManager linearLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Typeface typefacenum = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        // Inflate the layout for this fragment
        page2 activity = (page2) getActivity();
        String myDataFromActivity = activity.getMyData();
        id_category = activity.get_id();
        title=activity.get_title_category();
        deviceid=activity.get_deviceid();
        //Toast.makeText(getActivity(),"one",Toast.LENGTH_LONG).show();
        key=myDataFromActivity;
        /*  key="http://api.arbika.ir/v1/selectedNews/all"*/
       // url = key + "/type/most_comment/range/today";
        url=key;
        type="most_comment";
        range="today";
         view = inflater.inflate(R.layout.fragment_one, container, false);
        myRadioGroup = (RadioGroup) view.findViewById(R.id.myRadioGroup);
        rb1  = (RadioButton) view.findViewById(R.id.rb1);
        rb2  = (RadioButton) view.findViewById(R.id.rb2);
        rb3  = (RadioButton) view.findViewById(R.id.rb3);
        rb1.setTypeface(typefacenum);
        rb2.setTypeface(typefacenum);
        rb3.setTypeface(typefacenum);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioButtonID = myRadioGroup.getCheckedRadioButtonId();
                View radioButton = myRadioGroup.findViewById(radioButtonID);
                idx = myRadioGroup.indexOfChild(radioButton);
                r = (RadioButton) myRadioGroup.getChildAt(idx);
                selectedtext = r.getText().toString();
               // Toast.makeText(getActivity(),String.valueOf(idx), Toast.LENGTH_SHORT).show();

                switch(idx) {
                    case 0:
                       // url = key + "/type/most_comment/range/today";
                        url=key;
                        type="most_comment";
                        range="today";
                        //Toast.makeText(getActivity(),String.valueOf(url), Toast.LENGTH_SHORT).show();
                        new LoadViewTask().execute();
break;
                    case 1:
                        // Fragment 2
                       // url = key + "/type/most_comment/range/week";
                        //Toast.makeText(getActivity(),String.valueOf(url), Toast.LENGTH_SHORT).show();
                        url=key;
                        type="most_comment";
                        range="week";
                        new LoadViewTask().execute();
                        break;
                    case 2:
                       // url = key + "/type/most_comment/range/month";
                        // Toast.makeText(getActivity(),String.valueOf(url), Toast.LENGTH_SHORT).show();
                        url=key;
                        type="most_comment";
                        range="month";
                        new LoadViewTask().execute();
                        break;
                }
            }
        });




        Progress = (ProgressBar)view.findViewById(R.id.pd);
        progresmore = (ProgressBar) view.findViewById(R.id.pdloadmore);
        recyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        linearLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        new LoadViewTask().execute();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,final int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
            //Action here
            // Toast.makeText(getActivity().getBaseContext(),String.valueOf("hi"),Toast.LENGTH_SHORT).show();
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findLastVisibleItemPosition();
                // Toast.makeText(getActivity().getBaseContext(), String.valueOf(totalItemCount), Toast.LENGTH_SHORT).show();
                Log.d("pastVisiblesItems", String.valueOf(pastVisiblesItems));
                Log.d("totalItemCount", String.valueOf(totalItemCount));
                if (loaading == false) {
                    if (totalItemCount == (pastVisiblesItems + 1)) {
                        loaading = true;
                        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                        if(endlist==false)
                            new Loadmore().execute();

                        //   Toast.makeText(getActivity().getBaseContext(), String.valueOf("last"), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();

            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark);

        return view;

    }

    private class LoadViewTask extends AsyncTask<Void, Void,Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            recyclerView = (RecyclerView) view.findViewById(R.id.cardView);
            recyclerView.setVisibility(View.GONE);
            Progress.setVisibility(View.VISIBLE);
           /* Toast.makeText(getActivity(),"url:"+"\n"+url+"\n"+"parameter:"+"\n"+"type: "+type+"\n"+"range: "+range+"\n"+deviceid, Toast.LENGTH_LONG).show();*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            APISendingpage2 apiSendingData= new APISendingpage2(getActivity(),url);
            JSONObject requestJsonObject= new JSONObject();
            try {

                requestJsonObject.put("type", type);
                requestJsonObject.put("range", range);
                requestJsonObject.put("device_id",deviceid);

                apiSendingData.signUp(requestJsonObject, new APISendingpage2.OnSignupComplate() {

                    @Override
                    public void onSignUp(boolean success, JSONArray array_result,JSONObject obj) {
                        if(success == true){



                            page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), ParsingPostJSON(array_result),id_category,title,false,"page2");

                            recyclerView.setAdapter(page2RecyclerViewAdapter);
                            Progress.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                        }else{
                            // Toast.makeText(getActivity(), "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }


        @Override
        protected void onPostExecute(Void result)
        {


        }
    }
    // load initial data
    public void loadData() {
        /*APISendingTile apiSendingData= new APISendingTile(getActivity(),url);
        JSONObject requestJsonObject= new JSONObject();
        try {

            requestJsonObject.put("type", type);
            requestJsonObject.put("range", range);
            requestJsonObject.put("device_id",deviceid);

            apiSendingData.signUp(requestJsonObject, new APISendingTile.OnSignupComplate() {

                @Override
                public void onSignUp(boolean success, JSONArray array_result) {
                    if(success == true){


                        recyclerView = (RecyclerView) view.findViewById(R.id.cardView);
                        Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), ParsingPostJSON(array_result),id_category,title);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(page2RecyclerViewAdapter);
                        Progress.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(getActivity(), "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        mSwipeRefreshLayout.setRefreshing(false);
    }
    private List<Posts> ParsingPostJSON(JSONArray response) {

        try {
            List<Posts> posts = new ArrayList<>();


            for (int i = 0; i < response.length(); i++) {
                JSONObject o = response.getJSONObject(i);
                Posts post = new Posts();
                post.setid(o.optString("post_id", ""));
                post.settitle(o.optString("source_title", ""));
                post.setdes(o.optString("post_title", ""));
                post.setImage(o.optString("picture", ""));
                post.setdate(o.optString("short_date", ""));
                post.settime(o.optString("short_time", ""));
                post.setisvideo(o.optString("is_video",""));
                post.setIsgallery(o.optString("is_gallery",""));
                post.setimages_count(o.optString("images_count",""));
                post.setvisit(o.optString("hits",""));
                post.setcomment_count(o.optString("comments_count",""));
                posts.add(post);

            }

            return posts;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    private class Loadmore extends AsyncTask<Void, Void, Void> {
        //Before running code in separate thread
        @Override
        protected void onPreExecute() {

            progresmore.setVisibility(View.VISIBLE);
            recyclerView.scrollToPosition(pastVisiblesItems );
            pageing++;
            // Toast.makeText(getActivity(),String.valueOf(pageing), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            APISendingpage2 apiSendingData = new APISendingpage2(getActivity(), url + "?page=" + String.valueOf(pageing));
            JSONObject requestJsonObject = new JSONObject();
            try {

                requestJsonObject.put("type", type);
                requestJsonObject.put("range", range);
                requestJsonObject.put("device_id",deviceid);

                apiSendingData.signUp(requestJsonObject, new APISendingpage2.OnSignupComplate() {

                    @Override
                    public void onSignUp(boolean success, JSONArray array_result,JSONObject obj) {
                        if (success == true) {
                            try {
                                page = obj.getJSONObject("pagination");
                                String total = page.getString("total_pages");
                                String current_page = page.getString("current_page");
                                //  Toast.makeText(getActivity(),total+current_page, Toast.LENGTH_SHORT).show();

                                loaading = false;
                               /* Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();*/
                                post = ParsingPostJSON(array_result);
                                if (post == null || post.isEmpty()) {
                                    progresmore.setVisibility(View.GONE);
                                } else {
                                    page2RecyclerViewAdapter.updateData(getActivity(), post, id_category, title,                                            false, "page2");
                                    //  recyclerView_news.getAdapter().notifyDataSetChanged();

                                    // recyclerView_news.getLayoutManager().onRestoreInstanceState                                                          (recyclerViewState);

                                    // linearLayoutManager.setStackFromEnd(true);

                                    recyclerView.setAdapter(page2RecyclerViewAdapter);
                                    recyclerView.scrollToPosition(pastVisiblesItems - 1);


                                    progresmore.setVisibility(View.GONE);
                                    if (total==current_page) {

                                        endlist = true;
                                        // return;
                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            //  Toast.makeText(getActivity(), "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPostExecute(Void result) {

            // Progress.setVisibility(View.GONE);
        }
    }
}
