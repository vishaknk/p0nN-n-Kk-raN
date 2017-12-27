package com.exiox.ponnanikkaran.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.exiox.ponnanikkaran.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by priyesh on 21/11/17.
 */

public class General {
    private Context mContext;

    public General(Context context) {
        this.mContext = context;
    }

    /**
     * set the background image as per the screen size without changing the image ratio
     *
     * @param drawable
     * @param activity
     * @param imageLayout
     * @param childLayout
     */
    public void setBackgroundImage(Context mContext, int drawable, Activity activity, ImageView imageLayout, LinearLayout childLayout) {
        // Getting the image size.
        Coordinates imageSize = getImageSize(mContext, drawable);
        // Getting screen size.
        Coordinates screenSize = getScreenSize(activity);
        // Calculate the height and width of the images to set to the screen to fit into it.
        int height = (int) ((getDptoPixel(mContext, imageSize.getHeight()) * screenSize.getWidth()) / getDptoPixel(mContext, imageSize.getWidth()));
        int width = screenSize.getWidth();
        // Setting the image size to scaled size to fit into in screen.
        try {
            setImageSize(mContext, imageLayout, width, height, drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Setting the height and width of the childLayout(Relative layout).
        childLayout.getLayoutParams().height = screenSize.getHeight();
        childLayout.getLayoutParams().width = screenSize.getWidth();
    }

    /**
     * Convert the density pixels to pixels
     * This method converts the dp value to corresponding pixel value for the screen size
     *
     * @param context
     * @param dp
     * @return px
     */
    public float getDptoPixel(Context context, int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }

    /**
     * To set the image to the image view using the glide library
     * This method set image to image view with provided height  and width
     *
     * @param context
     * @param img
     * @param width
     * @param height
     * @param drawable
     */
    public void setImageSize(Context context, ImageView img, int width, int height, int drawable) throws Exception{
        try {
            Glide.with(context)
                    .load(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .override(width, height) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                    .into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert the density pixels to pixels
     * This method returns the coordinates(height, width) of the given drawable
     *
     * @param context
     * @param drawable
     * @return coordinates
     */
    public Coordinates getImageSize(Context context, int drawable) {
        //Model to add coordinates
        Coordinates data = new Coordinates();
        //Setting the drawable image
        Drawable d = ContextCompat.getDrawable(context, drawable);
        //Getting the height and width of the image
        data.setHeight((d.getIntrinsicHeight() / 2));
        data.setWidth((d.getIntrinsicWidth() / 2));
        return data;
    }

    /**
     * returns the height and width of the device screen
     *
     * @param activity
     * @return coordinate
     */
    public Coordinates getScreenSize(Activity activity) {
        //Model to add coordinates
        Coordinates data = new Coordinates();

        //Getting the display
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //Setting the height and width in the model
        data.setWidth(size.x);
        data.setHeight(size.y - getStatusBarHeight(activity));
        //Log.v("Screen Width", "" +width);
        //Log.v("Screen Height", "" +height);
        return data;
    }

    /**
     * returns the status bar height used to calculate the image size
     *
     * @param activity
     * @return int
     */
    public int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public  String getYoutubeVideoId(String youtubeUrl) {
        String video_id = "";
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {

            String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(7);
                if (groupIndex1 != null && groupIndex1.length() == 11)
                    video_id = groupIndex1;
            }
        }
        return video_id;
    }


    //method for validating email
    public static boolean validateEmail(Context mContext, String email) {

        if (email.equals("")) {
            Utils.showToast(mContext, mContext.getResources().getString(R.string.msg_email_empty));
            return false;
        }

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_+A-Za-z0-9-]+(\\.[_+A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z0-9-]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            Utils.showToast(mContext, mContext.getResources().getString(R.string.msg_email_invalid));
            return false;
        }
    }


}
