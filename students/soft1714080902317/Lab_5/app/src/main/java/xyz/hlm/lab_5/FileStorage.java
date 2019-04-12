package xyz.hlm.lab_5;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
