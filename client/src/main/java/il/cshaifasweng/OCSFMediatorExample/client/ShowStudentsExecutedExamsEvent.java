package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;

import java.util.ArrayList;

public class ShowStudentsExecutedExamsEvent {

    ArrayList<ExecutedExam> exe_ex;

    public ShowStudentsExecutedExamsEvent(ArrayList<ExecutedExam> exe_ex1) {
        this.exe_ex=exe_ex1;
    }

    public ArrayList<ExecutedExam> getStudentExecutedExamsList() {
        return exe_ex;
    }

    public void setStudentExecutedExamsList(ArrayList<ExecutedExam> exe_ex1) {
        this.exe_ex = exe_ex1;
    }

}
