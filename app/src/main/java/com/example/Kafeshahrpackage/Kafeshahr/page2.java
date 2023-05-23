package com.example.Kafeshahrpackage.Kafeshahr;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

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
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class page2 extends AppCompatActivity {
    private Menu menu;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AccountHeader headerResult = null;
    private Drawer result = null;
    //private SectionsPagerAdapter mSectionsPagerAdapter;

    private static ViewPager mPager;
    private static int currentPage = 0;
     int NUM_PAGES;
    AppBarLayout appBarLayout;
   // private ArrayList<String> ImagesArray = new ArrayList<String>();
   // private String[] IMAGES;
View v;
     String mykey;
    String title;
    String id;
    String device_id;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
         /* for support version api19*/
      //  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();
            title = b.getString("title");
            id=b.getString("id");
        }
       setTitle(title);
        init();
        //TextView titleTextView= (TextView) findViewById(R.id.title);
       /* Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/IRAN.ttf");
        Typeface typefacenum = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb(FaNum).ttf");
        Typeface typefacetext = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");*/
       // titleTextView.setTypeface (typefacetext);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(2).select();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    tabLayout.setVisibility(View.VISIBLE);


                }
                else
                {
                    //Expanded
                    tabLayout.setVisibility(View.INVISIBLE);

                }
            }
        });




//draw menu
        IProfile profile = new ProfileDrawerItem().withName("Malihe Alinaghizadeh").withEmail("Ahmadbadpey@gmail.com").withIcon(R.drawable.usercom);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true).withHeaderBackground(R.color.colorAccent)
                .addProfiles(
                        profile,
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Gmail Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .withTextColor(Color.WHITE)
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
        //end menu


    }
    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
// set Fragmentclass Arguments
        Fragment_three fragobj = new Fragment_three();
        fragobj.setArguments(bundle);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "پر بحث ترین");
        adapter.addFragment(new Fragment_two(), "پر بازدیدترین");
        adapter.addFragment(new Fragment_three(), "جدیدترین");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);

        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //hideOption(R.id.action_info);
        return true;
    }











    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            result.openDrawer();
            return true;
        } else if (id == R.id.refresh) {
            Intent i=new Intent(page2.this,page2.class);
            startActivity(i);
            return true;
        } if (id == R.id.search) {
            Intent i=new Intent(page2.this,Search.class);
            startActivity(i);
            return true;
        } else if (id == R.id.bookmark) {
           // Toast.makeText(page2.this,get_id(), Toast.LENGTH_SHORT).show();
            Bundle b=getIntent().getExtras();
            if(b!=null) {
                // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

                s= b.getString("id");
                // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
            }

            Intent i=new Intent(page2.this,Bookmark.class);
            i.putExtra("idcategory",String.valueOf(s));
            i.putExtra("source","page2");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivity(i);
            return true;
        }
    /* else if (id == R.id.filter) {
            {

                View menuItemView = findViewById(R.id.filter);
               showFilterPopup(menuItemView);
               // initiatePopupWindow(menuItemView);
               // getMenuInflater().inflate(R.menu.popup_filters, menu);


                return true;

            }
    }*/else if (id == android.R.id.home)
        {
            Intent i=new Intent(page2.this,maintest.class);
            finish();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private  void init() {
      /*  for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(String.valueOf(IMAGES[i]));
      */     // Toast.makeText(page2.this,String.valueOf(IMAGES[i]), Toast.LENGTH_SHORT).show();

        mPager = (ViewPager) findViewById(R.id.pager);
        APIGettingPosts apiGettingPosts = new APIGettingPosts(page2.this,"http://api.arbika.ir/v1/category/"+id+"/slides","category_news");
        apiGettingPosts.getPost(new APIGettingPosts.OnPostsReceived() {

            @Override
            public void onReceived(final List<Posts> posts) {
                // Toast.makeText(CardFragment.this, "wowwwwwwwwwww", Toast.LENGTH_SHORT).show();
                if(posts == null || posts.isEmpty()){
                    Toast.makeText(page2.this, "خطا در دریافت اطلاعات" , Toast.LENGTH_SHORT).show();

                }else {
                    NUM_PAGES =posts.size();

                   // Toast.makeText(page2.this, String.valueOf("post"+posts.size()) , Toast.LENGTH_SHORT).show();
                    mPager.setAdapter(new CustomPagerAdapterpage2(page2.this,posts));

                    CirclePageIndicator indicator = (CirclePageIndicator)
                            findViewById(R.id.indicator);
                    if(NUM_PAGES<=1)
                    {
                        indicator.setVisibility(View.GONE);
                    }
                    indicator.setViewPager(mPager);

                    final float density = getResources().getDisplayMetrics().density;

            //Set circle indicator radius
                    indicator.setRadius(5 * density);



                    // Auto start of viewpager
                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == posts.size()) {
                                currentPage = 0;
                            }
                            mPager.setCurrentItem(currentPage++, true);
                        }
                    };
                    Timer swipeTimer = new Timer();
                    swipeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 5000, 5000);


                }
            }
        });

       // Toast.makeText(page2.this,String.valueOf(NUM_PAGES), Toast.LENGTH_SHORT).show();


        // Pager listener over indicator
                    /*    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                            @Override
                            public void onPageSelected(int position) {
                                currentPage = position;

                            }

                            @Override
                            public void onPageScrolled(int pos, float arg1, int arg2) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int pos) {

                            }
                        });*/



    }

    // Display anchored popup menu based on view selected
   /* private void showFilterPopup(View v) {
        PopupMenu popup = new PopupMenu(this,v, Gravity.RIGHT);
      //  popup.setGravity(Gravity.RIGHT);
        popup.setGravity(Gravity.RIGHT);

        // Inflate the menu from xml
        popup.inflate(R.menu.popup_filters);
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action3_sub1:
                        Toast.makeText(page2.this, "Keyword!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action3_sub2:
                        Toast.makeText(page2.this, "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show();
    }*/
   // private PopupWindow pw;

  /*  private PopupWindow initiatePopupWindow(View v) {
        try {

            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.popup, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itema = (TextView) layout.findViewById(R.id.ItemA);


            final TextView itemb = (TextView) layout.findViewById(R.id.ItemB);
            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
             mDropdown = new PopupWindow(layout, FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);
            Drawable background = getResources().getDrawable(android.R.drawable.editbox_dropdown_dark_frame);
            mDropdown.setBackgroundDrawable(background);
        //    mDropdown.showAsDropDown(pop, 5, 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;
    }*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            toggleActionBar();
        }
        return true;
    }

    private void toggleActionBar() {
        ActionBar actionBar = getActionBar();

        if(actionBar != null) {
            if(actionBar.isShowing()) {
                actionBar.hide();
             //   tabLayout.setVisibility(View.GONE);
            }
            else {
                actionBar.show();
                //tabLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    public String getMyData() {

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();
             mykey = b.getString("url");

           // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }
            return mykey;
    }
    public String get_id() {

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            id= b.getString("id");
            // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }
        return id;
    }
    public String get_deviceid() {

        Bundle b=getIntent().getExtras();
        if(b!=null) {
            // Toast.makeText(page3.this,String.valueOf((b.getInt("key"))), Toast.LENGTH_LONG).show();

            device_id= b.getString("device_id");
            // Toast.makeText(page2.this,mykey, Toast.LENGTH_LONG).show();
        }
        return device_id;
    }
    public String get_title_category() {


        return title;
    }
}
