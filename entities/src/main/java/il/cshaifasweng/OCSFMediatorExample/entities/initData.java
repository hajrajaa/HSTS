//package il.cshaifasweng.OCSFMediatorExample.entities;
//
//public class initData {
//}
//
//
//
//import il.cshaifasweng.OCSFMediatorExample.entities.*;
//        import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
//        import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
//        import org.hibernate.HibernateException;
//        import org.hibernate.Session;
//        import org.hibernate.SessionFactory;
//        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//        import org.hibernate.cfg.Configuration;
//        import org.hibernate.service.ServiceRegistry;
//
//        import javax.persistence.TypedQuery;
//        import javax.persistence.criteria.CriteriaBuilder;
//        import javax.persistence.criteria.CriteriaQuery;
//        import javax.persistence.criteria.Root;
//        import java.io.IOException;
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class SimpleServer extends AbstractServer {
//
//    private static Session session;
//
//    public SimpleServer(int port) {
//        super(port);
//        SessionFactory sessionFactory = getSessionFactory();
//        session = sessionFactory.openSession();
//        connectToDate();
//    }
//
//    private static SessionFactory getSessionFactory() throws HibernateException {
//        Configuration configuration = new Configuration();
//
//        configuration.addAnnotatedClass(Student.class);
//        configuration.addAnnotatedClass(User.class);
//        configuration.addAnnotatedClass(Teacher.class);
//        configuration.addAnnotatedClass(Exam.class);
//        configuration.addAnnotatedClass(ExecutedExam.class);
//        configuration.addAnnotatedClass(ExecutedExamInfo.class);
//        configuration.addAnnotatedClass(ExamQuestion.class);
//        configuration.addAnnotatedClass(Question.class);
//        configuration.addAnnotatedClass(xxxxxxxx.class);
//        configuration.addAnnotatedClass(yyyyyyyyyy.class);
//        configuration.addAnnotatedClass(ExecutedManual.class);
//        configuration.addAnnotatedClass(ExecutedVirtual.class);
//        configuration.addAnnotatedClass(Message.class);
//        configuration.addAnnotatedClass(Course.class);
//        configuration.addAnnotatedClass(Subject.class);
//        configuration.addAnnotatedClass(Princiaple.class);
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties())
//                .build();
//
//        return configuration.buildSessionFactory(serviceRegistry);
//    }
//
//    private static <T> List<T> getAllObjects(Class<T> object) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(object);
//        Root<T> rootEnter = query.from(object);
//        query.select(rootEnter);
//        query.where(builder.isNotNull(rootEnter.get("id")));
//
//        TypedQuery<T> queries = session.createQuery(query);
//        return queries.getResultList();
//    }
//
//    //Coursesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
//    //Function:
//
//
//    private static void generateCourse() throws Exception {
//        try{
//            session.beginTransaction();
//
//            ArrayList<Course> courseArrayList = new ArrayList<>();
//
//            Course newCourse1 = new Course(231, "Data Structure", "Computer Science");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse2 = new Course(232, "Object Oriented Programming", "Computer Science");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//
//            Course newCourse3 = new Course(233, "C language", "Computer Science");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse4 = new Course(241, "Discrete Mathematics", "Mathematics");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse5 = new Course(242, "Calculus 1", "Mathematics");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse6 = new Course(243, "Algebra 1", "Mathematics");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse7 = new Course(251, "Fencing", "Sports");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse8 = new Course(252, "Equestrianism", "Sports");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            Course newCourse9 = new Course(253, "Rugby", "Sports");
//            courseArrayList.add(newCourse1);
//            session.save(newCourse1);
//            session.flush();
//
//            session.getTransaction().commit(); // Save everything.
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//
//    //Students:
//    private static void generateStudents() throws Exception {
//        try{
//            session.beginTransaction();
//
//            ArrayList<Student> studentsList = new ArrayList<>();
//
//            Student newStudent1 = new Student(322859450, "Mahatma Gandhi", "Mahtama123");
//            studentsList.add(newStudent1);
//            session.save(newStudent1);
//            session.flush();
//
//            Student newStudent2 = new Student(323048713, "Nelson Mandela", "Nelson123");
//            studentsList.add(newStudent2);
//            session.save(newStudent2);
//            session.flush();
//
//            Student newStudent3 = new Student(323022323, "Floyd Mayweather", "Floyd123");
//            studentsList.add(newStudent3);
//            session.save(newStudent3);
//            session.flush();
//
//            Student newStudent4 = new Student(328192773, "Felix Wobblebottom", "Felix123");
//            studentsList.add(newStudent4);
//            session.save(newStudent4);
//            session.flush();
//
//            Student newStudent5 = new Student(327826313, "Octavia Nebulosa", "Octavia123");
//            studentsList.add(newStudent5);
//            session.save(newStudent5);
//            session.flush();
//
//            Student newStudent6 = new Student(376200823, "Isabella Sparklebrook", "Isabella123");
//            studentsList.add(newStudent6);
//            session.save(newStudent6);
//            session.flush();
//
//            session.getTransaction().commit(); // Save everything.
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//    //Teachers:
//    private static void generateTeacher() throws Exception {
//        try{
//
//            session.beginTransaction();
//            ArrayList<Teacher> teachersList = new ArrayList<>();
//
//            Teacher newTeacher1 = new Teacher(323972633, "Mr.Davis", "Davis123");
//            teachersList.add(newTeacher1);
//            session.save(newTeacher1);
//            session.flush();
//
//
//            Teacher newTeacher2 = new Teacher(39066443, "Mr.Taylor", "Taylor123");
//            teachersList.add(newTeacher2);
//            session.save(newTeacher2);
//            session.flush();
//
//            Teacher newTeacher3 = new Teacher(372899333, "Mrs.Tina", "Tina123");
//            teachersList.add(newTeacher3);
//            session.save(newTeacher3);
//            session.flush();
//            session.getTransaction().commit(); // Save everything.
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//
////Principle..................
//
//    private static void generatePrinciple() throws Exception {
//        try{
//            session.beginTransaction();
//            Princiaple p = new Princiaple(545, "malki", "121");
//            session.save(p);
//            session.flush();
//
//            session.getTransaction().commit(); // Save everything.
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//
//    }
//    //Questions...............
//
//    private static void generateQuestion() throws Exception {
//        try{
//            session.beginTransaction();
//            //Subject: Computer Sci ,code: 11
//            //DataSt::::::::::::::::::::::::::::::::::::::::::::::::::::;
//            String[] DataStructures1 = {"LinkedList", "Stack", "Queue", "Array"};
//            Question q1 = new Question(001, "Which data structure allows elements to be stored in a linear order and accessed using an index?", DataStructures1, 4);
//
//            String[] DataStructures2 = {"Graph", "Tree", "Heap", "Array"};
//            Question q2 = new Question(002, "Which data structure represents a hierarchical structure with a root node and child nodes?", DataStructures2, 1);
//
//            String[] DataStructures3 = {"LIFO", "FIFO", "LILO", "FILO"};
//            Question q3 = new Question(003, "What is the characteristic property of a Stack data structure?", DataStructures3, 0);
//
//            String[] DataStructures4 = {"Binary Search Tree", "Hash Table", "Heap", "Queue"};
//            Question q4 = new Question(004, "Which data structure is used to maintain a dynamic set of distinct elements?", DataStructures4, 1);
//
//            String[] DataStructures5 = {"First-In-First-Out", "Last-In-First-Out", "First-In-Last-Out", "Last-In-Last-Out"};
//            Question q5 = new Question(005, "What does FIFO stand for in the context of data structures?", DataStructures5, 0);
//
//
////OOP:::::::::::::::::::::::::::::
//            String[] OOP1 = {"Class", "Object", "Method", "Variable"};
//            Question q6 = new Question(00611, "What is the basic building block of object-oriented programming?", OOP1, 2);
//
//            String[] OOP2 = {"Inheritance", "Encapsulation", "Polymorphism", "Abstraction"};
//            Question q7 = new Question(00711, "Which concept in OOP refers to the ability of a class to inherit properties and behaviors from a parent class?", OOP2, 1);
//
//            String[] OOP3 = {"Constructor", "Initializer Block", "Method", "Finalizer"};
//            Question q8 = new Question(00811, "What is used to initialize objects in Java and is called when an object is created? ", OOP3, 1);
//
//            String[] OOP4 = {"Overloading", "Overriding", "Inheriting", "Implementing"};
//            Question q9 = new Question(00911, "What is the term for providing a specific implementation of a method in a subclass that is already defined in the parent class?", OOP4, 2);
//
//            String[] OOP5 = {"Encapsulation", "Inheritance", "Polymorphism", "Abstraction"};
//            Question q10 = new Question(01011, "Which OOP concept refers to the process of hiding the internal implementation details of an object from the outside world?", OOP5, 1);
//
//
//            //C language Questions::::::::::::::::::::::::::::
//            String[] C1 = {"float", "int", "string", "char"};
//            Question q11 = new Question(100, "Which data type is used to store real numbers with decimal points in C?", C1, 1);
//
//            String[] C2 = {"for", "while", "do-while", "loop"};
//            Question q12 = new Question(101, "Which loop is used to execute a block of code repeatedly as long as a condition is true?", C2, 2);
//
//            String[] C3 = {"scanf", "printf", "input", "output"};
//            Question q13 = new Question(102, "Which function is used to take input from the user in C?", C3, 1);
//
//            String[] C4 = {"if", "case", "when", "switch"};
//            Question q14 = new Question(103, "Which keyword is used to implement a decision-making statement in C?", C4, 4);
//
//            String[] C5 = {"malloc", "new", "allocate", "create"};
//            Question q15 = new Question(104, "Which function is used to dynamically allocate memory in C?", C5, 1);
//
//
//            //Subject: Math, code: 22
//            //DiscreteMath:
//            String[] DM1 = {"True", "False", "Sometimes true", "Not enough information"};
//            Question q16 = new Question(01622, "In discrete mathematics, a subset is always equal to the original set.", DM1, 3);
//
//            String[] DM2 = {"Vertex", "Edge", "Node", "Link"};
//            Question q17 = new Question(01722, "In graph theory, what is a point that represents a distinct object or concept?", DM2, 2);
//
//            String[] DM3 = {"Permutation", "Combination", "Arrangement", "Selection"};
//            Question q18 = new Question(01822, "In how many ways can you arrange 5 books on a shelf?", DM3, 3);
//
//            String[] DM4 = {"Proposition", "Predicate", "Function", "Equation"};
//            Question q19 = new Question(01922, "In logic, a sentence that can be evaluated as true or false is called a ____.", DM4, 2);
//
//            String[] DM5 = {"Set", "Union", "Venn Diagram", "Subset"};
//            Question q20 = new Question(02022, "What is a diagram that shows all possible logical relations between a finite collection of different sets?", DM5, 3);
//
//
////Algebra1::::::::::::::::::::::::::::
//            String[] Algebra1_1 = {"x^2 + 4x + 4", "x^2 - 4x + 4", "x^2 + 2x + 2", "x^2 - 2x + 2"};
//            Question q21 = new Question(02122, "What is the square of the binomial (x + 2)?", Algebra1_1, 1);
//
//            String[] Algebra1_2 = {"y = 2x + 5", "y = -2x + 5", "y = 2x - 5", "y = -2x - 5"};
//            Question q22 = new Question(02222, "Which equation represents a line with a slope of 2 and a y-intercept of 5?", Algebra1_2, 1);
//
//            String[] Algebra1_3 = {"6x^2 + 7x + 1", "6x^2 + 7x - 1", "6x^2 - 7x + 1", "6x^2 - 7x - 1"};
//            Question q23 = new Question(02322, "What is the factorized form of the quadratic expression 6x^2 - x - 6?", Algebra1_3, 2);
//
//            String[] Algebra1_4 = {"x = -4", "x = 4", "x = -2", "x = 2"};
//            Question q24 = new Question(02422, "What is the solution to the equation x^2 - 4 = 0?", Algebra1_4, 4);
//
//            String[] Algebra1_5 = {"5", "4", "7", "6"};
//            Question q25 = new Question(02522, "If 2x + 3 = 11, what is the value of x?", Algebra1_5, 2);
//
//            //calculus::::::::::::::::::
//
//            String[] Calculus1_1 = {"Derivative", "Integral", "Limit", "Function"};
//            Question q26 = new Question(02622, "What is the mathematical concept that represents the rate of change of a function?", Calculus1_1, 1);
//
//            String[] Calculus1_2 = {"x^2 + C", "2x + C", "x + C", "x^2 / 2 + C"};
//            Question q27 = new Question(02722, "What is the indefinite integral of 2x with respect to x?", Calculus1_2, 2);
//
//            String[] Calculus1_3 = {"lim(x -> 0) (sin(x) / x)", "lim(x -> ∞) (1 / x)", "lim(x -> 1) (e^x - 1 / x - 1)", "lim(x -> π/2) (cos(x) / sin(x))"};
//            Question q28 = new Question(02822, "Which limit represents the fundamental constant e?", Calculus1_3, 1);
//
//            String[] Calculus1_4 = {"Area under the curve", "Derivative", "Tangent line", "Concavity"};
//            Question q29 = new Question(02922, "The definite integral of a function represents what geometric concept?", Calculus1_4, 1);
//
//            String[] Calculus1_5 = {"x^3 / 3 + C", "3x^2 + C", "x^2 + C", "3x + C"};
//            Question q30 = new Question(03022, "What is the indefinite integral of 3x^2 with respect to x?", Calculus1_5, 1);
//
//
//
//            //Subject: Sports, code:33
//            //Fencing::::::::::::::::::::::::::::::::
//            String[] Fencing1 = {"Sabre", "Foil", "Épée", "Rapier"};
//            Question q31 = new Question(03133, "Which type of fencing weapon has a small bell guard and a flexible blade?", Fencing1, 2);
//
//            String[] Fencing2 = {"En garde", "Fleche", "Lunge", "Parry"};
//            Question q32 = new Question(03233, "What is the term for the forward thrust movement used to score points in fencing?", Fencing2, 3);
//
//            String[] Fencing3 = {"Italy", "France", "Spain", "Germany"};
//            Question q33 = new Question(03333, "Modern foil fencing rules were developed in which European country?", Fencing3, 2);
//
//            String[] Fencing4 = {"1920", "1896", "1900", "1936"};
//            Question q34 = new Question(03433, "In which year did fencing become an official sport in the Summer Olympics?", Fencing4, 3);
//
//            String[] Fencing5 = {"Bouting", "Parrying", "Flèche", "Riposte"};
//            Question q35 = new Question(03533, "What is the term for a practice match or friendly duel in fencing?", Fencing5, 1);
//
//
//
//            //Equestrianism::::::::::::::::::::::::::::::
//            String[] Equestrianism1 = {"Dressage", "Show Jumping", "Cross-Country", "Endurance"};
//            Question q36 = new Question(03633, "Which equestrian discipline emphasizes the precise execution of predetermined movements?", Equestrianism1, 1);
//
//            String[] Equestrianism2 = {"Gait", "Stride", "Trot", "Canter"};
//            Question q37 = new Question(03733, "What is the term for a horse's sequence of footsteps at a particular speed?", Equestrianism2, 1);
//
//            String[] Equestrianism3 = {"Saddle", "Bridle", "Stirrups", "Reins"};
//            Question q38 = new Question(03833, "What is the name of the leather straps used to control a horse's head?", Equestrianism3, 2);
//
//            String[] Equestrianism4 = {"Eventing", "Barrel Racing", "Vaulting", "Polo"};
//            Question q39 = new Question(03933, "Which equestrian discipline involves timed runs around barrels in an arena?", Equestrianism4, 1);
//
//            String[] Equestrianism5 = {"Equestrianism", "Equinology", "Hippology", "Steedology"};
//            Question q40 = new Question(04033, "What is the study of horses, including their care, behavior, and management?", Equestrianism5, 3);
//
//
//
////Rugby::::::::::::::::::::::::::::::::::::::
//            String[] Rugby1 = {"Try", "Goal", "Conversion", "Penalty"};
//            Question q41 = new Question(04133, "What is the term for scoring a touchdown by placing the ball on the ground behind the opponent's goal line?", Rugby1, 1);
//
//            String[] Rugby2 = {"Scrums", "Rucks", "Lineouts", "Mauls"};
//            Question q42 = new Question(04233, "In rugby, what is the name of the method used to restart play after a minor infringement?", Rugby2, 2);
//
//            String[] Rugby3 = {"15 players", "11 players", "13 players", "7 players"};
//            Question q43 = new Question(04333, "How many players are there on a standard rugby union team?", Rugby3, 1);
//
//            String[] Rugby4 = {"New Zealand", "Australia", "South Africa", "England"};
//            Question q44 = new Question(04433, "Which country is known for its strong rugby tradition and is often referred to as the 'All Blacks'?", Rugby4, 1);
//
//            String[] Rugby5 = {"Scrum-half", "Fly-half", "Hooker", "Flanker"};
//            Question q45 = new Question(04533, "In rugby, which position wears the number 9 jersey and often passes the ball from the scrum?", Rugby5, 1);
//
//
//
//
//
//
//
//
////      private static void generateTeacher() throws Exception {
////              ArrayList<Teacher> teachersList = new ArrayList<>();
////
////              Teacher newTeacher1 = new Teacher(123, "teacher1", "111111");
////              teachersList.add(newTeacher1);
////              session.save(newTeacher1);
////              session.flush();
////
////
////              Teacher newTeacher2 = new Teacher(1, "a", "3");
////              teachersList.add(newTeacher2);
////              session.save(newTeacher2);
////              session.flush();
////
////              Teacher newTeacher3 = new Teacher(7, "1", "2");
////              teachersList.add(newTeacher3);
////              session.save(newTeacher3);
////              session.flush();
////
////              User ab = new User(343, "999", "111", User.UserType.Teacher);
////              session.save(ab);
////              session.flush();
////
////
////      }
////
////      private static void generatePrinciple() throws Exception {
////
////              User principle = new User(9, "Principle", "123456789", User.UserType.Princiaple);
////              session.save(principle);
////              session.flush();
////              User P = new User(888, "5", "9", User.UserType.Princiaple);
////              session.save(P);
////              session.flush();
////              ;
////      }
//
//
//            public static void connectToDate()
//            {
//
//                try {
//                    session.beginTransaction();
//
//                    ArrayList<Student> studentsList = new ArrayList<>();
//
//                    Student newStudent1 = new Student(0, "ManarZoabi", "manar123");
//                    studentsList.add(newStudent1);
//                    session.save(newStudent1);
//                    session.flush();
//
//                    Student newStudent2 = new Student(3, "r", "b");
//                    studentsList.add(newStudent2);
//                    session.save(newStudent2);
//                    session.flush();
//
//                    ArrayList<Teacher> teachersList = new ArrayList<>();
//
//                    Teacher newTeacher1 = new Teacher(123, "teacher1", "111111");
//                    teachersList.add(newTeacher1);
//                    session.save(newTeacher1);
//                    session.flush();
//
//
//                    Teacher newTeacher2 = new Teacher(1, "a", "3");
//                    teachersList.add(newTeacher2);
//                    session.save(newTeacher2);
//                    session.flush();
//
//                    Teacher newTeacher3 = new Teacher(7, "1", "2");
//                    teachersList.add(newTeacher3);
//                    session.save(newTeacher3);
//                    session.flush();
//
////                      User ab = new User(343, "999", "111", User.UserType.Teacher);
////                      session.save(ab);
////                      session.flush();
////                      //generatePrinciple();
////
////                      User principle = new User(9, "manger", "123456789", User.UserType.Princiaple);
////                      session.save(principle);
////                      session.flush();
//
//                    Princiaple p = new Princiaple(545, "malki", "121");
//                    session.save(p);
//                    session.flush();
//
//
//                    Exam exam = new Exam(9, 12312, "student", "teacher", newTeacher2, "Virtual");
//                    session.save(exam);
//                    session.flush();
//
//
//                    //List<Question> questions=new ArrayList<>();
//
////                      Subject s1 = new Subject("Computer Science");
////                      Course c1s1 = new Course("Intro to CS", s1);
////                      Course c2s1 = new Course("OOP", s1);
////                      Course c3s1 = new Course("Data Structures", s1);
//                    //session.save(newExam);
//                    //session.flush();
//
//
//                    //Subject subject1=new Subject("Computer Science",)
//
//                    session.getTransaction().commit(); // Save everything.
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//
//            }
//
//            @Override
//            protected void handleMessageFromClient(Object msg, ConnectionToClient client)
//            {
//                Message message = (Message) msg;
//                String msgString = ((Message) msg).getMessage();
//
//                //session.beginTransaction();
//
//
//                if (msgString.startsWith("#warning")) {
//                    Warning warning = new Warning("Warning from server!");
//                    try {
//                        client.sendToClient(warning);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else if (msgString.equals("#LoginRequest")) {
//                    Object[] newLogin = (Object[]) message.getObject1();
//
//                    String userName = (String) newLogin[0];
//                    String userPassword = (String) newLogin[1];
//
//                    try {
//                        session.beginTransaction();
//                        User user = session.find(User.class, userName);
//                        if (user == null) {
//                            Warning warning = new Warning("User Name doesn't exist");
//                            client.sendToClient(new Message("#loginWarning", warning));
//                        } else {
//                            if (user.isConnected()) {
//                                Warning warning = new Warning("You're already connected ");
//                                client.sendToClient(new Message("#loginWarning", warning));
//                            } else {
//                                if (user.getPassword().equals(userPassword)) {
//                                    user.setConnected(true);
//                                    client.sendToClient(new Message("#LogInSuccessfully", user));
//                                    session.update(user);
//                                    session.flush();
//                                } else {
//                                    Warning warning = new Warning("password is not correct");
//                                    client.sendToClient(new Message("#loginWarning", warning));
//                                }
//                            }
//                        }
//                        session.getTransaction().commit();
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//
//                } else if(msgString.equals("#LogOut"))
//                {
//                    try
//                    {
//                        session.beginTransaction();
//                        User currUser=(User) message.getObject1();
//                        currUser.setConnected(false);
//                        session.merge(currUser);
//                        session.getTransaction().commit();
//                    }
//                    catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//
//                else if (msgString.equals("#CreateQusetionRequest")) {
//                    try {
//                        session.beginTransaction();
//                        Question newQues = (Question) message.getObject1();
//                        session.save(newQues);
//                        session.flush();
//                        session.getTransaction().commit();
//
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                } else if (msgString.equals("#SolveExam")) {
//                    try {
//                        session.beginTransaction();
//                        String examCode = (String) message.getObject1();
//                        if (!validExamCode(examCode)) {
//                            // invalid exam code
//                            Warning warning = new Warning("Invalid Exam Code");
//                            client.sendToClient(new Message("#SolveExamWarning", warning));
//                        } else {
//                            int Ncode = Integer.valueOf(examCode);
//                            //Exam exam = session.find(Exam.class, Ncode);
//                            xxxxxxxx vExam = session.find(xxxxxxxx.class, Ncode);
//                            //vExam.myPrint();
//                            //System.out.println("vode"+vExam.getCodeExam());
//                            //String examType = exam.getType();
//
//                            if (vExam == null) {
//                                // there is no exam that have this code
//                                Warning warning = new Warning("Exam Code Dose Not Exist");
//                                client.sendToClient(new Message("#SolveExamWarning", warning));
//                            }
//                            // manual exam add
//                            //else if(!(exam instanceof VirtualExam))
//                            //      {
//
//                            //Warning warning = new Warning("Manual Exam Code");
//                            //client.sendToClient(new Message("#SolveExamWarning", warning));
//
//
//                            //}
//
//                            else {
//                                System.out.println("BBBBBB");
//                                vExam.myPrint();
//                                //VirtualExam virtualExam=(VirtualExam)exam;
//                                //VirtualExam ee = new VirtualExam();
//                                //VirtualExam ww = new VirtualExam(vExam);
//
//                                client.sendToClient(new Message("#SolveExamResponse",(xxxxxxxx) vExam));
//                            }
//                        }
//                        session.getTransaction().commit();
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//
//                } else if (msgString.equals("#GetExamCopy")) {
//                    try {
//                        session.beginTransaction();
//                        Question newQues = (Question) message.getObject1();
//                        session.save(newQues);
//                        session.flush();
//                        session.getTransaction().commit();
//
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                } else if (msgString.equals("#GetGrade")) {
//                    try {
//                        session.beginTransaction();
//                        Question newQues = (Question) message.getObject1();
//                        session.save(newQues);
//                        session.flush();
//                        session.getTransaction().commit();
//
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//
//                else if (msgString.startsWith("#GetListOfStudents")){
//                    session.beginTransaction();
//
//                    List<Student> students = getAllObjects(Student.class);
//
//                    try {
//                        client.sendToClient(new Message("#ShowAllStudents", students));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                else if (msgString.startsWith("#GetListOfTeachers")) {
//
//                    session.beginTransaction();
//
//                    List<Teacher> teachers = getAllObjects(Teacher.class);
//                    for (int i = 0; i < teachers.size(); i++) {
//                        System.out.println("AAAAAAAA");
//                        System.out.println(teachers.get(i).getUserName());
//                    }
//                    try {
//                        client.sendToClient(new Message("#ShowAllTeachers", teachers));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            private boolean validExamCode(String code) {
//                if (code == null) {
//                    return false;
//                }
//                if (!isNumber(code)) {
//                    return false;
//                }
//                if (code.length() != 5) {
//                    return false;
//                }
//                return true;
//            }
//
//            public boolean isNumber(String s) {
//                char[] arr = s.toCharArray();
//                for (char c : arr) {
//                    if ((c < '0') || (c > '9')) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//
//        }
