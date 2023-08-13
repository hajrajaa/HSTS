package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="executedexamsinfo")
public class ExecutedExamInfo implements Serializable {


    public enum ExamType{
        Virtual,Manual;
    }

    @Id
    private int id ;


    private int code;
    private String password;

    private int overtime;
    private double average;
    private double median;

    private ExamType type;


    private int[] hist;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "testDate")
    private List<ExecutedExam> executedExamList;

//    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
//    @JoinColumn(name = "teacher_id")
//    private Teacher executingTeacher;

    public ExecutedExamInfo(int code, String password, double average, double median,ExamType type) {
        this.code = code;
        this.password = password;
        this.overtime = 0;
        this.average = average;
        this.median = median;
        this.type=type;
        this.hist = new int[10];
        this.executedExamList = new ArrayList<ExecutedExam>();
    }

    public  ExecutedExamInfo(int code,String password,ExamType type)
    {
        this.code=code;
        this.password=password;
        this.type=type;
        this.hist = new int[10];
        this.executedExamList = new ArrayList<ExecutedExam>();

    }

    public ExecutedExamInfo() {
    }



    public void setOvertime (int d) { this.overtime = d;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public ExamType getType() {
        return type;
    }

    public void setType(ExamType type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    // public int[] getHist() {
       // return hist;
    //}

   // public void setHist(int[] hist) {
       // this.hist = hist;
   // }

    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
    }

    public void setExecutedExamList(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }

//    public  void setExecutingTeacher(Teacher teacher)
//    {
//        if (this.executingTeacher != null) {
//            this.executingTeacher.getExams().remove(this);
//        }
//
//        this.executingTeacher = teacher;
//        teacher.getExamsInfo().add(this);
//    }
}
