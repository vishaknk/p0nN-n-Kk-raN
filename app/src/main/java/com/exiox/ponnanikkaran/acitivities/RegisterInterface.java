package com.exiox.ponnanikkaran.acitivities;

import org.json.JSONObject;

/**
 * Created by MSI0588 on 07-10-2015.
 */
public interface RegisterInterface {
    //method for handling response to the sign in and sign up fragment
    void onResponseReceived(JSONObject object);
    void requestEndedWithError(String error);
    void onGetResponseReceived(JSONObject object);

}
