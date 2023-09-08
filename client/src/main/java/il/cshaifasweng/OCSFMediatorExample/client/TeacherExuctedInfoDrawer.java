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


    public static ExecutedExamInfo getSelectedWrittenExam() {
        return selectedWrittenExam;
    }

    public static void setSelectedWrittenExam(ExecutedExamInfo selectedWrittenExam) {
        TeacherExuctedInfoDrawer.selectedWrittenExam = selectedWrittenExam;
    }

    public static ExecutedExamInfo selectedWrittenExam;

    public static ExecutedExamInfo getSelectedExecutedExam() {
        return selectedExecutedExam;
    }

    public static void setSelectedExecutedExam(ExecutedExamInfo selectedExecutedExam) {
        TeacherExuctedInfoDrawer.selectedExecutedExam = selectedExecutedExam;
    }

    public static ExecutedExamInfo selectedExecutedExam;

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
    private Text executed_error_bar;

    @FXML
    private Text written_error_bar;

    @FXML
    private TableView ExecutedExamTable;

    @FXML
    private TableView WrittenExamsTable;

    @FXML
    Button Home_Button;

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

        executed_error_bar.setText("");
        written_error_bar.setText("");

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
            written_error_bar.setText("No Written Exam Found");
        }
        if(writtenListExamsInfo.size() == 0)
        {
            written_error_bar.setText("No Written Exam Found");
        }
        if(executedExamsInfoList==null)
        {
            executed_error_bar.setText("No Executed Exam Found");
        }
        if(executedExamsInfoList.size() == 0)
        {
            executed_error_bar.setText("No Executed Exam Found");
        }
        initWrittenExamTable();
        initExcutedExamTable();
    }

    private void initWrittenExamTable ()
    {
        ObservableList<ExecutedExamInfo> allWrittenExam = FXCollections.observableArrayList(writtenListExamsInfo);
        WrittenExamsTable.setItems(allWrittenExam);

        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        writtenFlag=true;
        if(!writtenTableInitFlag){
            initViewTableColumnWritten();
            writtenTableInitFlag=true;
        }

    }

    private void initExcutedExamTable ()
    {
        ObservableList<ExecutedExamInfo> allExcutedExams = FXCollections.observableArrayList(executedExamsInfoList);
        for(int i=0;i<allExcutedExams.size();i++)
        {
           System.out.println( allExcutedExams.get(i).getId());
        }
        ExecutedExamTable.setItems(allExcutedExams);
        CodeExcCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        passExcCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        titleExcCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        writtenFlag=false;
        if(!excutedTableInitFlag){
           initViewTableColumnExc();
           initOvertimeTableColumnExc();
           excutedTableInitFlag=true;
        }

    }



    private void initViewTableColumnWritten()
    {
        TableColumn<ExecutedExamInfo, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>> cellFactory = new Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>>() {
            @Override
            public TableCell<ExecutedExamInfo, Void> call(final TableColumn<ExecutedExamInfo, Void> param) {
                final TableCell<ExecutedExamInfo, Void> cell = new TableCell<ExecutedExamInfo, Void>() {

                    private final Button btn = new Button("Statistics");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedWrittenExam = getTableView().getItems().get(getIndex());
                            if(selectedWrittenExam!=null)
                            {
                                try {
                                    App.setRoot("written_exams_view");
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
        WrittenExamsTable.getColumns().add(colBtn);
    }
    private void initViewTableColumnExc() {
        TableColumn<ExecutedExamInfo, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>> cellFactory = new Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>>() {
            @Override
            public TableCell<ExecutedExamInfo, Void> call(final TableColumn<ExecutedExamInfo, Void> param) {
                final TableCell<ExecutedExamInfo, Void> cell = new TableCell<ExecutedExamInfo, Void>() {

                    private final Button btn = new Button("Statistics");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedExecutedExam = getTableView().getItems().get(getIndex());
                            if(selectedExecutedExam!=null)
                            {
                                try {
                                    App.setRoot("executed_exam_view");
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
        ExecutedExamTable.getColumns().add(colBtn);
    }

    private void initOvertimeTableColumnExc() {
        TableColumn<ExecutedExamInfo, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>> cellFactory = new Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>>() {
            @Override
            public TableCell<ExecutedExamInfo, Void> call(final TableColumn<ExecutedExamInfo, Void> param) {
                final TableCell<ExecutedExamInfo, Void> cell = new TableCell<ExecutedExamInfo, Void>() {

                    private final Button btn = new Button("Request Overtime");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedExecutedExam = getTableView().getItems().get(getIndex());
                            if(selectedExecutedExam!=null)
                            {
                                try {
                                    App.setRoot("overtime_for_exam_info");
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
                            ExecutedExamInfo info = getTableView().getItems().get(getIndex());
                            if(info.getIsRequestedOvertime())
                            {
                                btn.setDisable(true);
                            }
                            setGraphic(btn);
                        }

                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        ExecutedExamTable.getColumns().add(colBtn);
    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {

        EventBus.getDefault().unregister(this);
        App.setRoot("teacherMain");
    }

}
