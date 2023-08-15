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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class  ViewExcExamDrawer {


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
    private Text error_bar;

    @FXML
    private TableView exeExamTable;

    @FXML
    private TextField expTxt;

    @FXML
    private Text studentNameTxt;

    @FXML
    private TableColumn<ExecutedExam, String> gradeCol;

    @FXML
    private TableColumn<ExecutedExam, String> nameCol;

    @FXML
    private TextField newGradeTxt;

    @FXML
    private Text txtAverage;

    @FXML
    private Text txtMedian;

    @FXML
    Button Home_Button;

    @FXML
    private Button updateBtn;

    @FXML
    void newGradeTxt(ActionEvent event) {

    }

    @FXML
    void updateBtn(ActionEvent event) {

        UpdateExamGrade(selectedExam);

    }

    @FXML
    void expTxt(ActionEvent event) {

    }

    @Subscribe
    public  void ExcutedExamEventFunc(ExcutedExamEvent event)
    {
        executedExamList1= (event.getExecutedExamList());
        if(executedExamList1!=null)
        {
            initTable();
        }

    }

    @FXML
    void initialize() throws IOException {

        updateBtn.setDisable(true);
        error_bar.setText("");
        txtAverage.setText("");
        txtMedian.setText("");
        studentNameTxt.setText("");

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        examInfo=TeacherExuctedInfoDrawer.getSelectedExecutedExam();
        int id =examInfo.getId();
        try
        {
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
       }

        TableInitFlag=false;

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
                                String name=selectedExam.getStudentName();
                                studentNameTxt.setText(name.toString());
                                updateBtn.setDisable(false);

                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
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
        double newGrade=Double.parseDouble(newGradeSt);
        String explanation=expTxt.getText();
        Object[] obj={exam.getExamNum(),newGrade,explanation};
        try {
            SimpleClient.getClient().sendToServer(new Message("#UpdateGradeRequest",obj));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Subscribe
    public  void UpdateGradeEventFunc(UpdateGradeEvent event)
    {
        selectedExam= (event.getExecutedExam());
        if(selectedExam!=null)
        {
            initTable();
            String avg=examInfo.getAverage().toString();
            String med=examInfo.getMedian().toString();

            txtAverage.setText(avg);
            txtMedian.setText(med);
        }
        error_bar.setText("Grade Updated Successfully");
        updateBtn.setDisable(true);
        studentNameTxt.setText("");


    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        EventBus.getDefault().unregister(this);
        App.setRoot("teacherMain");
    }

}





