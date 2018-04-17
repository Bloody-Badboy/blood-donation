package com.udacity.nanodegree.blooddonation.ui.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.nanodegree.blooddonation.R;
import com.udacity.nanodegree.blooddonation.base.BaseActivity;
import com.udacity.nanodegree.blooddonation.ui.home.fragment.BloodSearchFragment;
import com.udacity.nanodegree.blooddonation.ui.home.fragment.GoogleMapFragment;
import com.udacity.nanodegree.blooddonation.ui.home.fragment.RequestListFragment;

/**
 * Created by riteshksingh on Apr, 2018
 */
public class HomeActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        if (findViewById(R.id.home_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            // BloodSearchFragment bloodSearchFragment = new BloodSearchFragment();

            GoogleMapFragment googleMapFragment = new GoogleMapFragment();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            // bloodSearchFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_fragment_container, googleMapFragment).commit();
            setToolbarTitle("Nearby Blood Donors");
        }
    }

    private void initView() {
        setContentView(R.layout.activity_home);

        mToolbarTitle = findViewById(R.id.toolbar_title);

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void replaceFragmentWith(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.home_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
        mToolbarTitle.clearAnimation();
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(mToolbarTitle, "alpha", 0, 1, 1, 1),
                ObjectAnimator.ofFloat(mToolbarTitle, "scaleX", 0.3f, 1.05f, 0.9f, 1),
                ObjectAnimator.ofFloat(mToolbarTitle, "scaleY", 0.3f, 1.05f, 0.9f, 1)
        );
        mAnimatorSet.setDuration(500);
        mAnimatorSet.start();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navigation_search) {
            replaceFragmentWith(new BloodSearchFragment());
            setToolbarTitle("Blood Search");
            return true;
        } else if (item.getItemId() == R.id.navigation_requests) {
            replaceFragmentWith(new RequestListFragment());
            setToolbarTitle("Blood Requests");
            return true;
        } else if (item.getItemId() == R.id.navigation_maps) {
            replaceFragmentWith(new GoogleMapFragment());
            setToolbarTitle("Nearby Blood Donors");
            return true;
        }
        return false;
    }
}
