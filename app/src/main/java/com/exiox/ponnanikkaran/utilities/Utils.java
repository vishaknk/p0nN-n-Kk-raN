package com.exiox.ponnanikkaran.utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * Created by priyesh on 15/09/17.
 */

public class Utils {

    // Description	: method to show small toast
    public static void showToast(Context context, String toastMessage){
        float scale = context.getResources().getDisplayMetrics().density;
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        int offsetY = (int) (-100 * scale);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, offsetY);
        toast.show();
    }

    // method to show long toast
    public void showLongToast(Context context, String toastMessage){
        float scale = context.getResources().getDisplayMetrics().density;
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
        int offsetY = (int) (-100 * scale);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, offsetY);
        toast.show();
    }

    /*
	 * Function for check the network connectivity
	 *
	 * @return true if network Available otherwise false
	 */
    public  boolean isNetworkAvailable(Context context) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    // returns the formatted value of input date
    public String setDate(Calendar mCalendar) {
        String format = "dd-MMM-yyyy"; //In which format you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(mCalendar.getTime());
    }

    //convert yyyy-MM-dd to yyyy format  from server
    public String getYearFromDate(String dateValue) {

        String dateInString = null;
        try {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdfs.parse(dateValue);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy",Locale.getDefault());
            dateInString = sdf.format(date);
        } catch (Exception e) {

        }
        return dateInString;
    }

    //convert yyyy-MM-dd to MM format  from server
    public String getMonthFromDate(String dateValue) {

        String dateInString = null;
        try {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdfs.parse(dateValue);

            SimpleDateFormat sdf = new SimpleDateFormat("MM",Locale.getDefault());
            dateInString = sdf.format(date);
        } catch (Exception e) {
        }
        return dateInString;
    }

    //convert yyyy-MM-dd to dd format  from server
    public String getDayFromDate(String dateValue) {

        String dateInString = null;
        try {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdfs.parse(dateValue);

            SimpleDateFormat sdf = new SimpleDateFormat("dd",Locale.getDefault());
            dateInString = sdf.format(date);
        } catch (Exception e) {

        }
        return dateInString;
    }

    //convert yyyy-MM-dd to dd-MM-yyyy format  from server
    public String dateFormatter(String dateValue) {

        String dateInString = null;
        try {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdfs.parse(dateValue);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
            dateInString = sdf.format(date);
            Log.e("dateInString", "" + dateInString);
        } catch (Exception e) {

        }
        return dateInString;
    }

    // This method will return the date given in yyyy-MM-dd HH:mm:ss format
    public Date getStringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date dateTime = new Date();
        try {
            dateTime = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**getting current date*/
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }
    // method to return the current date time in yyyy-MM-dd HH:mm:ss format
    public String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    // Method to parse the time only from a date and return
    public String getTime(Date date){
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dateformat.format(date);
    }



    // this is used in capitalising the string in phone number type for setting selected in spinner
    public String capitalize(String s) {
        String capitalisedString = s;
        if (s.length() == 0)
            return s;
        try {
            capitalisedString = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        }catch (Exception e){
            e.printStackTrace();
            capitalisedString = s ;
        }

        return capitalisedString;
    }

    //convert local date to UTC date
    public  String dateTimetoUtc(String datetime) {
        String dateInString = null;
        try {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = sdfs.parse(datetime);
            sdfs.setTimeZone(TimeZone.getTimeZone("UTC"));
            dateInString = sdfs.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            dateInString = "";
        }
        Log.e("dateInString", "" + dateInString);
        return dateInString;
    }

    //convert local date to UTC date
    public  String dateTimetoISOUtc(String datetime) {
        String dateInString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24hr for
            Date date = sdf.parse(datetime);
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
            df.setTimeZone(tz);
            dateInString = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            dateInString = "";
        }
        return dateInString;
    }

    // this method will return the k notation of the given number in k notation
    public String getKnotation(int number){
        int num = number;
        try {
            if(num > 10000){
                num = num/1000;
                return ( String.valueOf(num)+"K");
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
        return String.valueOf(num);
    }


    public String secondsToHoursAndMinutes(int sec){
        if(sec <= 0)
            return "0s";
        if(sec <= 60){
            return (sec + "s");
        }else if (sec <= 3600){
            return (sec / 60 + "min " + sec % 60 + "s");
        }else {
            return (sec / 3600 + "h " + (sec % 3600) / 60 + "min " + (sec % 3600) % 60 + "s" );
        }
    }


}
