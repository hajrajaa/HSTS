package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TeacherMainController {

    public static User user1;

    public static User getUser1() {
        return user1;
    }

    public static void setUser1(User user) {
        TeacherMainController.user1 = user;
    }

    @FXML
    Text welcome_text;

    @FXML
    private Button createQuestionButton, createExamButton, ExamsDrawerButton, seeStatisticsButton;

    @FXML
    private Button logOutBtn;

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
        welcome_text.setText("Welcome\n" + App.getUser().getUserName());

        setUser1(App.getUser());
        assert createExamButton != null : "fx:id=\"createExamButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert createQuestionButton != null : "fx:id=\"createQuestionButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert seeStatisticsButton != null : "fx:id=\"seeStatisticsButton\" was not injected: check your FXML file 'teacherMain.fxml'.";

    }

    @FXML
    void createQuestionButton(ActionEvent event) throws IOException {
        App.setRoot("create_question");
    }

    @FXML
    void createExamButton(ActionEvent event) throws IOException {
        App.setRoot("create_exam");
    }

    public void ExamDrawerClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("exams_drawer");
    }

    @FXML
    void seeStatisticsButton(ActionEvent event) {

    }

    public void create_question_in(MouseEvent mouseEvent) {
        App.setButtonColor(createQuestionButton, "green");
    }
    public void create_question_out(MouseEvent mouseEvent) {
        App.setButtonColor(createQuestionButton, "orange");
    }

    public void create_exam_in(MouseEvent mouseEvent) {
        App.setButtonColor(createExamButton, "green");
    }
    public void create_exam_out(MouseEvent mouseEvent) {
        App.setButtonColor(createExamButton, "orange");
    }

    public void statistics_in(MouseEvent mouseEvent) {
        App.setButtonColor(seeStatisticsButton, "green");
    }
    public void statistics_out(MouseEvent mouseEvent) {
        App.setButtonColor(seeStatisticsButton, "orange");
    }

    public void exams_drawer_in(MouseEvent mouseEvent) {
        App.setButtonColor(ExamsDrawerButton, "green");
    }
    public void exams_drawer_out(MouseEvent mouseEvent) {
        App.setButtonColor(ExamsDrawerButton, "orange");
    }
}