package com.example.pochi.whoareyou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by pochi on 2015/06/01.
 */
public class ResultListCardAdapter extends CardScrollAdapter {

    private List<View> mViews;
    private Context context;
    public ResultListCardAdapter(Context context, List<View> views) {
        this.mViews = views;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return mViews.get(i);
    }

    public View getView(int i) {
        return mViews.get(i);
    }

    @Override
    public int getPosition(Object o) {
        return mViews.indexOf(o);
    }

}
