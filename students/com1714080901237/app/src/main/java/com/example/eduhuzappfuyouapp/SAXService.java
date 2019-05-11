package com.example.eduhuzappfuyouapp;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXService {

    public List<Course> getCourses(InputStream inputStream) throws Exception {
        //得到SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //得到SAX解析器
        SAXParser parser = factory.newSAXParser();
        CoursesParser coursesParser = new CoursesParser();
        parser.parse(inputStream,coursesParser);
        inputStream.close();
        return coursesParser.getCourses();
    }

    private final class CoursesParser extends DefaultHandler {

        private List<Course> courses = null;
        private String tag = null;//记录当前解析到了那个元素节点名称
        private Course cous;

        public List<Course> getCourses() {
            return courses;
        }

        @Override
        public void startDocument() {
            courses = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if ("cous".equals(localName)) {
                cous = new Course();
                cous.setId(attributes.getValue(0));
            }
            tag = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if ("cous".equals(localName)) {
                courses.add(cous);
                cous = null;
            }
            tag = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (tag != null) {
                String data = new String(ch, start, length);
                if ("name".equals(tag)) {
                    cous.setName(data);
                } else if ("time".equals(tag)) {
                    cous.setTime(data);
                }
            }
        }
    }

}
class SAXwrite{
    public  void setCourses(List<Course> courses, FileOutputStream fileOutputStream) {


            try {
                XmlSerializer serializer = Xml.newSerializer();
                serializer.setOutput(fileOutputStream, "UTF-8");
                serializer.startDocument("UTF-8",true);

                serializer.startTag(null, "courses");
                // 开始子标签
                for(Course course:courses) {

                    serializer.startTag(null, "cous");
                    serializer.attribute(null, "id", course.getId());

                    serializer.startTag(null, "name");
                    serializer.text(course.getName());
                    serializer.endTag(null, "name");

                    serializer.startTag(null, "time");
                    serializer.text(course.getTime());
                    serializer.endTag(null, "time");

                    serializer.endTag(null, "cous");

                }
                serializer.endTag(null, "courses");

                serializer.endDocument();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
class Course {
    private String id;
    private String name;
    private String time;


    public Course(){ }
    public void setCourse(String id,String name,String time){
        setId(id);setName(name);setTime(time);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String toString(){
        return "id :"+id+",name :"+name+",time :"+time+";\n";
    }
}

