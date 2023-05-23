package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class FragmentC extends Fragment {
    View view;
    EditText name,family,phone,email;
     public FragmentC() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_c, container, false);
        name=(EditText) view.findViewById(R.id.name);

        phone=(EditText) view.findViewById(R.id.phone);
        email=(EditText) view.findViewById(R.id.email);

        Typeface typefacenum = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");

        name.setTypeface(typefacenum);

        phone.setTypeface(typefacenum);
        email.setTypeface(typefacenum);
        return view;
    }
    public String getName(){
        String name1 = name.getText().toString().trim();
        return name1;
    }
    public String getPhone(){
        String age1 = phone.getText().toString().trim();
        return age1;
    }
    public String getEmail(){
        String gender1 = email.getText().toString().trim();
        return gender1;
    }
}


