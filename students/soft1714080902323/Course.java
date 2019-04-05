package androidlabs2019.students.soft1714080902323;

import java.io.Serializable;

public class Course implements Serializable {
     String name;
     String teacher;
    public Course(String name, String teacher){
        this.name=name;
        this.teacher=teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    @Override
    public String toString() {
        return "课程" +":" +
                 name +
                ",任课老师为：" + teacher ;
    }

}
