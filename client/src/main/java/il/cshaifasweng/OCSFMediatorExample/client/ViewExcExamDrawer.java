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
    private Button updateBtn;

    @FXML
    void newGradeTxt(ActionEvent event) {

    }

    @FXML
    void updateBtn(ActionEvent event) {

    }

    @FXML
    void expTxt(ActionEvent event) {

    }

    @FXML
    void initialize() throws IOException {
        error_bar.setText("");
        txtAverage.setText("");
        txtMedian.setText("");
        studentNameTxt.setText("");

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

       executedExamList1=App.getExecutedExams();
       examInfo=App.getExecutedExamInfo();


        String avg=examInfo.getAverage().toString();
        String med=examInfo.getMedian().toString();

        txtAverage.setText(avg);
        txtMedian.setText(med);

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
            //initApproveTableColumn();
            initEditTableColumn();
            TableInitFlag=true;
        }
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
                            ExecutedExam edit_exam = getTableView().getItems().get(getIndex());
                            String name=edit_exam.getStudent().getUserName();
                            studentNameTxt.setText(name.toString());
                            UpdateExamGrade(edit_exam);
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

    @Subscribe
    public  void ExcutedExamEventFunc(ExcutedExamEvent event)
    {
        executedExamList1= (event.getExecutedExamList());
        examInfo =(event.getExamInfo());
    }

    void UpdateExamGrade(ExecutedExam exam)
    {
        String newGradeSt=newGradeTxt.getText();
        double newGrade=Double.parseDouble(newGradeSt);
        String explanation=expTxt.getText();
        Object[] obj={exam,newGrade,explanation};
        try {
            SimpleClient.getClient().sendToServer(new Message("#UpdateGradeRequest",obj));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}





