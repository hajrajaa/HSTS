package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TeacherMainController {

    public static User user1;

    public static User getUser1() {
        return user1;
    }

    public static void setUser1(User user) {
        TeacherMainController.user1 = user;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createExamButton;

    @FXML
    private Button createQuestionButton;

    @FXML
    private Button execExamButton;

    @FXML
    private Button seeStatisticsButton;


    @FXML
    private Button logOutBtn;

    @FXML
    void createExamButton(ActionEvent event) throws IOException {
        App.setRoot("create_exam");
    }

    @FXML
    void createQuestionButton(ActionEvent event) throws IOException {

        App.setRoot("create_question");
    }

    @FXML
    void execExamButton(ActionEvent event) throws IOException {
        App.setRoot("execute_exam");
    }

    @FXML
    void seeStatisticsButton(ActionEvent event) {

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
    void initialize() {

        setUser1(App.getUser());
        assert createExamButton != null : "fx:id=\"createExamButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert createQuestionButton != null : "fx:id=\"createQuestionButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert execExamButton != null : "fx:id=\"execExamButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert seeStatisticsButton != null : "fx:id=\"seeStatisticsButton\" was not injected: check your FXML file 'teacherMain.fxml'.";

    }

    public void ExamDrawerClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("exams_drawer");
    }
}


