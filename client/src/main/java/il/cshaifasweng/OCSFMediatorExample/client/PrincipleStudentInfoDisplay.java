package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import il.cshaifasweng.OCSFMediatorExample.client.PrincipleStudentsMenu;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrincipleStudentInfoDisplay {

    private static Student student = PrincipleStudentsMenu.getSelectedStudent();

    public static ArrayList<ExecutedExam> getExec_ex() {
        return exec_ex;
    }

    public static void setExec_ex(ArrayList<ExecutedExam> exec_ex) {
        PrincipleStudentInfoDisplay.exec_ex = exec_ex;
    }

    private static ArrayList<ExecutedExam> exec_ex;

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
    private TableView<ExecutedExam> grade_table;

    @FXML
    private TableColumn<ExecutedExam, String> title_col;

    @FXML
    private TableColumn<ExecutedExam, String> date_col;

    @FXML
    private TableColumn<ExecutedExam, String> grade_col;

    @FXML
    private void initialize () throws IOException {
        System.out.println("we got here!!!!!!");
        if (exec_ex == null) {
            exec_ex = App.getStudentExecutedExamsList();
            if (exec_ex == null) {
                System.out.println("Student list is not available!");
                return; // Abort initialization if the list is not available
            }
            System.out.println(App.getStudentExecutedExamsList().get(0).getTitle());
            System.out.println(exec_ex.get(0).getExamNum());
            System.out.println(exec_ex.get(0).getTitle());
        }

        int[] numbers = new int[11];
        numbers[0]=0;
        numbers[1]=0;
        numbers[2]=0;
        numbers[3]=0;
        numbers[4]=0;
        numbers[5]=0;
        numbers[6]=0;
        numbers[7]=0;
        numbers[8]=0;
        numbers[9]=0;
        numbers[10]=0;

        List<Double> integerList = new ArrayList<>();

        double avg=0;
        if(exec_ex != null) {
            System.out.println("HAHAHAHAHAHAHAHAHAHA");
            for (int i=0;i<exec_ex.size();i++) {
                System.out.println("QQQQQQQ");
                avg += exec_ex.get(i).getGrade();
                integerList.add(exec_ex.get(i).getGrade());
                numbers[(int)exec_ex.get(i).getGrade()/10]++;
            }

            Double[] grades = integerList.toArray(new Double[0]);

            // Print the elements of the array
            for (Double num : grades) {
                System.out.print(num + " ");
            }

            Arrays.sort(grades);
            int n=grades.length;
            double result = 0;

            if (n % 2 == 1) {
                result = grades[n / 2];
            } else {
                double mid1 = grades[(n - 1) / 2];
                double mid2 = grades[n / 2];
                result = (mid1 + mid2) / 2.0;
            }

            String aavg1 = String.format("%f", result);
            student_avg1.setText(aavg1);

            avg = avg/exec_ex.size();
            String aavg = String.format("%f", avg);
            student_avg.setText(aavg);
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

        ObservableList<ExecutedExam> names = FXCollections.observableArrayList(exec_ex);
        grade_table.setItems(names);

        title_col.setCellValueFactory(new PropertyValueFactory<ExecutedExam,String>("title"));
        grade_col.setCellValueFactory(new PropertyValueFactory<ExecutedExam,String>("grade"));
        date_col.setCellValueFactory(new PropertyValueFactory<ExecutedExam,String>("examDate"));
    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_student_menu");
    }

}
