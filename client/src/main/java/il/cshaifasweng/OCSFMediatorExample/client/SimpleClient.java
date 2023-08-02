package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.client.loginEvent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.io.IOException;
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
		System.out.println("mesaageeee"+messageSt);

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
		else if(messageSt.equals("#SolveExamResponse"))
		{
			System.out.println("MCCCC");
			VirtualExam exam=(VirtualExam) message.getObject1();
			SolveExamEvent newEvent=new SolveExamEvent(exam);
			Platform.runLater(()->{
						System.out.println("MDDD");
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

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
