package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewWrittenExamDrawer {


    public SimpleClient client;
    ExecutedExamInfo examInfo;


    List<ExecutedExam> writtenExamList1;

    public ExecutedExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExecutedExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public List<ExecutedExam> getExecutedExamList1() {
        return writtenExamList1;
    }

    public void setExecutedExamList1(List<ExecutedExam> executedExamList1) {
        this.writtenExamList1 = executedExamList1;
    }


    boolean TableInitFlag;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView exeExamTable;

    @FXML
    private TableColumn<ExecutedExam, String> gradeCol, nameCol;

    @FXML
    private Text txtAverage, txtMedian;

    @FXML
    private BarChart<String, Double> Histogram;

    @FXML
    Button Home_Button;

    @FXML
    void initialize() throws IOException {

        txtMedian.setText("");
        txtAverage.setText("");

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        examInfo=TeacherExuctedInfoDrawer.getSelectedWrittenExam();
        int id= examInfo.getId();
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetWrittenExams",id));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        TableInitFlag=false;
        if(examInfo!=null)
        {
            String avg=examInfo.getAverage().toString();
            String med=examInfo.getMedian().toString();
            txtAverage.setText(avg);
            txtMedian.setText(med);

            InitHistogram();
        }


        assert exeExamTable != null : "fx:id=\"exeExamTable\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert gradeCol != null : "fx:id=\"gradeCol\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert txtAverage != null : "fx:id=\"txtAverage\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert txtMedian != null : "fx:id=\"txtMedian\" was not injected: check your FXML file 'written_exams_view.fxml'.";

    }

    private void InitHistogram ()
    {
        Histogram.setAnimated(false);
        XYChart.Series<String, Double> s = new XYChart.Series();
        Histogram.getData().add(s);
        HistDataRefresh();
    }

    private void HistDataRefresh ()
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

        Histogram.getData().set(0,series);
    }

    @Subscribe
    public  void writtenExamEventFunc(WrittenExamEvent event)
    {
        writtenExamList1= (event.getExecutedExamList());
        if(writtenExamList1!=null)
        {
            initTable();
        }
    }

    private void initTable()
    {
        ObservableList<ExecutedExam> allWrittenExam = FXCollections.observableArrayList(writtenExamList1);
        exeExamTable.setItems(allWrittenExam);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {

        EventBus.getDefault().unregister(this);
        App.setRoot("teacherMain");
    }

}

