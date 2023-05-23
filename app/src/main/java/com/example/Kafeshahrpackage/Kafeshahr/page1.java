package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class page1 extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface i=Typeface.createFromAsset(getAssets(),"fonts/IRAN.ttf");
        TextView txt=(TextView)findViewById(R.id.action_bar_text);
        txt.setTypeface(i);

        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        flipper.startFlipping();
        flipper.setFocusable(true);
        flipper.requestFocus();

//draw menu
        IProfile profile = new ProfileDrawerItem().withName("Malihe Alinaghizadeh").withEmail("Ahmadbadpey@gmail.com").withIcon(R.drawable.usercom);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
              .withCompactStyle(true).withHeaderBackground(R.color.primary_light)
                .addProfiles(
                        profile,
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Gmail Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .withTextColor(Color.BLACK)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("خانه").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("بازی ها").withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName("توضیحات").withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName("بیشتر"),
                        new SecondaryDrawerItem().withName("تنطیمات").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("راهنمایی").withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName("تراکنش").withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName("مخاطب").withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem.getIdentifier() == 1) {
                        }

                        if (drawerItem instanceof Nameable) {
                           // toolbar.setTitle(((Nameable) drawerItem).getName().getText(page1.this));
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withDrawerGravity(Gravity.RIGHT)
                .build();




        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_LONG).show();*/
                result.openDrawer();
            }
        });
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.btn1);
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(), "hello baby",
                        Toast.LENGTH_LONG).show();*/
                Intent i=new Intent(page1.this,page2.class);
                startActivity(i);

            }
        });
        ImageButton imageButton2 = (ImageButton)findViewById(R.id.btn2);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(), "hello baby",
                        Toast.LENGTH_LONG).show();*/
                Intent i=new Intent(page1.this,page3.class);
                startActivity(i);

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  toolbar.setTitle("");
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.drawershow) {
            result.openDrawer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
