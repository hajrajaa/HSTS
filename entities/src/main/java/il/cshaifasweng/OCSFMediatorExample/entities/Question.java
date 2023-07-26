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
    private String[] possibilities;
    private int correct_answer;

    public Question(int code, String question, int correct_answer) {
        super();
        this.code = code;
        this.question = question;
        this.correct_answer = correct_answer;
        this.possibilities = new String[4];
        possibilities[0]="~";
        possibilities[1]="~";
        possibilities[2]="~";
        possibilities[3]="~";
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

    public String getPossibility(int x) {return possibilities[x];}
//    public void setPossibilities(List<String> possibilities,int x) {
//        this.possibilities[x] = possibilities;
//    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }
}
