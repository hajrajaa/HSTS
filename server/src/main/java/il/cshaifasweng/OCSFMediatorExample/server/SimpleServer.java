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
//		configuration.addAnnotatedClass(xxxxxxxx.class);
//		configuration.addAnnotatedClass(yyyyyyyyyy.class);
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

			Course C_language = new Course(231, "C language", cs);
			session.save(C_language);
			session.flush();

			Course DS = new Course(232, "Data Structure", cs);
			session.save(DS);
			session.flush();

			Course OOP = new Course(233, "Object Oriented Programming", cs);
			session.save(OOP);
			session.flush();

			///////////////////////////////////// Math Courses ///////////////////////////////////////

			Course dmath = new Course(241, "Discrete Mathematics", math);
			session.save(dmath);
			session.flush();

			Course calculus = new Course(242, "Calculus 1", math);
			session.save(calculus);
			session.flush();

			Course algebra = new Course(243, "Algebra 1", math);
			session.save(algebra);
			session.flush();

			///////////////////////////////////// Sports Courses ///////////////////////////////////////

			Course newCourse = new Course(251, "Fencing", sports);
			session.save(newCourse);
			session.flush();

			newCourse = new Course(252, "Equestrianism", sports);
			session.save(newCourse);
			session.flush();

			newCourse = new Course(253, "Rugby", sports);
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
			Question q1 = new Question(001, "Which data structure allows elements to be stored in a linear order and accessed using an index?", DataStructures1, 3, DS_Courses);
			session.save(q1);

			String[] DataStructures2 = {"Graph", "Tree", "Heap", "Array"};
			Question q2 = new Question(002, "Which data structure represents a hierarchical structure with a root node and child nodes?", DataStructures2, 1, DS);
			session.save(q2);

			String[] DataStructures3 = {"LIFO", "FIFO", "LILO", "FILO"};
			Question q3 = new Question(003, "What is the characteristic property of a Stack data structure?", DataStructures3, 0, DS);
			session.save(q3);

			session.flush();

			////////////////////////////////////////// C language ///////////////////////////////////////////

			String[] C1 = {"float", "int", "string", "char"};
			Question q11 = new Question(100, "Which data type is used to store real numbers with decimal points in C?", C1, 0, C_language);
			session.save(q11);

			String[] C2 = {"for", "while", "do-while", "loop"};
			Question q12 = new Question(101, "Which loop is used to execute a block of code repeatedly as long as a condition is true?", C2, 1, C_language);
			session.save(q12);

			String[] C3 = {"input", "printf", "scanf", "output"};
			Question q13 = new Question(102, "Which function is used to take input from the user in C?", C3, 2, C_language);
			session.save(q13);

			String[] C4 = {"if", "case", "when", "switch"};
			Question q14 = new Question(103, "Which keyword is used to implement a decision-making statement in C?", C4, 3, C_language);
			session.save(q14);

			ArrayList<Course> list2 = new ArrayList();
			list2.add(C_language);
			list2.add(OOP);
			String[] C5 = {"malloc", "new", "allocate", "create"};
			Question q15 = new Question(104, "Which function is used to dynamically allocate memory in C?", C5, 0, list2);
			session.save(q15);

			session.flush();

			////////////////////////////////////////////// OOP ///////////////////////////////////////////////

			ArrayList<Course> list3 = new ArrayList();
			list3.add(C_language);
			list3.add(OOP);
			list3.add(DS);
			String[] C6 = {"O(1)", "O(log n)", "O(n log n)", "O(n)"};
			Question q20 = new Question(2000, "What is the best Time Complexity in witch we can Sort an Array?", C6, 2, list3);
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

			Exam exam1 = new Exam(55, "Intro Exam", 30, "", "", newTeacherX, cs.getCourses().get(0));
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

			session.getTransaction().commit(); // Save everything.
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
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
		} else if (msgString.equals("#SolveExam")) {
//			try {
//				session.beginTransaction();
//				String examCode = (String) message.getObject1();
//				if (!validExamCode(examCode)) {
//					// invalid exam code
//					Warning warning = new Warning("Invalid Exam Code");
//					client.sendToClient(new Message("#SolveExamWarning", warning));
//				} else {
//					int Ncode = Integer.valueOf(examCode);
//					//Exam exam = session.find(Exam.class, Ncode);
//					xxxxxxxx vExam = session.find(xxxxxxxx.class, Ncode);
//					//vExam.myPrint();
//					//System.out.println("vode"+vExam.getCodeExam());
//					//String examType = exam.getType();
//
//					if (vExam == null) {
//						// there is no exam that have this code
//						Warning warning = new Warning("Exam Code Dose Not Exist");
//						client.sendToClient(new Message("#SolveExamWarning", warning));
//					}
//					// manual exam add
//					//else if(!(exam instanceof VirtualExam))
//					//	{
//
//					//Warning warning = new Warning("Manual Exam Code");
//					//client.sendToClient(new Message("#SolveExamWarning", warning));
//
//
//					//}
//
//					else {
//						System.out.println("BBBBBB");
//						vExam.myPrint();
//						//VirtualExam virtualExam=(VirtualExam)exam;
//						//VirtualExam ee = new VirtualExam();
//						//VirtualExam ww = new VirtualExam(vExam);
//
//						client.sendToClient(new Message("#SolveExamResponse",(xxxxxxxx) vExam));
//					}
//				}
//				session.getTransaction().commit();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}

		} else if (msgString.equals("#GetExamCopy")) {
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
		else if (msgString.startsWith("#GetListOfStudents")){
			session.beginTransaction();

			List<Student> students = getAllObjects(Student.class);

			try {
				client.sendToClient(new Message("#ShowAllStudents", students));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#GetListOfTeachers"))
		{
			session.beginTransaction();

			List<Teacher> teachers = getAllObjects(Teacher.class);
			for (int i = 0; i < teachers.size(); i++) {
				System.out.println("AAAAAAAA");
				System.out.println(teachers.get(i).getUserName());
			}
			try {
				client.sendToClient(new Message("#ShowAllTeachers", teachers));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
					ArrayList<Exam> allExams = new ArrayList<>();
					for (Exam e : course.getExamsList()){
						allExams.add(new Exam(e));
					}
					client.sendToClient(new Message("#GetAllExamsByCourse_Replay", allExams));
				}else{
					Warning warning = new Warning("Course Name doesn't exist");
					client.sendToClient(new Message("#loginWarning", warning));
				}
				session.getTransaction().commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
}



