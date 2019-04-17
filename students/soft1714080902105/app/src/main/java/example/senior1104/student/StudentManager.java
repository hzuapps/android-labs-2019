package example.senior1104.student;

import android.content.Context;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shaofa on 2017/12/24.
 */

public class StudentManager
{
    File xmlFile;
    ArrayList<Student> students = new ArrayList<>();

    public StudentManager(Context context)
    {
        File dataDir = context.getExternalFilesDir("data");
        xmlFile = new File(dataDir, "students.xml");
    }

    public List<Student> getAll()
    {
        return students;
    }

    // 添加
    public void add (Student s)
    {
        remove(s.id);
        students.add( s);

        try
        {
            save(); //
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    // 删除
    public void remove(String id)
    {
        Iterator<Student> iter = students.iterator();
        while(iter.hasNext())
        {
            Student s = iter.next();
            if(s.id.equals( id ))
            {
                iter.remove();
                break;
            }
        }

        try
        {
            save(); //
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 查找
    public Student find(String id)
    {
        Iterator<Student> iter = students.iterator();
        while(iter.hasNext())
        {
            Student s = iter.next();
            if(s.id.equals( id ))
                return s;
        }
        return null;
    }

    ///////////////////////////////////////////

    // 保存到XML文件
    public void save() throws Exception
    {
        Document x_doc = DocumentHelper.createDocument();
        Element x_root = x_doc.addElement( "root" );

        // 添加所有<student>
        for(Student s: students)
        {
            // 在<members>下添加 <student>
            Element x_student = x_root.addElement("student");

            // 在<student>下添加<id> <name><phone>
            x_student.addElement("id").addText(s.id);
            x_student.addElement("name").addText(s.name);
            x_student.addElement("phone").addText(s.phone);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
        writer.write( x_doc );
        writer.close();
    }

    // 加载XML文件
    public void load () throws Exception
    {
        // 先清空列表
        students.clear();

        // 加载XML
        FileInputStream inputStream = new FileInputStream(xmlFile);
        SAXReader xmlReader = new SAXReader();
        Document x_doc = xmlReader.read(inputStream);
        inputStream.close();

        // 取出根元素
        Element x_root = x_doc.getRootElement();

        // 取出所有 <student> 元素
        List<Element> x_student_list = x_root.elements("student");
        for(Element e: x_student_list)
        {
            Student s = new Student();
            s.id = e.elementText("id");
            s.name = e.elementText("name");
            s.phone = e.elementText("phone");
            students.add( s );
        }
    }
}
