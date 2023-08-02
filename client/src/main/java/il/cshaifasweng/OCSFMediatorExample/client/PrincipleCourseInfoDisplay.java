package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleCourseInfoDisplay {

    @FXML
    private Label average_lbl;

    @FXML
    private BarChart<?, ?> course_hist;

    @FXML
    private TextField info_average;

    @FXML
    private TextField info_median;

    @FXML
    private Label median_lbl;

    @FXML
    private Button p_back_btn;

    @FXML
    private void initialize ()
    {

    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_courses_menu");
    }

    @FXML
    void view_average(ActionEvent event) throws IOException {

    }

    @FXML
    void view_median(ActionEvent event) throws IOException {

    }

}