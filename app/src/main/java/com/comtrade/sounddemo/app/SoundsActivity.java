package com.comtrade.sounddemo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class SoundsActivity extends Activity implements ActionBar.TabListener {

    private ViewPager viewPager;

    public final List<Sound> sounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);

        initSoundsList();


        initActionBar();


//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new SoundListFragment())
//                    .commit();
//        }
    }

    private void initSoundsList() {
        sounds.add(new Sound(R.raw.appear, getString(R.string.appear)));
        sounds.add(new Sound(R.raw.can_you_hear_me_now, getString(R.string.can_you_hear_me_now)));
        sounds.add(new Sound(R.raw.chomp, getString(R.string.chomp)));
        sounds.add(new Sound(R.raw.contact_type, getString(R.string.contact_type)));
        sounds.add(new Sound(R.raw.dragonwheeze, getString(R.string.dragon_wheeze)));
        sounds.add(new Sound(R.raw.electronic_interference_28, getString(R.string.electronic_interference)));
        sounds.add(new Sound(R.raw.elfs_laughing, getString(R.string.elfs_laughing)));
        sounds.add(new Sound(R.raw.euh_2, getString(R.string.euh)));
        sounds.add(new Sound(R.raw.firebreath, getString(R.string.firebreath)));
        sounds.add(new Sound(R.raw.frustration, getString(R.string.frustration)));
        sounds.add(new Sound(R.raw.grunt, getString(R.string.grunt)));
        sounds.add(new Sound(R.raw.gulp, getString(R.string.gulp)));
        sounds.add(new Sound(R.raw.moan, getString(R.string.moan)));
        sounds.add(new Sound(R.raw.pump_im, getString(R.string.pump)));
        sounds.add(new Sound(R.raw.scaringcataway, getString(R.string.scaring_cat_away)));
        sounds.add(new Sound(R.raw.shots_8, getString(R.string.shots)));
        sounds.add(new Sound(R.raw.sneezing, getString(R.string.sneezing)));
        sounds.add(new Sound(R.raw.thud, getString(R.string.thud)));
        sounds.add(new Sound(R.raw.vacuumandtrumpet, getString(R.string.vacuum_and_trumpet)));
        sounds.add(new Sound(R.raw.yeah_realy, getString(R.string.yeah_really)));
    }

    @SuppressWarnings("ConstantConditions")
    private void initActionBar() {
        viewPager = (ViewPager) findViewById(R.id.container);

        ActionBar actionBar = getActionBar();

        // set the navigation mode to use tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // add tab that plays sounds using MediaPlayer
        actionBar.addTab(actionBar.newTab().setText("MediaPlayer").setTabListener(this));

        // add tab that plays sounds using SoundPool
        actionBar.addTab(actionBar.newTab().setText("MediaPlayer").setTabListener(this));

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            Fragment[] fragments = new Fragment[]{
                    new SoundListFragment(SoundListFragment.TYPE_MEDIA_PLAYER),
                    new SoundListFragment(SoundListFragment.TYPE_SOUND_POOL)
            };

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        int position = tab.getPosition();
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
