package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Exams")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Exam implements Serializable{


    private int time;

    @Id
    private String codeExam;

    private String descForStudent;

    private String descForTeacher;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id2")
    private Teacher teacher;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "exam_id")
    private List<ExecutedExam> executedExams;


    public Exam() {

    }


    public Exam( int time, String codeExam, String descForStudent, String descForTeacher, Teacher teacher) {
        super();
        this.time = time;
        this.codeExam = codeExam;
        this.descForStudent = descForStudent;
        this.descForTeacher = descForTeacher;
        setTeacher(teacher);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCodeExam() {
        return codeExam;
    }

    public void setCodeExam(String codeExam) {
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
        teacher.getExams().add(this);
    }

    public List<ExecutedExam> getExecutedExams() {
        return executedExams;
    }

    //TODO: check if need set for executedExams;

}


