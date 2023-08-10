package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentMainController {



    public static User user1;

    public static User getUser1() {
        return user1;
    }

    public static void setUser1(User user) {
        StudentMainController.user1 = user;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField examCodeTxt;

    @FXML
    private Button logOutBtn;

    @FXML
    void GetExamCopyButton(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetExamCopy", examCodeTxt.getText()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void GetGradeButton(ActionEvent event) throws IOException {
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#GetGrade", examCodeTxt.getText()));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        App.setRoot("get_grade");
    }

    @FXML
    void SolveExamButton(ActionEvent event) throws IOException {
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#SolveExam", examCodeTxt.getText()));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        App.setRoot("solve_exam_enter");

    }

    @FXML
    void logOutBtn(ActionEvent event)
    {
        try {
            SimpleClient.getClient().sendToServer(new Message("#LogOut", user1));
            App.setRoot("login1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void initialize()
    {
        setUser1(App.getUser());
        assert examCodeTxt != null : "fx:id=\"examCodeTxt\" was not injected: check your FXML file 'studentMain.fxml'.";
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'studentMain.fxml'.";

    }

}
