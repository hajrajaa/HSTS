package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.List;

public class ExcutedExamEvent {


    List<ExecutedExam> executedExamList;



    public ExcutedExamEvent(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }


    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
    }

    public void setExecutedExamList(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }





}
