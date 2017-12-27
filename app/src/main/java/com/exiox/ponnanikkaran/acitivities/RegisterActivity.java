package com.exiox.ponnanikkaran.acitivities;
/*
 * Description  : Activity for displaying signup and signin
 *
 * Author  		: Nibin Salim, Media Systems, Inc.
 * Date   		: 07/10/15
 * Reviewed By  :
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.exiox.ponnanikkaran.R;
import com.exiox.ponnanikkaran.base.BaseActivity;


public class RegisterActivity extends BaseActivity {

    //member variables
    public ViewPager mVpRegister;
    private ViewPagerAdapter mPagerAdapter;
    private Fragment mSignUpFragment;
    private Fragment mSignInFragment;
    private static RegisterInterface mRegisterInterface;
    private int mViewPagerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Remove title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

//        SharedPreferences sharedPref = getSharedPreferences(EnvoyConstants.ENVOY_DEFAULT_SHARED_PREF, Context.MODE_PRIVATE);
//        boolean loginStatus = sharedPref.getBoolean(EnvoyConstants.LOGININ_SUCCESS, false);
        //checks whether the sign in success or not and based on that redirect the user to home activity
//        if (loginStatus == true) {
            Intent homeIntent = new Intent(RegisterActivity.this, SplashScreenActivity.class);
            startActivity(homeIntent);
            finish();

//        }

        setContentView(R.layout.activity_register);

        mSignUpFragment = new Fragment();
        mSignInFragment = new Fragment();
        //mBottomBar = (TextView) findViewById(R.id.tv_bottomBar);
        mVpRegister = (ViewPager) findViewById(R.id.vp_register);
        mPagerAdapter = new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        //sets the adapter
        mVpRegister.setAdapter(mPagerAdapter);
        mVpRegister.setCurrentItem(0);

        //listener for handling page change
        mVpRegister.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mViewPagerPosition = mVpRegister.getCurrentItem();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //adapter for viewing signin and signup
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private Context mContext;

        public ViewPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {

                case 0: {
                    mSignUpFragment = SignUpFragment.newInstance(mContext);
                    fragment = mSignUpFragment;
                    break;
                }
                case 1: {
                    mSignInFragment = SignInFragment.newInstance(mContext);
                    fragment = mSignInFragment;
                    break;
                }
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}

