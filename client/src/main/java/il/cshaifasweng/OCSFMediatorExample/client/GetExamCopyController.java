//package il.cshaifasweng.OCSFMediatorExample.client;
//
//
//import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
//import il.cshaifasweng.OCSFMediatorExample.entities.Message;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.text.Text;
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GetExamCopyController
//{
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    private int examLength = 0;
//    private ExamQuestion currentQuestion;
//    private static int currentQuestionNumber;
//    private ExucetedExam execExam;
//    @FXML
//    private Button answer1_button, answer2_button, answer3_button, answer4_button;
//    private Button [] answersButtons;
//    @FXML
//    private TextArea question_text_area;
//
//    @FXML
//    private Text exam_name_text, date_text, question_number_text, Note_Text, grade_text;
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    @FXML
//    private void initialize ()
//    {
//        EventBus.getDefault().register(this);
//
//        answersButtons = new Button[4];
//
//        loadGrade(execExam.getGrade());
//        exam_name_text.setText(execExam.getName());
//        date_text.setText(execExam.getDate());
//
//        currentQuestionNumber = 0;
//        loadNewQuestion(currentQuestionNumber);
//    }
//
//    private void loadNewQuestion (int questionNumber)
//    {
//        currentQuestion = execExam.getExam().getQuestions().get(questionNumber);
//        initializeButtons();
//        loadQuestionNumber(questionNumber);
//        loadNote(currentQuestion.getNote());
//
//        question_text_area.setText(currentQuestion.getQuestion());
//        for(int i=0; i<answersButtons.length; i++)
//        {
//            String tempAnswer = currentQuestion.getPossibilities().get(i);
//            answersButtons[i].setText(tempAnswer);
//        }
//
//        setButtonColor(answersButtons[execExam.getSolutions().get(questionNumber)], "red");
//        setButtonColor(answersButtons[currentQuestion.getCorrectAnswer()], "green");
//    }
//
//    private void initializeButtons ()
//    {
//        answer1_button.setDisable(true);
//        setButtonColor(answer1_button, "gray");
//        answersButtons[0] = answer1_button;
//
//        answer2_button.setDisable(true);
//        setButtonColor(answer2_button, "gray");
//        answersButtons[1] = answer2_button;
//
//        answer3_button.setDisable(true);
//        setButtonColor(answer3_button, "gray");
//        answersButtons[2] = answer3_button;
//
//        answer4_button.setDisable(true);
//        setButtonColor(answer4_button, "gray");
//        answersButtons[3] = answer4_button;
//    }
//
//    private void loadQuestionNumber (int questionNumber)
//    {
//        String myQuestionNumber = "Question ";
//        myQuestionNumber += Integer.toString(questionNumber);
//        myQuestionNumber += "/";
//        myQuestionNumber += Integer.toString(examLength);
//        question_number_text.setText(myQuestionNumber);
//    }
//
//    private void loadNote (String note)
//    {
//        Note_Text.setText("");
//        if(!note.equals("")){
//            String text = "Note: " + note;
//            Note_Text.setText(text);
//        }
//    }
//
//    private void loadGrade (int grade)
//    {
//        String text = "Grade: " + Integer.toString(grade);
//        grade_text.setText(text);
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////// Common /////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
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
//            colorID =  "#a5a9ab";
//        }
//        B.setStyle("-fx-background-color: " + colorID);
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Talk To Server /////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
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
//    public void newMessageArrived (Message message) throws IOException {
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
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void finish_exam_click(ActionEvent actionEvent)
//    {
//        // TODO: Implement
//    }
//
//    public void prev_button_click(ActionEvent actionEvent)
//    {
//        currentQuestionNumber = (currentQuestionNumber + examLength - 1) % examLength;;
//        loadNewQuestion(currentQuestionNumber);
//    }
//    public void next_button_click(ActionEvent actionEvent)
//    {
//        currentQuestionNumber = (currentQuestionNumber + 1) % examLength;
//        loadNewQuestion(currentQuestionNumber);
//    }
//
//}
