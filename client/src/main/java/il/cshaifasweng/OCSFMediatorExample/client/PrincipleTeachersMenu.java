package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PrincipleTeachersMenu {

    public static Teacher teacher1;

    public static List<Teacher> getTeachers1() {
        return teachersList;
    }

    public static void setTeachers1(List<Teacher> teachers) {
        PrincipleTeachersMenu.teachersList = teachers;
    }

    private static List<Teacher> teachersList;
    @FXML
    private Button p_back_btn;
    @FXML
    private Button p_view_teacher;

    @FXML
    private Label teacher_lbl;

    @FXML
    private TableView<Teacher> p_teacher_list;

    @FXML
    private TableView<Teacher> p_teacher_list1;

    @FXML
    private TableColumn<Teacher, String> idCloumn;

    @FXML
    private TableColumn<Teacher, String> usernameCloumn;

    @FXML
    private TableColumn<Teacher, String> idCloumn1;

    @FXML
    private TableColumn<Teacher, String> usernameCloumn1;

    @FXML
    private void initialize () throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetListOfTeachers"));
        } catch (IOException e){
            e.printStackTrace();
        }
        if (teachersList == null) {
            teachersList = App.getTeacherList();
            if (teachersList == null) {
                System.out.println("Teacher list is not available!");
                return; // Abort initialization if the list is not available
            }
        }
        System.out.println(teachersList.size());

        for(int i=0;i<teachersList.size();i++)
        {
            System.out.println("BBBBBBB");

            System.out.println(teachersList.get(i).getUserName());
        }
        ObservableList<Teacher> names = FXCollections.observableArrayList(teachersList);
        p_teacher_list.setItems(names); // Set the items (rows) for the TableView
        p_teacher_list1.setItems(names);

        usernameCloumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("userName"));

        idCloumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("id"));

        usernameCloumn1.setCellValueFactory(new PropertyValueFactory<Teacher,String>("userName"));

        idCloumn1.setCellValueFactory(new PropertyValueFactory<Teacher,String>("id"));
        //p_student_list.getColumns().addAll(usernameCloumn, idCloumn);
    }
    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_stat_menu");
    }

    @FXML
    void view_teacher_stats(ActionEvent event) throws IOException {
        ////////////////////////////////
        App.setRoot("principle_teacher_exam_menu");
    }

}