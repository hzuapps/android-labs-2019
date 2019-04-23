package example.senior1502.http;

import android.os.AsyncTask;

import org.apache.myhttp.HttpEntity;
import org.apache.myhttp.StatusLine;
import org.apache.myhttp.client.config.RequestConfig;
import org.apache.myhttp.client.methods.CloseableHttpResponse;
import org.apache.myhttp.client.methods.HttpPost;
import org.apache.myhttp.entity.ContentType;
import org.apache.myhttp.entity.StringEntity;
import org.apache.myhttp.impl.client.CloseableHttpClient;
import org.apache.myhttp.impl.client.HttpClients;
import org.apache.myhttp.util.EntityUtils;
import org.myjson.JSONObject;

/**
 * Created by shaofa on 2017/12/29.
 * 在界面里发起REST调用, 在Callback里更新界面
 */

public class AfRest extends AsyncTask
{
    String url;
    JSONObject request;
    int connectTimeout = 3000;
    int socketTimeout = 3000;

    public AfRest(String url, JSONObject request)
    {
        this.url = url;
        this.request = request;
    }

    // 回调接口: jresp是网站后台返回的数据
    public interface Callback
    {
        public void onResult(JSONObject jresp) throws Exception;
    }
    public Callback callback;

    @Override
    protected Object doInBackground(Object[] objects)
    {
        // 发起请求
        JSONObject response = new JSONObject();
        try
        {
            String sreq = request.toString();
            String sresp = doPost(url, sreq);
            response = new JSONObject(sresp);
        }catch(Exception e)
        {
            try{
                response.put("errorCode", -1001);
                response.put("reason", e.getMessage());
            }catch(Exception e2){}
        }
        return response;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        JSONObject response = (JSONObject)o;

        //TODO: 如果在界面线程里处理这个返回数据，可能会引起界面卡顿
        if(callback != null)
        {
            try{
                callback.onResult( response);
            }catch(Exception e3){
                e3.printStackTrace();
            }
        }
    }

    // 发起POST请求
    private String doPost(String url, String reqText) throws Exception
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout )
                .setConnectTimeout(connectTimeout)
                .build();

        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);

        // 上行数据
        StringEntity dataSent = new StringEntity(reqText, ContentType.create("text/plain", "UTF-8"));
        httppost.setEntity(dataSent);

        CloseableHttpResponse response = httpclient.execute(httppost);
        try
        {
            StatusLine statusLine = response.getStatusLine();
            int status = statusLine.getStatusCode();
            if(status != 200)
                throw new Exception("HTTP POST出错:"+ status + ","+ statusLine.getReasonPhrase());

            // 下行数据
            HttpEntity dataRecv = response.getEntity();
            String ss = EntityUtils.toString(dataRecv);
            return ss;
        } finally
        {
            try{ httpclient.close(); }catch(Exception e){}
        }
    }

    // wrapper functions
    public static void post(String url, JSONObject request, Callback callback)
    {
        AfRest rest = new AfRest(url, request);
        rest.callback = callback;
        rest.execute();
    }
}
