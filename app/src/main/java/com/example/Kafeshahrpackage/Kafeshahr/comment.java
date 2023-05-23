package com.example.Kafeshahrpackage.Kafeshahr;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class comment extends AppCompatActivity {
EditText comment;
    ImageButton send;
    EditText title,name,email;
TextView txtdes;
    String id;
    String id_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
          /* for support version api19*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final ImageButton  close = (ImageButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send = (ImageButton) findViewById(R.id.send);
       comment=(EditText)findViewById(R.id.commentuser) ;
        title=(EditText)findViewById(R.id.titleTextView);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        txtdes = (TextView) findViewById(R.id.txtdes);
        Typeface typefacetext = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/IRANSansWeb.ttf");
        title.setTypeface (typefacetext);
        comment.setTypeface (typefacetext);
        txtdes.setTypeface (typefacetext);
        name.setTypeface (typefacetext);
        email.setTypeface (typefacetext);
        comment.addTextChangedListener(filterTextWatcher);

        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            String title_comment;

            if(!b.getString("title_news").isEmpty())
                title.setText(b.getString("title_news"));
            id=b.getString("id");
            id_parent=b.getString("id_parent");

           // Toast.makeText(comment.this,String.valueOf("parent"+"\n"+(b.getString("id_parent"))), Toast.LENGTH_LONG).show();
            //Toast.makeText(comment.this,String.valueOf("idnews"+"\n"+(b.getString("id"))), Toast.LENGTH_LONG).show();
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(comment.this, id, Toast.LENGTH_SHORT).show();
                APISendingData apiSendingData= new APISendingData(comment.this,id);
                JSONObject requestJsonObject= new JSONObject();
                try {
                    requestJsonObject.put("commenter", name.getText().toString());
                    requestJsonObject.put("comment_text", comment.getText().toString());
                    requestJsonObject.put("email", email.getText().toString());
                    requestJsonObject.put("parent", id_parent);

                    apiSendingData.signUp(requestJsonObject, new APISendingData.OnSignupComplate() {
                        @Override
                        public void onSignUp(boolean success, JSONArray array_result) {
                            if(success == true){

                                name.setText("");
                                comment.setText("");
                                email.setText("");

                                Toast.makeText(comment.this, " نظر شما ثبت شده و پس از بازبینی منتشر میشود", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(comment.this, "اطلاعات ارسال نشد", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              //  Toast.makeText(comment.this,String.valueOf((comment.getText().toString())), Toast.LENGTH_LONG).show();
            }
        });

    }
    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // DO THE CALCULATIONS HERE AND SHOW THE RESULT AS PER YOUR CALCULATIONS
            send.setImageResource(R.drawable.ic_send);
            if(s=="")
            {
                send.setImageResource(R.drawable.ic_sendt);
            }
          }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            send.setImageResource(R.drawable.ic_sendt);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
