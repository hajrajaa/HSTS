package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;


public class StartSolveExamEvent {

    Exam exam;
    ExecutedExamInfo.ExamType examType;
    int examInfoID;

    public StartSolveExamEvent(Exam exam , ExecutedExamInfo.ExamType type, int examInfoID) {
        this.exam=exam;
        this.examType=type;
        this.examInfoID=examInfoID;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExecutedExamInfo.ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExecutedExamInfo.ExamType examType) {
        this.examType = examType;
    }

    public int getExamInfoID() {
        return examInfoID;
    }

    public void stExamInfoID(int examInfoID) {
        this.examInfoID=examInfoID;
    }

}

