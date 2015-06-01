package com.example.pochi.whoareyou;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

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
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pochi on 2015/06/01.
 */

public class FindPicture extends AsyncTask<Void, Void, Void> {

    private Activity parentActivity;
    private String picturePath;
    private JSONArray jsonResult;

    public FindPicture(Activity activity, String picturePath) {
        this.parentActivity = activity;
        this.picturePath = picturePath;
    }

    @Override
    protected Void doInBackground(Void... params) {
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
        StringBuilder responseBuilder = new StringBuilder();

        try {
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }

            this.jsonResult = new JSONArray(responseBuilder.toString());
            //Log.i("Response", "confirm");
            //for(int i=0; i < jsonResponse.length(); i++) {
            //    JSONObject object = jsonResponse.getJSONObject(0);
            //    Log.i(Integer.toString(i), object.getString("url"));
            //}


        } catch(ClientProtocolException ex) {
            Log.i("ClientProtocolException", "some error");
        } catch(IOException ex) {
            Log.i("IOException", "IOException occured");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private View buildView(String url) {
        CardBuilder card = new CardBuilder(this.parentActivity, CardBuilder.Layout.MENU);
        card.setText(url);
        return card.getView();
    }

    @Override
    protected void onPostExecute(Void result) {
        List<ResultCard> views = new ArrayList<ResultCard>();

        try {
            Log.i("OnPostExecute", "test");
            for (int i = 0; i < this.jsonResult.length(); i++) {
                JSONObject object = this.jsonResult.getJSONObject(0);
                Log.i(Integer.toString(i), object.getString("url"));
                views.add(new ResultCard(object.getString("image"), object.getString("url")));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        CardScrollView resultScrollView = new CardScrollView(this.parentActivity);
        ResultListCardAdapter cardScroller = new ResultListCardAdapter(this.parentActivity, views);
        resultScrollView.setAdapter(cardScroller);
        resultScrollView.activate();
        this.parentActivity.setContentView(resultScrollView);

        GetPicture task = new GetPicture(this.parentActivity, views);
        task.execute();
    }
}
