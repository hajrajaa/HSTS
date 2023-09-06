package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;

import java.util.List;

public class refreshExecExam {


    List<ExecutedExam> executedExamList;



    public refreshExecExam(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }


    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
    }

    public void setExecutedExamList(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }
}
