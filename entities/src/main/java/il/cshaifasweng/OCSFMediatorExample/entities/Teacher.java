package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name = "Teachers")
public class Teacher extends User implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> coursesList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjectsList ;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "teacher")
    private List<Exam> exams;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> courseID;

    public Teacher(int id, String username, String password) {
        super(id, username, password, UserType.Teacher);
        //this.examsInfo = new ArrayList<ExecutedExamInfo>();
       // this.exams=new ArrayList<>();
       //this.courseID = new ArrayList<String>();
    }


    public Teacher() {
    }


//    public List<ExecutedExamInfo> getExamsInfo() {
//        return examsInfo;
//    }


   // public void addExam(Exam... exams) {
     //   for (Exam exam : exams) {
       //     this.exams.add(exam);
          //  exam.setTeacher(this);
       // }
   // }


//    public void addExecutedExamInfo(ExecutedExamInfo... executedExamsInfo) {
//        for (ExecutedExamInfo executedExamInfo : executedExamsInfo) {
//            this.examsInfo.add(executedExamInfo);
//            //executedExamInfo.setTeacher(this);
//        }
//    }


    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }



    public List<String> getCourseID() {
        return courseID;
    }

    public void setCourseID(List<String> courseID) {
        this.courseID = courseID;
    }

}


