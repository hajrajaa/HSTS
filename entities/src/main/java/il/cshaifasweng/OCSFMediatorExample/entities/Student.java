    package il.cshaifasweng.OCSFMediatorExample.entities;
    import javax.persistence.*;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "Students")
    //@DiscriminatorValue("student")
    public class Student extends User implements Serializable{

//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private int number;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "student")
        //@JoinColumn(name = "student_id")
        private List<ExecutedExam> myExams;

        public Student() {
        }

        public Student(int id, String username, String password) {
            super(id, username, password, "Student");
            this.myExams = new ArrayList<ExecutedExam>();
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

    }



