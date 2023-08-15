package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;

public class UpdateGradeEvent {

    ExecutedExam executedExam;


    public ExecutedExam getExecutedExam() {
        return executedExam;
    }

    public void setExecutedExam(ExecutedExam executedExam) {
        this.executedExam = executedExam;
    }

    public UpdateGradeEvent(ExecutedExam executedExam) {
        this.executedExam = executedExam;
    }



}
