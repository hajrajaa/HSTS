package il.cshaifasweng.OCSFMediatorExample.client;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewWrittenExamDrawer {
    ExecutedExamInfo examInfo;

    List<ExecutedExam> executedExamList1;

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
    private TableView exeExamTable;

    @FXML
    private TableColumn<ExecutedExam, String> gradeCol;

    @FXML
    private TableColumn<ExecutedExam,String> nameCol;

    @FXML
    private Text txtAverage;

    @FXML
    private Text txtMedian;

    @FXML
    void initialize() {

        txtMedian.setText("");
        txtAverage.setText("");

        setExecutedExamList1(App.getExecutedExams());
        ObservableList<ExecutedExam> allExcutedExams = FXCollections.observableArrayList(executedExamList1);
        exeExamTable.setItems(allExcutedExams);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        String avg=examInfo.getAverage().toString();
        String med=examInfo.getMedian().toString();

        txtAverage.setText(avg);
        txtMedian.setText(med);

        assert exeExamTable != null : "fx:id=\"exeExamTable\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert gradeCol != null : "fx:id=\"gradeCol\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert txtAverage != null : "fx:id=\"txtAverage\" was not injected: check your FXML file 'written_exams_view.fxml'.";
        assert txtMedian != null : "fx:id=\"txtMedian\" was not injected: check your FXML file 'written_exams_view.fxml'.";

    }

}

