package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class FragmentA extends Fragment {
    GridView grid;
    MyAdapter imagesAdapter;
    ArrayList<Uri> imageList = new ArrayList<>();
    ArrayList<String> imageList_string = new ArrayList<String>();
    View view;
    TextView  resfilm;
    Uri filmUri;
    String filePath;
    Button select,selectbtnfilm;
    public static final int video=3;
    Bitmap bitmap;
    public FragmentA() {
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

        view = inflater.inflate(R.layout.fragment_a, container, false);
        selectbtnfilm= (Button) view.findViewById(R.id.selectbtnfilm);

        select = (Button) view.findViewById(R.id.selectbtn);
        resfilm=(TextView)view.findViewById(R.id.resfilm);
        grid=(GridView) view.findViewById(R.id.grid);

        Typeface typefacenum = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");

        select.setTypeface(typefacenum);

        selectbtnfilm.setTypeface(typefacenum);
        TextView resfilm=(TextView)view.findViewById(R.id.resfilm);
        resfilm.setTypeface(typefacenum);

        loadgrid(imageList);
//grid=(ImageView)findViewById(R.id.grid);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooser();


            }
        });

        selectbtnfilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                intent.setType("video/*");
                startActivityForResult(Intent.createChooser(intent,"SelectVideo"),video);

            }
        });



        return view;


    }


    private void showChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
       intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

    }

    private void loadgrid(ArrayList<Uri> imageList)
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
        imagesAdapter = new MyAdapter(getContext(), imageList, check);

        grid.setAdapter(imagesAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

       if ((resultCode == Activity.RESULT_OK) && (data!=null)){
            //Toast.makeText(getContext(),"hast",Toast.LENGTH_SHORT).show();
            if (requestCode == video)
            {
                //Toast.makeText(getContext(),"film",Toast.LENGTH_SHORT).show();
               filmUri = data.getData();

                 filePath = getPathvideo(filmUri);
                resfilm.setText(filePath);
                Toast.makeText(getContext(),"film"+filePath,Toast.LENGTH_SHORT).show();
                return;
            }
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();

                    String filePath = getPath(uri);
                    //Toast.makeText(getContext(),filePath,Toast.LENGTH_SHORT).show();

                    imageList_string.add(filePath);


                    imageList.add(uri);

                }
                loadgrid(imageList);
            }
            else
            {

                Uri picUri = data.getData();

                filePath = getPath(picUri);
                //Toast.makeText(getContext(),filePath,Toast.LENGTH_SHORT).show();

                imageList_string.add(filePath);

                imageList.add(picUri);
                loadgrid(imageList);
                //  grid.setImageURI(picUri);
                //res.setText(filePath);



            }
       }



    }

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getActivity(),contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);

        // String result=String.valueOf(cursor.getColumnCount());
        cursor.close();
        return result;
    }
    private String getPathvideo(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
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

    public  ArrayList<Uri> getArrayimage(){


            //Toast.makeText(getContext(),imageList_string.toString(),Toast.LENGTH_SHORT).show();

        return imageList;
    }
    public String getFilm(){

        return filePath;
    }

}
