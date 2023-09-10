package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.fxml.FXML;


public class SolveExamEvent {

    Exam exam;

    public SolveExamEvent(Exam exam)
    {
       this.exam=exam;
    }

    public Exam getExam1() {
        return exam;
    }

    public void setExam1(Exam exam) {
        this.exam = exam;
    }

    @FXML
    public  void initialize()
    {
        exam=App.getExam();
    }


    }






