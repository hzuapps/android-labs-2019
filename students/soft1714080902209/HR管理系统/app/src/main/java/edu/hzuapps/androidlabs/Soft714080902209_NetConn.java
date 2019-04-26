package edu.hzuapps.androidlabs;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Soft714080902209_NetConn {
    public static final boolean IsNetConn(){
        String result = null;
        String ip = "www.baidu.com";
        try{
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while((content = in.readLine()) != null){
                stringBuffer.append(content);
            }
            Log.d("-----ping-----", "result content : " + stringBuffer.toString());
            int status = p.waitFor();
            if(status == 0){
                result = "success";
                return true;
            }else{
                result = "failed";
                return false;
            }
        }catch (IOException e){
            result = "IOException";
        }catch (InterruptedException e){
            result = "InterruptedException";
        }finally {
            Log.d("-----result-----","result: " + result);
        }
        return false;
    }
}
