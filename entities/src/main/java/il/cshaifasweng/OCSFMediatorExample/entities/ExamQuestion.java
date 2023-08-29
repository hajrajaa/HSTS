package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="examQuestions")
public class ExamQuestion implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int points;
    private String teacher_note;
    private String student_note;

    public String getQuestionMll() {
        return questionMll;
    }

    public void setQuestionMll(String questionMll) {
        this.questionMll = questionMll;
    }

    private String questionMll;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

//    public ExamQuestion(int id, int code, String question, int correct_answer, String[] answers, int points, String teacher_note, String student_note) {
//        super(code, question, answers, correct_answer);
//        this.points = points;
//        this.teacher_note = teacher_note;
//        this.student_note = student_note;
//    }

//    public ExamQuestion(int code, String question, String[] answers, int correct_answer, int points, String teacher_note, String student_note) {
//        super(code, question, answers, correct_answer);
//        this.points = points;
//        this.teacher_note = teacher_note;
//        this.student_note = student_note;
//    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ExamQuestion(Question question, int points, String teacher_note, String student_note) {
//        super(question.getCode(), question.getQuestion(), question.getAnswers(), question.getCorrect_answer());
        this.question=question;
        question.addExamQuestion(this);
        this.questionMll = this.question.getQuestion();
        this.points = points;
        this.teacher_note = teacher_note;
        this.student_note = student_note;
    }

    public ExamQuestion(ExamQuestion examQuestion) {
        this.question = new Question(examQuestion.getQuestion());
        this.questionMll = this.question.getQuestion();
        this.points = examQuestion.getPoints();
        this.teacher_note = examQuestion.getTeacher_note();
        this.student_note = examQuestion.getStudent_note();
        this.exam=examQuestion.getExam();
    }

    public ExamQuestion() {}

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

    public void setExam (Exam e){
        this.exam = e;
    }



    public Exam getExam() {
        return exam;
    }
}
