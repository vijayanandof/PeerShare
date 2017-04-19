package com.vijayanand.peershare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.vijayanand.peershare.fragment.MyPostsFragment;
import com.vijayanand.peershare.fragment.MyTopPostsFragment;
import com.vijayanand.peershare.fragment.RecentPostsFragment;

public class  MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new RecentPostsFragment(),
                    new MyPostsFragment(),
                    new MyTopPostsFragment(),
            };
            private final String[] mFragmentNames = new String[] {
                    "Recent",
                    "My Posts",
                    "My Top Posts"
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Button launches NewPostActivity
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            case R.id.about:startActivity(new Intent(this, about.class));
                return true;
            case R.id.feedback:
                Intent i1 = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://docs.google.com/forms/d/1-RvnSi6TopggBEOaXg_fzm8kINTX99dykJyBFWksHjI/viewform?c=0&w=1"));
                startActivity(i1);
                return true;
            case R.id.rate:
                Toast.makeText(MainActivity.this, "Hope I'll get a 5 star;)",
                        Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store"));
                startActivity(i2);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

