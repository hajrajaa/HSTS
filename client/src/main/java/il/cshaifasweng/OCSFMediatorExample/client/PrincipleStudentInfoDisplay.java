package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleStudentInfoDisplay {

    @FXML
    private Label avg_lbl;

    @FXML
    private Label grade_list_lbl;

    @FXML
    private ListView<?> grade_list_view;

    @FXML
    private Button p_back_btn;

    @FXML
    private TextField student_avg;

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_students_menu");
    }

}
