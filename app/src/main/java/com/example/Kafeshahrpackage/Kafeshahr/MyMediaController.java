package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;

/**
 * Created by Maliheh on 06/19/2018.
 */

public class MyMediaController extends MediaController
{ private ImageButton fullScreen;
    private String isFullScreen;

    public MyMediaController(Context context) {
        super(context);
    }

    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        //image button for full screen to be added to media controller
        fullScreen = new ImageButton (super.getContext());

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        params.rightMargin = 30;
        params.topMargin=30;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen =  ((Activity)getContext()).getIntent().
                getStringExtra("fullScreenInd");

        if("y".equals(isFullScreen)){
            fullScreen.setImageResource(R.drawable.fullscreenexit);
            fullScreen.setBackground(null);

        }else{
            fullScreen.setImageResource(R.drawable.fullscreenbutton);
            fullScreen.setBackground(null);
         //   fullScreen.setPadding(0,20,0,0);
        }

        //add listener to image button to handle full screen and exit full screen events
        fullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),page3_video.class);

                if("y".equals(isFullScreen)){
                    intent.putExtra("fullScreenInd", "");
                }else{
                    intent.putExtra("fullScreenInd", "y");
                }
                ((Activity)getContext()).startActivity(intent);
            }
        });
    }
   /* private FrameLayout anchorView;


    public MyMediaController(Context context, FrameLayout anchorView)
    {
        super(context);
        this.anchorView = anchorView;
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
    {
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) anchorView.getLayoutParams();
      //  lp.setMargins(0, 0, 0, yNew);

        anchorView.setLayoutParams(lp);
        anchorView.requestLayout();
    }*/
}