package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;


public class StartSolveExamEvent {

    Exam exam;



    ExecutedExamInfo.ExamType examType;



    public StartSolveExamEvent(Exam exam , ExecutedExamInfo.ExamType type) {
        this.exam=exam;
        this.examType=type;
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

}

