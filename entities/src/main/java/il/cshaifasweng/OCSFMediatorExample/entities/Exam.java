package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Exams")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Exam implements Serializable
{
    private int time;

    @Id
    private int codeExam;

    private String descForStudent;

    private String descForTeacher;

    String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id2")
    private Teacher teacher;




    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy ="exam")
    private List<ExecutedExam> executedExams;


    public Exam() {

    }


    public Exam(int time, int codeExam, String descForStudent, String descForTeacher, Teacher teacher, String type) {
        super();
        this.time = time;
        this.codeExam = codeExam;
        this.descForStudent = descForStudent;
        this.descForTeacher = descForTeacher;
        setTeacher(teacher);
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCodeExam() {
        return codeExam;
    }

    public void setCodeExam(int codeExam) {
        this.codeExam = codeExam;
    }

    public String getDescForStudent() {
        return descForStudent;
    }

    public void setDescForStudent(String descForStudent) {
        this.descForStudent = descForStudent;
    }

    public String getDescForTeacher() {
        return descForTeacher;
    }

    public void setDescForTeacher(String descForTeacher) {
        this.descForTeacher = descForTeacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }


    public void setTeacher(Teacher teacher) {
        if (this.teacher != null) {
            this.teacher.getExams().remove(this);
        }

        this.teacher = teacher;
        //teacher.getExams().add(this);
    }

    public List<ExecutedExam> getExecutedExams() {
        return executedExams;
    }

    public String getMyClass ()
    {
        return this.getClass().getName();
    }

    public String getType() {
        return type;
    }


    //TODO: check if need set for executedExams;


}


