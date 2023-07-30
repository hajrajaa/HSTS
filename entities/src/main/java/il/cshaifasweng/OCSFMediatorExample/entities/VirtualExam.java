package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="virtualExams")
public class VirtualExam extends Exam implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int serialNum;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "virtualExam_id")
    private List<ExamQuestion> examQuestion;

    public VirtualExam(int serialNum, int time, String codeExam, String descForStudent, String descForTeacher, Teacher teacher)
    {
        super(time, codeExam, descForStudent, descForTeacher, teacher);
        this.examQuestion=new ArrayList<ExamQuestion>();
    }

    public VirtualExam() {
    }

    public List<ExamQuestion> getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(List<ExamQuestion> examQuestion) {
        this.examQuestion = examQuestion;
    }


}
