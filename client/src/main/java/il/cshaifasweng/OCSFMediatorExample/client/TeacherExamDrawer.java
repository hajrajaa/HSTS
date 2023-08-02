package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeacherExamDrawer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane coursesBox;

    @FXML
    private ComboBox<?> examsBox;

    @FXML
    private TextField execCodeTxt;

    @FXML
    private Button executeBtn;

    @FXML
    private RadioButton manualTypeBtn;

    @FXML
    private RadioButton virtualTypeBtn;

    @FXML
    void executeBtn(ActionEvent event) {

    }

    @FXML
    void manualTypeBtn(ActionEvent event) {

    }

    @FXML
    void virtualTypeBtn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert coursesBox != null : "fx:id=\"coursesBox\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert examsBox != null : "fx:id=\"examsBox\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert execCodeTxt != null : "fx:id=\"execCodeTxt\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert executeBtn != null : "fx:id=\"executeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert manualTypeBtn != null : "fx:id=\"manualTypeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert virtualTypeBtn != null : "fx:id=\"virtualTypeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";

    }

}
