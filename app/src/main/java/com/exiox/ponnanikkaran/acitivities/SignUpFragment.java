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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exiox.ponnanikkaran.R;
import com.exiox.ponnanikkaran.model.SignUpModel;
import com.exiox.ponnanikkaran.model.SignUpResultModel;
import com.exiox.ponnanikkaran.network.ApiClient;
import com.exiox.ponnanikkaran.network.ApiInterface;
import com.exiox.ponnanikkaran.utilities.Constants;
import com.exiox.ponnanikkaran.utilities.General;
import com.exiox.ponnanikkaran.utilities.Utils;

import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onResume() {
        super.onResume();
        mJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinNewUser();
            }
        });
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
        return rootView;
    }

    //method for handling sign up text field validation and web service call
    private void joinNewUser() {

        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String email = mEmail.getText().toString().toLowerCase().trim();
        String password = mPassword.getText().toString();

        if (validateFirstName(firstName) && validateLastName(lastName) && validateEmail(email) && validatePassword(password)) {
            mSend = true;

            String appVersion = null;

            try {
                PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                appVersion = packageInfo.versionName;
            } catch (Exception e) {

            }
            mOverlayDialog.show();
            mProgressBar.setVisibility(View.VISIBLE);
            SignUpModel mModel = new SignUpModel();
            mModel.setUserEmail(email);
            mModel.setUserName(firstName.trim() + " " + lastName.trim());
            mModel.setUserPassword(password);

            // web service call for signing up a new user
            callSignUpAPi(mModel);

        }
        else {

            mOverlayDialog.dismiss();
            mProgressBar.setVisibility(View.INVISIBLE);
            mSend = false;

        }

    }

    private void callSignUpAPi(SignUpModel signUpModel) {
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.DEFAULT_SHARED_PREF, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SignUpResultModel> call = apiService.signUp(signUpModel);
        call.enqueue(new Callback<SignUpResultModel>() {
            @Override
            public void onResponse(Call<SignUpResultModel> call, Response<SignUpResultModel> response) {
                List<SignUpModel> mOrderList = response.body().getResult();
                editor.putBoolean(Constants.LOGIN_SUCCES,true).apply();
                Toast.makeText(mContext, "Success", Toast.LENGTH_LONG);
                mProgressBar.setVisibility(View.INVISIBLE);
                Intent homeIntent = new Intent(mContext, SplashScreenActivity.class);
                startActivity(homeIntent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<SignUpResultModel> call, Throwable t) {
                Toast.makeText(mContext, "error"+t.getLocalizedMessage(), Toast.LENGTH_LONG);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

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



    //method for validating firstname
    private boolean validateFirstName(String firstName) {

        Integer maxLength = 50;
        Integer minLength = 1;

        if (firstName.trim().equals("")) {

            Utils.showToast(mContext, getString(R.string.msg_firstname_empty));
            return false;

        } else if (firstName.trim().length() < minLength || firstName.trim().length() > maxLength) {

            Utils.showToast(mContext, getString(R.string.msg_firstname_length));
            return false;

        }

        return true;
    }

    //method for validating lastname
    private boolean validateLastName(String lastName) {

        Integer maxLength = 50;
        Integer minLength = 1;

        if (lastName.trim().equals("")) {

            Utils.showToast(mContext, getString(R.string.msg_lastname_empty));
            return false;

        } else if (lastName.trim().length() < minLength || lastName.trim().length() > maxLength) {

            Utils.showToast(mContext, getString(R.string.msg_lastname_length));
            return false;

        }

        return true;
    }

    //method for validating email
    private boolean validateEmail(String email) {

        if (email.equals("")) {
            Utils.showToast(mContext, getString(R.string.msg_email_empty));
            return false;
        }
        if(!General.validateEmail(mContext, email)) {
            return false;
        }
        return true;
    }

    //method for validating password
    private boolean validatePassword(String password) {
//        Integer minLength = getResources().getInteger(R.integer.et_last_password_min_length);
        int minLength = 8;
        boolean hasUppercase =  password.matches(".*[A-Z]+.*");
        boolean hasLowercase = password.matches(".*[a-z]+.*");
        boolean hasNumber = password.matches(".*\\d.*");
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(password);
        boolean hasSpecialCharacter = m.find();
        boolean hasAlphabets = password.matches(".*[a-zA-Z]+.*");
        String emailAddress = mEmail.getText().toString().trim().toLowerCase();
        String[] emailUserName = emailAddress.split("@");

        if(!password.equals("") && (password.contains(" ") || (password.trim().length() < minLength || !hasAlphabets || !hasNumber || !hasUppercase || !hasLowercase || !hasSpecialCharacter
                || (!emailAddress.equals("") && password.contains(emailAddress))))){

            if (password.trim().equals("") || (password.trim().length() < minLength)) {
                //            ConfirmationDialogFragment passwordInvalidDialog = new
                //                    ConfirmationDialogFragment(null, "Your password must be between 6 and 20 characters", getResources().getString(R.string.btn_ok), null);
                //            passwordInvalidDialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
                //            passwordInvalidDialog.show(getActivity().getFragmentManager(), "");
//                mTickCharacter.setVisibility(View.INVISIBLE);
                            return false;

            }
            //        //check whether passwords contains alphabets
            if(!hasAlphabets){
                //            Utils.showToast(mContext, getString(R.string.msg_one_character));
//                mTickAlphaNum.setVisibility(View.INVISIBLE);
                            return false;
            }

            //check whether passwords contains numbers
            if(!hasNumber){
                //            Utils.showToast(mContext, getString(R.string.msg_number));
//                mTickAlphaNum.setVisibility(View.INVISIBLE);
                //            return false;
            }

            //check whether passwords contains uppercase character
            if(!hasUppercase){
                //            Utils.showToast(mContext, getString(R.string.msg_uppercase_character));
//                mTickUprLwr.setVisibility(View.INVISIBLE);
                            return false;
            }

            //check whether passwords contains lowercase character
            if(!hasLowercase){
                //            Utils.showToast(mContext, getString(R.string.msg_lowercase_character));
//                mTickUprLwr.setVisibility(View.INVISIBLE);
                            return false;
            }

            //check whether passwords contains special character
            if(!hasSpecialCharacter){
                //            Utils.showToast(mContext, getString(R.string.msg_special_character));
//                mTickSpeclCharct.setVisibility(View.INVISIBLE);
                            return false;
            }

            if(!emailAddress.equals("") && password.contains(emailAddress)){
                //            Utils.showToast(mContext, getString(R.string.msg_password_similar_username));
//                mTickUsrnme.setVisibility(View.INVISIBLE);
                            return false;
            }

            return true;
        }else
            return true;
    }
}