package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;

public class ApproveGradeEvent {


    ExecutedExam executedExam;


    public ApproveGradeEvent(ExecutedExam executedExam) {
        this.executedExam = executedExam;
    }

    public ExecutedExam getExecutedExam() {
        return executedExam;
    }

    public void setExecutedExam(ExecutedExam executedExam) {
        this.executedExam = executedExam;
    }



}
