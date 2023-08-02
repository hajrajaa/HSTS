package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject  implements Serializable
{
    @Id
    private int id;


    private  String subName;

     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "subject")
    private List<Question> questions;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "subject")
    private List<Course> course;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjectsList")
    private List<Teacher> teachersList;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjectsList")
    private List<Student> students ;


    public Subject() {
    }


    public Subject(String subName)
    {
       this.subName=subName;
       //this.question=question;

    }


    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

}
