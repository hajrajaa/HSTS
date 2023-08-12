package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg)
	{
		Message message = (Message) msg;
		String messageSt = message.getMessage();
		System.out.println("message: "+messageSt);

		if (messageSt.equals("#LogInSuccessfully"))
		{
			User user = (User) message.getObject1();
			loginEvent newEvent= new loginEvent(user);
			Platform.runLater(()->{
			EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#loginWarning"))
		{
			Platform.runLater(()->{
			EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if (messageSt.equals("#GetUserResponce"))
		{
			User user = (User) message.getObject1();
			getUserEvent newEvent = new getUserEvent(user);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if(messageSt.equals("#SolveExamResponse"))
		{

			Exam exam=(Exam) message.getObject1();
			SolveExamEvent newEvent=new SolveExamEvent(exam);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);

		}
		else if (messageSt.equals("#SolveExamWarning"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}

		else if (messageSt.equals("#ShowAllStudents"))
		{
			List<Student> students=(List<Student>) message.getObject1();
			for(int i=0;i<students.size();i++)
			{
				System.out.println(students.get(i).getUserName());
			}
			ShowStudentsEvent newEvent= new ShowStudentsEvent(students);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}

		else if (messageSt.equals("#ShowAllTeachers"))
		{
			List<Teacher> teachers=(List<Teacher>) message.getObject1();
			for(int i=0;i<teachers.size();i++)
			{
				System.out.println(teachers.get(i).getUserName());
			}
			ShowTeachersEvent newEvent= new ShowTeachersEvent(teachers);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#GetAllSubjectsResponce"))
		{
			List<Subject> allSubjects = (List<Subject>) message.getObject1();
			EventGetAllSubjects newEvent = new EventGetAllSubjects(allSubjects);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#getAllSubjectsNames_Replay"))
		{
			List<String> list = (List<String>) message.getObject1();
			EventGetAllSubjectsNames newEvent = new EventGetAllSubjectsNames(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if (messageSt.equals("#GetAllCoursesBySubject_Replay"))
		{
			List<String> list = (List<String>) message.getObject1();
			EventGetAllCoursesBySubject newEvent = new EventGetAllCoursesBySubject(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if (messageSt.equals("#GetAllExamsByCourse_Replay"))
		{
			List<Exam> list = (List<Exam>) message.getObject1();
			EventGetAllExamsByCourse newEvent = new EventGetAllExamsByCourse(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
