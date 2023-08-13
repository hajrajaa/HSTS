package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PrincipleStudentsMenu {

    public static List<Student> getStudents1() {
        return studentsList;
    }

    public static void setStudents1(List<Student> students) {
        PrincipleStudentsMenu.studentsList = students;
    }

    private static List<Student> studentsList;

    public static Student getSelectedStudent() {
        return selectedStudent;
    }

    public static void setSelectedStudent(Student selectedStudent) {
        PrincipleStudentsMenu.selectedStudent = selectedStudent;
    }

    private static Student selectedStudent;

    @FXML
    private Button p_back_btn;
    @FXML
    private Button p_view_student;

    @FXML
    private TableView<Student> p_student_list;

    @FXML
    private TableView<Student> p_student_list1;

    @FXML
    private TableColumn<Student, String> idCloumn;

    @FXML
    private TableColumn<Student, String> usernameCloumn;

    @FXML
    private TableColumn<Student, String> idCloumn1;

    @FXML
    private TableColumn<Student, String> usernameCloumn1;

    @FXML
    private void initialize () throws IOException {

        assert p_back_btn != null : "fx:id=\"p_back_btn\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert p_view_student != null : "fx:id=\"p_view_student\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert p_student_list != null : "fx:id=\"p_student_list\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert p_student_list1 != null : "fx:id=\"p_student_list1\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert idCloumn != null : "fx:id=\"idCloumn\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert usernameCloumn != null : "fx:id=\"usernameCloumn\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert idCloumn1 != null : "fx:id=\"idCloumn1\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        assert usernameCloumn1 != null : "fx:id=\"usernameCloumn1\" was not injected: check your FXML file 'principle_students_menu.fxml'.";
        System.out.println("ssssasasasas");
        p_view_student.setDisable(true);
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#GetListOfStudents"));
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        if (studentsList == null) {
            studentsList = App.getStudentList();
            if (studentsList == null) {
                //System.out.println("Student list is not available!");
                return; // Abort initialization if the list is not available
            }
        }
        System.out.println(studentsList.size());

//        for(int i=0;i<studentsList.size();i++)
//        {
//            System.out.println("BBBBBBB");
//
//            System.out.println(studentsList.get(i).getUserName());
//        }
        ObservableList<Student> names = FXCollections.observableArrayList(studentsList);
        p_student_list.setItems(names); // Set the items (rows) for the TableView
        p_student_list1.setItems(names);

        usernameCloumn.setCellValueFactory(new PropertyValueFactory<Student,String>("userName"));

        idCloumn.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));

        usernameCloumn1.setCellValueFactory(new PropertyValueFactory<Student,String>("userName"));

        idCloumn1.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
//        //p_student_list.getColumns().addAll(usernameCloumn, idCloumn);
    }

//    @FXML
//    public void doit() throws IOException {
////        try {
////            SimpleClient.getClient().sendToServer(new Message("#GetListOfStudents"));
////        } catch (IOException e){
////            e.printStackTrace();
////        }
//        if (studentsList == null) {
//            studentsList = App.getStudentList();
//            if (studentsList == null) {
//                //System.out.println("Student list is not available!");
//                return; // Abort initialization if the list is not available
//            }
//        }
//        System.out.println(studentsList.size());
//
////        for(int i=0;i<studentsList.size();i++)
////        {
////            System.out.println("BBBBBBB");
////
////            System.out.println(studentsList.get(i).getUserName());
////        }
//        ObservableList<Student> names = FXCollections.observableArrayList(studentsList);
//        p_student_list.setItems(names); // Set the items (rows) for the TableView
//        p_student_list1.setItems(names);
//
//        usernameCloumn.setCellValueFactory(new PropertyValueFactory<Student,String>("userName"));
//
//        idCloumn.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
//
//        usernameCloumn1.setCellValueFactory(new PropertyValueFactory<Student,String>("userName"));
//
//        idCloumn1.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
//        //
//    }

    @FXML
    public void go_back(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_stat_menu");
    }

    @FXML
    public void view_student_stats(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_student_info_display");
    }

    @FXML
    public void save_item(){
        selectedStudent = p_student_list.getSelectionModel().getSelectedItem();
        p_view_student.setDisable(false);
        System.out.println(selectedStudent.getUserName());
    }
}
