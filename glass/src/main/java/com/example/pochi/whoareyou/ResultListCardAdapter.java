package com.example.pochi.whoareyou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by pochi on 2015/06/01.
 */
public class ResultListCardAdapter extends CardScrollAdapter {

    private List<ResultCard> mViews;
    private Context context;
    public ResultListCardAdapter(Context context, List<ResultCard> views) {
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
        ResultCard resultCard = mViews.get(i);
        Card card = new Card(this.context);
        // Card text
        if (resultCard.getText() != null)
            card.setText(resultCard.getText());

        // Card footer note
        if (resultCard.getFooterText() != null)
            card.setFootnote(resultCard.getFooterText());

        // Set image layout
        if (resultCard.getImgLayout() != null)
            card.setImageLayout(resultCard.getImgLayout());

        // loop and set card images
        //for(int img : resultCard.getImages()){
        //    card.addImage(img);
        //}

        return card.getView();
    }


    @Override
    public int getPosition(Object o) {
        return mViews.indexOf(o);
    }

}
