package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrincipleCourseInfoDisplay {

    private static ExecutedExamInfo exam1;

    private static ExecutedExamInfo exam2;

    @FXML
    private Label average_lbl;

    @FXML
    private Label average_lbl1;

    @FXML
    private BarChart<String, Double> course_hist1;

    @FXML
    private TextField info_average;

    @FXML
    private TextField info_average1;

    @FXML
    private TextField info_average2;

    @FXML
    private TextField info_median;

    @FXML
    private Label median_lbl;

    @FXML
    private Label median_lbl1;

    @FXML
    private Button p_back_btn;

    @FXML
    private void initialize ()
    {
        exam2 = PrincipleCourseInfoList.getChosenInfo2();
        exam1 = PrincipleCourseInfoList.getChosenInfo();

        System.out.println(exam1.getCode());
        System.out.println(exam2.getCode());

        String avg1 = String.format("%f", exam1.getAverage());
        String avg2 = String.format("%f", exam1.getMedian());
        String avg3 = String.format("%f", exam2.getAverage());
        String avg4 = String.format("%f", exam2.getMedian());

        info_average.setText(avg1);
        info_median.setText(avg2);
        info_average1.setText(avg3);
        info_average2.setText(avg4);

        course_hist1.setAnimated(false);
        XYChart.Series<String, Double> series = new XYChart.Series();
        series.setName("Exam 1 Grades");
        series.getData().add(new XYChart.Data("0-10",exam1.getHist()[0]));
        series.getData().add(new XYChart.Data("10-19",exam1.getHist()[1]));
        series.getData().add(new XYChart.Data("20-29",exam1.getHist()[2]));
        series.getData().add(new XYChart.Data("30-39",exam1.getHist()[3]));
        series.getData().add(new XYChart.Data("40-49",exam1.getHist()[4]));
        series.getData().add(new XYChart.Data("50-59",exam1.getHist()[5]));
        series.getData().add(new XYChart.Data("60-69",exam1.getHist()[6]));
        series.getData().add(new XYChart.Data("70-79",exam1.getHist()[7]));
        series.getData().add(new XYChart.Data("80-89",exam1.getHist()[8]));
        series.getData().add(new XYChart.Data("90-100",exam1.getHist()[9]));

        XYChart.Series<String, Double> series2 = new XYChart.Series();
        series2.setName("Exam 2 Grades");
        series2.getData().add(new XYChart.Data("0-10",exam2.getHist()[0]));
        series2.getData().add(new XYChart.Data("10-19",exam2.getHist()[1]));
        series2.getData().add(new XYChart.Data("20-29",exam2.getHist()[2]));
        series2.getData().add(new XYChart.Data("30-39",exam2.getHist()[3]));
        series2.getData().add(new XYChart.Data("40-49",exam2.getHist()[4]));
        series2.getData().add(new XYChart.Data("50-59",exam2.getHist()[5]));
        series2.getData().add(new XYChart.Data("60-69",exam2.getHist()[6]));
        series2.getData().add(new XYChart.Data("70-79",exam2.getHist()[7]));
        series2.getData().add(new XYChart.Data("80-89",exam2.getHist()[8]));
        series2.getData().add(new XYChart.Data("90-100",exam2.getHist()[9]));

        course_hist1.getData().addAll(series,series2);
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