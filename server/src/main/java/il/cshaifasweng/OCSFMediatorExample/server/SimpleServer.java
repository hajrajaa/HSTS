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
		configuration.addAnnotatedClass(xxxxxxxx.class);
		configuration.addAnnotatedClass(yyyyyyyyyy.class);
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


			Exam exam = new Exam(9, 12312, "student", "teacher", newTeacher2, "Virtual");
			session.save(exam);
			session.flush();


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

			Course newCourse = new Course(999, "Intro");

//			cs.getCourses().add(newCourse);

			session.save(cs);
			session.save(newCourse);
			session.flush();


//			cs.setCourses(new ArrayList<Course>());
//			session.save(cs);
//			session.flush();

			//Subject cs = new Subject(17, "Computer Science");
//			cs.getCourses().add(newCourse);
//			for(int i=0; i<cs.getCourses().size(); i++){
//				System.out.println(cs.getCourses().get(i));
//			}

//			Subject mathematics = new Subject(26, "Mathematics");
//			session.save(mathematics);
//			Subject sports = new Subject(39, "Sports");
//			session.save(sports);
//			session.flush();
//
//			ArrayList<Course> cs_course = new ArrayList<>();
//			ArrayList<Course> math_course = new ArrayList<>();
//			ArrayList<Course> sports_course = new ArrayList<>();
//
//			newCourse = new Course(231, "Data Structure", cs);
//			cs_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(232, "Object Oriented Programming", cs);
//			cs_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//
//			newCourse = new Course(233, "C language", cs);
//			cs_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
////			cs.addCourse(newCourse);
////			session.update(cs);
////			session.flush();
//
//			newCourse = new Course(241, "Discrete Mathematics", mathematics);
//			math_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(242, "Calculus 1", mathematics);
//			math_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(243, "Algebra 1", mathematics);
//			math_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(251, "Fencing", sports);
//			sports_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(252, "Equestrianism", sports);
//			sports_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();
//
//			newCourse = new Course(253, "Rugby", sports);
//			sports_course.add(newCourse);
//			session.save(newCourse);
//			session.flush();

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
							client.sendToClient(new Message("#LogInSuccessfully", user));
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
			User currUser=(User) message.getObject1();
			currUser.setConnected(false);
			session.merge(currUser);
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
		else if (msgString.equals("#GetAllSubjects"))
		{
			session.beginTransaction();
			System.out.println("AA1");
			List<Subject> allSubjects = getAllObjects(Subject.class);
//			System.out.println("Printing allSubjects list:");
//			for(int i=0; i<allSubjects.size(); i++){
//				System.out.println(allSubjects.get(i));
//				for(int k=0; k<allSubjects.get(i).getCourses().size(); k++){
//					System.out.println(allSubjects.get(i).getCourses().get(k));
//				}
//			}
			try {
//				Subject ss = new Subject();
//				ss.setSubName(allSubjects.get(0).getSubName());
//				ss.setCourses(allSubjects.get(0).getCourses());

//				Subject w = new Subject(17, "w");
//				Course ww = new Course(999, "www");
//				w.getCourses().add(ww);


				System.out.println("AA2");
				client.sendToClient(new Message("#GetAllSubjectsResponce", allSubjects));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
		}
		System.out.println("AAx");
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



