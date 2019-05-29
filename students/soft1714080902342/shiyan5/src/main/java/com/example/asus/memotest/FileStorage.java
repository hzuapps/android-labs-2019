package com.example.asus.memotest;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStorage {
    private File file;
    public FileStorage(String path,String name) throws IOException {
        file = new File("sdcard/"+path);
        if (!file.isDirectory()){
            file.mkdirs();
            Log.d("Mkdirs","successful");
        }
        file = new File("sdcard/" + path + "/" + name);
        if (!file.exists()){
            file.createNewFile();
            Log.d("Create","successful");
        }
    }

    public boolean store(String content) throws IOException,FileNotFoundException {
        FileOutputStream outputStream = new FileOutputStream(file,false);
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);
        bufferedWriter.write(content);
        if (bufferedWriter != null){
            bufferedWriter.close();
            Log.d("Store","successful");
            return true;
        }else return false;
    }

    FileInputStream instream;
    private String[] numbers = null;
    private int numberCount = 0;
    private InputStreamReader inputreader = null;
    private BufferedReader buffreader = null;
    public String get(){
        String content = "";
        try {
            instream = new FileInputStream ( file );
            if ( instream != null ) {
                inputreader = new InputStreamReader( instream );
                buffreader = new BufferedReader( inputreader );
                String line="";
                    instream = new FileInputStream ( file );
                    inputreader = new InputStreamReader ( instream );
                    buffreader = new BufferedReader ( inputreader );
                    while ( ( line = buffreader.readLine ( ) ) != null ) {
                        content+=line;
                    }
                instream.close ( );
                buffreader.close ( );
            }
        }
        catch (java.io.FileNotFoundException e)   {    }
        catch (IOException e)   {    }

        return content;
    }
}