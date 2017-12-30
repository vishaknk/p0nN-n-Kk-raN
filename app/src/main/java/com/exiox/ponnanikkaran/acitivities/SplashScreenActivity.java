package com.exiox.ponnanikkaran.acitivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.exiox.ponnanikkaran.base.BaseActivity;

/**
 * Created by priyesh on 27/12/17.
 */

public class SplashScreenActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent homeIntent = new Intent(this, FeedActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
