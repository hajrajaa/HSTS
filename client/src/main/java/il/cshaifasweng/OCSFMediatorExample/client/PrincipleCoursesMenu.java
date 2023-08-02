package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleCoursesMenu {

    @FXML
    private Label courses_lbl;

    @FXML
    private Label enter_course_lbl;

    @FXML
    private Button p_back_btn;

    @FXML
    private Button p_clear_btn;

    @FXML
    private Button p_enter_btn;

    @FXML
    private TextField p_enter_course;

    @FXML
    private Button p_view_btn;

    @FXML
    private ListView<?> view_course;

    @FXML
    void clear_search(ActionEvent event) {
        //////////////////////////
    }

    @FXML
    void confirm_course_enter(ActionEvent event) {
        ////////////////////////
    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_stat_menu");
    }

    @FXML
    void view_course_exam(ActionEvent event) throws IOException {
        ///////////////////////
        App.setRoot("principle_course_info_display");
    }

}
