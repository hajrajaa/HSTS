package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;

import java.util.List;

public class ShowCoursesEvent {

    List<Course> courses;

    public ShowCoursesEvent(List<Course> courses) {
        this.courses=courses;
    }

    public List<Course> getCoursesEvent() {
        return courses;
    }

    public void setCoursesEvent(List<Course> courses) {
        this.courses = courses;
    }
}
