package ua.ck.zabochen.son.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import ua.ck.zabochen.son.fragment.AnimalsFragment;
import ua.ck.zabochen.son.fragment.TransportFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mNumberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new AnimalsFragment();
            case 1:
                return new TransportFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
