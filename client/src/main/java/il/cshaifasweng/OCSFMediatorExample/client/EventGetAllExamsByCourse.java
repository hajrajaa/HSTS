package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;

import java.util.List;

public class EventGetAllExamsByCourse
{
    private  List<Exam> allExams;

    public EventGetAllExamsByCourse(List<Exam> all) {
        this.allExams = all;
    }

    public List<Exam> getAllExams() {
        return allExams;
    }

    public void setAllExams(List<Exam> all) {
        this.allExams = all;
    }
}
