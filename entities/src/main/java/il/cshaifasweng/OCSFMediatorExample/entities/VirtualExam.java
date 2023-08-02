package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="virtualExams")
public class VirtualExam extends Exam implements Serializable {


   // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy ="virtualExam" )
    //private List<ExamQuestion> examQuestion;

    public VirtualExam(int time, int codeExam, String descForStudent, String descForTeacher, Teacher teacher)
    {
        super(time, codeExam, descForStudent, descForTeacher, teacher, "Virtual");
       // this.examQuestion=new ArrayList<ExamQuestion>();
    }

    public VirtualExam() {
    }

 /*   public VirtualExam(VirtualExam other) {
        super(other.getTime(), other.getCodeExam(), other.getDescForStudent(),
                other.getDescForTeacher(), other.getTeacher(), "Virtual");
        // Copy the list of exam questions
        this.examQuestion = new ArrayList<>(other.getExamQuestion());
    }*/

    public void myPrint (){
        System.out.println("VirtualExam myPrint");
        System.out.println("---> codeExa: " + this.getCodeExam());

    }

    //public List<ExamQuestion> getExamQuestion() {
     //   return examQuestion;
   // }

  //  public void setExamQuestion(List<ExamQuestion> examQuestion) {
       // this.examQuestion = examQuestion;
   // }




}
