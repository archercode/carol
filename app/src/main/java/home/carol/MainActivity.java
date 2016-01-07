package home.carol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import static home.carol.TabKinds.INTERNET;
import static home.carol.TabKinds.NFC;
import static home.carol.TabKinds.SMS;

public class MainActivity extends AppCompatActivity
        implements android.support.v7.app.ActionBar.TabListener {


    public static final String TAG = "Carol";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);


        final ActionBar actionBar = this.getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        final ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Internet")
                .setTag(INTERNET)
                .setIcon(android.R.drawable.ic_menu_recent_history)
                .setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        final ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("NFC")
                .setTag(NFC)
                .setIcon(android.R.drawable.ic_menu_compass)
                .setTabListener(this);

        actionBar.addTab(tab2);

        final ActionBar.Tab tab3 = actionBar
                .newTab()
                .setText("SMS")
                .setTag(SMS)
                .setIcon(android.R.drawable.ic_menu_call)
                .setTabListener(this);

        actionBar.addTab(tab3);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        actionBar.selectTab(tab1);
                        break;
                    case 1:
                        actionBar.selectTab(tab2);
                        break;
                    case 2:
                        actionBar.selectTab(tab3);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        Object i = tab.getTag();
        if (i.equals(TabKinds.INTERNET)) {
            mViewPager.setCurrentItem(0);
        } else if (i.equals(TabKinds.NFC)) {
            mViewPager.setCurrentItem(1);
        } else if (i.equals(TabKinds.SMS)) {
            mViewPager.setCurrentItem(2);
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){
                default:
                    return new InternetFragment();
                case 1:
                    return new NFCItemFragment();
                case 2:
                    return new SMSFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }


}
