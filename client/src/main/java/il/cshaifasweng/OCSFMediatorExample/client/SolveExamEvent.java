package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.VirtualExam;


public class SolveExamEvent {

    VirtualExam exam;

    public SolveExamEvent(VirtualExam exam)
    {
       this.exam=exam;
    }

    public VirtualExam getExam() {
        return exam;
    }

    public void setExam(VirtualExam exam) {
        this.exam = exam;
    }

}




