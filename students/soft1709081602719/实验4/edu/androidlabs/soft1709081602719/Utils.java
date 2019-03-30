package edu.androidlabs.soft1709081602719;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String getSDCardPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static List<FileInfo> getListData(String path) {

        List<FileInfo> list = new ArrayList<>();

        File pfile = new File(path);
        File[] files = null;
        if (pfile.exists()) {
            files = pfile.listFiles();
        }

        if (files != null && files.length > 0) {
            for (File file : files) {
                FileInfo item = new FileInfo();
                if (file.isDirectory()
                        && file.canRead()
                        ) {

                   item.icon=R.drawable.fileicon;
                    item.size="";


                } else if(file.isFile()){

                    item.icon = R.drawable.fileicon;
                    String size = getSize(file.length());
                    item.size=size;

                }
                item.name=file.getName();
                item.path=file.getPath();
                list.add(item);
            }
        }
        files = null;
        return list;
    }

    public static String getSize(float length) {

        long kb = 1024;
        long mb = 1024*kb;
        long gb = 1024*mb;
        if(length<kb){
            return String.format("%dB", (int)length);
        }else if(length<mb){
            return String.format("%.2fKB", length/kb);
        }else if(length<gb){
            return String.format("%.2fMB", length/mb);
        }else {
            return String.format("%.2fGB", length/gb);
        }
    }

}
