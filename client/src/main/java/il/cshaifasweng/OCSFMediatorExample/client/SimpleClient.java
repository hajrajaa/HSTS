package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.client.loginEvent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.io.IOException;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg)
	{

		String message = ((Message) msg).getMessage();
		if (message.equals("#LogISuccessfully"))
		{
			User user = (User) ((Message) msg).getObject1();
			loginEvent newEvent= new loginEvent(user);
			EventBus.getDefault().post(newEvent);
		}
		else if (message.equalsIgnoreCase("#loginWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message)msg).getObject1()));
		}

	}


	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
