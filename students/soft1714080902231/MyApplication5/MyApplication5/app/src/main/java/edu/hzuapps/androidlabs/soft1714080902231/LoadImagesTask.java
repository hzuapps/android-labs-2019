package edu.hzuapps.androidlabs.soft1714080902231;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImagesTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public LoadImagesTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL imageUrl = null;
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            imageUrl = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}