package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="examQuestions")
public class ExamQuestion extends Question implements Serializable{

    private int points;
    private String teacher_note;
    private String student_note;





    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    public ExamQuestion(int id, int code, String question, int correct_answer, String[] answers, int points, String teacher_note, String student_note) {
        super(code, question, answers, correct_answer);
        this.points = points;
        this.teacher_note = teacher_note;
        this.student_note = student_note;
    }

//    public ExamQuestion(int code, String question, String[] answers, int correct_answer, int points, String teacher_note, String student_note) {
//        super(code, question, answers, correct_answer);
//        this.points = points;
//        this.teacher_note = teacher_note;
//        this.student_note = student_note;
//    }

    public ExamQuestion(Question question, int points, String teacher_note, String student_note) {
        super(question.getCode(), question.getQuestion(), question.getAnswers(), question.getCorrect_answer());
        this.points = points;
        this.teacher_note = teacher_note;
        this.student_note = student_note;
    }

    public ExamQuestion() {

    }

//    public int getId() {
//        return id;
//    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTeacher_note() {
        return teacher_note;
    }

    public void setTeacher_note(String teacher_note) {
        this.teacher_note = teacher_note;
    }

    public String getStudent_note() {
        return student_note;
    }

    public void setStudent_note(String student_note) {
        this.student_note = student_note;
    }
}
