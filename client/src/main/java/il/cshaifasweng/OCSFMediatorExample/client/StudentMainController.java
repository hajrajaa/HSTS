package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField examCodeTxt;

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
    void GetGradeButton(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetGrade", examCodeTxt.getText()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void SolveExamButton(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#SolveExam", examCodeTxt.getText()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        App.setRoot("s");

    }

    @FXML
    void initialize() {
        assert examCodeTxt != null : "fx:id=\"examCodeTxt\" was not injected: check your FXML file 'studentMain.fxml'.";

    }

}
