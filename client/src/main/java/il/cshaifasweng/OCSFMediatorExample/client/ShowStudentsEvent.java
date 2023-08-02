package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;

import java.util.List;


public class ShowStudentsEvent {

    List<Student> students;

    public ShowStudentsEvent(List<Student> students) {
        this.students=students;
    }

    public List<Student> getStudentList() {
        return students;
    }

    public void setStudentList(List<Student> students) {
        this.students = students;
    }

}


