package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="executedManual")
//@DiscriminatorValue("exacutedmanual")
public class ExecutedManual extends ExecutedExam implements Serializable {


    String solutionFile;


    public ExecutedManual (ExecutedExam ex, String solutionFile)
    {
        super(ex.getTitle(), ex.getInfoID(), ex.getStudentName(), ex.getExamDate(), ex.getStartime(), ex.getEndtime(), ex.isSubmitInTime(), ex.isMarked());
        this.solutionFile = solutionFile;
    }


    public ExecutedManual(int serialNum, Student student,String examDate , String startime ,double grade, boolean submitInTime ,Exam exam,String solutionFile, ExecutedExamInfo examInfo) {
        super(serialNum, student,examDate,startime,grade,submitInTime,exam,examInfo);
        this.solutionFile = solutionFile;
    }

    public ExecutedManual() {
    }

    public String getSolutionFile() {
        return solutionFile;
    }

    public void setSolutionFile(String solutionFile) {
        this.solutionFile = solutionFile;
    }
}
