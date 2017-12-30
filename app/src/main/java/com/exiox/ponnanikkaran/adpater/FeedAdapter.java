package com.exiox.ponnanikkaran.adpater;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.exiox.ponnanikkaran.R;
import com.exiox.ponnanikkaran.model.posts.PostsResultsModel;
import com.exiox.ponnanikkaran.utilities.General;
import com.exiox.ponnanikkaran.utilities.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.exiox.ponnanikkaran.utilities.Constants.IMAGE_PATH;

/**
 * Created by priyesh on 30/12/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ProspectViewHolder> {

    private List<PostsResultsModel> mFeedList;
    private int mRowLayout;
    private Context mContext;
    private General mGeneral;
    private Utils mUtils;

    public void setAdapter(List<PostsResultsModel> mFeedListItems) {
        this.mFeedList = mFeedListItems;
        notifyDataSetChanged();
    }

    public static class ProspectViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mPostHeading, mPostDetails, mPostDate;
        ImageView mProfileImage;
        CheckBox like;

        public ProspectViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.tv_full_name);
            mPostHeading = (TextView) v.findViewById(R.id.tv_status_title);
            mPostDetails = (TextView) v.findViewById(R.id.tv_status_msg);
            mPostDate = (TextView) v.findViewById(R.id.tv_post_time_stamp);
            mProfileImage = (ImageView) v.findViewById(R.id.iv_profile_pic);
            like = (CheckBox) v.findViewById(R.id.cb_like);
        }
    }

    public FeedAdapter(List<PostsResultsModel> mFeedLists, int rowLayout, Context context) {
        this.mFeedList = mFeedLists;
        this.mRowLayout = rowLayout;
        this.mContext = context;
        mGeneral = new General(context);
        mUtils = new Utils();
    }

    @Override
    public FeedAdapter.ProspectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new ProspectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProspectViewHolder holder, final int position) {
        holder.mName.setText(mFeedList.get(position).getUserName());
        holder.mPostHeading.setText(mFeedList.get(position).getPostHeading());
        holder.mPostDetails.setText(mFeedList.get(position).getPostDetails());
        holder.like.setButtonDrawable(ContextCompat.getDrawable(mContext, R.drawable.btn_like));
        try {
            mGeneral.setImage(mContext,holder.mProfileImage,IMAGE_PATH + mFeedList.get(position).getUserImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        try {
            Date startDateParse = simpleDateFormat.parse(mFeedList.get(position).getPostDt());
            Calendar cal = Calendar.getInstance();
            String strDate = simpleDateFormat.format(cal.getTime());
            Date endDate = null;
            endDate = simpleDateFormat.parse(strDate);
            holder.mPostDate.setText(mGeneral.getTimeAgo(startDateParse,endDate,mContext));
        } catch (ParseException e) {
            e.printStackTrace();

        }

    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }


}