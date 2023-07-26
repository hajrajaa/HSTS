package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="executedManual")
//@DiscriminatorValue("exacutedmanual")
public class ExecutedManual extends ExecutedExam implements Serializable {

    String solutionFile;


    public ExecutedManual(int serialNum, Student student,String examDate , String startime ,double grade, boolean submitInTime ,Exam exam,String solutionFile) {
        super(serialNum, student,examDate,startime,grade,submitInTime,exam);
        this.solutionFile = solutionFile;
    }

    public String getSolutionFile() {
        return solutionFile;
    }

    public void setSolutionFile(String solutionFile) {
        this.solutionFile = solutionFile;
    }
}
