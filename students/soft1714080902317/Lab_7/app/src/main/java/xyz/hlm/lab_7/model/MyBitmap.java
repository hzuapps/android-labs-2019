package xyz.hlm.lab_7.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MyBitmap {
    private Activity context;
    private File path;
    private Uri[] uris;
    private Intent intent;
    private static MyBitmap myBitmap = null;
    public int times = 0;

    public static MyBitmap instance(Activity activity) {
        if (myBitmap == null) myBitmap = new MyBitmap(activity);
        return myBitmap;
    }

    private MyBitmap(Activity context) {
        this.context = context;
        path = context.getExternalCacheDir();
    }

    public void take() {
        times = getUris().length;
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri uri = getUri(times);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, 1);
    }

    public Uri[] getUris() {
        if (path.exists()) {
            int count = path.listFiles().length;
            uris = new Uri[count];
            for (int i = 0; i < count; i++) {
                uris[i] = getUri(i);
            }
        }
        return uris;
    }

    private Uri getUri(int i) {
        Uri uri = null;
        File out = new File(path, String.valueOf(i) + ".jpg");
        try {
            if (!out.exists()) {
                out.createNewFile();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, "xyz.hlm.lab_7.Soft1714080902317Activity", out);
        } else uri = Uri.fromFile(out);
        return uri;
    }
}