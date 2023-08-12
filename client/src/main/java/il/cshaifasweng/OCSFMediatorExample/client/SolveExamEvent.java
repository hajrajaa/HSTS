package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;


public class SolveExamEvent {

    Exam exam;

    public SolveExamEvent(Exam exam)
    {
       this.exam=exam;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

}




