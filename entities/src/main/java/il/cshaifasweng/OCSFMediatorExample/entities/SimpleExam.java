package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleExam implements Serializable
{
    private int codeExam;
    private String title;
    private int time;
    private String descForStudent;
    private String descForTeacher;
    private String teacherName;
    private String courseName;

    public SimpleExam(int codeExam, String title, int time, String descForStudent, String descForTeacher, String teacherName, String courseName)
    {
        super();
        this.title = title;
        this.codeExam = codeExam;
        this.time = time;
        this.descForStudent = descForStudent;
        this.descForTeacher = descForTeacher;
        this.teacherName = teacherName;
        this.courseName = courseName;
    }

    public int getCodeExam() {
        return codeExam;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public String getDescForStudent() {
        return descForStudent;
    }

    public String getDescForTeacher() {
        return descForTeacher;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

}
