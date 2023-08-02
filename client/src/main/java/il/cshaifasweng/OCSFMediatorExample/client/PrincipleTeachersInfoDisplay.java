package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleTeachersInfoDisplay {

    @FXML
    private Label average_lbl;

    @FXML
    private TextField info_average;

    @FXML
    private TextField info_median;

    @FXML
    private Label median_lbl;

    @FXML
    private Button p_back_btn;

    @FXML
    private BarChart<?, ?> teacher_exam_hist;

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_teacher_exam_menu");
    }

}
