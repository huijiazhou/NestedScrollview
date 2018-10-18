package com.zhj.cau.nestedscrollview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.game_toolbar);
        tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewpager);


        OneFragment fragment = new OneFragment();
        OneFragment fragment0 = new OneFragment();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragment);
        fragmentList.add(fragment0);
        List<String> mList_tile = new ArrayList<String>();
        mList_tile.add("tab1");
        mList_tile.add("tab2");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(),fragmentList,mList_tile);
        viewPager.setAdapter(tabAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);



    }


}
