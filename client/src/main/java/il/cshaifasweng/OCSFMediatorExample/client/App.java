package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;


    private  User user;

    private   User getUser() {
        return user;
    }

    private   void setUser(User user) {
        this.user = user;
    }


    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("login1"), 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    	
    }

    @Subscribe
    public void loginEventfunc(loginEvent event)
    {
        setUser(event.getUser());
        chnageScene();

    }

    private void chnageScene()
    {
        String fxmlFile;
        switch (user.getType())
        {
            case Student:
                fxmlFile="studentMain";
                break;

            case Teacher:
                fxmlFile="teacherMain";
                break;

            case Principal:
                fxmlFile="principalMain";
                break;
            default:
                return;
        }

        Platform.runLater(()->{
            try
            {
                scene.setRoot(loadFXML(fxmlFile));
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        );
    }

	public static void main(String[] args) {
        launch();
    }

}