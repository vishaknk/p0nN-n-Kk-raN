package com.exiox.ponnanikkaran.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.exiox.ponnanikkaran.utilities.Constants;
import com.exiox.ponnanikkaran.utilities.General;
import com.exiox.ponnanikkaran.utilities.Utils;

/**
 * Created by Visak on 14/04/17.
 */

public class BaseActivity extends AppCompatActivity {

    private General mGeneral;
    private Utils mUtils;
    protected SharedPreferences mSharedPref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGeneral = new General(this);
        mUtils = new Utils();
        mSharedPref = getSharedPreferences(Constants.DEFAULT_SHARED_PREF,MODE_PRIVATE);

    }
}
