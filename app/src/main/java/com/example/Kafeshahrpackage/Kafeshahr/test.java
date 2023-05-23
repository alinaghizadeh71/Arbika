package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

public class test extends AppCompatActivity {

    public String dir;
    URL url;
    String pass = "http://www.androidbegin.com/tutorial/testimage.png";
    // button to show progress dialog
    Button btnShowProgress;
    InputStream inputStream1;
    TextView txt;
    // Progress Dialog
    BufferedInputStream bufferedInputStream;
    private ProgressDialog pDialog;
    private AsyncTask mMyTask;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url = "https://api.androidhive.info/progressdialog/hive.jpg";
    ImageView my_image;
    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private ProgressDialog mProgressDialog;
    private ImageView mImageView;
    private ImageView mImageViewInternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // show progress bar button
        btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
        // Image view to show image after downloading
        my_image = (ImageView) findViewById(R.id.my_image);
        txt = (TextView) findViewById(R.id.txtt);
        /**
         * Show Progress bar click event
         * */
        // Initialize the progress dialog

        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        mProgressDialog = new ProgressDialog(test.this);
        mProgressDialog.setIndeterminate(true);
        // Progress dialog horizontal style
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Progress dialog title
        mProgressDialog.setTitle("AsyncTask");
        // Progress dialog message
        mProgressDialog.setMessage("Please wait, we are downloading your image file...");

        btnShowProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // starting new Async Task
             /*   mMyTask = new DownloadTask()
                        .execute(stringToURL(
                                "http://www.freeimageslive.com/galleries/objects/general/pics/woodenbox0482.jpg"));
             */   mMyTask = new DownloadTask()
                        .execute(stringToURL(
                                pass));

            }
        });
    }

        private class DownloadTask extends AsyncTask<URL, Void, Bitmap> {
            // Before the tasks execution
            protected void onPreExecute() {
                // Display the progress dialog on async task start
                mProgressDialog.show();
            }

            // Do the task in background/non UI thread
            protected Bitmap doInBackground(URL... urls) {
                URL url = urls[0];
                HttpURLConnection connection = null;

                try {

                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                     inputStream1 = new BufferedInputStream(url.openStream(), 10240);
                     BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                     Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                    // Return the downloaded bitmap
                    return bmp;

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Disconnect the http url connection
                    connection.disconnect();
                }
                return null;
            }

            // When all async task done
            protected void onPostExecute(Bitmap result) {
                // Hide the progress dialog
                mProgressDialog.dismiss();

                if (result != null) {
                    // Display the downloaded image into ImageView
                    my_image.setImageBitmap(result);

                    // Save bitmap to internal storage
                    Uri imageInternalUri = saveImageToInternalStorage(result);
                    // Set the ImageView image from internal storage
                    my_image.setImageURI(imageInternalUri);
                } else {
                    // Notify user that an error occurred while downloading image
                    Snackbar.make(mCLayout, "Error", Snackbar.LENGTH_LONG).show();
                }
            }
        }

        // Custom method to convert string to url

    protected URL stringToURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Custom method to save a bitmap into internal storage
    protected Uri saveImageToInternalStorage(Bitmap bitmap) {
        // Initialize ContextWrapper
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        // Initializing a new file
        // The bellow line return a directory in internal storage
       /*         String intStorageDirectory = getFilesDir().toString();
                File file = new File(Environment.getRootDirectory(), "mypic" + ".jpg");
                file.mkdirs();*/




       // String path = Environment.getExternalStorageDirectory().toString()+"/myvideo";

        File file =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);

        // Create a file to save the image
        File filebase = new File(file, "myfiile"+".jpg");
       /* if (!file.exists())
        {*/
            //file.mkdirs();
      //  }

        try {
            OutputStream stream = null;

            // If the output file exists, it can be replaced or appended to it
            stream = new FileOutputStream(filebase);

            byte buffer[] = new byte[1024];

            int dataSize;

            int loadedSize = 0;

            while ((dataSize = inputStream1.read(buffer)) != -1) {

                loadedSize += dataSize;

               // publishProgress(loadedSize);

                stream.write(buffer, 0, dataSize);

            }



            stream.close();

          /*  // Initialize a new OutputStream
            OutputStream stream = null;

            // If the output file exists, it can be replaced or appended to it
            stream = new FileOutputStream(filebase);

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            // Flushes the stream
            stream.flush();

            // Closes the stream
            stream.close();*/

        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(filebase.getAbsolutePath());
        txt.setText(filebase.getAbsolutePath());
        // Return the saved image Uri
        return savedImageURI;
    }

}




        /**
         * Downloading file in background thread
         * */






