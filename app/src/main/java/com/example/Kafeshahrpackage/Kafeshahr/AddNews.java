package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.Activity;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.CursorLoader;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;


import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;


import static android.net.sip.SipErrorCode.SERVER_ERROR;



public class AddNews extends AppCompatActivity /*implements View.OnClickListener*/{

String nameuser,phoneuser,emailuser,titleuser,desuser;
    String filmUri,imagename;
    ArrayList<Uri> ArrayimageUri;
    ArrayList<String> test;
GridView grid;
    MyAdapter imagesAdapter;
    ArrayList<Uri> imageList = new ArrayList<>();
    ArrayList<String> imageList_string = new ArrayList<>();
   EditText name,title,des;
    TextView tv, resfilm;
    Button select,upload,selectbtnfilm;
public static final int video=3;
    FragmentC fc;
    FragmentB fb;
    FragmentA fa;
    Toolbar toolbar;
     MyCommand myCommand;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
      toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* for support version api19*/
      //  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        myCommand = new MyCommand(getApplicationContext());

        fc = new FragmentC();
        fb = new FragmentB();
        fa = new FragmentA();




        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fa,"تصاویر" );
        adapter.addFragment(fb, "متن خبر");
        adapter.addFragment(fc,"ارسال کننده" );

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(2).select();

        // tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);
       /* tabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, savedInstanceState);
*/

        //we need the savedInstanceState to get the position





      /*  selectbtnfilm= (Button) findViewById(R.id.selectbtnfilm);
        upload = (Button) findViewById(R.id.upload);
        select = (Button) findViewById(R.id.selectbtn);
        resfilm=(TextView)findViewById(R.id.resfilm);
        grid=(GridView)findViewById(R.id.grid);
        name=(EditText)findViewById(R.id.name);
        title=(EditText)findViewById(R.id.title);
        des=(EditText)findViewById(R.id.des);
        name.clearFocus();
        upload.requestFocus();
        Typeface typefacenum = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        name.setTypeface(typefacenum);
        title.setTypeface(typefacenum);
        des.setTypeface(typefacenum);
        select.setTypeface(typefacenum);
        upload.setTypeface(typefacenum);
        selectbtnfilm.setTypeface(typefacenum);
        TextView resfilm=(TextView)findViewById(R.id.resfilm);
        resfilm.setTypeface(typefacenum);

        loadgrid(imageList);
//grid=(ImageView)findViewById(R.id.grid);
    select.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
        showChooser();


                                                    }
                                                });
        upload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),String.valueOf(imageList.size()),Toast.LENGTH_SHORT).show();
            }
        });
        selectbtnfilm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                intent.setType("video*//*");
                startActivityForResult(Intent.createChooser(intent,"SelectVideo"),video);

            }
        });*/

    }
   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }*/
    private void showChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
intent.setAction(Intent.ACTION_GET_CONTENT);
startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

    }
