package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="executedexamsinfo")
public class ExecutedExamInfo implements Serializable {

    @Id
    private int id ;

    private String code;
    private String password;
    private Double average;
    private Double median;
    private int[] hist;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "testDate_id")
    private List<ExecutedExam> executedExamList;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher executingTeacher;

    public ExecutedExamInfo(String code, String password, Double average, Double median) {
        this.code = code;
        this.password = password;
        this.average = average;
        this.median = median;
        this.hist = new int[10];
        this.executedExamList = new ArrayList<ExecutedExam>();
    }

    public ExecutedExamInfo() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public int[] getHist() {
        return hist;
    }

    public void setHist(int[] hist) {
        this.hist = hist;
    }

    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
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
        teacher.getExamsInfo().add(this);
    }
}
