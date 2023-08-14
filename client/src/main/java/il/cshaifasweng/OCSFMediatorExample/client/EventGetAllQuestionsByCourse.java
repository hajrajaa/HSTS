package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import java.util.List;
public class EventGetAllQuestionsByCourse
{
    private List<Question> allQuestions;

    public EventGetAllQuestionsByCourse(List<Question> all) {
        this.allQuestions = all;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<Question> all) {
        this.allQuestions = all;
    }
}
