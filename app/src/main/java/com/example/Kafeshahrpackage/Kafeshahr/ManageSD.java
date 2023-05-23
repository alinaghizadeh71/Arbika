package com.example.Kafeshahrpackage.Kafeshahr;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class ManageSD extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    ProgressBar pd;
    String device_id;
    TextView text,text1,lb;
    int pval = 0;
    String statusprogress="100";
    SharedPreferences sharedpreferencesheap;
    private static final int WRITE_REQUEST_CODE = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_sd);

        Typeface typefacenum = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(getApplication().getAssets(), "fonts/IRANSansWeb.ttf");

        pd=(ProgressBar)findViewById(R.id.pd);

          text=(TextView)findViewById(R.id.numnews1);
        text1=(TextView)findViewById(R.id.numnews);
        lb=(TextView)findViewById(R.id.lb);
        Button btndel=(Button)findViewById(R.id.btndel);
        Button btnsend=(Button)findViewById(R.id.send);

        final SeekBar seekbar=(SeekBar)findViewById(R.id.sbBar);
        //title.setTypeface (typefacetext);
        text.setTypeface (typefacenum);
        text1.setTypeface (typefacenum);
        lb.setTypeface (typefacenum);
        btndel.setTypeface (typefacenum);
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            device_id= b.getString("device_id");
            // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }
        //text.setText(seekbar.getProgress() + "/" + seekbar.getMax());
        sharedpreferencesheap = getSharedPreferences("heap",
                Context.MODE_PRIVATE);

        if (sharedpreferencesheap.contains("heapsize"))
        {
                statusprogress=sharedpreferencesheap.getString("heapsize", "");
            //Toast.makeText(this,sharedpreferencesheap.getString("heapsize", ""),Toast.LENGTH_LONG).show();
        }

            seekbar.setProgress(Integer.parseInt(statusprogress));
            text.setText(statusprogress + " مگابایت");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText(pval + " مگابایت");
            }
        });

btndel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String folder = Environment.getExternalStorageDirectory() + File.separator + "Kafeshahr/";

        File directory = new File(folder);
        boolean check=deleteDirectory(directory);
        if(check==true)
        {

            Toast.makeText(getBaseContext(),"پوشه تصاویر با موفقیت حذف شد",Toast.LENGTH_LONG).show();
            lb.setText("سایز فعلی پوشه تصاویر:"+String.valueOf(0)+"مگابایت");
        }
        else
            Toast.makeText(getBaseContext(),"خطا",Toast.LENGTH_LONG).show();

    }
});
btnsend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("heap", 0); // 0 - for
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("heapsize", String.valueOf(pval));

        editor.commit();
        finish();
    }
});

        if (CheckForSDCard.isSDCardPresent()) {

            //check if app has permission to write to the external storage.
            if (EasyPermissions.hasPermissions(ManageSD.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Get the URL entered
                try {
                    writeToSD();
                } catch (IOException e) {
                    Toast.makeText(this,String.valueOf("edddddd"),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            } else {
                //If permission is not present request for the same.
                EasyPermissions.requestPermissions(ManageSD.this, "write", WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        } else {
            Toast.makeText(getApplicationContext(),
                    "SD Card not found", Toast.LENGTH_LONG).show();

        }

    }
    private void writeToSD() throws IOException {
        mydatabasehandler db = new mydatabasehandler(getBaseContext());
       // Toast.makeText(this,db.getpath(),Toast.LENGTH_LONG).show();

        File currentDB = new File(db.getpath());
        String folder = Environment.getExternalStorageDirectory() + File.separator + "Kafeshahr/";

        //Create androiddeft folder if it does not exist
        File directory = new File(folder);

        if (!directory.exists()) {
            directory.mkdirs();
        }


       // Toast.makeText(this,String.valueOf(sizeMB+s),Toast.LENGTH_LONG).show();

//////////////////////////////get backup db///////////////////////////////////////
                       /* File sd = Environment.getExternalStorageDirectory();
                        if (sd.canWrite()) {
                            String backupDBPath = "Kafeshahr.db";
                            File backupDB = new File(folder, backupDBPath);
                            if (currentDB.exists()) {
                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                //Toast.makeText(this,String.valueOf(src.size()),Toast.LENGTH_LONG).show();
                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                dst.transferFrom(src, 0, src.size());
                                src.close();
                                dst.close();
                            }
                            if (backupDB.exists()) {
                                //Toast.makeText(this,String.valueOf("backup"),Toast.LENGTH_LONG).show();

                            }
                           }*/
///////////////////////////////////////////////////////////////////////////////////
        long size = 0;
        size= getFileFolderSize(directory);
        float sizeMB = (float) size / 1024 / 1024;
        String s = " MB";

        lb.setText("سایز فعلی پوشه تصاویر:"+String.valueOf(String.format("%.2f", sizeMB))+"مگابایت");
        if (sizeMB < 1) {
            sizeMB = (float) size / 1024;
            s = " KB";

            lb.setText("سایز فعلی پوشه تصاویر:"+String.valueOf(String.format("%.2f", sizeMB))+"کیلوبایت");
        }
         }
    public static long getFileFolderSize(File dir) {
        long size = 0;
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else
                    size += getFileFolderSize(file);
            }
        } else if (dir.isFile()) {
            size += dir.length();
        }
        return size;
    }
    public  boolean deleteDirectory(File path) {
        ///////////delete db////////////
        try {
            mydatabasehandler db = new mydatabasehandler(getBaseContext());

           boolean b=db.delall();
            Toast.makeText(this,"deldb"+String.valueOf(b),Toast.LENGTH_LONG).show();
        }
      catch (Exception e)
      {
          return false;
      }

        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }














    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, ManageSD.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
        //url = editTextUrl.getText().toString();
        try {
            writeToSD();
        } catch (IOException e) {
            Toast.makeText(this,String.valueOf("edddddd"),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
       // Log.d(TAG, "Permission has been denied");
    }

}
