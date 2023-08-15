package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class PrincipleCourseInfoList {

    List<ExecutedExamInfo> courseinfo;

    public static ExecutedExamInfo getChosenInfo() {
        return chosenInfo;
    }

    public static void setChosenInfo(ExecutedExamInfo chosenInfo) {
        PrincipleCourseInfoList.chosenInfo = chosenInfo;
    }

    private static ExecutedExamInfo chosenInfo;

    public static ExecutedExamInfo getChosenInfo2() {
        return chosenInfo2;
    }

    public static void setChosenInfo2(ExecutedExamInfo chosenInfo2) {
        PrincipleCourseInfoList.chosenInfo2 = chosenInfo2;
    }

    private static ExecutedExamInfo chosenInfo2;
    @FXML
    private Label course_lbl;

    @FXML
    private TableColumn<ExecutedExamInfo, String>  idCloumn;
    @FXML
    private TableColumn<ExecutedExamInfo, String>  idCloumn2;

    @FXML
    private Button p_back_btn;

    @FXML
    private TableView<ExecutedExamInfo> p_course_info;
    @FXML
    private TableView<ExecutedExamInfo> p_course_info2;

    @FXML
    private Button p_view_course_info;

    @FXML
    private TableColumn<ExecutedExamInfo, String> passwordColumn;
    @FXML
    private TableColumn<ExecutedExamInfo, String> passwordColumn2;

    @FXML
    private TableColumn<ExecutedExamInfo, String>  titleColumn;
    @FXML
    private TableColumn<ExecutedExamInfo, String>  titleColumn2;

    @FXML
    private void initialize (){
        p_view_course_info.setDisable(true);
        if (courseinfo == null) {
            courseinfo = App.getCourseInfo();
            if (courseinfo == null) {
                return;
            }
        }

        ObservableList<ExecutedExamInfo> names = FXCollections.observableArrayList(courseinfo);
        p_course_info.setItems(names);
        p_course_info2.setItems(names);

        titleColumn.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("title"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("password"));
        idCloumn.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("code"));

        titleColumn2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("title"));
        passwordColumn2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("password"));
        idCloumn2.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("code"));

    }

    @FXML
    void go_back(ActionEvent event) throws IOException{
        App.setRoot("principle_courses_menu");
    }

    @FXML
    void save_item(MouseEvent event) {
        chosenInfo = p_course_info.getSelectionModel().getSelectedItem();
        chosenInfo2 = p_course_info2.getSelectionModel().getSelectedItem();
        if(chosenInfo!=null&&chosenInfo2!=null) {p_view_course_info.setDisable(false);}
        if(chosenInfo!=null)System.out.println(chosenInfo.getCode());
        if(chosenInfo2!=null)System.out.println(chosenInfo2.getCode());
    }

    @FXML
    void view_teacher_stats(ActionEvent event) throws IOException {
        App.setRoot("principle_course_info_display");
    }

    public List<ExecutedExamInfo> getCourseinfo() {
        return courseinfo;
    }

    public void setCourseinfo(List<ExecutedExamInfo> courseinfo) {
        this.courseinfo = courseinfo;
    }

}
