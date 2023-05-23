package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Manage_Font extends AppCompatActivity {
TextView txt,txt1,txt2,txt3,txt4;
    Button btn;
    RadioButton rb0;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    Spinner fontnews;
    Spinner fontcomment;
    String fontstyle;
    String newssize;
    String commentsize;
    RadioGroup myRadioGroup;
    String selectedtext;
    int idx;
    RadioButton r;
    Typeface customfont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        Typeface typefacenum = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");

        txt=(TextView)findViewById(R.id.txt);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt3=(TextView)findViewById(R.id.txt3);
        txt4=(TextView)findViewById(R.id.txt4);
        btn=(Button)findViewById(R.id.send);
        myRadioGroup = (RadioGroup)findViewById(R.id.fontfamily);
        rb0=(RadioButton)findViewById(R.id.radio0);
        rb1=(RadioButton)findViewById(R.id.radio1);
        rb2=(RadioButton)findViewById(R.id.radio2);
        rb3=(RadioButton)findViewById(R.id.radio3);
        fontcomment=(Spinner)findViewById(R.id.fontcomment);
        fontnews=(Spinner)findViewById(R.id.fontnews);

        txt.setTypeface(typefacenum,Typeface.BOLD);
        txt4.setTypeface(typefacenum,Typeface.BOLD);
        txt1.setTypeface(typefacenum);
        txt2.setTypeface(typefacenum);
        txt3.setTypeface(typefacenum);
        btn.setTypeface(typefacenum,Typeface.BOLD);
        rb0.setTypeface(typefacenum);
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
                       fontstyle="IRANSansWeb(FaNum).ttf";
                        customfont= Typeface.createFromAsset(getApplication().getAssets(), "fonts/"+fontstyle);
                        txt3.setTypeface(customfont);


                        break;

                    case 1:
                        fontstyle="Yekan.ttf";
                        customfont= Typeface.createFromAsset(getApplication().getAssets(), "fonts/"+fontstyle);
                        txt3.setTypeface(customfont);

                        break;
                    case 2:
                        fontstyle="Mj_Casablanca.ttf";
                        customfont= Typeface.createFromAsset(getApplication().getAssets(), "fonts/"+fontstyle);
                        txt3.setTypeface(customfont);

                        break;
                    case 3:
                        fontstyle="B_Aria_0.ttf";
                        customfont= Typeface.createFromAsset(getApplication().getAssets(), "fonts/"+fontstyle);
                        txt3.setTypeface(customfont);

                        break;

                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newssize=fontnews.getSelectedItem().toString();
                commentsize=fontcomment.getSelectedItem().toString();


               // Toast.makeText(getApplication(),newssize+"\n"+commentsize,Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("font", 0); // 0 - for
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("fontnews", newssize);
                editor.putString("fontcomment", commentsize);
                editor.putString("fontstyle", fontstyle);
                editor.commit();
                finish();
            }
        });

    }
}
