package com.som.android.oditek;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;

/**
 *
 */
public class TabFragment extends Fragment {



    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    int[] tabIcons = {
            R.drawable.select_video,
            R.drawable.select_image,
            R.drawable.select_navi_milestone
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.activity_icon_text_tabs,null);

        viewPager = (ViewPager)x.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return x;

    }



    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {


        TabFragment.ViewPagerAdapter adapter = new TabFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new OneFragment(), "VIDEOS");
        adapter.addFrag(new TwoFragment(), "IMAGES");
        adapter.addFrag(new ThreeFragment(), "MILESTONE");
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);

        }
    }


}
