package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.StatisticsFilter;

import java.util.ArrayList;

public class EventPrincipleStatisticsLists {

    private ArrayList<StatisticsFilter> studentsList;
    private ArrayList<StatisticsFilter> teachersList;
    private ArrayList<StatisticsFilter> coursesList;

    public EventPrincipleStatisticsLists(ArrayList<StatisticsFilter> studentsList, ArrayList<StatisticsFilter> teachersList, ArrayList<StatisticsFilter> coursesList) {
        this.studentsList = studentsList;
        this.teachersList = teachersList;
        this.coursesList = coursesList;
    }

    public ArrayList<StatisticsFilter> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(ArrayList<StatisticsFilter> studentsList) {
        this.studentsList = studentsList;
    }

    public ArrayList<StatisticsFilter> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(ArrayList<StatisticsFilter> teachersList) {
        this.teachersList = teachersList;
    }

    public ArrayList<StatisticsFilter> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<StatisticsFilter> coursesList) {
        this.coursesList = coursesList;
    }

}
