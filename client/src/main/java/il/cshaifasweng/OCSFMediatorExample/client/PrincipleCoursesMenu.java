package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipleCoursesMenu {

//    public SimpleClient client;

//    public static List<ExecutedExamInfo> courseInfo;

//    private static ExecutedExamInfo chosenInfo;
//
//    private static ExecutedExamInfo chosenInfo2;

    private static List<Course> courseList;

    private static Course chosenCourse;

//    private static ArrayList<ExecutedExamInfo> courseInfoList;

    @FXML
    private Label courses_lbl;


    @FXML
    private Label enter_course_lbl;

    @FXML
    private TableView<Course> exam_table;


    @FXML
    private TableColumn<Course, String> id_col;


    @FXML
    private Button p_back_btn;

    @FXML
    private Button p_view_btn;

    @FXML
    private TableColumn<Course, String> title_col;


    @FXML
    private void initialize () throws IOException {

        p_view_btn.setDisable(true);

        if (courseList == null) {
            courseList = App.getCourseList();
            if (courseList == null) {
                System.out.println("Course list is not available!");
                return; // Abort initialization if the list is not available
            }
        }

        ArrayList<String> data = new ArrayList<>();
        if(courseList!=null) {
            for(Course c : courseList){
                System.out.println("TTTTTTTTTTTTTTTTT");
                System.out.println(c.getCourseName());
                data.add(c.getCourseName());
            }
        }

        System.out.println("boop");
        ObservableList<Course> names = FXCollections.observableArrayList(courseList);
        exam_table.setItems(names);
        System.out.println("woopi");

        id_col.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        title_col.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
    }

//    @FXML
//    void filter_info(ActionEvent event) throws IOException {
//        String chosenCourse = drop_down_list.getSelectionModel().getSelectedItem();
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#GetInfoOfCourse",chosenCourse));
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        List<ExecutedExamInfo> CourseInfoList = courseInfo;
//
//        if(courseInfo!=null){
//            for(ExecutedExamInfo i : courseInfo)
//            {
//                System.out.println(i.getTitle());
//            }
//        }
//
////        ObservableList<ExecutedExamInfo> names = FXCollections.observableList(courseInfo);
////        exam_table.setItems(names);
////
////        title_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("title"));
////        password_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("password"));
////        id_col.setCellValueFactory(new PropertyValueFactory<ExecutedExamInfo, String>("code"));
//
//    }

    @FXML
    void go_back(ActionEvent event) throws IOException {
        App.setRoot("principle_stat_menu");
    }

    @FXML
    void view_course_exam(ActionEvent event) throws IOException {
        try {
            System.out.println("we are trying to send a msg");
            SimpleClient.getClient().sendToServer(new Message("#GetInfoOfCourse", chosenCourse.getId()));
            System.out.println("msg sent");
        } catch (IOException e){
            System.out.println("we are trying to send a msg but no luck");
            e.printStackTrace();
        }

        App.setRoot("principle_course_info_list");
    }

    @FXML
    void save_item(){
        chosenCourse = exam_table.getSelectionModel().getSelectedItem();
        p_view_btn.setDisable(false);
        System.out.println(chosenCourse.getCourseName());
    }

//    public static ExecutedExamInfo getChosenInfo() {
//        return chosenInfo;
//    }
//
//    public static void setChosenInfo(ExecutedExamInfo chosenInfo) {
//        PrincipleCoursesMenu.chosenInfo = chosenInfo;
//    }
//
//    public static ExecutedExamInfo getChosenInfo2() {
//        return chosenInfo2;
//    }
//
//    public static void setChosenInfo2(ExecutedExamInfo chosenInfo2) {
//        PrincipleCoursesMenu.chosenInfo2 = chosenInfo2;
//    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(List<Course> courseList) {
        PrincipleCoursesMenu.courseList = courseList;
    }

//    public static ArrayList<ExecutedExamInfo> getCourseInfoList() {
//        return courseInfoList;
//    }
//
//    public static void setCourseInfoList(ArrayList<ExecutedExamInfo> courseInfoList) {
//        PrincipleCoursesMenu.courseInfoList = courseInfoList;
//    }

//    public static List<ExecutedExamInfo> getCourseInfo() {
//        return courseInfo;
//    }
//
//    public static void setCourseInfo(List<ExecutedExamInfo> courseInfo) {
//        PrincipleCoursesMenu.courseInfo = courseInfo;
//    }

//    @Subscribe
//    public void ShowCoursesInfoEvent(ShowCoursesInfoEvent event)
//    {
//        setCourseInfo(event.getCoursesInfoEvent());
//        List<ExecutedExamInfo> chosenInfo=getCourseInfo();
//        Platform.runLater(()->{
//                    try
//                    {
//                        App.setRoot("principle_courses_menu");
//                    }catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//        );
//    }
    public static Course getChosenCourse() {
        return chosenCourse;
    }

    public static void setChosenCourse(Course chosenCourse) {
        PrincipleCoursesMenu.chosenCourse = chosenCourse;
    }

}
