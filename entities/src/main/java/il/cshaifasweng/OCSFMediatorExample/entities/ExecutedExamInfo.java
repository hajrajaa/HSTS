package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="executedexamsinfo")
public class ExecutedExamInfo implements Serializable
{
    public enum ExamType{
        Virtual,Manual;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;
    private String password;
    private String title;
    private int overtime;
    private double average;
    private double median;
    private ExamType type;
    private int[] hist;
    private ArrayList<Double> allGrades;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "testDate")
    private List<ExecutedExam> executedExamList;

    public Teacher getExecutingTeacher() {
        return executingTeacher;
    }

    public void setTeacher (Teacher teacher){
        this.executingTeacher=teacher;
        teacher.addExecutedExamsInfo(this);
    }

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher executingTeacher;
  
    public ExecutedExamInfo(int code, String password,String title, double average, double median,ExamType type,Teacher teacher) {
        this.code = code;
        this.password = password;
        this.title=title;
        this.overtime = 0;
        this.average = average;
        this.median = median;
        this.type=type;
        this.hist = new int[10];
        for (int i=0; i<hist.length; i++) {this.hist[i]=0;}
        this.executedExamList = new ArrayList<ExecutedExam>();
        this.allGrades = new ArrayList<>();
        setExecutingTeacher(teacher);
    }

    public ExecutedExamInfo(int code, String password,String title, double average, double median,ExamType type,int[] hist) {
        this.code = code;
        this.password = password;
        this.title=title;
        this.overtime = 0;
        this.average = average;
        this.median = median;
        this.type=type;
        this.hist = hist;
        this.executedExamList = new ArrayList<ExecutedExam>();
        this.allGrades = new ArrayList<>();
    }

    public ExecutedExamInfo(ExecutedExamInfo exam)
    {
        this.id=exam.getId();
        this.code = exam.getCode();
        this.password = exam.getPassword();
        this.title = exam.getTitle();
        this.overtime = 0;
        this.average = exam.getAverage();
        this.median = exam.getMedian();
        this.type = exam.getType();
        this.hist = exam.getHist();
        this.allGrades = new ArrayList<>();
    }

    public  ExecutedExamInfo(int code,String password,ExamType type)
    {
        this.code=code;
        this.password=password;
        this.type=type;
        this.hist = new int[10];
        for (int i=0; i<hist.length; i++) {this.hist[i]=0;}
        this.executedExamList = new ArrayList<ExecutedExam>();
        this.allGrades = new ArrayList<>();
    }

    public  ExecutedExamInfo(int code,String password,ExamType type, String title, Teacher teacher)
    {
        this.code=code;
        this.password=password;
        this.type=type;
        this.hist = new int[10];
        for (int i=0; i<hist.length; i++) {this.hist[i]=0;}
        this.executingTeacher=teacher;
        teacher.addExecutedExamsInfo(this);
        this.title=title;
        this.executedExamList = new ArrayList<ExecutedExam>();
        this.overtime=0;
        this.average=0;
        this.median=0;
        this.allGrades = new ArrayList<>();
    }

    public ExecutedExamInfo() {}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int[] getHist() {
        return hist;
    }
    public void setHist(int[] hist) {
        this.hist = hist;
    }

    public void setExecutedExamList(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }

    public  void setExecutingTeacher(Teacher teacher)
    {
        if (this.executingTeacher != null) {
            this.executingTeacher.getExams().remove(this);
        }

        this.executingTeacher = teacher;
        if(teacher!=null)
        {
            teacher.getExecutedExamsInfo().add(this);
        }
    }

    public void addExecutedExam (ExecutedExam ex){
        if(this.executedExamList == null){
            this.executedExamList = new ArrayList<>();
        }
        this.executedExamList.add(ex);
        updateGradesInfo();
    }

    public void updateGradesInfo ()
    {
        refreshAllGradesList();
        this.createHist();
        this.setAverage(culcAverage());
        this.setMedian(culcMedian());
    }

    private void refreshAllGradesList ()
    {
        if(this.executedExamList != null){
            this.allGrades = new ArrayList<>();
            for (ExecutedExam ee : this.executedExamList){
                this.allGrades.add(ee.getGrade());
            }
        }
    }

    private void createHist ()
    {
        ArrayList<Double> values = new ArrayList<>(allGrades);
        for (int i=0; i < this.hist.length; i++) {
            this.hist[i] = 0;
        }
        for (int i=0; i < values.size(); i++) {
            addToHist(values.get(i));
        }
    }

    private void addToHist (double newGrade)
    {
        if(newGrade >= 100) {
            this.hist[9]++;
        }else{
            this.hist[(int)newGrade/10]++;
        }

    }

    private double culcAverage ()
    {
        ArrayList<Double> values = new ArrayList<>(allGrades);
        double sum = 0;
        for (int i=0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return sum / values.size();
    }

    private double culcMedian ()
    {
        ArrayList<Double> values = new ArrayList<>(allGrades);
        Collections.sort(values);

        if (values.size() % 2 == 1)
            return values.get((values.size() + 1) / 2 - 1);
        else {
            double lower = values.get(values.size() / 2 - 1);
            double upper = values.get(values.size() / 2);

            return (lower + upper) / 2.0;
        }
    }
}
