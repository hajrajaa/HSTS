package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public SimpleClient client;

    public static User user;

    public  static Exam exam;

    public  static List<Student> studentList;

    public  static List<Teacher> teacherList;

    public static ArrayList<ExecutedExam> getStudentExecutedExamsList() {
        return studentsExecutedExams;
    }

    public static ExecutedExamInfo[] teacherExamInfo;

    public static ExecutedExamInfo[] getTeacherExamInfo() {
        return teacherExamInfo;
    }

    public static void setTeacherExamInfo(ExecutedExamInfo[] teacherExamInfo) {
        App.teacherExamInfo = teacherExamInfo;
    }

    public static void setStudentExecutedExamsList(ArrayList<ExecutedExam> studentsExecutedExams) {
        App.studentsExecutedExams = studentsExecutedExams;
    }

    public  static ArrayList<ExecutedExam> studentsExecutedExams;

    public static ArrayList<ExecutedExamInfo> getTeacherExecutedExamInfo() {
        return teacherExecutedExamInfo;
    }

    public static void setTeacherExecutedExamInfo(ArrayList<ExecutedExamInfo> teacherExecutedExamInfo) {
        App.teacherExecutedExamInfo = teacherExecutedExamInfo;
    }

    public  static ArrayList<ExecutedExamInfo> teacherExecutedExamInfo;

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void setStudentList(List<Student> studentList) {
        App.studentList = studentList;
    }

    public static List<Teacher> getTeacherList() {
        return teacherList;
    }

    public static void setTeacherList(List<Teacher> teacherList) {
        App.teacherList = teacherList;
    }


    public static Exam getExamByCode() {return exam;}

    public static void setExamBycode(Exam exam) {
        App.exam = exam;
    }


    public static Exam getExam() {
        return exam;
    }

    public static void setExam(Exam ex) {
        App.exam = ex;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    public static void setButtonColor(Button B, String color)
    {
        String colorID = "#ffffff"; // default white
        if(color.equals("black")){
            colorID = "#000000";
        } else if (color.equals("orange")) {
            colorID = "#e28743";
        } else if (color.equals("dblue")) {
            colorID = "#063970";
        } else if (color.equals("lblue")) {
            colorID = "#abdbe3";
        } else if (color.equals("green")) {
            colorID = "#34b048";
        } else if (color.equals("red")) {
            colorID = "#ff0404";
        } else if (color.equals("gray")) {
            colorID = "#c5c5c5";
        }
        B.setStyle("-fx-background-color: " + colorID);
    }


    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("login1"), 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    	
    }

    @Subscribe
    public void loginEventfunc(loginEvent event)
    {
        setUser(event.getUser());
        changeScene();
    }

//    @Subscribe
//    public  void solveExamEventfunc(SolveExamEvent event)
//    {
//        setExam(event.getExam());
//        Platform.runLater(()->{
//                    try
//                    {
//                        //// change the page name
//                        scene.setRoot(loadFXML("solve_exam"));
//                    }catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//        );
//
//    }

    @Subscribe
    public void ShowStudentsEvent(ShowStudentsEvent event)
    {
        setStudentList(event.getStudentList());
        List<Student> students=getStudentList();
        Platform.runLater(()->{
                    try
                    {
                        scene.setRoot(loadFXML("principle_student_menu"));
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }

//    @Subscribe
//    public void getUserResponse(getUserEvent event)
//    {
//        setUser(event.getUser());
//        GetGradeController.hww(user);
//    }

    @Subscribe
    public void ShowExamEventfunc(ShowExamEvent event)
    {
        setExamBycode(event.getExamByCode());
        changeScene();
    }

    @Subscribe
    public void ShowTeachersEvent(ShowTeachersEvent event)
    {
        setTeacherList(event.getTeacherList());
        List<Teacher> Teacher=getTeacherList();
        Platform.runLater(()->{
                    try
                    {
                        scene.setRoot(loadFXML("principle_teachers_menu"));
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Subscribe
    public void ShowStudentsExecutedExamsEvent(ShowStudentsExecutedExamsEvent event)
    {
        setStudentExecutedExamsList(event.getStudentExecutedExamsList());
        System.out.println(studentsExecutedExams.get(0).getExamNum());
        ArrayList<ExecutedExam> studentsExecutedExams=getStudentExecutedExamsList();
        Platform.runLater(()->{
                    try
                    {
                        scene.setRoot(loadFXML("principle_student_info_display"));
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Subscribe
    public void ShowTeachersExamInfoDetailsEvent(ShowTeachersExamInfoDetailsEvent event)
    {
        setTeacherExamInfo(event.getTeachersExamInfoDetailsEvent());
        System.out.println(teacherExamInfo[0].getCode());
        System.out.println(teacherExamInfo[1].getCode());
        ExecutedExamInfo[] studentsExecutedExams=getTeacherExamInfo();
        Platform.runLater(()->{
                    try
                    {
                        scene.setRoot(loadFXML("principle_teachers_info_display"));
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Subscribe
    public void ShowTeachersExamInfoEvent(ShowTeachersExamInfoEvent event)
    {
        setTeacherExecutedExamInfo(event.getTeachersExamInfoEvent());
        System.out.println(teacherExecutedExamInfo.get(0).getCode());
        ArrayList<ExecutedExamInfo> teacherExecutedExamInfo=getTeacherExecutedExamInfo();
        Platform.runLater(()->{
                    try
                    {
                        scene.setRoot(loadFXML("principle_teacher_exam_menu"));
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void changeScene()
    {
        String fxmlFile1;

        switch (user.getType())
        {
            case Student:
                fxmlFile1="studentMain";
                break;

            case Teacher:
                fxmlFile1="teacherMain";
                break;

            case Princiaple:
                fxmlFile1="principle_homepage";
                break;
            default:
                return;
        }

        Platform.runLater(()->{
            try
            {
                scene.setRoot(loadFXML(fxmlFile1));
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        );
    }

	public static void main(String[] args) {
        launch();
    }

}