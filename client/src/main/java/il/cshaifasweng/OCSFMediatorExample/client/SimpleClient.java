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

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
