package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;

import java.util.List;


public class ShowTeachersEvent {

    List<Teacher> teachers;

    public ShowTeachersEvent(List<Teacher> teachers) {
        this.teachers=teachers;
    }

    public List<Teacher> getTeacherList() {
        return teachers;
    }

    public void setTeacherList(List<Teacher> teachers) {
        this.teachers = teachers;
    }

}


