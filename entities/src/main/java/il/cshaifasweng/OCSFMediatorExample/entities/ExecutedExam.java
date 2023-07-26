package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="executedExams")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ExecutedExam implements Serializable {
    @Id
    private int examNum;

    private String examDate;

    private  String startime;

    private String endtime;

    private  double grade;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    private boolean submitInTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "testDate_id")
    private ExecutedExamInfo testDate;

    public ExecutedExam() {
    }

    public ExecutedExam(int examNum, Student student, String examDate, String startime, double grade, boolean submitInTime, Exam exam) {
        super();
        this.examNum = examNum;
        setStudent(student);
        setExam(exam);
        this.examDate=examDate;
        this.startime=startime;
        this.endtime= this.startime+exam.getTime();
        this.grade=grade;
        this.submitInTime=submitInTime;
    }

    public int getExamNum() {return examNum;}

    public void setExamNum(int examNum) {this.examNum = examNum;}

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public void setEndtime(String endtime) {
        this.startime = endtime;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isInDuration() {
        return submitInTime;
    }

    public void setInDuration(boolean inDuration) {
        this.submitInTime = inDuration;
    }


    //TODO:check if need
//    public void setStudent(Student student)
//    {
//        if(this.student!=null)
//        {
//            this.student.getMyExams().remove(this);
//        }
//        this.student=student;
//        student.getMyExams().add(this);
//    }
//
//
//    public  void setExam(Exam exam)
//    {
//        if(this.exam!=null)
//        {
//            this.exam.getExecutedExams().remove(this);
//        }
//        this.exam=exam;
//        exam.getExecutedExams().add(this);
//    }
    public void setStudent(Student student) {
        if (this.student != null) {
            this.student.getMyExams().remove(this);
        }
        this.student = student;
        if (student != null) {
            student.getMyExams().add(this);
        }
    }

    public void setExam(Exam exam) {
        if (this.exam != null) {
            this.exam.getExecutedExams().remove(this);
        }
        this.exam = exam;
        if (exam != null) {
            exam.getExecutedExams().add(this);
        }
    }
}
