package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class PrincipleTeacherExamMenu {

    @FXML
    private Label exams_lbl;

    @FXML
    private Button p_back_btn;

    @FXML
    private ListView<?> p_exams_list;

    @FXML
    private Button p_view_teacher_exams;

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_menu");
    }

    @FXML
    void view_teacher_exam_stats(ActionEvent event) throws IOException {
        App.setRoot("principle_teacher_info_display");
        ////////////////////////////////
    }

}
