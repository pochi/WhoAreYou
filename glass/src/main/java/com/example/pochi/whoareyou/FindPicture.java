package com.example.pochi.whoareyou;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by pochi on 2015/06/01.
 */

public class FindPicture extends AsyncTask<Uri.Builder, Void, String> {

    private Activity parentActivity;
    private String picturePath;

    public FindPicture(Activity activity, String picturePath) {
        this.parentActivity = activity;
        this.picturePath = picturePath;
    }

    @Override
    protected String doInBackground(Uri.Builder... params) {
        Log.i("Asynctask", "sample");
        HttpGet httpGet = new HttpGet("http://google-image-searcher.herokuapp.com/search?path=" + this.picturePath);
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(httpGet);
        } catch(ClientProtocolException ex) {
            Log.i("ClientProtocolException", "some error");
        } catch(IOException ex) {
            Log.i("IOException", "IOException occured");
        }

        return "hoge";
    }
}
