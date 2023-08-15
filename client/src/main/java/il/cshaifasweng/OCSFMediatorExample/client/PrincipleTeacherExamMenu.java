package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class PrincipleTeacherExamMenu {

    private static ArrayList<ExecutedExamInfo> teacherExamInfo;

    private static ExecutedExamInfo chosenInfo;

    private static ExecutedExamInfo chosenInfo2;

    @FXML
    private TableColumn<ExecutedExamInfo, String> code_col;

    @FXML
    private TableColumn<ExecutedExamInfo, String> code_col2;

    @FXML
    private Label exams_lbl;

    @FXML
    private Label exams_lbl1;

    @FXML
    private TableView<ExecutedExamInfo> info_table;

    @FXML
    private TableView<ExecutedExamInfo> info_table2;

    @FXML
    private Button p_back_btn;

    @FXML
    private Button p_view_teacher_exams;

    @FXML
    private TableColumn<ExecutedExamInfo, String> password_col;

    @FXML
    private TableColumn<ExecutedExamInfo, String> password_col2;

    @FXML
    private TableColumn<ExecutedExamInfo, String> title_col;

    @FXML
    private TableColumn<ExecutedExamInfo, String> title_col2;


    @FXML
    private void initialize () throws IOException {
        chosenInfo = null;
        chosenInfo2 = null;

        System.out.println("we got here!!!!!!");
        p_view_teacher_exams.setDisable(true);
        if (teacherExamInfo == null) {
            teacherExamInfo = App.getTeacherExecutedExamInfo();
            if (teacherExamInfo == null) {
                System.out.println("Student list is not available!");
                //return; // Abort initialization if the list is not available
            }
        }

        ObservableList<ExecutedExamInfo> names = FXCollections.observableArrayList(teacherExamInfo);
        info_table.setItems(names);
        info_table2.setItems(names);

        title_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("title"));
        password_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("password"));
        code_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("code"));

        title_col2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("title"));
        password_col2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("password"));
        code_col2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("code"));
    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_teachers_menu");
    }

    @FXML
    void view_teacher_exam_stats(ActionEvent event) throws IOException {
        int[] code={chosenInfo.getCode(), chosenInfo2.getCode()};
        try {
            System.out.println("we are trying to send a msg");
            SimpleClient.getClient().sendToServer(new Message("#TeacherExamInfoDetails", code));
            System.out.println("msg sent");
        } catch (IOException e){
            System.out.println("we are trying to send a msg but no luck");
            e.printStackTrace();
        }
        App.setRoot("principle_teachers_info_display");
    }

    @FXML
    void save_item(){
        chosenInfo = info_table.getSelectionModel().getSelectedItem();
        chosenInfo2 = info_table2.getSelectionModel().getSelectedItem();
        if(chosenInfo!=null&&chosenInfo2!=null) {p_view_teacher_exams.setDisable(false);}
        if(chosenInfo!=null)System.out.println(chosenInfo.getCode());
        if(chosenInfo2!=null)System.out.println(chosenInfo2.getCode());
    }

    public static ArrayList<ExecutedExamInfo> getTeacherExamInfo() {
        return teacherExamInfo;
    }

    public static void setTeacherExamInfo(ArrayList<ExecutedExamInfo> teacherExamInfo) {
        PrincipleTeacherExamMenu.teacherExamInfo = teacherExamInfo;
    }

    public static ExecutedExamInfo getChosenInfo() {
        return chosenInfo;
    }

    public static void setChosenInfo(ExecutedExamInfo chosenInfo) {
        PrincipleTeacherExamMenu.chosenInfo = chosenInfo;
    }

    public static ExecutedExamInfo getChosenInfo2() {
        return chosenInfo2;
    }

    public static void setChosenInfo2(ExecutedExamInfo chosenInfo2) {
        PrincipleTeacherExamMenu.chosenInfo2 = chosenInfo2;
    }

}
