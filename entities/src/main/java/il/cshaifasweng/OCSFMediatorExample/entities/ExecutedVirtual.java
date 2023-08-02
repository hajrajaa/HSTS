package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name = "executedVirtual")
//@DiscriminatorValue("executedvirtual")
public class ExecutedVirtual extends ExecutedExam implements Serializable {


    private String note;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> solutions;

    public ExecutedVirtual(int examNum, Student student,String examDate , String startime ,double greade, boolean submitInTime ,Exam exam,String note) {
        super(examNum, student,examDate,startime,greade,submitInTime,exam);
        this.note = note;
        this.solutions=new ArrayList<String>();
    }

    public ExecutedVirtual() {
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<String> solutions) {
        this.solutions = solutions;
    }



}
