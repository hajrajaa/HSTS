package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.event.ActionEvent;
import il.cshaifasweng.OCSFMediatorExample.client.PrincipleStudentsMenu;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.IOException;

public class PrincipleStudentInfoDisplay {

    private static Student student = PrincipleStudentsMenu.getSelectedStudent();

//    private static int grades =

    @FXML
    private Label avg_lbl;

    @FXML
    private Label avg_lbl1;

    @FXML
    private Label grade_list_lbl;

    @FXML
    private Button p_back_btn;

    @FXML
    private TextField student_avg;

    @FXML
    private TextField student_avg1;

    @FXML
    private BarChart<String, Double> student_hist;

    @FXML
    private TableView<?> grade_table;

    @FXML
    private TableColumn<?, ?> title_col;

    @FXML
    private TableColumn<?, ?> date_col;

    @FXML
    private TableColumn<?, ?> grade_col;

    @FXML
    private void initialize () throws IOException {
        int[] numbers = new int[11];
        numbers[0]=2;
        numbers[1]=2;
        numbers[2]=2;
        numbers[3]=2;
        numbers[4]=2;
        numbers[5]=2;
        numbers[6]=2;
        numbers[7]=2;
        numbers[8]=2;
        numbers[9]=2;
        numbers[10]=2;

        double avg=0;
        if(student.getMyExams()!=null) {
            for (int i=0;i<student.getMyExams().size();i++) {
                avg += student.getMyExams().get(i).getGrade();
                numbers[(int)student.getMyExams().get(i).getGrade()/10]++;
            }
            avg = avg/student.getMyExams().size();
            student_avg.setText(student.getUserName());
        }
        else {
            student_avg.setText("-");
        }
        student_hist.setAnimated(false);
        XYChart.Series<String, Double> series = new XYChart.Series();
        series.setName("grades");
        series.getData().add(new XYChart.Data("0-10",numbers[0]));
        series.getData().add(new XYChart.Data("10-19",numbers[1]));
        series.getData().add(new XYChart.Data("20-29",numbers[2]));
        series.getData().add(new XYChart.Data("30-39",numbers[3]));
        series.getData().add(new XYChart.Data("40-49",numbers[4]));
        series.getData().add(new XYChart.Data("50-59",numbers[5]));
        series.getData().add(new XYChart.Data("60-69",numbers[6]));
        series.getData().add(new XYChart.Data("70-79",numbers[7]));
        series.getData().add(new XYChart.Data("80-89",numbers[8]));
        series.getData().add(new XYChart.Data("90-100",numbers[9]+numbers[10]));

        student_hist.getData().add(series);
    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_student_menu");
    }

}
