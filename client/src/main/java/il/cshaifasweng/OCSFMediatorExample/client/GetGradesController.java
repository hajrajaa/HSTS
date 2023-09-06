package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedVirtual;
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
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.scene.text.Text;

public class GetGradesController {

    public SimpleClient client;



    public static User user;

    List<ExecutedVirtual> executedExamList1;

    public static  ExecutedVirtual selectedExam;

    public static ExecutedVirtual getSelectedExam() {
        return selectedExam;
    }

    public static void setSelectedExam(ExecutedVirtual selectedExam) {
        GetGradesController.selectedExam = selectedExam;
    }

    public List<ExecutedVirtual> getExecutedExamList1() {
        return executedExamList1;
    }

    public void setExecutedExamList1(List<ExecutedVirtual> executedExamList1) {
        this.executedExamList1 = executedExamList1;
    }


    public static User getUser1() {
        return user;
    }

    public static void setUser1(User user) {
        GetGradesController.user = user;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Home_Button;

    @FXML
    private TableColumn<ExecutedVirtual, String> DateCol;

    @FXML
    private TableColumn<ExecutedVirtual, String> codeCol;

    @FXML
    private Text error_bar;


    @FXML
    private TableView examsTable;

    @FXML
    private TableColumn<ExecutedVirtual, Double> gradeCol;

    @FXML
    private TableColumn<ExecutedVirtual, String> titleCol;

    boolean TableInitFlag=false;


    @FXML
    void Home_Click(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        App.setRoot("studentMain");
    }


    @FXML
    void initialize() throws IOException {

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        setUser1(App.getUser());
        error_bar.setText("");


        assert DateCol != null : "fx:id=\"DateCol\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert codeCol != null : "fx:id=\"codeCol\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert examsTable != null : "fx:id=\"examsTable\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert gradeCol != null : "fx:id=\"gradeCode\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert error_bar != null : "fx:id=\"error_bar\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert titleCol != null : "fx:id=\"titleCol\" was not injected: check your FXML file 'get_grade.fxml'.";
        assert Home_Button != null : "fx:id=\"Home_Button\" was not injected: check your FXML file 'get_grade.fxml'.";

        try {
            SimpleClient.getClient().sendToServer(new Message("#GetStudentGrades", user.getUserName()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Subscribe
    public  void getStudentExamsEventFunc(GetStudentExamsEvent event)
    {
        System.out.println("ZZZZZZZZZZZZ");
        executedExamList1 = (event.getExecutedVirtuals());

        if(executedExamList1==null)
        {
            error_bar.setText("You Don't Have Any Virtual Executed Exams ");
            System.out.println("aaaaaaaa");
        }
        else
        {
            for (ExecutedVirtual e : executedExamList1) {
                System.out.println(e.isMarked());
            }
            initTable();
        }

    }

    private void initTable()
    {
        ObservableList<ExecutedVirtual> executedExams = FXCollections.observableArrayList(executedExamList1);
        examsTable.setItems(executedExams);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("infoID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        examsTable.refresh();
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        gradeCol.setCellFactory(column -> {
            return new TableCell<ExecutedVirtual, Double>() {
                @Override
                protected void updateItem(Double grade, boolean empty) {
                    super.updateItem(grade, empty);
                    if (empty || grade == null)
                    {
                        setText("");
                    }
                    else
                    {
                        ExecutedVirtual exam = getTableView().getItems().get(getIndex());
                        if (exam != null)
                        {
                            if (!(exam.isMarked()))
                            {
                                setText("Not Approved Yet");
                            }
                            else
                            {
                                setText(String.valueOf(exam.getGrade()));
                            }
                        }
                        else {
                            error_bar.setText("empty table");
                        }
                    }
                }
            };
        });
        initViewTableColumn("Get Exam Copy");

//        if(!TableInitFlag){
//
//            TableInitFlag=true;
//        }
    }

    public  void  initViewTableColumn(String columnName)
    {
        TableColumn<ExecutedVirtual, Void> colBtn = new TableColumn("");

        Callback<TableColumn<ExecutedVirtual, Void>, TableCell<ExecutedVirtual, Void>> cellFactory = new Callback<TableColumn<ExecutedVirtual, Void>, TableCell<ExecutedVirtual, Void>>() {
            @Override
            public TableCell<ExecutedVirtual, Void> call(final TableColumn<ExecutedVirtual, Void> param) {
                final TableCell<ExecutedVirtual, Void> cell = new TableCell<ExecutedVirtual, Void>() {

                    private final Button btn = new Button("View");
                    {

                        btn.setOnAction((ActionEvent event) -> {
                            selectedExam = getTableView().getItems().get(getIndex());
                            if(selectedExam!=null) {
                                try {
                                    SimpleClient.getClient().sendToServer(new Message("#getExamCopy", selectedExam.getExamNum()));
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

                            ExecutedVirtual exam = getTableView().getItems().get(getIndex());
                            if(!exam.isMarked())
                            {
                               btn.setDisable(true);
                            }
//                            else {
//                                btn.setDisable(false);
//                                btn.setStyle(null); // Reset style to default
//                            }
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        examsTable.getColumns().add(colBtn);
    }

}

