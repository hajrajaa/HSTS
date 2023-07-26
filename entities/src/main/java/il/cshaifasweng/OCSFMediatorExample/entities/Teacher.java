package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="Teachers")
public class Teacher extends User implements Serializable{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExecutedExamInfo> examsInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> courseID;

    public Teacher(int id, String username, String password){
        super(id,username,password,"Teacher");
        this.examsInfo=new ArrayList<ExecutedExamInfo>();
        this.courseID = new ArrayList<String>();
    }


    public List<ExecutedExamInfo> getExamsInfo() {
        return examsInfo;
    }


    public void addExam(Exam... exams){
        for(Exam exam : exams){
            this.exams.add(exam);
            exam.setTeacher(this);
        }
    }


    public void addExecutedExamInfo(ExecutedExamInfo... executedExamsInfo){
        for(ExecutedExamInfo executedExamInfo : executedExamsInfo){
            this.examsInfo.add(executedExamInfo);
            //executedExamInfo.setTeacher(this);
        }
    }





    public List<Exam> getExams() {
        return exams;
    }


    /*
    public List<String> getCourseID() {
        return courseID;
    }

    public void setCourseID(List<String> courseID) {
        this.courseID = courseID;
    }
    */

}


