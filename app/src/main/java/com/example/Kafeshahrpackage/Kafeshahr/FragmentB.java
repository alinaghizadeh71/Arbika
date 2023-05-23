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

public class FragmentB extends Fragment {

View view;
    EditText title,des;
    public FragmentB() {
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
        view= inflater.inflate(R.layout.fragment_b, container, false);
        title=(EditText) view.findViewById(R.id.title);
        des=(EditText) view.findViewById(R.id.des);

        Typeface typefacenum = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");

        title.setTypeface(typefacenum);
        des.setTypeface(typefacenum);
        return  view;
    }

    public String getTitle(){
        String title1 = title.getText().toString().trim();
        return title1;
    }
    public String getDes(){
        String des1 = des.getText().toString().trim();
        return des1;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
