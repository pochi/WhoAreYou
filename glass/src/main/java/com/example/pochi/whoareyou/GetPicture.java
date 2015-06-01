package com.example.pochi.whoareyou;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by pochi on 2015/06/01.
 */
public class GetPicture extends AsyncTask<Void, Void, Void> {

    private Card currentCard;
    private ResultCard resultCard;
    private BitmapDrawable bitmapDrawable;
    private List<ResultCard> resultCardList;
    private List<BitmapDrawable> bitmapDrawableList;
    private Activity parentActivity;

    public GetPicture(Card view, ResultCard card) {
        this.currentCard = view;
        this.resultCard = card;
    }

    public GetPicture(Activity parentActivity, List<ResultCard> resultCardList) {
        this.resultCardList = resultCardList;
        this.parentActivity = parentActivity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.i("start", "get");
            for(int i=0; i < resultCardList.size(); i++) {
                ResultCard currentCard = resultCardList.get(i);
                Log.i("Url image", currentCard.getImageUrl());
                HttpURLConnection connection = (HttpURLConnection) new URL(currentCard.getImageUrl()).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap;
                bitmap = BitmapFactory.decodeStream(input);
                currentCard.setImage(new BitmapDrawable(bitmap));
                this.resultCardList.set(i, currentCard);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Log.i("hoge", "add images");
        CardScrollView resultScrollView = new CardScrollView(this.parentActivity);
        ResultListCardAdapter cardScroller = new ResultListCardAdapter(this.parentActivity, resultCardList);
        resultScrollView.setAdapter(cardScroller);
        resultScrollView.activate();
        this.parentActivity.setContentView(resultScrollView);
    }
}
