package edu.hzuapps.androidlabs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import java.io.File;
import java.util.List;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Com1714080901238Activity03 extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = Com1714080901238Activity03.class.getSimpleName();
    public static final String IMAGE_URL_PREFIX = "http://p0.qhimg.com/t01893928db69d75161.png";
    public static final String imageUri="/data/user/0/edu.hzuapps.androidlabs/files/images/t01893928db69d75161.png";

    private Button mCheckButton;
    private Button mDownloadImageButton;
    private TextView mNetworkText;
    private ImageView mResultView;
    private TextView mShakeView;
    int ringValue = 20;


    private boolean mConnected;

    private NetworkFileDownloader mFileDownloader;

    // App的内部存储目录
    private File mPrivateRootDir;
    // “images”子目录
    private File mImagesDir;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.com_1714080901238_activity03);

        mCheckButton = (Button) findViewById(R.id.button_check);
        mDownloadImageButton = (Button) findViewById(R.id.button_download_image);
        mNetworkText = (TextView) findViewById(R.id.text_network);
        mResultView = (ImageView) findViewById(R.id.text_result);
        mShakeView = (TextView) findViewById(R.id.text_s);

        mCheckButton.setOnClickListener(this);
        mDownloadImageButton.setOnClickListener(this);


        // 获取内部存储目录: files
        mPrivateRootDir = getFilesDir();
        // 获取内部存储子目录: files/images
        mImagesDir = new File(mPrivateRootDir, "images");

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 通过传感器管理器 获取 本地所有的传感器
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensors) {
            System.out.println("Sensor == " + s.toString());
        }
        // 获取指定的某一个传感器
        Sensor type_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (type_accelerometer != null) {
            System.out.println("Sensor 获取指定的某一个传感器 " + type_accelerometer.toString());
        }
        // 注册传感器的监听器 （摇一摇）
        sm.registerListener(new SensorEventListener() {
            public void onSensorChanged(SensorEvent sensorEvent) {
                // 传感器数据变化，在该方法中我们可以获取传感器变化的值
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                if (Math.abs(x) + Math.abs(y) + Math.abs(z) >= ringValue) {
                    String string="您的余额为0...";
                    mShakeView.setText(string);


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                System.out.println(sensor);
                System.out.println(accuracy);
            }
        }, type_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void onClick (View view){
        if (view.getId() == R.id.button_check) {
            checkNetworkState(); // 检查网络
        } else if (view.getId() == R.id.button_download_image) {
            downloadImages(); // 下载图片
            mResultView.setImageURI(Uri.parse(imageUri));
        }
    }
    private void checkNetworkState () {
        // 取得连接管理器
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // 检查当前激的网络
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mConnected = true;
        } else {
            mConnected = false;
        }

        String types = "";

        // 检查Wi-Fi
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo != null && networkInfo.isConnected();
        types += isWifiConn ? "Wi-Fi" : "";

        // 检查数据网络
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isMobileConn = networkInfo != null && networkInfo.isConnected();
        types += isMobileConn ? "流量" : "unkonw";
        mNetworkText.setTextColor(mConnected ? Color.GREEN : Color.RED);
        mNetworkText.setText(mConnected ? "网络正常 (" + types + ")" : "网络未连接!");
    }
    private void downloadImages () {
        // 创建下载器
        mFileDownloader = new NetworkFileDownloader(new NetworkFileDownloader.OnImageDownloadListener() {
            @Override
            public void onError(String error) {
                Toast.makeText(Com1714080901238Activity03.this, error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChange(int percent) {
                Log.i(TAG, "当前进度 = " + percent);
            }

            @Override
            public void onComplete(Bitmap bitmap, String imageUrl) {
                // 下载的图片格式为PNG
                final Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
                // 解析出原始文件名
                String filename = imageUrl.replace(IMAGE_URL_PREFIX, "");
                final File imageFile = new File(mImagesDir, filename);
                // 将文件保存到磁盘中
                NetworkFileDownloader.writeToDisk(imageFile, bitmap, new NetworkFileDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Toast.makeText(Com1714080901238Activity03.this, "文件已保存: " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                        mResultView.setImageURI(Uri.fromFile(imageFile));
                    }

                    @Override
                    public void onBitmapSaveError(String error) {
                        Toast.makeText(Com1714080901238Activity03.this, error, Toast.LENGTH_LONG).show();
                    }
                }, format, false);
            }
        });
        String imageUrl = IMAGE_URL_PREFIX;
        Log.d("is", "run !");
        mFileDownloader.download(imageUrl, true);

    }
}
class NetworkFileDownloader {

    private static final String TAG = NetworkFileDownloader.class.getSimpleName();
    private static final int MAX_SIZE = 8192;

    // 记录当前下载的URL
    private Set<String> mUrlsInProgress = new HashSet<>();
    private OnImageDownloadListener mImageDownloadListener;

    public NetworkFileDownloader(@NonNull OnImageDownloadListener listener) {
        mImageDownloadListener = listener;
    }

    /**
     * 自定义监听器,供外部处理.
     */
    public interface OnImageDownloadListener {
        void onError(String error);

        void onProgressChange(int percent);

        void onComplete(Bitmap bitmap, String imageUrl);
    }

    @SuppressLint("StaticFieldLeak")
    public void download(@NonNull final String imageUrl, final boolean displayProgress) {
        if (mUrlsInProgress.contains(imageUrl)) {
            Log.w(TAG, "该图片已经在下载列表中!");
            return;
        }
        new AsyncTask<Void, Integer, Bitmap>() {
            private String error;

            @Override
            protected void onCancelled() {
                mUrlsInProgress.remove(imageUrl);
                mImageDownloadListener.onError(error);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                mImageDownloadListener.onProgressChange(values[0]);
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap bitmap = null;
                HttpURLConnection connection = null;
                InputStream is = null;
                ByteArrayOutputStream out = null;
                try { // 创建连接
                    connection = (HttpURLConnection) new URL(imageUrl).openConnection();
                    if (displayProgress) {
                        connection.connect(); // 建立连接
                        final int length = connection.getContentLength();
                        if (length <= 0) {
                            error = "URL不正确!";
                            this.cancel(true);
                        }
                        is = new BufferedInputStream(connection.getInputStream(), MAX_SIZE);
                        out = new ByteArrayOutputStream();
                        byte bytes[] = new byte[MAX_SIZE];
                        int count;
                        long read = 0;
                        while ((count = is.read(bytes)) != -1) {
                            read += count;
                            out.write(bytes, 0, count);
                            publishProgress((int) ((read * 100) / length));
                        }
                        bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                    } else {
                        is = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    }
                } catch (Throwable e) {
                    if (!this.isCancelled()) {
                        error = "网络错误!";
                        this.cancel(true);
                    }
                } finally {
                    try {
                        if (connection != null)
                            connection.disconnect();
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                        if (is != null)
                            is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                if (result == null) {
                    Log.e(TAG, "下载结果为空!");
                    mImageDownloadListener.onError("文件无法解码!");
                } else {
                    Log.d(TAG, "下载完成,大小为 " + result.getByteCount());
                    mImageDownloadListener.onComplete(result, imageUrl);
                }
                mUrlsInProgress.remove(imageUrl);
                System.gc();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    // 将下载的文件保存到磁盘中
    public interface OnBitmapSaveListener {
        void onBitmapSaved();
        void onBitmapSaveError(String error);
    }
    // 声明为静态方式,直接使用.
    public static void writeToDisk(@NonNull final File imageFile, @NonNull final Bitmap image,
                                   @NonNull final OnBitmapSaveListener listener,
                                   @NonNull final Bitmap.CompressFormat format, boolean shouldOverwrite) {

        if (imageFile.isDirectory()) {
            listener.onBitmapSaveError("存在同名目录!");
            return;
        }

        if (imageFile.exists()) {
            if (!shouldOverwrite&!imageFile.delete()) {
                listener.onBitmapSaveError("文件已存在!无法删除原始同名文件!");
                Log.d("exists","URL:"+imageFile.getAbsolutePath());
                return;
            }
            listener.onBitmapSaveError("请再次点击！");
            return;

        }

        File parent = imageFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            listener.onBitmapSaveError("无法创建上级目录!");
            return;
        }

        try {
            if (!imageFile.createNewFile()) {
                listener.onBitmapSaveError("无法创建文件!");
                return;
            }
        } catch (IOException e) {
            listener.onBitmapSaveError("写入文件失败!");
            return;
        }

        new AsyncTask<Void, Void, Void>() {

            private String error;

            @Override
            protected Void doInBackground(Void... params) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(imageFile);
                    image.compress(format, 100, fos);
                } catch (IOException e) {
                    error = "写入文件失败!";
                    this.cancel(true);
                } finally {
                    if (fos != null) {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onCancelled() {
                listener.onBitmapSaveError(error);
            }

            @Override
            protected void onPostExecute(Void result) {
                listener.onBitmapSaved();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}