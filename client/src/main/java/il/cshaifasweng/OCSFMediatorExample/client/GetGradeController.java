package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetGradeController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField Code_TextField;
    @FXML
    private Text error_bar_text;

    @FXML
    private TableColumn<ExamQuestion, String> Code_Column, Question_Column, Points_Column;

    public static User user;
    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        GetGradeController.user = user;
    }

    public SimpleClient client;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize () throws IOException {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

//        answersButtons = new Button[4];
//        selectQuestionFlag = false;
//        removeColumnFlag = true;
        error_bar_text.setText("");
//        initializeButtons();
//        allExamQuestions = new ArrayList<ExamQuestion>();
//        allQuestions = getQuestions();
//        initSelectQuestionComboBox();
    }

    private void initializeButtons ()
    {
//        Answer1.setDisable(false);
//        setButtonColor(Answer1, "orange");
//        answersButtons[0] = Answer1;
//
//        Answer2.setDisable(false);
//        setButtonColor(Answer2, "orange");
//        answersButtons[1] = Answer2;
//
//        Answer3.setDisable(false);
//        setButtonColor(Answer3, "orange");
//        answersButtons[2] = Answer3;
//
//        Answer4.setDisable(false);
//        setButtonColor(Answer4, "orange");
//        answersButtons[3] = Answer4;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Common /////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

//    private void setButtonColor (Button B, String color)
//    {
//        String colorID = "#ffffff"; // default white
//        if(color.equals("black")){
//            colorID = "#000000";
//        } else if (color.equals("orange")) {
//            colorID = "#e28743";
//        } else if (color.equals("dblue")) {
//            colorID = "#063970";
//        } else if (color.equals("lblue")) {
//            colorID = "#abdbe3";
//        } else if (color.equals("green")) {
//            colorID = "#34b048";
//        } else if (color.equals("red")) {
//            colorID = "#ff0404";
//        } else if (color.equals("gray")) {
//            colorID = "#c5c5c5";
//        }
//        B.setStyle("-fx-background-color: " + colorID);
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Talk To Server /////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

//    private void sendMessage (String op, Object obj)
//    {
//        try {
//            Message message = new Message(op, obj);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Subscribe
//    public void newMessageArrived (Message message)
//    {
//        String replay = message.getMessage();
//        Object obj = message.getObject1();
//
//        if(replay.equals("Error")){ // connection error
////            updateStudentInfo(tempStudent);
////            errorBar.setText("ERROR! cannot connect to server");
//        }
//        else if (replay.equals("added question")){
//            // obj should be Null
//            SimpleClient.getClient().switchPage("teacher_page");
//        }
//    }

//    public boolean isNumber (String s)
//    {
//        char [] arr = s.toCharArray();
//        for(char c : arr){
//            if((c < '0') || (c > '9')){
//                return false;
//            }
//        }
//        return true;
//    }


    public boolean isNumber (String s)
    {
        char [] arr = s.toCharArray();
        for(char c : arr){
            if((c < '0') || (c > '9')){
                return false;
            }
        }
        return true;
    }

    private boolean validateExamCode (String code)
    {
        if (!isNumber(code)){
            error_bar_text.setText("Exam Code Must Be a Number");
            return false;
        }
        if (code.length() != 5){
            error_bar_text.setText("Exam Code Must Be 5 Digits");
            return false;
        }
        return true;
    }

//    private boolean validatePoints (String points)
//    {
//        if (points.equals("")){
//            error_bar_text.setText("Please Fill Question Points");
//            return false;
//        }
//        if (!isNumber(points)){
//            error_bar_text.setText("Question Points Must Be a Number");
//            return false;
//        }
//        int Npoints = Integer.valueOf(points);
//        if(Npoints < 0){
//            error_bar_text.setText("Question Points Must Be Higher Than 0");
//            return false;
//        }
//        if(Npoints > 100){
//            error_bar_text.setText("Question Points Must Be Less Than 100");
//            return false;
//        }
//        return true;
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void getUserResponse(getUserEvent event)
    {
        setUser(event.getUser());
        error_bar_text.setText("Hello " + user.getUserName());
    }

//    public static void hww(User x)
//    {
//        setUser(x);
//        error_bar_text.setText("Hello " + user.getUserName());
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void finish_exam_click(ActionEvent actionEvent)
    {
//        String title = Title_TextField.getText().toString();
//        String code = Code_TextField.getText().toString();
//        String time = Time_TextField.getText().toString();
//        String studentDesc = Student_Desc_TextField.getText().toString();
//        String teacherDesc = Teacher_Desc_TextField.getText().toString();
//
//        if (title.equals("")) {error_bar_text.setText("Please Enter The Exam Name");}
//        else if (code.equals("")) {error_bar_text.setText("Please Enter The Exam Cade");}
//        else if (!isNumber(code)){
//            error_bar_text.setText("Exam Code Must Be a Number");
//        }
//        else if (code.length() != 5){
//            error_bar_text.setText("Exam Code Must Be 5 Digits");
//        }
//        else if (time.equals("")) {error_bar_text.setText("Please Enter The Exam Time");}
//        else if (!isNumber(time)){
//            error_bar_text.setText("Exam Time Must Be a Number");
//        }
//        else{
//            error_bar_text.setText("Creating Exam ...");
//            //        (int time, int codeExam, String descForStudent, String descForTeacher, Teacher teacher, String type)
////        public VirtualExam(String title, int codeExam, int time, List<ExamQuestion> examQuestions, String descForStudent, String descForTeacher, Teacher teacher)
////        {
////            super(time, codeExam, descForStudent, descForTeacher, teacher, "Virtual");
////            this.examQuestion=new ArrayList<ExamQuestion>();
////        }
////        Teacher teacher = (Teacher) App.getUser();
////        VirtualExam exam = new VirtualExam (title, code, time, allExamQuestions, studentDesc, teacherDesc, teacher, "Virtual");
//        }
    }

    public void QuestionSelected(ActionEvent actionEvent)
    {
//        String selectedQuestionName = SelectQuestionComboBox.getValue().toString();
//        Question selectedQuestion = findQuestion(selectedQuestionName);
//        if(selectedQuestion != null){
//            loadNewQuestion(selectedQuestion);
//        }
    }

    public void Add_Question_Click(ActionEvent actionEvent)
    {
//        String points = Points_TextField.getText().toString();
//        if(selectQuestionFlag == false){
//            error_bar_text.setText("Please Select a Question to Add ");
//        }
//        else if(validatePoints(points)){
//            int Npoints = Integer.valueOf(points);
//            String teacher_note = Teacher_Note_TextField.getText().toString();
//            String student_note = Student_Note_TextField.getText().toString();
//            allExamQuestions.add(new ExamQuestion(currentQuestion, Npoints, teacher_note, student_note));
//            refreshTable();
//            error_bar_text.setText("The Question Has Been Added To The Exam");
//        }
    }

    public void Click_Back(ActionEvent actionEvent) throws IOException
    {
        EventBus.getDefault().unregister(this);
        App.setRoot("studentMain");
    }

    public void Click_GetGrade(ActionEvent actionEvent)
    {
        String code = Code_TextField.getText().toString();
        if(validateExamCode(code)){
            User myUser = App.getUser();
            Object[] user_data = {myUser.getUserName(), myUser.getPassword()};
            try {
                SimpleClient.getClient().sendToServer(new Message("#GetUserRequest", user_data));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            try {
//                SimpleClient.getClient().sendToServer(new Message("#GetGrade", Ncode));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}