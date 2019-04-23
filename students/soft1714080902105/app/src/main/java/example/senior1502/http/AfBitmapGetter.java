package example.senior1502.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.myhttp.HttpEntity;
import org.apache.myhttp.StatusLine;
import org.apache.myhttp.client.config.RequestConfig;
import org.apache.myhttp.client.methods.CloseableHttpResponse;
import org.apache.myhttp.client.methods.HttpGet;
import org.apache.myhttp.impl.client.CloseableHttpClient;
import org.apache.myhttp.impl.client.HttpClients;

import java.io.InputStream;

/**
 * Created by shaofa on 2017/12/31.
 * 下载网络图片，直接得到一个Bitmap对象
 */

public class AfBitmapGetter extends AsyncTask
{
    final String TAG = "AfBitmapGetter";

    String uri;
    String reason; // 出错原因

    public AfBitmapGetter(String uri)
    {
        this.uri = uri;
    }

    // 回调接口
    public interface Callback
    {
        public void onResult(AfBitmapGetter ab, Bitmap bitmap) throws Exception;
    }
    public Callback callback;

    @Override
    protected Object doInBackground(Object[] params)
    {
        Bitmap bitmap = null;
        try
        {
            bitmap = download(uri);
            Log.i(TAG, "图片下载成功" + uri);
        }catch(Exception e)
        {
            reason = e.getMessage();
            Log.e(TAG, "图片下载失败" + reason + ",uri=" + uri);
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Object result)
    {
        if(callback != null)
        {
            Bitmap bitmap = (Bitmap)result;
            try
            {
                callback.onResult(this, bitmap);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getUri()
    {
        return uri;
    }
    public String getReason()
    {
        return reason;
    }

    // 下载文件
    private Bitmap download(String uri) throws Exception
    {
        // 1 创建客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 2 创建一个HTTP请求
        HttpGet httpget = new HttpGet(uri);

        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000)
                .build();
        httpget.setConfig(requestConfig);

        // 3 执行HTTP请求
        CloseableHttpResponse response = httpclient.execute(httpget);// 发起HTTP
        try
        {
            // 4 解析和处理服务器返回的数据
            // 检查状态码
            StatusLine statusLine = response.getStatusLine();
            int status = statusLine.getStatusCode();
            if (status != 200)
            {
                String reason = statusLine.getReasonPhrase();
                throw new Exception("Status Error: " + status + "," + reason);
            }

            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                InputStream dataStream = entity.getContent();

                // 直接解码得到可显示的Bitmap对象
                Bitmap bitmap = BitmapFactory.decodeStream(dataStream);
                return bitmap;
            }
        } finally
        {
            try	{	httpclient.close();	} catch (Exception e){ }
        }
        return null;
    }

    // 封装一个快速方法
    public static void get(String uri, Callback callback)
    {
        AfBitmapGetter ab = new AfBitmapGetter(uri);
        ab.callback = callback;
        ab.execute();
    }
}
