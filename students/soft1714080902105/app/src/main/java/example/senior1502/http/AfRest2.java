package example.senior1502.http;

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
 * 同步REST调用, 在线程里可以调用
 */

public class AfRest2
{
    int connectTimeout = 3000;
    int socketTimeout = 3000;

    public AfRest2()
    {
    }

    // 发起POST请求
    public String doPost(String url, String reqText) throws Exception
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
    public static JSONObject post(String url, JSONObject request) throws Exception
    {
        AfRest2 rest = new AfRest2();
        String sresp = rest.doPost(url, request.toString());
        JSONObject response = new JSONObject(sresp);
        return response;
    }
}
