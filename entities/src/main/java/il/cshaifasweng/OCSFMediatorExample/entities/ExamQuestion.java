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

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "virExam_id")
    //private VirtualExam virtualExam;

    public ExamQuestion(int id, int code, String question, int correct_answer, String[] answers, int points, String teacher_note, String student_note) {
        super(code, question, answers, correct_answer);
//        this.id=id;
        this.points = points;
        this.teacher_note = teacher_note;
        this.student_note = student_note;
       // this.virtualExam=virtualExam;
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
