package com.example.Kafeshahrpackage.Kafeshahr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the


 * create an instance of this fragment.
 */
public class Fragment_three extends Fragment {
    public Fragment_three() {
        // Required empty public constructor
    }

    private Parcelable recyclerViewState;
    boolean loaading = false;
    JSONObject page;
    int sizepin;
    int pageing = 1;
    Page2_Recycler_View_Adapter page2RecyclerViewAdapter;
    int totalItemCount, pastVisiblesItems;
boolean endlist;
    RecyclerView recyclerView_news;
    ProgressBar Progress, progresmore;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    String key;

    String url, url_pin;
    String id_category, title, deviceid;
    Boolean news;
    LinearLayoutManager linearLayoutManager;

    List<Posts> post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //  new AsyncCaller().execute();
        //  AsyncTaskRunner runner = new AsyncTaskRunner();

        //runner.execute("5");

        //  setHasOptionsMenu(true);

        /*APIGettingPosts apiGettingPosts = new APIGettingPosts(getActivity(),"http://api.arbika.ir/v1/category/"+key+"/news/0/10","category_news");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(getActivity(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }else {
                    //  Toast.makeText(getActivity(), "oooooook", Toast.LENGTH_SHORT).show();

                    recyclerView = (RecyclerView) getView().findViewById(R.id.cardView);
                    Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), posts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(page2RecyclerViewAdapter);
                }
            }
        });*/
      /*  APIGettingPosts apiGettingPosts = new APIGettingPosts(getActivity(),url_pin,"pin_news");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), url_pin, Toast.LENGTH_SHORT).show();
                if(posts== null || posts.isEmpty()){
                    Toast.makeText(getActivity(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getActivity(), "ooooook", Toast.LENGTH_SHORT).show();
                    recyclerView_pin = (RecyclerView) view.findViewById(R.id.cardView);
                    Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), posts,id_category,title);
                    recyclerView_pin.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recyclerView_pin.setAdapter(page2RecyclerViewAdapter);
                    Progress.setVisibility(View.GONE);



                }
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        page2 activity = (page2) getActivity();
        String myDataFromActivity = activity.getMyData();
        id_category = activity.get_id();
        title = activity.get_title_category();
        deviceid = activity.get_deviceid();
        //Toast.makeText(getActivity(),String.valueOf(deviceid), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(),id_category,Toast.LENGTH_LONG).show();
        /*  key="http://api.arbika.ir/v1/selectedNews/all"*/
        if (!myDataFromActivity.equals("")) {
            key = myDataFromActivity;
            url = key;
            // url=key+"/type/latest/range/all";
            url_pin = "http://api.arbika.ir/v1/category/" + id_category + "/pins";
            // Toast.makeText(getActivity(),key,Toast.LENGTH_LONG).show();
        }


        view = inflater.inflate(R.layout.fragment_three, container, false);
        Progress = (ProgressBar) view.findViewById(R.id.pd);
        progresmore = (ProgressBar) view.findViewById(R.id.pdloadmore);
        recyclerView_news = (RecyclerView) view.findViewById(R.id.cardView);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView_news.setLayoutManager(linearLayoutManager);
        recyclerView_news.setHasFixedSize(true);

        /*RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView_news.setItemAnimator(itemAnimator);*/


        new LoadViewTask().execute();


