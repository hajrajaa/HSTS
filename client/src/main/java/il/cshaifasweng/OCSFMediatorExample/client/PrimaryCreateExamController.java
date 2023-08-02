package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PrimaryCreateExamController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> courseBox;

    @FXML
    private Button createBtn;

    @FXML
    private ComboBox<?> subjectsBox;

    @FXML
    void courseBox(ActionEvent event) {

    }

    @FXML
    void createBtn(ActionEvent event) throws IOException
    {
        App.setRoot("ADDD");
    }

    @FXML
    void subjectsBox(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert courseBox != null : "fx:id=\"courseBox\" was not injected: check your FXML file 'create_exam1.fxml'.";
        assert createBtn != null : "fx:id=\"createBtn\" was not injected: check your FXML file 'create_exam1.fxml'.";
        assert subjectsBox != null : "fx:id=\"subjectsBox\" was not injected: check your FXML file 'create_exam1.fxml'.";



    }

}
