//package il.cshaifasweng.OCSFMediatorExample.client;
//
//import il.cshaifasweng.OCSFMediatorExample.entities.*;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.text.Text;
//import javafx.util.Callback;
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import javax.persistence.Table;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PrincipleStatisticsController2
//{
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    public SimpleClient client;
//
//    private String filterBy;
//    private ArrayList<Student> studentsList;
//    private ArrayList<Teacher> teachersList;
//    private ArrayList<Course> coursesList;
//    @FXML
//    TableView StudentsTable, TeachersTable, CoursesTable;
//    @FXML
//    private TableColumn<ExecutedExamInfo, String> codeCol_s, titleCol_s, codeCol_t, titleCol_t, codeCol_c, titleCol_c;
//    private boolean [] flags = {true,true,true};
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @FXML
//    private void initialize() throws IOException
//    {
//        System.out.println("\n--------------------------------------------> PrincipleStatisticsController");
//        EventBus.getDefault().register(this);
//        client = SimpleClient.getClient();
//        client.openConnection();
//
//        initTable(StudentsTable, studentsList, codeCol_s, titleCol_s, 0);
//        initTable(TeachersTable, teachersList, codeCol_t, titleCol_t, 1);
//        initTable(CoursesTable, coursesList, codeCol_c, titleCol_c, 2);
//
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#PrincipleStatisticsLists"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////// Common /////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Subscribe
//    public void getAllSubjectsResponse(EventGetAllSubjects event)
//    {
//        allSubjects = event.getAllSubjects();
//        ArrayList<String> allSubjectsNames = new ArrayList<String>();
//        for (Subject subject : allSubjects)
//        {
//            allSubjectsNames.add(subject.getSubName());
//        }
//        ObservableList<String> basesList = FXCollections.observableArrayList(allSubjectsNames);
//        Subject_ComboBox.setItems(basesList);
//
//        error_bar_text.setText("Please Choose a Subject");
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////// Table //////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    private void initTable (TableView Table, ArrayList list, TableColumn codeCol, TableColumn titleCol, int flagIndex)
//    {
//        ObservableList<Exam> allExamQuestions_OL = FXCollections.observableArrayList(list);
//        Table.setItems(allExamQuestions_OL);
//
//        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
//        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//        if(flags[flagIndex]){
//            initViewTableColumn(Table);
//            flags[flagIndex] = false;
//        }
//    }
//
//    private void unregisterMe ()
//    {
//        EventBus.getDefault().unregister(this);
//    }
//
//    private void initViewTableColumn(TableView Table)
//    {
//        TableColumn<ExecutedExamInfo, Void> colBtn = new TableColumn("");
//
//        Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>> cellFactory = new Callback<TableColumn<ExecutedExamInfo, Void>, TableCell<ExecutedExamInfo, Void>>() {
//            @Override
//            public TableCell<ExecutedExamInfo, Void> call(final TableColumn<ExecutedExamInfo, Void> param) {
//                final TableCell<ExecutedExamInfo, Void> cell = new TableCell<ExecutedExamInfo, Void>() {
//
//                    private final Button btn = new Button("view");
//                    {
//                        btn.setOnAction((ActionEvent event) -> {
//                            ExecutedExamInfo exam = getTableView().getItems().get(getIndex());
//                            try {
//                                unregisterMe();
//                                App.setRoot("exam_drawer");
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        colBtn.setCellFactory(cellFactory);
//        Table.getColumns().add(colBtn);
//    }
//
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////// On Action //////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void Home_Click(ActionEvent actionEvent) throws IOException {
//        EventBus.getDefault().unregister(this);
//        App.setRoot("principle_homepage");
//    }
//}
