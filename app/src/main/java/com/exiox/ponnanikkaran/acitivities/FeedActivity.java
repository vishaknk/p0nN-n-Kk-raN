package com.exiox.ponnanikkaran.acitivities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exiox.ponnanikkaran.R;
import com.exiox.ponnanikkaran.adpater.FeedAdapter;
import com.exiox.ponnanikkaran.base.BaseActivity;
import com.exiox.ponnanikkaran.model.posts.PostResult;
import com.exiox.ponnanikkaran.model.posts.PostsResultsModel;
import com.exiox.ponnanikkaran.network.ApiClient;
import com.exiox.ponnanikkaran.network.ApiInterface;
import com.exiox.ponnanikkaran.utilities.EndlessRecyclerViewScrollListener;
import com.exiox.ponnanikkaran.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyesh on 30/12/17.
 */

public class FeedActivity extends BaseActivity {
    private Context mContext;
    private RecyclerView mFeedList;
    private FeedAdapter mFeedAdapter;
    private List<PostsResultsModel>mFeedListItems;
    private int mIndex = 0, limit = 6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mContext = FeedActivity.this;
        mFeedListItems = new ArrayList<>();
        mFeedList = (RecyclerView) findViewById(R.id.rv_feedList);

        mFeedAdapter = new FeedAdapter(mFeedListItems, R.layout.list_item_feeds,mContext);
        mFeedList.setAdapter(mFeedAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mFeedList.setLayoutManager(linearLayoutManager);
        getFeeds(mIndex);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(final int page, int totalItemsCount, RecyclerView view) {
                final int curSize = mFeedAdapter.getItemCount();

                view.post(new Runnable() {
                    @Override
                    public void run() {
                        getFeeds(page);
                        mFeedAdapter.setAdapter(mFeedListItems);
                    }
                });
            }
        };
        mFeedList.addOnScrollListener(scrollListener);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getFeeds(int index) {
        mIndex = index;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PostResult> call = apiService.getpost(mIndex * 6, 6);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                mFeedListItems.addAll(response.body().getResult());
                Utils.showToast(mContext,"success");
                mFeedAdapter.setAdapter(mFeedListItems);
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                Utils.showToast(mContext,"erorr");
            }
        });

    }
}
