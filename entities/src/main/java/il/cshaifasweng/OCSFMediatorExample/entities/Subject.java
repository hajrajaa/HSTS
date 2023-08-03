package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject  implements Serializable
{

    private int id;

    @Id
    private  String subName;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "subject")
    private List<Course> courses;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjectsList")
    private List<Teacher> teachersList;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjectsList")
    private List<Student> students ;


    public Subject() {
    }


    public Subject(int id ,String subName)
    {
        this.id=id;
       this.subName=subName;
    }


    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }




     public void addCourse(Course... courses) {
       for (Course  course: courses) {
         this.courses.add(course);
      course.setSubject(this);
     }
     }



    public void addTeacher(Teacher... teachersList) {
        for (Teacher  teacher: teachersList) {
            this.teachersList.add(teacher);
            teacher.getSubjectsList().add(this);
        }
    }

    public void addStudent(Student... students) {
        for (Student  student: students) {
            this.students.add(student);
            student.getSubjectsList().add(this);
        }
    }





}
