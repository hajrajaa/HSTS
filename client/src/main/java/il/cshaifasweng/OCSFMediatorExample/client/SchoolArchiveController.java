package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SchoolArchiveController {

    @FXML
    private Button p_back_btn;
    @FXML
    private Button p_questions;
    @FXML
    private Button p_exam;
//    @FXML
//    private Button p_results;

    @FXML
    public void go_back(ActionEvent actionEvent) throws IOException {
        if(App.getUser().getType()== User.UserType.Teacher){
            App.setRoot("teacherMain");
        }else if(App.getUser().getType()==User.UserType.Princiaple){
            App.setRoot("principle_homepage");
        }else {
            App.setRoot("login1");
        }
    }

    @FXML
    public void view_questions(ActionEvent actionEvent) throws IOException {
        App.setRoot("questions_drawer");
    }

    @FXML
    public void view_exams(ActionEvent actionEvent) throws IOException {
        App.setRoot("exams_drawer");
    }

//    @FXML
//    public void view_results(ActionEvent actionEvent) throws IOException {
//        App.setRoot("principle_stat_menu");
//    }
}
