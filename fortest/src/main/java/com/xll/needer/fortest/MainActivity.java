package com.xll.needer.fortest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager);
        List<MyFragment> list = new ArrayList<>();

        MyFragment fragment1 = new MyFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("hah", "MyFragment1");
        fragment1.setArguments(bundle1);

        MyFragment fragment2 = new MyFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("hah", "MyFragment2");
        fragment2.setArguments(bundle2);
//
//        MyFragment fragment3 = new MyFragment();
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("hah", "MyFragment3");
//        fragment3.setArguments(bundle3);


        list.add(fragment1);
        list.add(fragment2);
//        list.add(fragment3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        List<MyFragment> list;
        public MyPagerAdapter(FragmentManager fm, List<MyFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
