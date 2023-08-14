package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.ArrayList;

public class EventGetTeacherAllExams
{
    private ArrayList<ExecutedExamInfo> writtenExamsInfoList;
    private ArrayList<ExecutedExamInfo> executedExamInfoList;

    public EventGetTeacherAllExams(ArrayList<ExecutedExamInfo> writtenExamsInfoList, ArrayList<ExecutedExamInfo> executedExamInfoList) {
        this.writtenExamsInfoList = writtenExamsInfoList;
        this.executedExamInfoList = executedExamInfoList;
    }

    public ArrayList<ExecutedExamInfo> getWrittenExamsInfoList() {
        return writtenExamsInfoList;
    }

    public void setWrittenExamsInfoList(ArrayList<ExecutedExamInfo> writtenExamsInfoList) {
        this.writtenExamsInfoList = writtenExamsInfoList;
    }

    public ArrayList<ExecutedExamInfo> getExecutedExamInfoList() {
        return executedExamInfoList;
    }

    public void setExecutedExamInfoList(ArrayList<ExecutedExamInfo> executedExamInfoList) {
        this.executedExamInfoList = executedExamInfoList;
    }
}
