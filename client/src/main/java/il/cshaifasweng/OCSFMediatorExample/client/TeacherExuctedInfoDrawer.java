package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TeacherExuctedInfoDrawer {

    public static List<ExecutedExamInfo> executedExamsInfoList;

    public  static List<ExecutedExamInfo> writtenListExamsInfo;

    boolean writtenTableInitFlag;

    boolean excutedTableInitFlag;

    boolean writtenFlag;

    public SimpleClient client;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ExecutedExamInfo, String> CodeExcCol;

    @FXML
    private Text error_bar_text;

    @FXML
    private TableView ExecutedExamTable;

    @FXML
    private TableView WrittenExamsTable;

    @FXML
    private TableColumn<ExecutedExamInfo, String> codeCol;

    @FXML
    private TableColumn<ExecutedExamInfo, String> passExcCol;

    @FXML
    private TableColumn<ExecutedExamInfo, String> titleCol;

    @FXML
    private TableColumn<ExecutedExamInfo, String> titleExcCol;

    @FXML
    void initialize() throws IOException
    {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        error_bar_text.setText("");

        writtenTableInitFlag=false;
        excutedTableInitFlag=false;

        assert CodeExcCol != null : "fx:id=\"CodeExcCol\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert ExecutedExamTable != null : "fx:id=\"ExecutedExamTable\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert WrittenExamsTable != null : "fx:id=\"WrittenExamsTable\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert codeCol != null : "fx:id=\"codeCol\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert passExcCol != null : "fx:id=\"passExcCol\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert titleCol != null : "fx:id=\"titleCol\" was not injected: check your FXML file 'teacherExamsView.fxml'.";
        assert titleExcCol != null : "fx:id=\"titleExcCol\" was not injected: check your FXML file 'teacherExamsView.fxml'.";

        try {
            SimpleClient.getClient().sendToServer(new Message("#GetTeacherAllExams",App.getUser().getUserName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void GetTeacherAllExams_Replay(EventGetTeacherAllExams event)
    {

        writtenListExamsInfo=event.getWrittenExamsInfoList();
        executedExamsInfoList=event.getExecutedExamInfoList();
        if(writtenListExamsInfo==null)
        {
            error_bar_text.setText("No Written Exam Found");
        }
        if(writtenListExamsInfo.size() == 0)
        {
            error_bar_text.setText("No Written Exam Found");
        }
        if(executedExamsInfoList==null)
        {
            error_bar_text.setText("No Executed Exam Found");
        }
        if(executedExamsInfoList.size() == 0)
        {
            error_bar_text.setText("No Executed Exam Found");
        }
        initWrittenExamTable();
        initExcutedExamTable();
    }

    private void initWrittenExamTable ()
    {
        System.out.println(writtenListExamsInfo.size() + " +++++++");
        ObservableList<ExecutedExamInfo> allWrittenExam = FXCollections.observableArrayList(writtenListExamsInfo);
        WrittenExamsTable.setItems(allWrittenExam);

        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        writtenFlag=true;
        if(!writtenTableInitFlag){
            initViewTableColumn();
            writtenTableInitFlag=true;
        }

    }

    private void initExcutedExamTable ()
    {
        System.out.println(executedExamsInfoList.size() + " 2+++++++");
        ObservableList<ExecutedExamInfo> allExcutedExams = FXCollections.observableArrayList(executedExamsInfoList);
        ExecutedExamTable.setItems(allExcutedExams);
        CodeExcCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        passExcCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        titleExcCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        writtenFlag=false;
        if(!excutedTableInitFlag){
            initViewTableColumn();
           excutedTableInitFlag=true;
        }

    }

    private void initViewTableColumn() {
        TableColumn<ExecutedExamInfo, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>> cellFactory = new Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>>() {
            @Override
            public TableCell<ExecutedExamInfo, Void> call(final TableColumn<ExecutedExamInfo, Void> param) {
                final TableCell<ExecutedExamInfo, Void> cell = new TableCell<ExecutedExamInfo, Void>() {

                    private final Button btn = new Button("view");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ExecutedExamInfo view_exam = getTableView().getItems().get(getIndex());
                            try {
                                if(writtenFlag)
                                {
                                    App.setRoot("written_exams_view");

                                }
                                else {
                                    App.setRoot("executed_exam_view");
                                }
                                SimpleClient.getClient().sendToServer(new Message("#GetExcutedExams",view_exam));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
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
        if(writtenFlag)
        {
            WrittenExamsTable.getColumns().add(colBtn);
        }
        else {
            ExecutedExamTable.getColumns().add(colBtn);
        }
    }

}
