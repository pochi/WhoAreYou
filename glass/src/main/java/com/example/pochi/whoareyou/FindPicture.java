package com.example.pochi.whoareyou;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.File;
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
        HttpPost httpPost = new HttpPost("http://google-image-searcher.herokuapp.com/search");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        File file = new File(this.picturePath);
        FileBody fileBody = new FileBody(file);

        builder.addPart("encoded_image", fileBody);
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        Log.i("pochi", "garurur");
        HttpClient client = new DefaultHttpClient();


        try {
            HttpResponse response = client.execute(httpPost);
        } catch(ClientProtocolException ex) {
            Log.i("ClientProtocolException", "some error");
        } catch(IOException ex) {
            Log.i("IOException", "IOException occured");
        }

        return "hoge";
    }
}
