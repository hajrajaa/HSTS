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

import javax.persistence.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipleStatisticsController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    TableView StudentsTable, TeachersTable, CoursesTable;
    @FXML
    private TableColumn<StatisticsFilter, String> titleCol_s, titleCol_t, titleCol_c;
    private boolean [] flags = {true,true,true};

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void initialize() throws IOException
    {
        System.out.println("\n--------------------------------------------> PrincipleStatisticsController");


        initTable(StudentsTable, App.studentsStatisticsFilterList, titleCol_s, 0);
        initTable(TeachersTable, App.teachersStatisticsFilterList, titleCol_t, 1);
        initTable(CoursesTable, App.coursesStatisticsFilterList, titleCol_c, 2);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Table //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    private void initTable (TableView Table, ArrayList<StatisticsFilter> list, TableColumn titleCol, int flagIndex)
    {
        ObservableList<StatisticsFilter> allExamQuestions_OL = FXCollections.observableArrayList(list);
        Table.setItems(allExamQuestions_OL);

        titleCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        if(flags[flagIndex]){
            initViewTableColumn(Table);
            flags[flagIndex] = false;
        }
    }
    private void initViewTableColumn(TableView Table)
    {
        TableColumn<StatisticsFilter, Void> colBtn = new TableColumn("");

        Callback<TableColumn<StatisticsFilter, Void>, TableCell<StatisticsFilter, Void>> cellFactory = new Callback<TableColumn<StatisticsFilter, Void>, TableCell<StatisticsFilter, Void>>() {
            @Override
            public TableCell<StatisticsFilter, Void> call(final TableColumn<StatisticsFilter, Void> param) {
                final TableCell<StatisticsFilter, Void> cell = new TableCell<StatisticsFilter, Void>() {

                    private final Button btn = new Button("view");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            StatisticsFilter exam = getTableView().getItems().get(getIndex());
                            try {
                                App.setRoot("exam_drawer");
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
        Table.getColumns().add(colBtn);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// On Action //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_homepage");
    }
}