        //final LinearLayoutManager getlinearLayoutManager = (LinearLayoutManager) recyclerView_news.getLayoutManager();
        recyclerView_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

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
                        recyclerViewState = recyclerView_news.getLayoutManager().onSaveInstanceState();
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

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        //   new AsyncCaller().execute();

    }


    private class LoadViewTask extends AsyncTask<Void, Void, Void> {
        //Before running code in separate thread
        @Override
        protected void onPreExecute() {
            Progress.setVisibility(View.VISIBLE);
           /* Toast.makeText(getActivity(),"url:"+"\n"+url+"\n"+"parameter:"+"\n"+"type: "+"latest"+"\n"+"range: "+"' '"+"\n"+deviceid, Toast.LENGTH_LONG).show();*/
        }

        @Override
        protected Void doInBackground(Void... params) {

            APISendingpage2 apiSendingData = new APISendingpage2(getActivity(), url);
            JSONObject requestJsonObject = new JSONObject();
            try {

                requestJsonObject.put("type", "latest");
                requestJsonObject.put("range", "");
                requestJsonObject.put("device_id", deviceid);

                apiSendingData.signUp(requestJsonObject, new APISendingpage2.OnSignupComplate() {

                    @Override
                    public void onSignUp(boolean success, JSONArray array_result,JSONObject obj) {
                        if (success == true) {


                            if (sizepin == 0) {
                                /*Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();*/

                                page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), ParsingPostJSON(array_result), id_category, title, false, "page2");
                            } else {
                               /* Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();*/
                                post = ParsingPostJSON(array_result);
                                page2RecyclerViewAdapter.updateData(getActivity(), ParsingPostJSON(array_result), id_category, title, false, "page2");

                            }

                            recyclerView_news.setAdapter(page2RecyclerViewAdapter);
                            Progress.setVisibility(View.GONE);
                        } else {
                            //Toast.makeText(getActivity(), "مشکل در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                APIGettingPosts apiGettingPosts_pin = new APIGettingPosts(getActivity(), url_pin, "category_news");
                apiGettingPosts_pin.getPost(new APIGettingPosts.OnPostsReceived() {

                    @Override
                    public void onReceived(List<Posts> posts_pin) {
                        // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                        if (posts_pin == null || posts_pin.isEmpty()) {
                        } else {

                            sizepin = posts_pin.size();

                            page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), posts_pin, id_category, title, true, "page2");


                            recyclerView_news.setAdapter(page2RecyclerViewAdapter);
                            Progress.setVisibility(View.GONE);


                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getActivity(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT);
            }

            return null;

        }
    }

    private class Loadmore extends AsyncTask<Void, Void, Void> {
        //Before running code in separate thread
        @Override
        protected void onPreExecute() {

            progresmore.setVisibility(View.VISIBLE);
            recyclerView_news.scrollToPosition(pastVisiblesItems );
            pageing++;
            // Toast.makeText(getActivity(),String.valueOf(pageing), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            APISendingpage2 apiSendingData = new APISendingpage2(getActivity(), url + "?page=" + String.valueOf(pageing));
            JSONObject requestJsonObject = new JSONObject();
            try {

                requestJsonObject.put("type", "latest");
                requestJsonObject.put("range", "");
                requestJsonObject.put("device_id", deviceid);

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

                                        recyclerView_news.setAdapter(page2RecyclerViewAdapter);
                                        recyclerView_news.scrollToPosition(pastVisiblesItems - 1);


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

    // load initial data
    public void loadData() {
        //"http://api.arbika.ir/v1/category/"+key+"/news/?page=1"
       /* APISendingpage2 apiSendingData= new APISendingpage2(getActivity(),url);
        JSONObject requestJsonObject= new JSONObject();
        try {

            requestJsonObject.put("type", "latest");
            requestJsonObject.put("range", "all");
            requestJsonObject.put("device_id",deviceid);
            apiSendingData.signUp(requestJsonObject, new APISendingpage2.OnSignupComplate() {

                @Override
                public void onSignUp(boolean success, JSONArray array_result) {
                    if(success == true){


                        recyclerView = (RecyclerView) view.findViewById(R.id.cardView);
                        Page2_Recycler_View_Adapter page2RecyclerViewAdapter = new Page2_Recycler_View_Adapter(getActivity(), ParsingPostJSON(array_result),id_category,title,);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(page2RecyclerViewAdapter);
                        Progress.setVisibility(View.GONE);

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
                post.setisvideo(o.optString("is_video", ""));
                post.setIsgallery(o.optString("is_gallery", ""));
                post.setispin(o.optString("is_pin", ""));
                post.setimages_count(o.optString("images_count", ""));
                // Toast.makeText(getActivity(), post.getimages_count(), Toast.LENGTH_SHORT).show();
                post.setvisit(o.optString("hits", ""));
                post.setcomment_count(o.optString("comments_count", ""));
                posts.add(post);
            }
            return posts;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}