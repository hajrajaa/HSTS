package il.cshaifasweng.OCSFMediatorExample.client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PrincipleMenu {

    @FXML
    private Button p_back_btn;
    @FXML
    private Button p_questions;
    @FXML
    private Button p_manual;
    @FXML
    private Button p_virtual;
    @FXML
    private Button p_results;

    @FXML
    public void go_back(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_homepage");
    }

    @FXML
    public void view_questions(ActionEvent actionEvent) throws IOException {
        //App.setRoot("principle_view_questions");
    }

    @FXML
    public void view_manual(ActionEvent actionEvent) throws IOException {
        //App.setRoot("principle_view_manual_exams");
    }
    @FXML
    public void view_virtual(ActionEvent actionEvent) throws IOException {
        //App.setRoot("principle_view_virtual_exams");
    }

    @FXML
    public void view_results(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_stat_menu");
    }
}
