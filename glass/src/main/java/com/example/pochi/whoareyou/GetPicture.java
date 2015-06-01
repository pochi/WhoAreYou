package com.example.pochi.whoareyou;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pochi on 2015/06/01.
 */
public class GetPicture extends AsyncTask<Void, Void, Void> {

    private View currentView;

    public GetPicture(View view) {
        this.currentView = view;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.i("start", "get");
            Bitmap bitmap;
            HttpURLConnection connection = (HttpURLConnection) new URL("").openConnection();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
