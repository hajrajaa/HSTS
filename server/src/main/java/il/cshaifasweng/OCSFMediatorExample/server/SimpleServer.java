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

import java.io.IOException;
import java.util.ArrayList;

public class SimpleServer extends AbstractServer {

	private static Session session;

	//private static final SessionFactory sessionFactory=getSessionFactory();

	public SimpleServer(int port) {
		super(port);
		
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
		configuration.addAnnotatedClass(VirtualExam.class);
		configuration.addAnnotatedClass(ManualExam.class);
		configuration.addAnnotatedClass(ExecutedManual.class);
		configuration.addAnnotatedClass(ExecutedVirtual.class);
		configuration.addAnnotatedClass(Message.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}


	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private  static  void generateTeacher() throws  Exception
	{
		ArrayList<Teacher> teachersList = new ArrayList<>();

		Teacher newTeacher = new Teacher(123 , "teacher1","111111");
		teachersList.add(newTeacher);
		session.save(newTeacher);
		session.flush();
	}

	private static void generatePrinciple() throws Exception{

		User princable= new User(111,"Principle","123456789","Principle");
	}

	private static void generateStudents() throws Exception
	{
		ArrayList<Student> studentsList = new ArrayList<>();

		Student newStudent = new Student(0,"ManarZoabi","manar123");
		studentsList.add(newStudent);
		session.save(newStudent);
		session.flush();
	}

	public void connectToDate() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			generatePrinciple();
			generateTeacher();
			generateStudents();
			session.getTransaction().commit(); // Save everything.
		} catch (Exception exception) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occurred, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	public void connectToDate()
//	{
//		try {
//			SessionFactory sessionFactory = getSessionFactory();
//			session = sessionFactory.openSession();
//			session.beginTransaction();
//			generatePrinciple();
//			generateTeacher();
//			generateStudents();
//			session.close();
//			session.getTransaction().commit(); // Save everything.
//
//		} catch (Exception exception) {
//			if (session != null) {
//				session.getTransaction().rollback();
//			}
//			System.err.println("An error occured, changes have been rolled back.");
//			exception.printStackTrace();
//		} finally {
//			session.close();
//			session.getSessionFactory().close();
//		}
//	}

}
