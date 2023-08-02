package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="questions")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Question implements Serializable {
    @Id
    private int code;
    private String question;



    private String[] answers;

    private int correct_answer;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "question")
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "questions_courses",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> coursesList;

    public Question(int code, String question, String[] answers, int correct_answer) {
        super();
        this.code = code;
        this.question = question;
        this.correct_answer = correct_answer;
        this.answers = new String[4];
        this.subjects=new ArrayList<>();
    }

    public Question() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

//    public String getPossibility(int x) {return possibilities[x];}
////    public void setPossibilities(List<String> possibilities,int x) {
////        this.possibilities[x] = possibilities;
////    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }




    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }
}
