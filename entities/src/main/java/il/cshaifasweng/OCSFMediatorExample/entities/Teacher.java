package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name = "Teachers")
public class Teacher extends User implements Serializable {



//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "teacher_course",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private List<Course> teachersList;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "teacher_subject",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id"))
//    private List<Subject> subjectsList ;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "teacher")
    private List<Exam> exams;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy ="executingTeacher")
    private List<ExecutedExamInfo> executedExamsInfo;

//    @Column
//    @ElementCollection(targetClass = String.class)
//    private List<String> courseID;

    public Teacher(int id, String username, String password) {
        super(id, username, password, UserType.Teacher);

        this.exams = new ArrayList<>();
        this.executedExamsInfo=new ArrayList<ExecutedExamInfo>();
       //this.courseID = new ArrayList<String>();
    }

    public List<ExecutedExamInfo> getExecutedExamsInfo() {
        return executedExamsInfo;
    }

    public Teacher() {}



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

    public void addExecutedExamInfo(ExecutedExamInfo ExecutedExamsInfo) {
            this.executedExamsInfo.add(ExecutedExamsInfo);
    }


    public List<Exam> getExams() {
        return exams;
    }

    public List<ExecutedExamInfo> getExecutedExamsInfo() {
        return executedExamsInfo;
    }
    public void addExecutedExamsInfo(ExecutedExamInfo e) {
        if(this.executedExamsInfo == null){
            this.executedExamsInfo = new ArrayList<>();
        }
        this.executedExamsInfo.add(e);
    }

//
//    public void setExams(List<Exam> exams) {
//        this.exams = exams;
//    }
//
//
//
//    public List<String> getCourseID() {
//        return courseID;
//    }
//
//    public void setCourseID(List<String> courseID) {
//        this.courseID = courseID;
//    }
//
//
//    public List<Subject> getSubjectsList() {
//        return subjectsList;
//    }
//
//    public void setSubjectsList(List<Subject> subjectsList) {
//        this.subjectsList = subjectsList;
//    }
//
//    public List<Course> getCoursesList() {
//        return teachersList;
//    }
//
//    public void setCoursesList(List<Course> coursesList) {
//        this.teachersList = coursesList;
//    }

}


