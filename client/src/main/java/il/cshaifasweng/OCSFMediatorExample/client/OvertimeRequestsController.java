package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OvertimeRequestsController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public SimpleClient client;

    private ArrayList<OvertimeRequest> allOvertimeRequests;
    @FXML
    TableView Table;
    private boolean tableInitFlag;

    @FXML
    Button ApproveAll_Button;
    @FXML
    private TableColumn<OvertimeRequest, String> Exam_Column, Teacher_Column, Time_Column, Reasons_Column;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize () throws IOException {
//        EventBus.getDefault().register(this);
//        client = SimpleClient.getClient();
//        client.openConnection();

        if(App.getAllOvertimeReq() != null)
        {
            allOvertimeRequests = App.getAllOvertimeReq();
            tableInitFlag = true;
            initTable();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Common /////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    private void approveRequest (OvertimeRequest req)
    {
        allOvertimeRequests.remove(req);
        initTable();

        try {
            SimpleClient.getClient().sendToServer(new Message("#ApproveOvertimeRequest", req));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void denyRequest (OvertimeRequest req)
    {
        allOvertimeRequests.remove(req);
        initTable();

        try {
            SimpleClient.getClient().sendToServer(new Message("#DenyOvertimeRequest", req));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

//    @Subscribe
//    public void GetAllOvertimeRequests_Replay(EventGetAllOvertimeRequests event)
//    {
//        if(event.getAllRequests() != null)
//        {
//            allOvertimeRequests = event.getAllRequests();
//            initTable();
//        }
//    }

    private void unregisterMe ()
    {
        EventBus.getDefault().unregister(this);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Table //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    private void initTable()
    {
        ObservableList<OvertimeRequest> allExamQuestions_OL = FXCollections.observableArrayList(allOvertimeRequests);
        Table.setItems(allExamQuestions_OL);

        Exam_Column.setCellValueFactory(new PropertyValueFactory<>("examName"));
        Teacher_Column.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        Time_Column.setCellValueFactory(new PropertyValueFactory<>("timeToAdd"));
        Reasons_Column.setCellValueFactory(new PropertyValueFactory<>("reason"));

        if(tableInitFlag){
            initApproveTableColumn();
            initDenyTableColumn();
            tableInitFlag = false;
        }
    }

    private void initApproveTableColumn() {
        TableColumn<OvertimeRequest, Void> colBtn = new TableColumn("");

        Callback<TableColumn<OvertimeRequest, Void>, TableCell<OvertimeRequest, Void>> cellFactory = new Callback<TableColumn<OvertimeRequest, Void>, TableCell<OvertimeRequest, Void>>() {
            @Override
            public TableCell<OvertimeRequest, Void> call(final TableColumn<OvertimeRequest, Void> param) {
                final TableCell<OvertimeRequest, Void> cell = new TableCell<OvertimeRequest, Void>() {

                    private final Button btn = new Button("Approve");
                    {
                        App.setButtonColor(btn, "green");

                        btn.setOnAction((ActionEvent event) ->
                        {
                            OvertimeRequest req = getTableView().getItems().get(getIndex());
                            approveRequest(req);
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
        Table.getColumns().add(colBtn);
    }

    private void initDenyTableColumn() {
        TableColumn<OvertimeRequest, Void> colBtn = new TableColumn("");

        Callback<TableColumn<OvertimeRequest, Void>, TableCell<OvertimeRequest, Void>> cellFactory = new Callback<TableColumn<OvertimeRequest, Void>, TableCell<OvertimeRequest, Void>>() {
            @Override
            public TableCell<OvertimeRequest, Void> call(final TableColumn<OvertimeRequest, Void> param) {
                final TableCell<OvertimeRequest, Void> cell = new TableCell<OvertimeRequest, Void>() {

                    private final Button btn = new Button("Deny");
                    {
                        App.setButtonColor(btn, "red");

                        btn.setOnAction((ActionEvent event) ->
                        {
                            OvertimeRequest req = getTableView().getItems().get(getIndex());
                            denyRequest(req);
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
        Table.getColumns().add(colBtn);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void approve_all_click(ActionEvent actionEvent)
    {
        for (OvertimeRequest req : allOvertimeRequests){
            approveRequest(req);
        }
    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {
//        EventBus.getDefault().unregister(this);
        App.setRoot("principle_homepage");
    }

    public void save_in() {
        App.setButtonColor(ApproveAll_Button,"green");
    }
    public void save_out() {
        App.setButtonColor(ApproveAll_Button,"orange");
    }

}

