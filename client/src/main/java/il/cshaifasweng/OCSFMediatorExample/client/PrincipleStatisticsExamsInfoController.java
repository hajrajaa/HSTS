package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.persistence.Table;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipleStatisticsExamsInfoController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public SimpleClient client;
    private ArrayList<StatisticsInfo> allExamsInfoList;
    private StatisticsInfo examInfo1, examInfo2;
    private boolean [] flags = {true,true,true};
    @FXML
    Text Exam1_Avg, Exam1_Median, Exam2_Avg, Exam2_Median;
    @FXML
    ComboBox Exam1_ComboBox, Exam2_ComboBox;
    @FXML
    private BarChart<String, Double> Histogram;
    @FXML
    PieChart Exam1_Pie, Exam2_Pie;
    private PieChart [] allPieCharts;
    private XYChart.Series<String, Double> [] allHist;
    private Text [] allAvgTexts;
    private Text [] allMedianText;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void initialize() throws IOException
    {
        System.out.println("\n--------------------------------------------> PrincipleStatisticsExamsInfoController");

        allExamsInfoList = App.statisticsInfoList;

        initHistogram();
        allHist = new XYChart.Series[2];

        allPieCharts = new PieChart[2];
        allPieCharts[0] = Exam1_Pie;
        allPieCharts[1] = Exam2_Pie;
        allAvgTexts = new Text[2];
        allAvgTexts[0] = Exam1_Avg;
        allAvgTexts[1] = Exam2_Avg;
        allMedianText = new Text[2];
        allMedianText[0] = Exam1_Median;
        allMedianText[1] = Exam2_Median;

        if(allExamsInfoList != null){
            ArrayList<String> allExamsInfoNames = new ArrayList<String>();
            for (StatisticsInfo sifo : allExamsInfoList)
            {
                allExamsInfoNames.add(sifo.getTitle());
            }
            ObservableList<String> basesList = FXCollections.observableArrayList(allExamsInfoNames);
            Exam1_ComboBox.setItems(basesList);
            Exam2_ComboBox.setItems(basesList);
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Pie Chart //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private void initPieChart (PieChart chart, int inTime, int timeUp)
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Submit In Time", inTime));
        pieChartData.add(new PieChart.Data("Time Is Up", timeUp));

        chart.setData(pieChartData);
        chart.setTitle("Submit In Time");

        initPieChartData(chart);
    }

    private void initPieChartData (PieChart chart)
    {
        Node inTimeNode = chart.getData().get(0).getNode();
        Node timeUpNode = chart.getData().get(1).getNode();
        double inTimeVal = chart.getData().get(0).getPieValue();
        double timeUpVal = chart.getData().get(1).getPieValue();
        double sumVal = inTimeVal + timeUpVal;

        inTimeNode.setStyle("-fx-pie-color: #34b048;"); // green
        timeUpNode.setStyle("-fx-pie-color: #ff0404;"); // red

        inTimeNode.setOnMouseEntered(event -> {chart.setTitle("Submit In Time: " + (int)((inTimeVal/sumVal)*100) + "%");});
        inTimeNode.setOnMouseExited(event -> {chart.setTitle("Submit In Time");});

        timeUpNode.setOnMouseEntered(event -> {chart.setTitle("Not In Time: " + (int)((timeUpVal/sumVal)*100) + "%");});
        timeUpNode.setOnMouseExited(event -> {chart.setTitle("Submit In Time");});
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Histogram //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private void initHistogram ()
    {
        Histogram.setAnimated(false);
        Histogram.setTitle("Grades Histogram");
        XYChart.Series<String, Double> s1 = new XYChart.Series();
        XYChart.Series<String, Double> s2 = new XYChart.Series();
        Histogram.getData().addAll(s1,s2);
    }

    private void addHistogramSeries (StatisticsInfo examInfo, int index)
    {
        XYChart.Series<String, Double> series = new XYChart.Series();
        series.setName(examInfo.getTitle());
        series.getData().add(new XYChart.Data("0-9",examInfo.getHist()[0]));
        series.getData().add(new XYChart.Data("10-19",examInfo.getHist()[1]));
        series.getData().add(new XYChart.Data("20-29",examInfo.getHist()[2]));
        series.getData().add(new XYChart.Data("30-39",examInfo.getHist()[3]));
        series.getData().add(new XYChart.Data("40-49",examInfo.getHist()[4]));
        series.getData().add(new XYChart.Data("50-59",examInfo.getHist()[5]));
        series.getData().add(new XYChart.Data("60-69",examInfo.getHist()[6]));
        series.getData().add(new XYChart.Data("70-79",examInfo.getHist()[7]));
        series.getData().add(new XYChart.Data("80-89",examInfo.getHist()[8]));
        series.getData().add(new XYChart.Data("90-100",examInfo.getHist()[9]));

//        if(Histogram.getData().size() < 2){
//            Histogram.getData().add(series);
//        }else{
//            Histogram.getData().add(index, series);
//        }
        Histogram.getData().set(index, series);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// On Action //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private StatisticsInfo findExamInfoByName (String name)
    {
        if(allExamsInfoList != null){
            for(StatisticsInfo ex : allExamsInfoList){
                if(ex.getTitle().equals(name)){
                    return ex;
                }
            }
        }
        return null;
    }

    private void examInfoFound (StatisticsInfo exam, int index)
    {
        if(exam != null){
            initPieChart(allPieCharts[index], exam.getInTimeCounter(), exam.getTimeUpCounter());
            allAvgTexts[index].setText("Average: " + (int)exam.getAverage());
            allMedianText[index].setText("Median: " + (int)exam.getMedian());
            addHistogramSeries(exam, index);
        }
    }

    public void Exam1Selected(ActionEvent actionEvent)
    {
        if(Exam1_ComboBox.getValue() != null){
            String infoName = Exam1_ComboBox.getValue().toString();
            examInfo1 = findExamInfoByName(infoName);
            examInfoFound(examInfo1, 0);
        }
    }

    public void Exam2Selected(ActionEvent actionEvent)
    {
        if(Exam2_ComboBox.getValue() != null){
            String infoName = Exam2_ComboBox.getValue().toString();
            examInfo2 = findExamInfoByName(infoName);
            examInfoFound(examInfo2, 1);
        }
    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_homepage");
    }
}
