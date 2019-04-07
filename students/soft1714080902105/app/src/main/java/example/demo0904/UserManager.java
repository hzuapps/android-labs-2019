package example.demo0904;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaofa on 2017/11/14.
 */

public class UserManager
{
    // 创建一个List来缓存User信息
    List<User> userlist = new ArrayList();

    // 数据保存到这个文件里
    File file;

    public UserManager(File file)
    {
        this.file =file;
    }

    // 保存到文件
    public void save() throws Exception
    {
        // 每行存储一个用户的信息，以逗号分隔
        FileOutputStream fstream = new FileOutputStream(file);
        for(User u : userlist)
        {
            String line = u.username + "," + u.password + "\n";
            fstream.write( line.getBytes("UTF-8"));
        }
        fstream.close(); // 此处宜用try{} finally}{} 确保fstream被关闭
    }

    // 从文件加载
    public void load() throws Exception
    {
        InputStreamReader m = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader reader = new BufferedReader(m);

        userlist.clear(); // 先清空链表
        while(true)
        {
            String line = reader.readLine();
            if(line == null) break;

            String[] cols = line.split(",");
            if(cols.length<2) continue;

            User u = new User();
            u.username = cols[0].trim();
            u.password = cols[1].trim();
            userlist.add( u );
        }

        reader.close(); // reader关闭后，底层的inputstream会被自动关闭
    }


    // 注册一个用户
    public void add(User u)
    {
        userlist.add(u);
    }

    // 按名称查找
    public User find(String username)
    {
        for(User u: userlist)
        {
            if(u.username.equals(username))
            {
                return u;
            }
        }
        return null;
    }


}
