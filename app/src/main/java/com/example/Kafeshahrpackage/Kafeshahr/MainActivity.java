package com.example.Kafeshahrpackage.Kafeshahr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity/* implements APIGettingPosts.OnPostsReceived */{
    EditText t,t1;
   // private APIGettingPosts apiGettingPosts;
Button btnViewProducts;
   // private ArrayList<String> id, name, pic, tozihat, packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         t=(EditText)findViewById(R.id.txt1);
        t1=(EditText)findViewById(R.id.txt11);








        // Tag used to cancel the request

        String urlone = "https://reqres.in/api/users/2";
        String urlmulti = "https://reqres.in/api/users?page=2";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlmulti, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        t.setText("object  :  "+response.toString());


                        //get data from multi user
                        try {
                            JSONArray arrayStatus = response.getJSONArray("data");
                            int len = arrayStatus.length();
                            String[] names = new String[len];
                            for (int j = 0; j < len; j++) {
                                JSONObject o = arrayStatus.getJSONObject(j);
                                String statusSeverityDescription = o.optString("first_name", "");
                                Toast.makeText(MainActivity.this,statusSeverityDescription, Toast.LENGTH_SHORT).show();
                                names[j]=statusSeverityDescription;
                            }
                           /* for (int i = 0; i < len; i++) {
                                Toast.makeText(MainActivity.this,names[i], Toast.LENGTH_SHORT).show();
                            }*/
                        }
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }




                                                          //get data from one user
                                                    /*      String data="";
                                                            try {
                                                                JSONObject obj = response.getJSONObject("data");

                                                                String first_name = obj.getString("first_name");
                                                                String last_name = obj.getString("last_name");

                                                                // Adds strings from object to the "data" string
                                                                data += "first_name: " + first_name +
                                                                        "last_name : " + last_name;

                                                                // Adds the data string to the TextView "results"
                                                                t.setText(data);
                                                            }
                                                            // Try and catch are included to handle any errors due to JSON
                                                            catch (JSONException e) {
                                                                // If an error occurs, this prints the error to the log
                                                                e.printStackTrace();
                                                            }*/

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText("Error: " + error.getMessage());

            }
        });
       jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(8000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);


        // Tag used to cancel the request


    }
}
