package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TeacherMainController {

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
    void createExamButton(ActionEvent event) throws IOException {
        App.setRoot("create_exam1");
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
    void initialize() {
        assert createExamButton != null : "fx:id=\"createExamButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert createQuestionButton != null : "fx:id=\"createQuestionButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert execExamButton != null : "fx:id=\"execExamButton\" was not injected: check your FXML file 'teacherMain.fxml'.";
        assert seeStatisticsButton != null : "fx:id=\"seeStatisticsButton\" was not injected: check your FXML file 'teacherMain.fxml'.";

    }

}


