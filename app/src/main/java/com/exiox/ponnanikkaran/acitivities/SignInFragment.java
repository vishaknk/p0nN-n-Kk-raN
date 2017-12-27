package com.exiox.ponnanikkaran.acitivities;
/*
 * Description  : SignIn fragment
 *
 * Author  		: Nibin Salim, Media Systems, Inc.
 * Date   		: 06/10/15
 * Reviewed By  :
 */

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exiox.ponnanikkaran.R;

import org.json.JSONObject;

public class SignInFragment extends Fragment implements RegisterInterface {

    //member variables
    private Context mContext;
    private EditText mEmail;
    private EditText mPassword;
    private Button mSignIn;
    private TextView mEmailAddress;
    private TextView mForgotOrResend;
    private RelativeLayout mVerificationLayout;
    private RelativeLayout mSignInTitleLayout;
    private ProgressBar mProgressBar;
    private Dialog mOverlayDialog;
    private JSONObject mResponseObject;
    private static int GET_VERIFICATION = 0, SIGN_IN = 123;
    private static final String[] INITIAL_PERMS_PHONE_STATE = {
            Manifest.permission.READ_PHONE_STATE};
    private static final int INITIAL_REQUEST_PHONE_STATE = 133;


    public static Fragment newInstance(Context context) {
        SignInFragment fragment = new SignInFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, null);

        mContext = getActivity();
        mEmail = (EditText) rootView.findViewById(R.id.et_email);
        mPassword = (EditText) rootView.findViewById(R.id.et_password);
        mSignIn = (Button) rootView.findViewById(R.id.btn_signIn);
        mEmailAddress = (TextView) rootView.findViewById(R.id.tv_emailAddress);
        mVerificationLayout = (RelativeLayout) rootView.findViewById(R.id.rl_blueBackground);
        mSignInTitleLayout = (RelativeLayout) rootView.findViewById(R.id.rl_signInTitle);
        mForgotOrResend = (TextView) rootView.findViewById(R.id.tv_forgotPassword);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.pb_progressSignIn);
        mOverlayDialog = new Dialog(mContext, android.R.style.Theme_Panel);
        mOverlayDialog.setCancelable(false);
        return rootView;
    }

    @Override
    public void onResponseReceived(JSONObject object) {

    }

    @Override
    public void requestEndedWithError(String error) {

    }

    @Override
    public void onGetResponseReceived(JSONObject object) {

    }
}