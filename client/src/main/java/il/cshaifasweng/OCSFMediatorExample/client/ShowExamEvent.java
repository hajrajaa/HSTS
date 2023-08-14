package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;

import java.util.List;


public class ShowExamEvent {

    Exam exam;

    public ShowExamEvent(Exam exam) {
        this.exam=exam;
    }
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Exam getExamByCode() {
        return exam;
    }
}
