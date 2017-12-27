package com.exiox.ponnanikkaran.acitivities;
/*
 * Description  : SignUp fragment
 *
 * Author  		: Nibin Salim, Media Systems, Inc.
 * Date   		: 06/10/15
 * Reviewed By  :
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exiox.ponnanikkaran.R;

import org.json.JSONObject;

public class SignUpFragment extends Fragment implements RegisterInterface{

    //member variables
    private Context mContext;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mPassword;
    private boolean mSend = false;
    private CheckBox mAgreement;
    private Button mJoin, mPopupClose;
    private ProgressBar mProgressBar;
    private Dialog mOverlayDialog, popupPswrdValid;
    private int mPagerPosition;
    private TextView mAgreementText, mPswrdMsg;
    private static int SIGN_UP = 1;
    private ImageView mTickCharacter, mTickAlphaNum, mTickUprLwr, mTickSpeclCharct, mTickUsrnme;
    private boolean isInfo = true;


    public static Fragment newInstance(Context context) {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up, null);

        mContext = getActivity();
        mFirstName = (EditText) rootView.findViewById(R.id.et_firstName);
        mLastName = (EditText) rootView.findViewById(R.id.et_lastName);
        mEmail = (EditText) rootView.findViewById(R.id.et_email);
        mPassword = (EditText) rootView.findViewById(R.id.et_password);
        mAgreement = (CheckBox) rootView.findViewById(R.id.cb_agree);
        mAgreementText = (TextView) rootView.findViewById(R.id.tv_agreement);
        mJoin = (Button) rootView.findViewById(R.id.btn_join);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.pb_progressSignUp);
        mPswrdMsg = (TextView) rootView.findViewById(R.id.tv_pswrd_valid);
        String first = getResources().getString(R.string.cb_agree);
        String next = "<font color='#00719E'>Terms of use and Privacy Policy</font>";
        mAgreementText.setText(Html.fromHtml(first + " " + next));
        mOverlayDialog = new Dialog(mContext, android.R.style.Theme_Panel);
        mOverlayDialog.setCancelable(false);
        //password validation-tapping on alert icon shows the rules with checkmarks showing passed rules
//        mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_info, 0);
        popupPswrdValid();
        mPassword.addTextChangedListener(new TextWatcher() {
            Drawable originalDrawable = mPassword.getBackground();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() != 0) {
                    //if all the requirements met
                    if (validatePassword(s.toString().trim())) {
//                        mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tick, 0);
                        mPassword.setBackground(originalDrawable);
                        mPswrdMsg.setVisibility(View.GONE);
                    }
                    //if any of the requirmnt is not met
                    else {
//                        mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
//                        mPassword.setBackground(getResources().getDrawable(R.drawable.pswrd_backgrnd));
                        mPswrdMsg.setVisibility(View.VISIBLE);
                    }
                    // compound drwabale of password edit text is not R.drawable.ic_info
                    isInfo = false;
                }
                //default showing rules for paswrd
                else {
//                    mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_info, 0);
                    mPassword.setBackground(originalDrawable);
                    mPswrdMsg.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mPassword.getWindowToken(), 0);
                    // compound drwabale of password edit text is R.drawable.ic_info
                    isInfo = true;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                //tapping on alert icon shows the rules with checkmarks showing passed rules
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mPassword.getRight() - mPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            popupPswrdValid();
                            validatePassword(mPassword.getText().toString());
                            popupPswrdValid.show();
                        return true;
                    }
                }
                return false;
            }
    });

        // to check whether password contains email(check after entering password if any changes made on email)
        mEmail.addTextChangedListener(new TextWatcher() {
            Drawable originalDrawable = mPassword.getBackground();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mPassword.getText().toString().trim().length() != 0) {
                    //if all the requirements met
                    if (validatePassword(mPassword.getText().toString().trim())) {
//                        mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tick, 0);
                        mPassword.setBackground(originalDrawable);
                        mPswrdMsg.setVisibility(View.GONE);
                    }
                    //if any of the requirmnt is not met
                    else {
//                        mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
//                        mPassword.setBackground(getResources().getDrawable(R.drawable.pswrd_backgrnd));
                        mPswrdMsg.setVisibility(View.VISIBLE);
                    }
                    // compound drwabale of password edit text is not R.drawable.ic_info
                    isInfo = false;
                }
                //default showing rules for paswrd
                else {
//                    mPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_info, 0);
                    mPassword.setBackground(originalDrawable);
                    mPswrdMsg.setVisibility(View.GONE);
                    // compound drwabale of password edit text is R.drawable.ic_info
                    isInfo = true;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return rootView;

    }

    private boolean validatePassword(String trim) {
        return  true;
    }

    private void popupPswrdValid() {

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