package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malihe on 5/24/2018.
 */

public class APIGettingPosts {
    private String url;
    private Context context;
    private String key;
    //ApplicationController AppController;
    JsonObjectRequest jsonObjReq;
    ProgressDialog pd;

    public APIGettingPosts(Context context, String url, String key) {
        this.context = context;
        this.url = url;
        this.key = key;
        //


    }

    public void getPost(final OnPostsReceived onPostsReceived) {
        String urlmulti = "http://api.arbika.ir/v1/categories";

        // https://reqres.in/api/users?page=2
      /*  pd = ProgressDialog.show(context,"Please Wait...","Please Wait...");
        try{
            Thread.sleep(5000);
        }catch(Exception e){

        }*/
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //  t.setText("object  :  "+response.toString());

                        try {

                          /*                                                          //get data from one user
                                                                                    if (key.equals("details_news")) {
                                                                                        String data = "";
                                                                                        try {
                                                                                            JSONObject obj = response.getJSONObject("result");

                                                                                            String first_name = obj.getString("first_name");
                                                                                            String last_name = obj.getString("last_name");

                                                                                            // Adds strings from object to the "data" string
                                                                                            data += "first_name: " + first_name +
                                                                                                    "last_name : " + last_name;

                                                                                            // Adds the data string to the TextView "results"
                                                                                            // t.setText(data);
                                                                                        }
                                                                                        // Try and catch are included to handle any errors due to JSON
                                                                                        catch (JSONException e) {
                                                                                            // If an error occurs, this prints the error to the log
                                                                                            e.printStackTrace();
                                                                                        }
                                                                                    return;
                                                                                    }*/


                            JSONArray arrayStatus = response.getJSONArray("result");
                            onPostsReceived.onReceived(ParsingPostJSON(arrayStatus));


                            // Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                            //   pd.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //get data from multi user


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//error.getMessage()
                Toast.makeText(context, "ارتباط خود را با اینترنت برقرار کنید", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));

                    return Response.success(new JSONObject(jsonString), cacheEntry);

                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };
        //  pd= ProgressDialog.show(context,"Please Wait...","Please Wait...");
        //ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(8000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjReq);


    }


    private List<Posts> ParsingPostJSON(JSONArray response) {
  /*      if (key.equals("category")) {*/
            try {
                List<Posts> posts = new ArrayList<>();

                                       /* JSONObject o = response.getJSONObject(0);
                                         String statusSeverityDescription = o.optString("first_name", "");
                                        Toast.makeText(context, statusSeverityDescription, Toast.LENGTH_SHORT).show();
                            */
                //  Toast.makeText(context, String.valueOf(response.length()), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject o = response.getJSONObject(i);
                    // String statusSeverityDescription = o.optString("first_name", "");
                    // Toast.makeText(context, o.optString("first_name", ""), Toast.LENGTH_SHORT).show();

                    // JSONArray postArray= response.getJSONArray(i);
                    Posts post = new Posts();

                                if (key.equals("category"))
                                {
                                    post.setid(o.optString("cat_id", ""));
                                    post.settitle(o.optString("name", ""));
                                    post.setImage(o.optString("picture", ""));
                                    posts.add(post);
                                }
                                else if (key.equals("category_news"))
                                {
                                    post.setid(o.optString("post_id", ""));
                                    post.settitle(o.optString("source_title", ""));
                                    post.setdes(o.optString("post_title", ""));
                                    post.setImage(o.optString("picture", ""));
                                    post.setdate(o.optString("short_date", ""));
                                    post.settime(o.optString("short_time", ""));
                                    post.setispin(o.optString("is_pin",""));
                                    post.setisvideo(o.optString("is_video",""));
                                    post.setIsgallery(o.optString("is_gallery",""));
                                    post.setimages_count(o.optString("images_count",""));
                                    post.setvisit(o.optString("hits",""));
                                    post.setcomment_count(o.optString("comments_count",""));
                                    posts.add(post);
                                }
                                else if (key.equals("pin_news"))
                                {
                                    post.setid(o.optString("post_id", ""));
                                    post.settitle(o.optString("post_title", ""));
                                    post.setdes(o.optString("post_sup_title", ""));
                                    post.setImage(o.optString("picture", ""));
                                      /*  post.setdate(o.optString("short_date", ""));
                                        post.settime(o.optString("short_time", ""));*/
                                    // post.setisvideo(o.optString("is_video",""));
                                    posts.add(post);
                                    //is_gallery
                                }
                                else if (key.equals("bookmark"))
                                {
                                    post.setid(o.optString("post_id", ""));
                                    post.settitle(o.optString("source_title", ""));
                                    post.setdes(o.optString("post_title", ""));
                                    post.setImage(o.optString("picture", ""));
                                    post.setdate(o.optString("short_date", ""));
                                    post.settime(o.optString("short_time", ""));

                                    post.setisvideo(o.optString("is_video",""));

                                    posts.add(post);
                                }
                                else if (key.equals("slideshow"))
                                {
                                    post.setid(o.optString("post_id", ""));
                                    post.settitle(o.optString("post_title", ""));
                                    post.setdes(o.optString("post_content_full", ""));
                                    post.setImage(o.optString("picture", ""));
                                    post.setdate(o.optString("short_date", ""));
                                    post.settime(o.optString("short_time", ""));
                                    post.setisvideo(o.optString("is_video",""));
                                    posts.add(post);
                                }
                                else if (key.equals("details_news"))
                                {
                                    post.setid(o.optString("post_id", ""));
                                    post.settitle(o.optString("post_title", ""));
                                    post.setdes(o.optString("post_content_full", ""));
                                    post.setImage(o.optString("picture", ""));
                                    post.setdate(o.optString("short_date", ""));
                                    post.settime(o.optString("short_time", ""));
                                    post.setisvideo(o.optString("is_video",""));
                                    posts.add(post);
                                    //is_gallery
                                }
                                else if (key.equals("comment"))
                                {
                                    post.setid(o.optString("comment_id", ""));
                                    post.settitle(o.optString("commenter", ""));
                                    post.setdes(o.optString("comment_text", ""));
                                    post.setdate(o.optString("created_at",""));
                                    post.setreplies(o.getJSONArray("replies"));
                                    post.setisReply(o.optString("has_replay"));
                                    posts.add(post);

                                    if(post.getisReply()=="true")
                                    {
                                        for (int ii = 0; ii < post.getreplies().length(); ii++)
                                        {
                                            Posts p = new Posts();
                                            JSONObject o1 = post.getreplies().getJSONObject(ii);
                                            p.setid(o1.optString("comment_id", ""));
                                            p.settitle(o1.optString("commenter", ""));
                                            p.setdes(o1.optString("comment_text", ""));
                                            p.setdate(o.optString("created_at",""));
                                            p.setisReply("false");
                                            posts.add(p);
                                        }
                                    }

                                   // post.setImage(o.optString("picture", ""));
                                    //post.setdate(o.optString("short_date", ""));
                                    //post.settime(o.optString("short_time", ""));

                                }
                   // posts.add(post);
                }

                return posts;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

    public interface OnPostsReceived{
        void onReceived(List<Posts> posts);
    }



 /*   public  class MyAsyncTask extends AsyncTask<Void,Void,JSONArray> {
        @Override
        protected void onPreExecute() {
            //UI Interaction
//Create a new progress dialog
            ProgressDialog progressDialog = new ProgressDialog(context);
            //Set the progress dialog to display a horizontal progress bar
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //Set the dialog title to 'Loading...'
            progressDialog.setTitle("Loading...");
            //Set the dialog message to 'Loading application View, please wait...'
            progressDialog.setMessage("Loading application View, please wait...");
            //This dialog can't be canceled by pressing the back key
            progressDialog.setCancelable(false);
            //This dialog isn't indeterminate
            progressDialog.setIndeterminate(false);
            //The maximum number of items is 100
            progressDialog.setMax(100);
            //Set the current progress to zero
            progressDialog.setProgress(0);
            //Display the progress dialog
            progressDialog.show();
        }



        @Override
        protected JSONArray doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray aVoid) {
            //UI Interaction
            progressDialog.dismiss();
        }
    }*/



















}
