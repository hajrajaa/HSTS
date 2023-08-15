package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.List;

public class ShowCoursesInfoEvent {

    List<ExecutedExamInfo> courses;

    public ShowCoursesInfoEvent(List<ExecutedExamInfo> courses) {
        this.courses=courses;
    }

    public List<ExecutedExamInfo> getCoursesInfoEvent() {
        return courses;
    }

    public void setCoursesInfoEvent(List<ExecutedExamInfo> courses) {
        this.courses = courses;
    }
}
