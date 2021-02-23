package com.raghu.loadmorelib;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreWrapper {
    private final LoadMoreAdapter mLoadMoreAdapter;

    public LoadMoreWrapper(LoadMoreAdapter loadMoreAdapter) {
        mLoadMoreAdapter = loadMoreAdapter;
    }

    public static LoadMoreWrapper with(RecyclerView.Adapter adapter,boolean loadFromStart) {
        LoadMoreAdapter loadMoreAdapter = new LoadMoreAdapter(adapter,loadFromStart);
        return new LoadMoreWrapper(loadMoreAdapter);
    }

    public LoadMoreWrapper setFooterView(@LayoutRes int resId) {
        mLoadMoreAdapter.setFooterView(resId);
        return this;
    }

    public LoadMoreWrapper setFooterView(View footerView) {
        mLoadMoreAdapter.setFooterView(footerView);
        return this;
    }

    public View getFooterView() {
        return mLoadMoreAdapter.getFooterView();
    }

    public LoadMoreWrapper setNoMoreView(@LayoutRes int resId) {
        mLoadMoreAdapter.setNoMoreView(resId);
        return this;
    }

    public LoadMoreWrapper setNoMoreView(View noMoreView) {
        mLoadMoreAdapter.setNoMoreView(noMoreView);
        return this;
    }

    public View getNoMoreView() {
        return mLoadMoreAdapter.getNoMoreView();
    }

    public LoadMoreWrapper setLoadFailedView(@LayoutRes int resId) {
        mLoadMoreAdapter.setLoadFailedView(resId);
        return this;
    }

    public LoadMoreWrapper setLoadFailedView(View view) {
        mLoadMoreAdapter.setLoadFailedView(view);
        return this;
    }

    public View getLoadFailedView() {
        return mLoadMoreAdapter.getLoadFailedView();
    }

    public LoadMoreWrapper setListener(LoadMoreAdapter.OnLoadMoreListener listener) {
        mLoadMoreAdapter.setLoadMoreListener(listener);
        return this;
    }


    public LoadMoreWrapper setLoadMoreEnabled(boolean enabled) {
        mLoadMoreAdapter.setLoadMoreEnabled(enabled);
        if (!enabled) {
            mLoadMoreAdapter.setShouldRemove(true);
        }
        return this;
    }

    public LoadMoreWrapper setShowNoMoreEnabled(boolean enabled) {
        mLoadMoreAdapter.setShowNoMoreEnabled(enabled);
        return this;
    }

    public void setLoadFailed(boolean isLoadFailed) {
        mLoadMoreAdapter.setLoadFailed(isLoadFailed);
    }


    public LoadMoreWrapper setNotShowFooterWhenNotCoveredScreen(boolean notShow) {
        mLoadMoreAdapter.setNotShowFooterWhenNotCoveredScreen(notShow);
        return this;
    }

    public RecyclerView.Adapter getOriginalAdapter() {
        return mLoadMoreAdapter.getOriginalAdapter();
    }

    public LoadMoreAdapter into(RecyclerView recyclerView) {
        mLoadMoreAdapter.setHasStableIds(mLoadMoreAdapter.getOriginalAdapter().hasStableIds());
        recyclerView.setAdapter(mLoadMoreAdapter);
        return mLoadMoreAdapter;
    }
}
