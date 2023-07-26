package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="manualexams")
public class ManualExam  extends Exam implements Serializable{

    private String file;

    ManualExam( int time, String codeExam, String descForStudent, String descForTeacher, Teacher teacher,String file)
    {
        super( time, codeExam, descForStudent, descForTeacher, teacher);
        this.file=file;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
