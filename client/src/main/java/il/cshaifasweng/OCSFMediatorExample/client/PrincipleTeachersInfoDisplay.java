package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleTeachersInfoDisplay {

    private static ExecutedExamInfo[] infoArray;

    @FXML
    private Label average_lbl;

    @FXML
    private Label average_lbl2;

    @FXML
    private TextField info_average;

    @FXML
    private TextField info_average2;

    @FXML
    private TextField info_median;

    @FXML
    private TextField info_median2;

    @FXML
    private Label median_lbl;

    @FXML
    private Label median_lbl2;

    @FXML
    private Button p_back_btn;

    @FXML
    private BarChart<?, ?> teacher_exam_hist;


    @FXML
    private void initialize () throws IOException {

        if (infoArray == null) {
            infoArray = App.getTeacherExamInfo();
            if (infoArray == null) {
                System.out.println("Student list is not available!");
                //return; // Abort initialization if the list is not available
            }
        }
        if (infoArray != null) {
            ExecutedExamInfo exam1 = infoArray[0];
            ExecutedExamInfo exam2 = infoArray[1];
            System.out.println(exam1.getCode());
            System.out.println(exam2.getCode());
        }
    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_teacher_exam_menu");
    }

    public static ExecutedExamInfo[] getInfoArray() {
        return infoArray;
    }

    public static void setInfoArray(ExecutedExamInfo[] infoArray) {
        PrincipleTeachersInfoDisplay.infoArray = infoArray;
    }

}
