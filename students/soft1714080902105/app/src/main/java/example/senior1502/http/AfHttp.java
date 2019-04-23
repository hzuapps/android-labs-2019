package example.senior1502.http;

import org.apache.myhttp.Consts;
import org.apache.myhttp.HttpEntity;
import org.apache.myhttp.NameValuePair;
import org.apache.myhttp.StatusLine;
import org.apache.myhttp.client.config.RequestConfig;
import org.apache.myhttp.client.entity.UrlEncodedFormEntity;
import org.apache.myhttp.client.methods.CloseableHttpResponse;
import org.apache.myhttp.client.methods.HttpGet;
import org.apache.myhttp.client.methods.HttpPost;
import org.apache.myhttp.impl.client.CloseableHttpClient;
import org.apache.myhttp.impl.client.HttpClients;
import org.apache.myhttp.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by shaofa on 2017/12/30.
 */

public class AfHttp
{
    public static String get(String uri) throws Exception
    {
        // 1 创建HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000) // 读写超时设置
                .setConnectTimeout(3000) // 连接超时设置
                .build();

        // 2 创建GET请求
        HttpGet httpget = new HttpGet(uri);
        httpget.setConfig(requestConfig);

        // 3 发起请求
        CloseableHttpResponse response = httpclient.execute(httpget);
        try
        {
            // 4 获取应答
            StatusLine statusLine = response.getStatusLine();
            int status = statusLine.getStatusCode();
            if (status != 200)
                throw new Exception("HTTP 出错:" + status + "," + statusLine.getReasonPhrase());

            // 下行数据
            HttpEntity dataRecv = response.getEntity();
            String ss = EntityUtils.toString(dataRecv);
            return ss;
        } finally
        {
            // 关闭连接
            try
            {
                httpclient.close();
            } catch (Exception e)
            {
            }
        }
    }

    public static String post(String uri, List<NameValuePair> pairs) throws Exception
    {
        // 1 创建HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000) // 读写超时设置
                .setConnectTimeout(3000) // 连接超时设置
                .build();

        // 2 创建POST请求
        HttpPost httppost = new HttpPost(uri);
        httppost.setConfig(requestConfig);

        // 设置请求参数
        // List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // formparams.add(new BasicNameValuePair("username", "android"));
        // formparams.add(new BasicNameValuePair("password", "123456"));
        UrlEncodedFormEntity dataSent = new UrlEncodedFormEntity(pairs, Consts.UTF_8);
        httppost.setEntity(dataSent);

        // 3 发起请求
        CloseableHttpResponse response = httpclient.execute(httppost);
        try
        {
            // 4 获取应答
            StatusLine statusLine = response.getStatusLine();
            int status = statusLine.getStatusCode();
            if (status != 200)
                throw new Exception("HTTP 出错:" + status + "," + statusLine.getReasonPhrase());

            // 下行数据
            HttpEntity dataRecv = response.getEntity();
            String ss = EntityUtils.toString(dataRecv);
            return ss;
        } finally
        {
            // 关闭连接
            try
            {
                httpclient.close();
            } catch (Exception e)
            {
            }
        }
    }

    // 下载文件
    public static File download(String uri, File localFile) throws Exception
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

            // 保存文件
            final int MAXSIZE = 1024 * 1024 * 128; // 128M
            byte[] buf = new byte[1024 * 4];

            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                InputStream instream = entity.getContent();
                FileOutputStream fstream = new FileOutputStream(localFile);

                int total = 0;
                int retry = 0;
                while (total < MAXSIZE)
                {
                    int n = instream.read(buf);
                    if (n < 0)
                        break;
                    if (n == 0)
                    {
                        // busy
                        try	{Thread.sleep(5);} catch (Exception e){}

                        if (retry++ > 5)
                        {
                            System.out.println("** 不能下载, 重试次数:" + retry);
                            break;
                        }
                        else
                            continue; // 允许在服务器busy的情况下重试N次
                    }

                    fstream.write(buf, 0, n);
                    retry = 0;
                    total += n;
                }

                return localFile;
                //System.out.println("** HTTP GET finished,  total: " + total);
            }
        } finally
        {
            try	{	httpclient.close();	} catch (Exception e){ }
        }
        return null;
    }
}
