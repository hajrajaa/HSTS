package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer extends AbstractServer {

	private static Session session;

	public SimpleServer(int port) {
		super(port);
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		connectToDate();
	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Teacher.class);
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(ExecutedExam.class);
		configuration.addAnnotatedClass(ExecutedExamInfo.class);
		configuration.addAnnotatedClass(ExamQuestion.class);
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(ExecutedManual.class);
		configuration.addAnnotatedClass(ExecutedVirtual.class);
		configuration.addAnnotatedClass(Message.class);
		configuration.addAnnotatedClass(Course.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Princiaple.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static <T> List<T> getAllObjects(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(object);
		Root<T> rootEnter = query.from(object);
		query.select(rootEnter);
		query.where(builder.isNotNull(rootEnter.get("id")));

		TypedQuery<T> queries = session.createQuery(query);
		return queries.getResultList();
	}

	private static void generateTeacher() throws Exception {
		ArrayList<Teacher> teachersList = new ArrayList<>();

		Teacher newTeacher1 = new Teacher(123, "teacher1", "111111");
		teachersList.add(newTeacher1);
		session.save(newTeacher1);
		session.flush();


		Teacher newTeacher2 = new Teacher(1, "a", "3");
		teachersList.add(newTeacher2);
		session.save(newTeacher2);
		session.flush();

		Teacher newTeacher3 = new Teacher(7, "1", "2");
		teachersList.add(newTeacher3);
		session.save(newTeacher3);
		session.flush();

		User ab = new User(343, "999", "111", User.UserType.Teacher);
		session.save(ab);
		session.flush();


	}

	private static void generatePrinciple() throws Exception {

		User principle = new User(9, "Principle", "123456789", User.UserType.Princiaple);
		session.save(principle);
		session.flush();
		User P = new User(888, "5", "9", User.UserType.Princiaple);
		session.save(P);
		session.flush();
		;
	}

	private static void generateStudents() throws Exception {
		ArrayList<Student> studentsList = new ArrayList<>();

		Student newStudent1 = new Student(0, "ManarZoabi", "manar123");
		studentsList.add(newStudent1);
		session.save(newStudent1);
		session.flush();

		Student newStudent2 = new Student(3, "r", "b");
		studentsList.add(newStudent2);
		session.save(newStudent2);
		session.flush();

	}

	public static void connectToDate()
	{

		try {
			session.beginTransaction();

			ArrayList<Student> studentsList = new ArrayList<>();

			Student newStudent1 = new Student(0, "Manar Zoabi", "manar123");
			studentsList.add(newStudent1);
			session.save(newStudent1);
			session.flush();

			Student newStudent2 = new Student(3, "r", "b");
			studentsList.add(newStudent2);
			session.save(newStudent2);
			session.flush();

			ArrayList<Teacher> teachersList = new ArrayList<>();

			Teacher newTeacher1 = new Teacher(123, "teacher1", "111111");
			teachersList.add(newTeacher1);
			session.save(newTeacher1);
			session.flush();


			Teacher newTeacher2 = new Teacher(1, "Ms. Tina", "123");
			teachersList.add(newTeacher2);
			session.save(newTeacher2);
			session.flush();

			Teacher newTeacherX = new Teacher(33, "a", "3");
			teachersList.add(newTeacherX);
			session.save(newTeacherX);
			session.flush();

			Teacher newTeacher3 = new Teacher(7, "1", "2");
			teachersList.add(newTeacher3);
			session.save(newTeacher3);
			session.flush();

			User ab = new User(343, "999", "111", User.UserType.Teacher);
			session.save(ab);
			session.flush();
			//generatePrinciple();

			User principle = new User(9, "manger", "123456789", User.UserType.Princiaple);
			session.save(principle);
			session.flush();

			Princiaple p = new Princiaple(545, "Principle Malki", "121");
			session.save(p);
			session.flush();


//			Exam exam = new Exam(9, 12312, "student", "teacher", newTeacher2, "Virtual");
//			session.save(exam);
//			session.flush();


			//List<Question> questions=new ArrayList<>();

//			Subject s1 = new Subject("Computer Science");
//			Course c1s1 = new Course("Intro to CS", s1);
//			Course c2s1 = new Course("OOP", s1);
//			Course c3s1 = new Course("Data Structures", s1);
			//session.save(newExam);
			//session.flush();

	//////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////// Subjects and Courses ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

			Subject cs = new Subject(17, "Computer Science");
			Subject math = new Subject(26, "Mathematics");
			Subject sports = new Subject(39, "Sports");
			session.save(cs);
			session.save(math);
			session.save(sports);
			session.flush();

			///////////////////////////////////// CS Courses ///////////////////////////////////////

			Course C_language = new Course(31, "C language", cs);
			session.save(C_language);
			session.flush();

			Course DS = new Course(32, "Data Structure", cs);
			session.save(DS);
			session.flush();

			Course OOP = new Course(33, "Object Oriented Programming", cs);
			session.save(OOP);
			session.flush();

			///////////////////////////////////// Math Courses ///////////////////////////////////////

			Course dmath = new Course(41, "Discrete Mathematics", math);
			session.save(dmath);
			session.flush();

			Course calculus = new Course(42, "Calculus 1", math);
			session.save(calculus);
			session.flush();

			Course algebra = new Course(43, "Algebra 1", math);
			session.save(algebra);
			session.flush();

			///////////////////////////////////// Sports Courses ///////////////////////////////////////

			Course newCourse = new Course(51, "Fencing", sports);
			session.save(newCourse);
			session.flush();

			newCourse = new Course(52, "Equestrianism", sports);
			session.save(newCourse);
			session.flush();

			newCourse = new Course(53, "Rugby", sports);
			session.save(newCourse);
			session.flush();

			//////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////// Questions ////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////

			//////////////////////////////////////// Data Structures /////////////////////////////////////////

			ArrayList<Course> DS_Courses = new ArrayList<>();
			DS_Courses.add(C_language);
			DS_Courses.add(DS);
			String[] DataStructures1 = {"LinkedList", "Stack", "Queue", "Array"};
			Question q1 = new Question(001, "Which data structure allows elements to be stored in a linear order and accessed using an index?", DataStructures1, 4, DS_Courses);
			session.save(q1);

			String[] DataStructures2 = {"Graph", "Tree", "Heap", "Array"};
			Question q2 = new Question(002, "Which data structure represents a hierarchical structure with a root node and child nodes?", DataStructures2, 2, DS);
			session.save(q2);

			String[] DataStructures3 = {"LIFO", "FIFO", "LILO", "FILO"};
			Question q3 = new Question(003, "What is the characteristic property of a Stack data structure?", DataStructures3, 1, DS);
			session.save(q3);

			session.flush();

			////////////////////////////////////////// C language ///////////////////////////////////////////

			String[] C1 = {"float", "int", "string", "char"};
			Question q11 = new Question(100, "Which data type is used to store real numbers with decimal points in C?", C1, 1, C_language);
			session.save(q11);

			String[] C2 = {"for", "while", "do-while", "loop"};
			Question q12 = new Question(101, "Which loop is used to execute a block of code repeatedly as long as a condition is true?", C2, 2, C_language);
			session.save(q12);

			String[] C3 = {"input", "printf", "scanf", "output"};
			Question q13 = new Question(102, "Which function is used to take input from the user in C?", C3, 3, C_language);
			session.save(q13);

			String[] C4 = {"if", "case", "when", "switch"};
			Question q14 = new Question(103, "Which keyword is used to implement a decision-making statement in C?", C4, 4, C_language);
			session.save(q14);

			ArrayList<Course> list2 = new ArrayList();
			list2.add(C_language);
			list2.add(OOP);
			String[] C5 = {"malloc", "new", "allocate", "create"};
			Question q15 = new Question(104, "Which function is used to dynamically allocate memory in C?", C5, 1, list2);
			session.save(q15);

			session.flush();

			////////////////////////////////////////////// OOP ///////////////////////////////////////////////

			ArrayList<Course> list3 = new ArrayList();
			list3.add(C_language);
			list3.add(OOP);
			list3.add(DS);
			String[] C6 = {"O(1)", "O(log n)", "O(n log n)", "O(n)"};
			Question q20 = new Question(222, "What is the best Time Complexity in witch we can Sort an Array?", C6, 3, list3);
			session.save(q20);

			session.flush();

			//////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////// Exams //////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////

			ExamQuestion eq1 = new ExamQuestion(q11, 10, "", "We learned this in the first lecture");
			ExamQuestion eq2 = new ExamQuestion(q13, 15, "from OOP", "");
			ExamQuestion eq3 = new ExamQuestion(q1, 20, "from DS", "");
			ExamQuestion eq4 = new ExamQuestion(q20, 30, "from DS and OOP", "You learned this in many courses");
			ExamQuestion eq5 = new ExamQuestion(q15, 25, "", "");

			Exam exam1 = new Exam(55, "Intro Exam", 30, "", "", newTeacherX, C_language);
			exam1.addExamQuestion(eq1);
			exam1.addExamQuestion(eq2);
			exam1.addExamQuestion(eq3);
			exam1.addExamQuestion(eq4);
			exam1.addExamQuestion(eq5);

			session.save(eq1);
			session.save(eq2);
			session.save(eq3);
			session.save(eq4);
			session.save(eq5);
			session.save(exam1);
			session.flush();

			//////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////// Exams 2 /////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////

			String[] Calculus1_1 = {"Derivative", "Integral", "Limit", "Function"};
			Question q26 = new Question(622, "What is the mathematical concept that represents the rate of change of a function?", Calculus1_1, 1, calculus);

			String[] Calculus1_2 = {"x^2 + C", "2x + C", "x + C", "x^2 / 2 + C"};
			Question q27 = new Question(722, "What is the indefinite integral of 2x with respect to x?", Calculus1_2, 2, calculus);

			String[] Calculus1_3 = {"lim(x -> 0) (sin(x) / x)", "lim(x -> ∞) (1 / x)", "lim(x -> 1) (e^x - 1 / x - 1)", "lim(x -> π/2) (cos(x) / sin(x))"};
			Question q28 = new Question(822, "Which limit represents the fundamental constant e?", Calculus1_3, 1, calculus);

			String[] Calculus1_4 = {"Area under the curve", "Derivative", "Tangent line", "Concavity"};
			Question q29 = new Question(922, "The definite integral of a function represents what geometric concept?", Calculus1_4, 1, calculus);

			String[] Calculus1_5 = {"x^3 / 3 + C", "3x^2 + C", "x^2 + C", "3x + C"};
			Question q30 = new Question(122, "What is the indefinite integral of 3x^2 with respect to x?", Calculus1_5, 1, calculus);

			ExamQuestion x1 = new ExamQuestion(q26, 10, "", "");
			ExamQuestion x2 = new ExamQuestion(q27, 15, "", "");
			ExamQuestion x3 = new ExamQuestion(q28, 20, "", "");
			ExamQuestion x4 = new ExamQuestion(q29, 30, "", "Think Hard!");
			ExamQuestion x5 = new ExamQuestion(q30, 25, "", "");

			Exam exam2 = new Exam(66, "Calculus 1 Final Exam", 5, "", "", newTeacher2, calculus);
			exam2.addExamQuestion(x1);
			exam2.addExamQuestion(x2);
			exam2.addExamQuestion(x3);
			exam2.addExamQuestion(x4);
			exam2.addExamQuestion(x5);

			session.save(q26);
			session.save(q27);
			session.save(q28);
			session.save(q29);
			session.save(q30);
			session.save(x1);
			session.save(x2);
			session.save(x3);
			session.save(x4);
			session.save(x5);
			session.save(exam2);
			session.flush();

			/// Bshara ///

//			ExecutedExam ex1 = new ExecutedExam(1,newStudent1,"15/8/2023","10:00",90,true,exam1,true);
//			session.save(ex1);
//			session.flush();
//
//			newStudent1.addExam1(ex1);
//			session.merge(newStudent1);
//			session.flush();
//			int[] hist1 = {1,2,0,4,2,1,2,4,5,2,2};
//
//			int[] hist2 = {2,1,2,1,2,4,1,3,3,6,2};
//
//			ExecutedExamInfo moeda = new ExecutedExamInfo(173155,"1234","Discrete Mathematics",64,60,ExecutedExamInfo.ExamType.Virtual,hist1);
//			session.save(moeda);
//			session.flush();
//
//			ExecutedExamInfo moedb = new ExecutedExamInfo(173155,"6789","Discrete Mathematics B",70,65,ExecutedExamInfo.ExamType.Virtual,hist2);
//			session.save(moedb);
//			session.flush();
//
//			dmath.addExam(exam1);
//			session.save(dmath);
//			session.flush();
//
//			newTeacherX.addExecutedExamInfo(moeda);
//			session.merge(newTeacherX);
//
//			ExecutedExamInfo moedc = new ExecutedExamInfo(1122,"4545","Discrete Mathematics",64,60,ExecutedExamInfo.ExamType.Virtual,newTeacherX);
//			moeda.addExecutedExam(ex1);
//			session.save(moedc);
//			session.flush();
//
//			newTeacherX.addExecutedExamInfo(moedb);
//			session.merge(newTeacherX);

			session.getTransaction().commit(); // Save everything.

		}
		catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {
		Message message = (Message) msg;
		String msgString = message.getMessage();
		System.out.println("MessageFromClient: " + msgString);
		//session.beginTransaction();

		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.equals("#LoginRequest")) {
			Object[] newLogin = (Object[]) message.getObject1();

			String userName = (String) newLogin[0];
			String userPassword = (String) newLogin[1];

			try {
				session.beginTransaction();
				User user = session.find(User.class, userName);
				if (user == null) {
					Warning warning = new Warning("User Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				} else {
					if (user.isConnected()) {
						Warning warning = new Warning("You're already connected ");
						client.sendToClient(new Message("#loginWarning", warning));
					} else {
						if (user.getPassword().equals(userPassword)) {
							user.setConnected(true);
							client.sendToClient(new Message("#LogInSuccessfully", copyUser(user)));
							session.update(user);
							session.flush();
						} else {
							Warning warning = new Warning("password is not correct");
							client.sendToClient(new Message("#loginWarning", warning));
						}
					}
				}
				session.getTransaction().commit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		else if (msgString.equals("#GetUserRequest")) {
			Object[] newLogin = (Object[]) message.getObject1();

			String userName = (String) newLogin[0];
			String userPassword = (String) newLogin[1];

			try {
				session.beginTransaction();
				User user = session.find(User.class, userName);
				if (user == null) {
					Warning warning = new Warning("User Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				} else {
					if (!user.isConnected()) {
						Warning warning = new Warning("User Not Connected");
						client.sendToClient(new Message("#loginWarning", warning));
					} else {
						if (user.getPassword().equals(userPassword)) {
							client.sendToClient(new Message("#GetUserResponce", user));
						} else {
							Warning warning = new Warning("password is not correct");
							client.sendToClient(new Message("#loginWarning", warning));
						}
					}
				}
				session.getTransaction().commit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		else if(msgString.equals("#LogOut"))
		{
			try
			{
				session.beginTransaction();
				User currUser = (User) message.getObject1();
				String userName = currUser.getUserName();
				User user = session.find(User.class, userName);
				if(user != null){
					user.setConnected(false);
					session.merge(user);
				}else{
					Warning warning = new Warning("Can't Log Out");
					client.sendToClient(new Message("#loginWarning", warning));
				}

				session.getTransaction().commit();
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (msgString.equals("#CreateQusetionRequest")) {
			try {
				session.beginTransaction();
				Question newQues = (Question) message.getObject1();
				session.save(newQues);
				session.flush();
				session.getTransaction().commit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		 else if (msgString.equals("#GetExamCopy")) {
			try {
				session.beginTransaction();
				Question newQues = (Question) message.getObject1();
				session.save(newQues);
				session.flush();
				session.getTransaction().commit();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (msgString.equals("#GetGrade")) {
			try {
				session.beginTransaction();
				Question newQues = (Question) message.getObject1();
				session.save(newQues);
				session.flush();
				session.getTransaction().commit();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (msgString.equals("#GetListOfStudents")){
			System.out.println("GetListOfStudents");
			session.beginTransaction();

			List<Student> students = getAllObjects(Student.class);
			ArrayList<Student> res = new ArrayList<>();
			for(Student s : students){
				res.add((Student)copyUser(s));
			}

			try {
				client.sendToClient(new Message("#ShowAllStudents", res));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetListOfTeachers"))
		{
			System.out.println("GetListOfTeachers");
			session.beginTransaction();

			List<Teacher> teachers = getAllObjects(Teacher.class);
			ArrayList<Teacher> res = new ArrayList<>();
			for(Teacher s : teachers){
				res.add((Teacher)copyUser(s));
			}

			for (int i = 0; i < teachers.size(); i++) {
				System.out.println("AAAAAAAA");
				System.out.println(teachers.get(i).getUserName());
			}
			try {
				client.sendToClient(new Message("#ShowAllTeachers", res));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetListOfCourses"))
		{
			System.out.println("GetListOfCourses");
			session.beginTransaction();

			List<Course> teachers = getAllObjects(Course.class);
			ArrayList<Course> res = new ArrayList<>();
			for(Course s : teachers){
				res.add((Course)copyCourse(s));
			}

			for (int i = 0; i < teachers.size(); i++) {
				System.out.println("AAAAAAAA");
				System.out.println(teachers.get(i).getCourseName());
				System.out.println(res.get(i).getCourseName());
			}
			try {
				client.sendToClient(new Message("#ShowAllCourses", res));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetInfoOfCourse"))
		{
			System.out.println("GetInfoOfCourse");
			session.beginTransaction();
			int name = (int)message.getObject1();
			System.out.println(name);
			Course courses = session.find(Course.class,name);
			List<ExecutedExamInfo> info = getAllObjects(ExecutedExamInfo.class);
			List<ExecutedExamInfo> filtered = new ArrayList<>();
			List<Exam> courseexams = new ArrayList<>();
			for(Exam s : courses.getExamsList()){
				System.out.println(s.getTitle());
				System.out.println(s.getCodeExam());
			}

			for(ExecutedExamInfo i : info){
				System.out.println(i.getCode());
			}
			System.out.println("THE END");


			for(Exam s : courses.getExamsList()){
				for(ExecutedExamInfo i : info){
					if(s.getCodeExam()==i.getCode()){
						System.out.println("hello dod");
						filtered.add(new ExecutedExamInfo(i));
					}
				}
			}
//			for(ExecutedExamInfo i : info){
//				for(Exam e : courseexams){
//					if(e.getCodeExam()==i.getCode()){
//						filtered.add(new ExecutedExamInfo(i));
//					}
//				}
//			}
//			System.out.println("AAAAAAAA");
//			for (int i = 0; i < courseexams.size(); i++) {
//
//				System.out.println(courses.get(i).getCourseName());
//			}
			System.out.println("AAAAAAAA");
			try {
				client.sendToClient(new Message("#ShowAllCoursesInfo", filtered));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#TeacherExamInfoList"))
		{
			System.out.println("TeacherExamInfoList");
			session.beginTransaction();

			String userName = (String) message.getObject1();

			System.out.println(userName);

			Teacher user = session.find(Teacher.class, userName);

			ArrayList<ExecutedExamInfo> exams_list = new ArrayList<ExecutedExamInfo>();
			ArrayList<ExecutedExamInfo> list= new ArrayList<>(user.getExecutedExamsInfo());

			System.out.println("AAAAAAA");

			for (ExecutedExamInfo e : list){
				System.out.println("bbbbbbbb");
				exams_list.add(new ExecutedExamInfo(e));
			}
			System.out.println("CCC");

			if(user.getExecutedExamsInfo()==null)
			{
				System.out.println("SADSADSADSAD");
			}
			else if(user.getExecutedExamsInfo()!=null)
			{
				System.out.println("YAYYYYYY");
				System.out.println(user.getExecutedExamsInfo().get(0).getCode());
				System.out.println(exams_list.get(0).getCode());
			}


			try {
				client.sendToClient(new Message("#ShowAllTeachersExamInfo", exams_list));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#StudentsExecutedExams"))
		{
			session.beginTransaction();

			String userName = (String) message.getObject1();

			System.out.println(userName);

			Student user = session.find(Student.class, userName);

			System.out.println(user.getUserName());
			System.out.println(user.getMyExams());

			ArrayList<ExecutedExam> exams_list = new ArrayList<ExecutedExam>();
			ArrayList<ExecutedExam> list= new ArrayList<>(user.getMyExams());

			for (ExecutedExam e : list){
				exams_list.add(new ExecutedExam(e));
			}

			try {
				client.sendToClient(new Message("#ShowStudentExecutedExams", exams_list));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#TeacherExamInfoDetails"))
		{
			System.out.println("TeacherExamInfoDetails");
			session.beginTransaction();

			int[] codes = (int[]) message.getObject1();

			int code1 = codes[0];
			int code2 = codes[1];

			System.out.println(code1);
			System.out.println(code2);

			ExecutedExamInfo user1 = session.find(ExecutedExamInfo.class, code1);
			ExecutedExamInfo user2 = session.find(ExecutedExamInfo.class, code2);

			System.out.println(user1.getCode());
			System.out.println(user2.getCode());
//
//			List<Teacher> teachers = getAllObjects(Teacher.class);
//
			ExecutedExamInfo displayInfo1 = new ExecutedExamInfo(user1);
			ExecutedExamInfo displayInfo2 = new ExecutedExamInfo(user2);
//			ArrayList<ExecutedExamInfo> list= new ArrayList<>(user.getExecutedExamsInfo());
      
			ExecutedExamInfo[] infoarray = {user1,user2};

			System.out.println(infoarray[0].getCode());
			System.out.println(infoarray[1].getCode());
			System.out.println("BBBBBBB");

			try {
				client.sendToClient(new Message("#ShowTeachersExamInfoDetails", infoarray));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#DoesExamCodeExist")) {
			session.beginTransaction();
			int codeExam = (int) message.getObject1();
			Exam exam = session.find(Exam.class, codeExam);
			if (exam == null) {
// there is no exam that have this code
				Warning warning = new Warning("Exam Code Dose Not Exist");
				try {
					client.sendToClient(new Message("#DoesCodeExistWarning", warning));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					client.sendToClient(new Message("#CodeForETExists", codeExam));
					System.out.println(exam.getCodeExam());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.getTransaction().commit();
		}
		else if(msgString.equals("#drawExamRequest"))
		{
			try {
				session.beginTransaction();
				ExecutedExamInfo newExecExam = (ExecutedExamInfo) message.getObject1();
				Exam exam = session.find(Exam.class, newExecExam.getCode());
				if(exam != null){
					newExecExam.setTitle(exam.getTitle());
				}
				Teacher teacher = session.find(Teacher.class, newExecExam.getExecutingTeacher().getUserName());
				if(teacher != null){
					newExecExam.setTeacher(teacher);
				}
				session.save(newExecExam);
				session.merge(teacher);
				session.flush();

				client.sendToClient(new Message("#drawExamRes"));
				session.getTransaction().commit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(msgString.equals("#StartSolveExam"))
		{
			Object[] newExecExam = (Object[]) message.getObject1();
			int examCode = (int) Integer.valueOf((String) newExecExam[0]);
			String examPassword = (String) newExecExam[1];

			boolean codeExist=false;
			boolean passwordExist=false;
			try {
				session.beginTransaction();
				List<ExecutedExamInfo> executedExamInfoList=getAllObjects(ExecutedExamInfo.class);

				for (ExecutedExamInfo ex : executedExamInfoList){
					if(ex.getCode() == examCode)
					{
						codeExist=true;
						if(ex.getPassword().equals(examPassword))
						{
							passwordExist = true;
							Exam currExam = session.find(Exam.class,examCode);
							Exam newExam = new Exam(currExam);
							ExecutedExamInfo.ExamType examType = ex.getType();
							int ExamInfoID = ex.getId();
							System.out.println(examType);
							Object[] obj = {newExam,examType,ExamInfoID};
							client.sendToClient(new Message("#StartSolveSuccessfully",obj));
						}
					}
				}
				if(!codeExist)
				{
					Warning warning = new Warning("Exam Code Doesn't Exist");
					client.sendToClient(new Message("#StartSolveWarning", warning));
				}
				else if(!passwordExist){
					Warning warning = new Warning("Exam Password Is Not Correct");
					client.sendToClient(new Message("#StartSolveWarning", warning));
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			session.getTransaction().commit();
		}
		else if(msgString.equals("#GetTeacherAllExams"))
		{
				session.beginTransaction();
				String userName = (String) message.getObject1();
				Teacher teacher = session.find(Teacher.class,userName);
				if(teacher != null){
					ArrayList<Exam> WrittenExamsList = new ArrayList<>(teacher.getExams());
					ArrayList<ExecutedExamInfo> allInfo = new ArrayList<>(getAllObjects(ExecutedExamInfo.class));
					ArrayList<ExecutedExamInfo> writtenExamsInfoList = new ArrayList<ExecutedExamInfo>();
					for (Exam exam : WrittenExamsList){
						writtenExamsInfoList.addAll(findInfoByExamCode(allInfo, exam.getCodeExam()));
					}
					System.out.println(writtenExamsInfoList.size() + " 3+++++++");

					List<ExecutedExamInfo> list = teacher.getExecutedExamsInfo();
					ArrayList<ExecutedExamInfo> executedExamInfoList = new ArrayList<>();
					for(ExecutedExamInfo eq : list){
						executedExamInfoList.add(new ExecutedExamInfo(eq));
					}
					System.out.println(executedExamInfoList.size() + " 4+++++++");

					Object [] obj = {writtenExamsInfoList, executedExamInfoList};
					try {
						client.sendToClient(new Message("#GetTeacherAllExams_Replay", obj));
					} catch (IOException e) {
						e.printStackTrace();
					}
					session.getTransaction().commit();
				}
		}
		else if (msgString.equals("#GetAllExcutedExam"))
		{
//			session.beginTransaction();
//			String userName=(String) message.getObject1();
//			Teacher teacher=session.find(Teacher.class,userName);
//			List<ExecutedExamInfo> executedExamInfoList=teacher.getExecutedExamsInfo();
//			ArrayList<ExecutedExamInfo> res = new ArrayList<>();
//			for(ExecutedExamInfo eq : executedExamInfoList){
//				res.add(new ExecutedExamInfo(eq));
//			}
//			try {
//				client.sendToClient(new Message("#GetAllExcutedExamRes", res));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

		else if (msgString.equals("#GetExcutedExams"))
		{
			session.beginTransaction();
			int currId=(int)message.getObject1();
			ExecutedExamInfo realInfo = session.find(ExecutedExamInfo.class, currId);
			List<ExecutedExam> executedExams=realInfo.getExecutedExamList();
			ArrayList<ExecutedExam> copyexecutedExams=new ArrayList<>();
			for(ExecutedExam ex:executedExams)
			{
				copyexecutedExams.add(new ExecutedExam(ex));
			}

			try {
				client.sendToClient(new Message("#GetExcutedExamRes", copyexecutedExams));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if (msgString.equals(("#GetWrittenExams")))
		{
			session.beginTransaction();
			int currId=(int)message.getObject1();
			System.out.println(currId);
			ExecutedExamInfo realInfo = session.find(ExecutedExamInfo.class, currId);
			System.out.println(realInfo);
			System.out.println(realInfo.getCode());

			List<ExecutedExam> executedExams=realInfo.getExecutedExamList();
			ArrayList<ExecutedExam> copyexecutedExams=new ArrayList<>();
			for(ExecutedExam ex:executedExams)
			{
				copyexecutedExams.add(new ExecutedExam(ex));
			}

			try {
				client.sendToClient(new Message("#GeWrittenExamRes", copyexecutedExams));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(msgString.equals("#UpdateGradeRequest"))
		{
			Object[] obj = (Object[]) message.getObject1();
			int id =(int)obj[0];
			double newGrade=(double) obj[1];
			String explanation=(String) obj[2];
			ExecutedExam currExam = session.find(ExecutedExam.class, id);
			try {
				session.beginTransaction();
				if (!isGrade(newGrade)) {
					Warning warning = new Warning("Invalid Grade");
					client.sendToClient(new Message("#UpdateGradeWarning", warning));
				} else if (explanation == null) {
					Warning warning = new Warning("Please Enter An Explanation");
					client.sendToClient(new Message("#UpdateGradeWarning", warning));
				} else {
					currExam.setGrade(newGrade);
					ExecutedExam copyExam=new ExecutedExam(currExam);
					client.sendToClient(new Message("#UpdateGradeSuccessfully", copyExam));
					session.update(currExam);
					session.flush();

				}
				session.getTransaction().commit();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		else if (msgString.equals("#ApproveExamGradeReq"))
		{

			int currId=(int)message.getObject1();
			try
			{
				session.beginTransaction();
				ExecutedExam currExam = session.find(ExecutedExam.class, currId);
				if(currExam!=null)
				{
					if(!currExam.isMarked())
					{
						currExam.setMarked(true);
						Warning warning = new Warning("Grade approved Successfully");
						client.sendToClient(new Message("#GradeApprovedSuccessfully", warning));
						session.update(currExam);
						session.flush();
					}
					else
					{
						Warning warning = new Warning("Grade is already approved");
						client.sendToClient(new Message("#ApproveGradeWarning", warning));
					}

				}
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
			session.getTransaction().commit();
		}

		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////// Michel //////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		else if (msgString.equals("#GetAllSubjects"))
		{
			session.beginTransaction();
			List<Subject> allSubjects = getAllObjects(Subject.class);

			try {
				ArrayList<Subject> myAllSubjects = new ArrayList<>();
				for (int i=0; i<allSubjects.size(); i++){
					myAllSubjects.add(copySubject(allSubjects.get(i)));
				}

				client.sendToClient(new Message("#GetAllSubjectsResponce", myAllSubjects));
				session.getTransaction().commit();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetAllSubjectsNames")) ///
		{
			session.beginTransaction();
			List<Subject> list = getAllObjects(Subject.class);

			try {
				ArrayList<String> allNames = new ArrayList<>();
				for (Subject s : list){
					allNames.add(s.getSubName());
				}
				client.sendToClient(new Message("#getAllSubjectsNames_Replay", allNames));
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetAllCoursesBySubject")) ///
		{
			session.beginTransaction();
			String name = (String) message.getObject1();
			List<Subject> list = getAllObjects(Subject.class);
			Subject subject = null;
			for(Subject s : list){
				if(s.getSubName().equals(name)){
					subject = s;
					break;
				}
			}
			try {
				if(subject != null){
					ArrayList<String> allNames = new ArrayList<>();
					for (Course c : subject.getCourses()){
						allNames.add(c.getCourseName());
					}
					client.sendToClient(new Message("#GetAllCoursesBySubject_Replay", allNames));
				}else{
					Warning warning = new Warning("Subject Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				}
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetAllExamsByCourse")) ///
		{
			session.beginTransaction();
			String name = (String) message.getObject1();
			System.out.println("GetAllExamsByCourse   1");
			List<Course> list = getAllObjects(Course.class);
			Course course = null;
			System.out.println("GetAllExamsByCourse   2");
			for(Course c : list){
				if(c.getCourseName().equals(name)){
					course = c;
					break;
				}
			}
			System.out.println("GetAllExamsByCourse   3");
			try {
				if(course != null){
					System.out.println("GetAllExamsByCourse   4");
					ArrayList<Exam> allExams = new ArrayList<>();
					for (Exam e : course.getExamsList()){
						System.out.println("GetAllExamsByCourse   --> "+e.getTitle());
						allExams.add(new Exam(e));
						System.out.println("GetAllExamsByCourse   <-- "+e.getTitle());
					}
					System.out.println("GetAllExamsByCourse   5");
					client.sendToClient(new Message("#GetAllExamsByCourse_Replay", allExams));
				}else{
					Warning warning = new Warning("Course Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				}
				System.out.println("GetAllExamsByCourse   xx");
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#GetAllQuestionsByCourse")) ///
		{
			session.beginTransaction();
			String name = (String) message.getObject1();
			List<Course> list = getAllObjects(Course.class);
			Course course = null;
			for(Course c : list){
				if(c.getCourseName().equals(name)){
					course = c;
					break;
				}
			}
			try {
				if(course != null){
					ArrayList<Question> allQuestions = new ArrayList<>();
					for (Question q : course.getQuestions()){
						allQuestions.add(new Question(q));
					}
					client.sendToClient(new Message("#GetAllQuestionsByCourse_Replay", allQuestions));
				}else{
					Warning warning = new Warning("Course Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				}
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#CreateNewQusetion")) ///
		{
			session.beginTransaction();
			Question question = (Question) message.getObject1();
			ArrayList<Course> courses = new ArrayList<>(question.getCoursesList());

			ArrayList<Course> allCoursesList = new ArrayList<>(getAllObjects(Course.class));
			for(Course c : allCoursesList){ // get all courses from copies
				int index = findCourseIndex(courses, c.getCourseName());
				if(index != -1){
					courses.set(index, c);
				}
			}
			question.resetCoursesList();
			for(Course c : courses){question.addCourse(c);}

			session.save(question);
			session.flush();

			for(Course c : courses){
				System.out.println(c.getCourseName());
				session.merge(c);
				session.flush();
			}
			client.sendToClient(new Message("#successAlert", "Question Saved"));
			session.getTransaction().commit();
		}
		else if (msgString.equals("#CreateNewExam")) ///
		{
			System.out.println("p1");
			session.beginTransaction();
			Object [] data = (Object []) message.getObject1();
			System.out.println("p2");

			SimpleExam simpleExam = (SimpleExam) data[0];
			System.out.println("p3");
			ArrayList<ExamQuestion> allExamQuestions = new ArrayList<>((List<ExamQuestion>) data[1]);
			System.out.println("p4");

			String teacherName = simpleExam.getTeacherName();
			Teacher teacher = session.find(Teacher.class, teacherName);
			System.out.println("p5");

			String courseName = simpleExam.getCourseName();
			Course course = null;
			System.out.println("p6");

			ArrayList<Course> allCoursesList = new ArrayList<>(getAllObjects(Course.class));
			for(Course c : allCoursesList){
				if(c.getCourseName().equals(courseName)){
					course = c;
					break;
				}
			}
			System.out.println("p66 --> " + course.getCourseName());

			Exam exam = new Exam(simpleExam, teacher, course);
			System.out.println("p7");

			if(allExamQuestions != null)
			{
				System.out.println("p7 -s-");
				for(ExamQuestion eq : allExamQuestions){
					Question question = session.find(Question.class, eq.getQuestion().getCode());
					if(question != null){
						System.out.println("p7 ---> "+question.getQuestion());
						ExamQuestion examQuestion = new ExamQuestion(question, eq.getPoints(), eq.getTeacher_note(), eq.getStudent_note());
						System.out.println("p7 --->   A");
						exam.addExamQuestion(examQuestion);
						System.out.println("p7 --->   B");
						session.save(examQuestion);
						System.out.println("p7 <---s "+question.getQuestion());
					}
				}
				System.out.println("p7 -f-");
			}

			session.save(exam);
			System.out.println("p8");
			session.flush();
			System.out.println("ppx");
			try
			{
				client.sendToClient(new Message("#successAlert", "Exam Saved"));
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
			session.getTransaction().commit();
		}
		else if (msgString.equals("#newExecutedVirtualExam")) //
		{
			session.beginTransaction();
			System.out.println("v1");
			ExecutedVirtual vExam = (ExecutedVirtual) message.getObject1();
			System.out.println("v2");
			boolean inTime = vExam.isSubmitInTime();

			Student student = session.find(Student.class, vExam.getStudentName());
			System.out.println("v3");
			if (student == null){
				Warning warning = new Warning("Error1! Could Not Save Exam");
				client.sendToClient(new Message("#loginWarning", warning));
			}else{
				System.out.println("v4");
				ExecutedExamInfo eInfo = session.find(ExecutedExamInfo.class, vExam.getInfoID());
				if (eInfo == null){
					Warning warning = new Warning("Error2! Could Not Save Exam");
					client.sendToClient(new Message("#loginWarning", warning));
				}else{
					System.out.println("v5");
					Exam exam = session.find(Exam.class, eInfo.getCode());
					if (exam == null){
						Warning warning = new Warning("Error3! Could Not Save Exam");
						client.sendToClient(new Message("#loginWarning", warning));
					}else{
						System.out.println("v6");
						vExam.setStudent(student);
						System.out.println("v7");
						vExam.setExam(exam);
						System.out.println("v8");
						vExam.setExecutedExamInfo(eInfo);
						System.out.println("v9");
						try
						{
							System.out.println("v10");
							session.save(vExam);
							System.out.println("v11");
							session.flush();
							System.out.println("v12");

							if(inTime){
								client.sendToClient(new Message("#successAlert", "The Exam Has Been Finished And Saved In Time"));
							}else {
								Warning warning = new Warning("Time Is Up! The Exam Has Been Automatically Saved");
								client.sendToClient(new Message("#loginWarning", warning));
							}
						}
						catch (Exception e1) {
							e1.printStackTrace();
						}

					}
				}
			}
			System.out.println("vx");
			session.getTransaction().commit();
		}

	} /////////////////////////////////////////////////////////////////////////////////////////////////////

	private int findCourseIndex (ArrayList<Course> list, String courseName)
	{
		int index = -1;
		for(Course c : list){
			index++;
			if(c.getCourseName().equals(courseName)){
				return index;
			}
		}
		return -1;
	}

	public User copyUser (User u)
	{
		User temp;
		if(u.getType() == User.UserType.Student)
		{
			temp = new Student(u.getId(), u.getUserName(), u.getPassword());
		}
		else if(u.getType() == User.UserType.Teacher)
		{
			temp = new Teacher(u.getId(), u.getUserName(), u.getPassword());
			temp.setConnected(u.isConnected());

		}
		else if(u.getType() == User.UserType.Princiaple)
		{
			temp = new Princiaple(u.getId(), u.getUserName(), u.getPassword());
		}
		else {
			temp = new User();
		}
		return temp;
	}

	public Course copyCourse (Course c)
	{
		Course newCourse = new Course (c.getId(), c.getCourseName());
		return newCourse;
	}

	public Subject copySubject (Subject s)
	{
		Subject newSubject = new Subject (s.getId(), s.getSubName());
		if(s.getCourses() != null){
			for(int i=0; i<s.getCourses().size(); i++){
				Course c = copyCourse(s.getCourses().get(i));
				c.setSubject(newSubject);
			}
		}
		return newSubject;
	}

	private ArrayList<ExecutedExamInfo> findInfoByExamCode (ArrayList<ExecutedExamInfo> list, int code)
	{
		ArrayList<ExecutedExamInfo> res = new ArrayList<>();
		for(ExecutedExamInfo ei : list){
			if(ei.getCode() == code){
				System.out.println(ei.getCode());
				res.add(new ExecutedExamInfo(ei));
			}
		}
		return res;
	}

	private boolean validExamCode(String code) {
		if (code == null) {
			return false;
		}
		if (!isNumber(code)) {
			return false;
		}
		if (code.length() != 5) {
			return false;
		}
		return true;
	}

	public boolean isNumber(String s) {
		char[] arr = s.toCharArray();
		for (char c : arr) {
			if ((c < '0') || (c > '9')) {
				return false;
			}
		}
		return true;
	}

	public boolean isGrade(double grade)
	{
		return (grade>=0 && grade <=100) ;
	}
}