/*
    public void loadgrid(ArrayList<Uri> imageList)
    {
        Boolean check;
        if(imageList.size()>0)
        {
            check=true;
        }
        else
        {
            check=false;
        }
         imagesAdapter = new MyAdapter(AddNews.this, imageList, check);

        grid.setAdapter(imagesAdapter);
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK) && (data != null)) {
            if (requestCode == video)
            {

                Uri picUri = data.getData();

                String filePath = getPathvideo(picUri);
                resfilm.setText(filePath);
                Toast.makeText(this,"film"+filePath,Toast.LENGTH_SHORT).show();
                return;
            }
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    imageList.add(uri);

                }
                loadgrid(imageList);
            }
            else
            {
                Uri picUri = data.getData();

                String filePath = getPath(picUri);
                imageList.add(picUri);
                loadgrid(imageList);
                //  grid.setImageURI(picUri);
                //res.setText(filePath);
              //  Toast.makeText(this,filePath,Toast.LENGTH_SHORT).show();
            }
        }



    }
    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(),contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);

       // String result=String.valueOf(cursor.getColumnCount());
        cursor.close();
        return result;
    }
    public String getPathvideo(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.addnews, menu);
      /*  for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.senddata) {
                View  itemChooser = item.getActionView();
                if (itemChooser != null) {
                       Intent ii=new Intent(AddNews.this,Search.class);
                     startActivity(ii);
                }
            }
        }*/
        //hideOption(R.id.action_info);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setTitle("hi");

        int id = item.getItemId();

     if (id == R.id.senddata) {
         setTitle("hi");


        /* nameuser=fc.getName();
         phoneuser=fc.getPhone();
         emailuser=fc.getEmail();
         titleuser=fb.getTitle();
         desuser=fb.getDes();
         */  filmUri=fa.getFilm();
         Toast.makeText(getApplicationContext(), "hiiii", Toast.LENGTH_LONG).show();
         ArrayimageUri=fa.getArrayimage();
         for(final Uri imagePath: ArrayimageUri) {
             Bitmap bitmap = null;
             try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                // Toast.makeText(this, "" + bitmap, Toast.LENGTH_SHORT).show();
                 String images = getStringImage(bitmap);
                 imageList_string.add(images);
             } catch (IOException e) {
                 e.printStackTrace();
             }

         }
         Log.d("teeeeeeeeeeeeeeeeest",imageList_string.toString());
         Toast.makeText(getApplicationContext(), imageList_string.toString(), Toast.LENGTH_LONG).show();
       //  uploaduserimage();
                 /*    for(final String imagePath: ArrayimageUri){
                         try {
                           //  Toast.makeText(getApplicationContext(), imagePath, Toast.LENGTH_SHORT).show();
                          //   Bitmap bitmap = PhotoLoader.init().from(imagePath).getBitmap();
                            // final String encodedString = ImageBase64.encode(bitmap);
                           //  Bitmap bitmap = null;
                         //    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                           //   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                             //byte[] imageBytes = baos.toByteArray();
                             //final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                             String url = "http://api.arbika.ir/v1/userNews";
                                     StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                         @Override
                                         public void onResponse(String response) {
                                             Log.d("Response", response);
                                             try {
                                                 JSONObject jObj = new JSONObject(response);
                                                 String message = jObj.getString("success");

                                                 Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                             } catch (JSONException e) {
                                                 // JSON error
                                                 e.printStackTrace();
                                                 Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                             }


                                         }
                                     }, new Response.ErrorListener() {
                                         @Override
                                         public void onErrorResponse(VolleyError error) {
                                             Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                                         }
                                     }){
                                         @Override
                                         protected Map<String, String> getParams() throws AuthFailureError {
                                             Map<String, String> params = new HashMap<>();
                                             params.put("name", "ali");
                                             params.put("title", "khabar");
                                             params.put("desc", "des");
                                             params.put("images[]", "");
                                             params.put("video", "");
                                             return params;
                                         }
                                     };
                             stringRequest.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                             Volley.newRequestQueue(getBaseContext()).add(stringRequest);
                                   // myCommand.add(stringRequest);

                         } catch (Exception e) {
                             Toast.makeText(getApplicationContext(), "Error while loading image", Toast.LENGTH_SHORT).show();
                         }
                     }*/


                  //  myCommand.execute();


        /* APISendingnews apiSendingData= new APISendingnews(AddNews.this,"http://api.arbika.ir/v1/userNews");
         JSONObject requestJsonObject= new JSONObject();
         try {
             requestJsonObject.put("name", "ali");
             requestJsonObject.put("title", "ahmadi");
             requestJsonObject.put("desc", "test");
             requestJsonObject.put("images[]", "");
             requestJsonObject.put("video", "");
             apiSendingData.signUp(requestJsonObject, new APISendingnews.OnSignupComplate() {
                 @Override
                 public void onSignUp(boolean success) {
                     if(success == true){
setTitle("ok");
                        Toast.makeText(AddNews.this, " اطلاعات ارسال شد", Toast.LENGTH_SHORT).show();

                     }else{
                         setTitle("no");
                         Toast.makeText(AddNews.this, "اطلاعات ارسال نشد", Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         } catch (JSONException e) {
             e.printStackTrace();
         }*/
            return true;
        }else if (id == android.R.id.home)
        {
            Intent i=new Intent(AddNews.this,maintest.class);
            startActivity(i);
            finish();
            return true;
        }
     else if (id == R.id.send)
     {
         filmUri=fa.getFilm();
        // Log.d("teeeeeeeeeeeeeeeeest",imageList_string.toString());
      //   Toast.makeText(getApplicationContext(), ArrayimageUri.toString(), Toast.LENGTH_LONG).show();
       //  uploaduserimage();
         upload();
         return true;
     }

        return super.onOptionsItemSelected(item);
    }
    /*public void uploaduserimage(){
        progressDialog = new ProgressDialog(AddNews.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();
        Toast.makeText(getApplicationContext(), filmUri.toString(), Toast.LENGTH_LONG).show();
        ArrayimageUri=fa.getArrayimage();
        for(Uri imagePath: ArrayimageUri) {

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                // Toast.makeText(this, "" + bitmap, Toast.LENGTH_SHORT).show();
                imagename = getStringImage(bitmap);
               // imageList_string.add(imagename);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        RequestQueue requestQueue = Volley.newRequestQueue(AddNews.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://api.arbika.ir/v1/userNews", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //  JSONObject jObj = new JSONObject(response);
                //  String message = jObj.getString("success");

                Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Mysmart",""+error);
                Toast.makeText(AddNews.this,"errorrrrrrrrr"+ ""+error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();




                param.put("name", "ali");
                param.put("title", "khabar");
                param.put("desc", "des");
               // param.put("images[]","");
                param.put("video", filmUri);
                return param;
            }
        };
       *//* myCommand.add(stringRequest);
        myCommand.execute();*//*
       requestQueue.add(stringRequest);

    ;
    }*/


    public String getStringImage(Bitmap bitmap){
        Log.i("MyHitesh",""+bitmap);
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);


        return temp;


    }
    public void upload()
    { progressDialog = new ProgressDialog(AddNews.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        ArrayimageUri=fa.getArrayimage();
        for(Uri imagePath: ArrayimageUri) {

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                 Toast.makeText(this, "" + bitmap, Toast.LENGTH_SHORT).show();
               // imagename = getStringImage(bitmap);
                // imageList_string.add(imagename);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest
                (Request.Method.POST, "http://api.arbika.ir/v1/userNews",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("success"), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
            * If you want to add more parameters with the image
            * you can do it here
            * here we have only one parameter with the image
            * which is tags
            * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "ali");
                params.put("title", "khabar");
                params.put("desc", "des");
                return params;
            }


            /*
            * Here we are passing image by renaming it with a unique name
            * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("images", new DataPart(imagename + ".PNG", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);

        }



    }











/*
    private void UploadImage() {

        RestApiMultiPartRequests<String> restApiMultiPartRequest =
                new RestApiMultiPartRequests<String>("", hashMap, fileparts, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.i(LOG_TAG, "URL " + url + "\n Response : " + response);
                        if (iRestApiListener != null) {
                            setparsing(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle your error types accordingly.For Timeout & No
                        // connection error, you can show 'retry' button.
                        // For AuthFailure, you can re login with user
                        // credentials.
                        // For ClientError, 400 & 401, Errors happening on
                        // client side when sending api request.
                        // In this case you can check how client is forming the
                        // api and debug accordingly.
                        // For ServerError 5xx, you can do retry or handle
                        // accordingly.
                        int errorCode;
                        if (error instanceof NetworkError) {
                            errorCode = NETWORK_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "NetworkError" + error);
                        } else if (error instanceof ServerError) {
                            errorCode = SERVER_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "ServerError" + error);
                        } else if (error instanceof AuthFailureError) {
                            errorCode = AUTH_FAILURE_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "AuthFailureError" + error);
                        } else if (error instanceof ParseError) {
                            errorCode = PARSE_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "ParseError" + error);
                        } else if (error instanceof NoConnectionError) {
                            errorCode = NO_CONNECTION_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "NoConnectionError" + error);
                        } else if (error instanceof TimeoutError) {
                            errorCode = TIME_OUT_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "TimeoutError" + error);
                        } else {
                            errorCode = UNKNOWN_ERROR;
                            com.beloo.widget.chipslayoutmanager.util.log.Log.i(LOG_TAG, "TimeoutError" + error);
                        }

                        //Log.i(LOG_TAG,"StatusCode" + error.networkResponse.statusCode);
                        if (iRestApiListener != null) {
                            iRestApiListener.onCallFinish();
                            try {
                                iRestApiListener.onError(new JSONArray());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        if (StringUtils.isNotEmpty(AppClass.preferences.getValueFromPreferance(Preferences.TOKEN))) {
                            params.put("Authorization", AppClass.preferences.getValueFromPreferance(Preferences.TOKEN));
                        }

                        return params;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }
                };

        restApiMultiPartRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 2));//10000
        MyApplication.mVolleyInstance.addToRequestQueue(restApiMultiPartRequest);
    }*/
}
