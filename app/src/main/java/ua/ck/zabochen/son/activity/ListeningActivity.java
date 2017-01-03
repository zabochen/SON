package ua.ck.zabochen.son.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.adapter.ViewPagerAdapter;

public class ListeningActivity extends AppCompatActivity {

    private static final int NUMBER_OF_TABS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_listening);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_listening_toolbar);
        if (toolbar != null) {
            // Set title color
            toolbar.setTitleTextColor(ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.activity_listening_toolbar_title));
            // Toolbar instead ActionBar
            setSupportActionBar(toolbar);
            // Add Home button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Tab Layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_listening_tab_layout);
        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab().setText(R.string.activity_listening_tab_layout_tab_animal_title));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.activity_listening_tab_layout_tab_transport_title));
        }

        // View Pager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.activity_listening_view_pager);
        if (viewPager != null) {
            viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), NUMBER_OF_TABS));
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
    }

}