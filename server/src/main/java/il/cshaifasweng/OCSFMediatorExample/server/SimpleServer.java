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

	private static final SessionFactory sessionFactory=getSessionFactory();
	private static Session session;

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

	private static <T> List<T> getAllObjects(Class<T> object){
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(object);
		Root<T> rootEnter= query.from(object);
		CriteriaQuery<T> criteriaQueries = query.select(rootEnter);

		TypedQuery<T> queries=session.createQuery(criteriaQueries);
		return queries.getResultList();
	}


	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message=(Message)msg;
		String msgString = ((Message) msg).getMessage().toString();

		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.equals("#LoginRequest"))
		{
			User newLogin = (User) message.getObject1();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String userName=newLogin.getName();
			String userPassword=newLogin.getPassword();
			try
			{
			List<User> usersList=getAllObjects(User.class);

			boolean userExist=false;
			for(User user: usersList)
			{
				    if(user.getName().equals(userName)) {
					userExist = true;
					if (user.isConnected())
					{
						Warning warning = new Warning("You're already connected ");
						client.sendToClient(new Message("#loginWarning", warning));
						return;
					}
					else
					{
						if (user.getPassword().equals(userPassword))
						{
							user.setConnected(true);
							client.sendToClient(new Message("#LogISuccessfully", user));
							session.update(user);
							session.flush();
							session.getTransaction().commit();
							session.close();
						}
						else
						{
							Warning warning = new Warning("password is not correct");
							client.sendToClient(new Message("#loginWarning", warning));
							return;
						}

					}

				}
			}
			if(!userExist)
			{
				Warning warning=new Warning("User Name doesn't exist");
				client.sendToClient(new Message("#loginWarning",warning));
			}
		}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
	   }
	}



	private  static  void generateTeacher() throws  Exception
	{
		ArrayList<Teacher> teachersList = new ArrayList<>();

		Teacher newTeacher1 = new Teacher(123 , "teacher1","111111");
		teachersList.add(newTeacher1);
		session.save(newTeacher1);


		Teacher newTeacher2=new Teacher(1,"a","3");
		teachersList.add(newTeacher2);
		session.save(newTeacher2);

		session.flush();
	}

	private static void generatePrinciple() throws Exception{

		User principle= new User(9,"Principle","123456789", User.type.Principal);
		session.save(principle);
		session.flush();
	}

	private static void generateStudents() throws Exception
	{
		ArrayList<Student> studentsList = new ArrayList<>();

		Student newStudent1 = new Student(0,"ManarZoabi","manar123");
		studentsList.add(newStudent1);
		session.save(newStudent1);

		Student newStudent2 = new Student(3,"r","b");
		studentsList.add(newStudent2);
		session.save(newStudent2);


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
//	try {			SessionFactory sessionFactory = getSessionFactory();
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
