package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.ArrayList;

public class ShowTeachersExamInfoEvent {

    ArrayList<ExecutedExamInfo> exe_ex;

    public ShowTeachersExamInfoEvent(ArrayList<ExecutedExamInfo> exe_ex1) {
        this.exe_ex=exe_ex1;
    }

    public ArrayList<ExecutedExamInfo> getTeachersExamInfoEvent() {
        return exe_ex;
    }

    public void setTeachersExamInfoEvent(ArrayList<ExecutedExamInfo> exe_ex1) {
        this.exe_ex = exe_ex1;
    }

}
