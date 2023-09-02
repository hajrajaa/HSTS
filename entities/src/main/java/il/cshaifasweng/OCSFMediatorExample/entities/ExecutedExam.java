package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="executedExams")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ExecutedExam implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int examNum;

    private String title;

    private int infoID;

    private String examDate;

    private  String startime;

    private String studentName;

    private String endtime;

    private double grade;

    private boolean marked;

    private boolean submitInTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "testDate_id")
    private ExecutedExamInfo testDate;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isMarked() {
        return marked;
    }
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isSubmitInTime() {
        return submitInTime;
    }
    public void setSubmitInTime(boolean submitInTime) {
        this.submitInTime = submitInTime;
    }

    public ExecutedExam() {}

    ///
    public ExecutedExam(String title, int infoID, String studentName, String examDate, String startime, String endtime, boolean submitInTime, boolean marked, double grade) {
        this.title = title;
        this.infoID = infoID;
        this.examDate = examDate;
        this.startime = startime;
        this.studentName = studentName;
        this.endtime = endtime;
        this.grade = grade;
        this.marked = marked;
        this.submitInTime = submitInTime;
    }

    ///
    public ExecutedExam(String title, int infoID, String studentName, String examDate, String startime, String endtime, boolean submitInTime, boolean marked) {
        this.title = title;
        this.infoID = infoID;
        this.examDate = examDate;
        this.startime = startime;
        this.studentName = studentName;
        this.endtime = endtime;
        this.grade = grade;
        this.marked = marked;
        this.submitInTime = submitInTime;
    }

    public ExecutedExam(int examNum, Student student, String examDate, String startime, double grade, boolean submitInTime, Exam exam,ExecutedExamInfo examInfo) {
        super();
        this.examNum = examNum;
        setStudent(student);
        setExam(exam);
        setTestDate(examInfo);
        if(this.student != null){
            this.studentName = this.student.getUserName();
        }else {
            this.studentName = "";
        }
        this.examDate=examDate;
        this.startime=startime;
        this.endtime= this.startime+exam.getTime();
        this.grade=grade;
        this.submitInTime=submitInTime;
        this.title = exam.getTitle();
        this.marked=false;
    }

    public ExecutedExam(int examNum, Student student, String examDate, String startime, double grade, boolean submitInTime, Exam exam,boolean marked) {
        super();
        this.examNum = examNum;
        setStudent(student);
        setExam(exam);
        if(this.student != null){
            this.studentName = this.student.getUserName();
        }else {
            this.studentName = "";
        }
        this.examDate=examDate;
        this.startime=startime;
        this.endtime= this.startime+exam.getTime();
        this.grade=grade;
        this.submitInTime=submitInTime;
        this.title = exam.getTitle();
        this.marked=marked;
    }

    public ExecutedExam(ExecutedExam exam) {
        super();
        this.examNum = exam.getExamNum();
        this.title = exam.exam.getTitle();
        this.studentName=exam.getStudentName();
//        setStudent(exam.getStudent());
//        setExam(exam.getExam());
        this.examDate=exam.getExamDate();
        this.startime=exam.getStartime();
        this.endtime= this.startime+exam.getExam().getTime();
        this.grade=exam.getGrade();
        this.submitInTime=exam.isSubmitInTime();
        this.marked = exam.isMarked();
    }

    public int getInfoID() { return infoID; }
    public void setInfoID(int infoID) { this.infoID = infoID; }

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

    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
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

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTestDate(ExecutedExamInfo testDate) {
        if (this.testDate != null) {
            this.testDate.getExecutedExamList().remove(this);
        }
        this.testDate = testDate;
        if (testDate != null) {
            testDate.getExecutedExamList().add(this);
        }
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
//    public void setStudent(Student student) {
//        if (this.student != null) {
//            if(this.student.getMyExams()!=null){this.student.getMyExams().remove(this);}
//        }
//        this.student = student;
//        if (student != null) {
//            if(student.getMyExams()!=null){student.getMyExams().add(this);}
//        }
//    }

    public void setStudent (Student s)
    {
        this.student=s;
        if(s != null){
            s.addExam(this);
        }
    }
    public Student getStudent() {
        return student;
    }

    public Exam getExam() {return this.exam;}

    public void setExam(Exam exam) {
        this.exam = exam;
        if (exam != null) {
            exam.addExecutedExam(this);
        }
    }

    public ExecutedExamInfo getTestDate() {
        return testDate;
    }

    public void setExecutedExamInfo(ExecutedExamInfo executedExamInfo) {
        this.testDate=executedExamInfo;
        if(executedExamInfo != null){
            executedExamInfo.addExecutedExam(this);
        }
    }



}
