package com.reign.danapotplant.home;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.reign.danapotplant.BaseApp;
import com.reign.danapotplant.R;
import com.reign.danapotplant.models.Exam;
import com.reign.danapotplant.models.ProfileResponse;
import com.reign.danapotplant.models.Skill;
import com.reign.danapotplant.networking.Service;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeActivity extends BaseApp implements HomeView, AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    @Inject
    public Service service;

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 30;
    private static final int PERCENTAGE_TO_ADD_TOOLBAR_ELEVATION = 55;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private AppCompatButton saveProfile;

    private ImageView mProfileImage;
    private TextView username;
    private TextView username2;

    private ProfileFragment profileFragment;

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            saveProfile.setVisibility(tab.getPosition() == 0 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();

        HomePresenter presenter = new HomePresenter(service, this);
        presenter.getProfile();
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
        if (id == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void renderView() {
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progress);
        saveProfile = (AppCompatButton) findViewById(R.id.save_profile);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.materialup_appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        username = (TextView) findViewById(R.id.username);
        username2 = (TextView) findViewById(R.id.username2);
        mProfileImage = (ImageView) findViewById(R.id.materialup_profile_image);

        ArrayList<Fragment> fragments = new ArrayList<>();
        profileFragment = new ProfileFragment();
        ReviewFragment placeholderFragment = new ReviewFragment();
        fragments.add(profileFragment);
        fragments.add(placeholderFragment);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(HomeActivity.this, appErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getProfileSuccess(ProfileResponse profileResponse) {
        Gson gson = new Gson();
        Log.d(TAG, "profileResponse: " + gson.toJson(profileResponse));

        username.setText(profileResponse.getUsername());
        username2.setText(profileResponse.getUsername());
        Glide.with(getApplicationContext())
                .load(profileResponse.getAvatar())
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(mProfileImage);

        ArrayList<Skill> skills = (ArrayList<Skill>) profileResponse.getSkills();
        if (skills != null && profileFragment != null) {
            profileFragment.setSkills(skills);
        }

        ArrayList<Exam> exams = (ArrayList<Exam>) profileResponse.getExams();
        if (exams != null && profileFragment != null) {
            profileFragment.setExams(exams);
        }

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        if (mMaxScrollSize != 0) {

            int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;
//            Log.d(TAG, "percentage: " + percentage);
            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false;
                mProfileImage.animate()
                        .scaleY(0).scaleX(0)
                        .setDuration(200)
                        .start();
            }

            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true;
                mProfileImage.animate()
                        .scaleY(1).scaleX(1)
                        .start();
            }

            if (percentage >= PERCENTAGE_TO_ADD_TOOLBAR_ELEVATION && !mIsAvatarShown) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(8);
                }
            } else {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(0);
                }
            }
        }
    }

}
