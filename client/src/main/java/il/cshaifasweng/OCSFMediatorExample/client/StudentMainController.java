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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StudentMainController {



    public static User user1;

    public static User getUser1() {
        return user1;
    }

    public static void setUser1(User user) {
        StudentMainController.user1 = user;
    }

    @FXML
    Text welcome_text;

    @FXML
    private Button solveExam_Button, getGrade_Button, examCopy_Button, logOutBtn;

    @FXML
    void GetExamCopyButton(ActionEvent event) {
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#GetExamCopy", examCodeTxt.getText()));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

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
        welcome_text.setText("Welcome\n" + App.getUser().getUserName());

        setUser1(App.getUser());
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'studentMain.fxml'.";

    }

    public void solve_exam_in(MouseEvent mouseEvent) {
        App.setButtonColor(solveExam_Button, "green");
    }
    public void solve_exam_out(MouseEvent mouseEvent) {
        App.setButtonColor(solveExam_Button, "orange");
    }

    public void get_grade_in(MouseEvent mouseEvent) {
        App.setButtonColor(getGrade_Button, "green");
    }
    public void get_grade_out(MouseEvent mouseEvent) {
        App.setButtonColor(getGrade_Button, "orange");
    }

    public void exam_copy_in(MouseEvent mouseEvent) {
        App.setButtonColor(examCopy_Button, "green");
    }
    public void exam_copy_out(MouseEvent mouseEvent) {
        App.setButtonColor(examCopy_Button, "orange");
    }

}
