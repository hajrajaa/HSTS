package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;

import java.util.ArrayList;

public class ShowTeachersExamInfoDetailsEvent {

    ExecutedExamInfo[] exe_ex;

    public ShowTeachersExamInfoDetailsEvent(ExecutedExamInfo[] exe_ex1) {
        this.exe_ex=exe_ex1;
    }

    public ExecutedExamInfo[] getTeachersExamInfoDetailsEvent() {
        return exe_ex;
    }

    public void setTeachersExamInfoDetailsEvent(ExecutedExamInfo[] exe_ex1) {
        this.exe_ex = exe_ex1;
    }

}
