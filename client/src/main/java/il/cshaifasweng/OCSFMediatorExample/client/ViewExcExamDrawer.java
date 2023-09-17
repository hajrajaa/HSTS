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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class  ViewExcExamDrawer
{
    public SimpleClient client;
    ExecutedExamInfo examInfo;
    List<ExecutedExam> executedExamList1;
    boolean TableInitFlag;

    public ExecutedExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExecutedExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public List<ExecutedExam> getExecutedExamList1() {
        return executedExamList1;
    }

    public void setExecutedExamList1(List<ExecutedExam> executedExamList1) {
        this.executedExamList1 = executedExamList1;
    }

    public static ExecutedExam getSelectedExam() {
        return selectedExam;
    }

    public static void setSelectedExam(ExecutedExam selectedExam) {
        ViewExcExamDrawer.selectedExam = selectedExam;
    }

    public static  ExecutedExam selectedExam;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane editgradePane;
    @FXML
    private TableView exeExamTable;
    @FXML
    private TableColumn<ExecutedExam, String> gradeCol, nameCol;
    @FXML
    private TextField newGradeTxt, expTxt;
    @FXML
    private Text error_bar, txtAverage, txtMedian, studentNameTxt;

//    @FXML
//    private Button Home_Click;

    @FXML
    private Button updateBtn;
    @FXML
    private BarChart<String, Double> Histogram;
    @FXML
    void newGradeTxt(ActionEvent event) {}

    @FXML
    void updateBtn(ActionEvent event) {
        UpdateExamGrade(selectedExam);
        exeExamTable.refresh();
    }

    @FXML
    void expTxt(ActionEvent event) {}

    @FXML
    void initialize() throws IOException
    {
        editgradePane.setDisable(true);
        error_bar.setText("");
        txtAverage.setText("");
        txtMedian.setText("");
        studentNameTxt.setText("");

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        examInfo=TeacherExuctedInfoDrawer.getSelectedExecutedExam();
        int id =examInfo.getId();
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetExcutedExams",id));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if(examInfo!=null)
        {
            String avg=examInfo.getAverage().toString();
            String med=examInfo.getMedian().toString();
            txtAverage.setText(avg);
            txtMedian.setText(med);
            InitHistogram();
        }

        TableInitFlag=false;
        updateBtn.setDisable(true);
        exeExamTable.setStyle("-fx-pref-height: 510;");

        assert error_bar != null : "fx:id=\"error_bar\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert exeExamTable != null : "fx:id=\"exeExamTable\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert expTxt != null : "fx:id=\"expTxt\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert gradeCol != null : "fx:id=\"gradeCol\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert newGradeTxt != null : "fx:id=\"newGradeTxt\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert txtAverage != null : "fx:id=\"txtAverage\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert txtMedian != null : "fx:id=\"txtMedian\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert studentNameTxt != null : "fx:id=\"studentNameTxt\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'executed_exam_view.fxml'.";
    }

    private void InitHistogram ()
    {
        Histogram.setAnimated(false);
        Histogram.setTitle("Grades Histogram");
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

    private void initTable()
    {
        ObservableList<ExecutedExam> excutedExams = FXCollections.observableArrayList(executedExamList1);
        exeExamTable.setItems(excutedExams);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        if(!TableInitFlag){

            initApproveTableColumn();
            initEditTableColumn();
            TableInitFlag=true;
        }
    }

    @Subscribe
    public void ExcutedExamEventFunc(ExcutedExamEvent event)
    {
        executedExamList1= (event.getExecutedExamList());
        if(executedExamList1!=null)
        {
            initTable();
        }
    }

    @Subscribe
    public void refreshExecEventFunc(refreshExecExam event)
    {
        executedExamList1=(event.getExecutedExamList());
        examInfo=(event.getExamInfo());
        if(executedExamList1!=null)
        {
            initTable();
        }
        if(examInfo!=null)
        {
            String avg = examInfo.getAverage().toString();
            String med = examInfo.getMedian().toString();
            txtAverage.setText(avg);
            txtMedian.setText(med);

            HistDataRefresh();
        }
    }

    @Subscribe
    public void approveGradeEventFunc(ApproveGradeEvent event)
    {
        selectedExam = (event.getExecutedExam());
        exeExamTable.refresh();
        if (selectedExam != null)
        {
            if (selectedExam.isMarked())
            {
                for (ExecutedExam executedExam : executedExamList1) {
                    if (executedExam.getExamNum() == selectedExam.getExamNum()) {
                        executedExam.setMarked(true);
                        initTable();
                    }
                }
            }
        }
        error_bar.setText("Grade Approved Successfully");
    }

    public  void initApproveTableColumn()
    {
        TableColumn<ExecutedExam, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExam, Void>, TableCell<ExecutedExam, Void>> cellFactory = new Callback<TableColumn<ExecutedExam, Void>, TableCell<ExecutedExam, Void>>() {
            @Override
            public TableCell<ExecutedExam, Void> call(final TableColumn<ExecutedExam, Void> param) {
                final TableCell<ExecutedExam, Void> cell = new TableCell<ExecutedExam, Void>() {

                    private final Button btn = new Button("Approve");
                    {

                        btn.setOnAction((ActionEvent event) -> {
                            selectedExam = getTableView().getItems().get(getIndex());
                            if(selectedExam!=null) {
                                try {
                                    SimpleClient.getClient().sendToServer(new Message("#ApproveExamGradeReq", selectedExam.getExamNum()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            ExecutedExam exam = getTableView().getItems().get(getIndex());
                            if(exam.isMarked())
                            {
//                                btn.setStyle("-fx-background-color: green;");
                                App.setButtonColor(btn,"green");
                                btn.setText("Approved");
                            }
                            else {
                                btn.setStyle(null); // Reset style to default
                            }
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        exeExamTable.getColumns().add(colBtn);
    }

    private void initEditTableColumn() {
        TableColumn<ExecutedExam, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExam, Void>, TableCell<ExecutedExam, Void>> cellFactory = new Callback<TableColumn<ExecutedExam, Void>, TableCell<ExecutedExam, Void>>() {
            @Override
            public TableCell<ExecutedExam, Void> call(final TableColumn<ExecutedExam, Void> param) {
                final TableCell<ExecutedExam, Void> cell = new TableCell<ExecutedExam, Void>() {

                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedExam = getTableView().getItems().get(getIndex());
                            if(selectedExam!=null)
                            {
                                error_bar.setText("");
                                String name=selectedExam.getStudentName();
                                studentNameTxt.setText(name.toString());
                                editgradePane.setDisable(false);
                                updateBtn.setDisable(false);
                                exeExamTable.setStyle("-fx-pref-height: 320;");
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            App.setButtonColor(btn,"orange");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        exeExamTable.getColumns().add(colBtn);
    }

    public void UpdateExamGrade(ExecutedExam exam)
    {
        String newGradeSt=newGradeTxt.getText();
        if(newGradeSt.equals("")){
            error_bar.setText("Please Enter a Grade");
        }else{
            double newGrade=Double.parseDouble(newGradeSt);
            String explanation=expTxt.getText();
            Object[] obj={exam.getExamNum(),newGrade,explanation};
            try {
                SimpleClient.getClient().sendToServer(new Message("#UpdateGradeRequest",obj));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    public void UpdateGradeEventFunc(UpdateGradeEvent event)
    {
        selectedExam = (event.getExecutedExam());
        exeExamTable.refresh();

        if (selectedExam != null) {

            String avg = examInfo.getAverage().toString();
            String med = examInfo.getMedian().toString();

            txtAverage.setText(avg);
            txtMedian.setText(med);
        }
        error_bar.setText("Grade Updated Successfully");
        editgradePane.setDisable(true);
        updateBtn.setDisable(true);
        exeExamTable.setStyle("-fx-pref-height: 510;");
        studentNameTxt.setText("");
        expTxt.clear();
        newGradeTxt.clear();
        exeExamTable.refresh();
        try
        {
            SimpleClient.getClient().sendToServer(new Message("#GetRefreshExcutedExams",examInfo.getId()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        EventBus.getDefault().unregister(this);
        App.setRoot("teacherMain");
    }

}





