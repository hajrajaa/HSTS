package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student extends User implements Serializable{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "student")
    private List<ExecutedExam> myExams;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> coursesList;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "students_subjects",
            joinColumns = @JoinColumn(name = "studnet_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjectsList;


    public Student() {
    }

    public Student(int id, String username, String password) {
        super(id, username, password, UserType.Student);
       //this.myExams = new ArrayList<ExecutedExam>();
    }

//    public void addExam(ExecutedExam... executedExams) {
//        for (ExecutedExam executedExam : executedExams) {
//            this.myExams.add(executedExam);
//            executedExam.setStudent(this);
//        }
//    }

    public List<ExecutedExam> getMyExams() {
        return myExams;
    }

    //TODO: MANAR: implement the following function:
    // public ExecutedExam findExam(String examCode)
    // the function searches for the exam according to examCode in the list and return it (as ExecutedExam obj)


    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }


}







