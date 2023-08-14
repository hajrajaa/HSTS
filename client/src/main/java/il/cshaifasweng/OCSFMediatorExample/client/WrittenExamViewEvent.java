package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.List;

// TODO: DELETE
public class WrittenExamViewEvent {

    List<ExecutedExamInfo> executedExamInfoList;

    public WrittenExamViewEvent(List<ExecutedExamInfo> executedExamInfoList) {
        this.executedExamInfoList = executedExamInfoList;
    }



    public List<ExecutedExamInfo> getExecutedExamInfoList() {
        return executedExamInfoList;
    }

    public void setExecutedExamInfoList(List<ExecutedExamInfo> executedExamInfoList) {
        this.executedExamInfoList = executedExamInfoList;
    }

}
