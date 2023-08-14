package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.List;

public class WrittenExamEvent {

    List<ExecutedExam> executedExamList;


    ExecutedExamInfo examInfo;


    public WrittenExamEvent(List<ExecutedExam> executedExamList,ExecutedExamInfo examInfo) {
        this.executedExamList = executedExamList;
        this.examInfo=examInfo;
    }


    public List<ExecutedExam> getExecutedExamList() {
        return executedExamList;
    }

    public void setExecutedExamList(List<ExecutedExam> executedExamList) {
        this.executedExamList = executedExamList;
    }

    public ExecutedExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExecutedExamInfo examInfo) {
        this.examInfo = examInfo;
    }


}
