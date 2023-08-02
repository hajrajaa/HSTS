//package il.cshaifasweng.OCSFMediatorExample.client;
//
//
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
//import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
//import il.cshaifasweng.OCSFMediatorExample.entities.VirtualExam;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
//
//
//public class SolveExamController
//{
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    private List<Integer> examAnswers;
//    private int examLength = 0;
//
//    private ExamQuestion currentQuestion;
//    private static int currentQuestionNumber;
//    private VirtualExam vexam;
//    @FXML
//    private Button answer1_button, answer2_button, answer3_button, answer4_button;
//    private Button [] answersButtons;
//    @FXML
//    private TextArea question_text_area;
//
//    @FXML
//    private Text exam_name_text, date_text, question_number_text, note_text;
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    @FXML
//    private void initialize ()
//    {
//        vexam = App.getExam();
//
//        answersButtons = new Button[4];
//
//        examAnswers = new ArrayList<Integer>();
//        examLength = getQuestions().size();
//        for(int i=0; i<examLength; i++)
//        {
//            examAnswers.add(0);
//        }
//
//        currentQuestionNumber = 0;
//        loadNewQuestion(currentQuestionNumber);
//    }
//
//    private void loadNewQuestion (int questionNumber)
//    {
//        currentQuestion = getQuestions().get(questionNumber);
//        initializeButtons();
//
//        if (examAnswers.get(questionNumber) != 0){
//            setCorrectAnswer(examAnswers.get(questionNumber));
//        }
//
//        question_text_area.setText(currentQuestion.getQuestion());
//        for(int i=0; i<answersButtons.length; i++)
//        {
//            String tempAnswer = currentQuestion.getAnswers()[i];
//            answersButtons[i].setText(tempAnswer);
//        }
//
//        String myQuestionNumber = "Question ";
//        myQuestionNumber += Integer.toString(questionNumber+1);
//        myQuestionNumber += "/";
//        myQuestionNumber += Integer.toString(examLength);
//        question_number_text.setText(myQuestionNumber);
//    }
//
//    private ArrayList<ExamQuestion> getQuestions ()
//    {
////        String [] s1 = {"1","2","100","pi"};
////        ExamQuestion e1 = new ExamQuestion("111","1+1=?", Arrays.asList(s1),2);
////
////        String [] s2 = {"blue","green","black","red"};
////        ExamQuestion e2 = new ExamQuestion("222","Apples are ____", Arrays.asList(s2),4);
////
////        String [] s3 = {"0","10","100","1000"};
////        ExamQuestion e3 = new ExamQuestion("333","100*0=?", Arrays.asList(s3),1);
////
////        ArrayList<ExamQuestion> eee = new ArrayList<ExamQuestion>();
////        eee.add(e1);
////        eee.add(e2);
////        eee.add(e3);
////        return eee;
//    //    ArrayList<ExamQuestion> allQuestions = new ArrayList<ExamQuestion>(vexam.getQuestions());
//      //  return allQuestions;
//    }
//
//
//    private void initializeButtons ()
//    {
//        answer1_button.setDisable(false);
//        setButtonColor(answer1_button, "gray");
//        answersButtons[0] = answer1_button;
//
//        answer2_button.setDisable(false);
//        setButtonColor(answer2_button, "gray");
//        answersButtons[1] = answer2_button;
//
//        answer3_button.setDisable(false);
//        setButtonColor(answer3_button, "gray");
//        answersButtons[2] = answer3_button;
//
//        answer4_button.setDisable(false);
//        setButtonColor(answer4_button, "gray");
//        answersButtons[3] = answer4_button;
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
//    private void setCorrectAnswer (int n)
//    {
//        int correctAnswerNumber = n-1;
//        examAnswers.set(currentQuestionNumber, n);
//
//        for (int i=0; i<answersButtons.length; i++)
//        {
//            if(i == correctAnswerNumber){
//                setButtonColor(answersButtons[i], "orange");
//            }
//            else {
//                setButtonColor(answersButtons[i], "gray");
//            }
//        }
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Talk To Server /////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
////    private void sendMessage (String op, Object obj)
////    {
////        try {
////            Message message = new Message(op, obj);
////            SimpleClient.getClient().sendToServer(message);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Subscribe
////    public void newMessageArrived (Message message) throws IOException {
////        String replay = message.getMessage();
////        Object obj = message.getObject1();
////
////        if(replay.equals("Error")){ // connection error
//////            updateStudentInfo(tempStudent);
//////            errorBar.setText("ERROR! cannot connect to server");
////        }
////        else if (replay.equals("added question")){
////            // obj should be Null
////            SimpleClient.getClient().switchPage("teacher_page");
////        }
////    }
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
//    public void answer1_click(ActionEvent actionEvent) {
//        setCorrectAnswer(1);
//    }
//
//    public void answer2_click(ActionEvent actionEvent) {
//        setCorrectAnswer(2);
//    }
//
//    public void answer3_click(ActionEvent actionEvent) {
//        setCorrectAnswer(3);
//    }
//
//    public void answer4_click(ActionEvent actionEvent) {
//        setCorrectAnswer(4);
//    }
//
//    public void finish_exam_click(ActionEvent actionEvent) throws IOException {
//        // TODO: Implement
////        HelloApplication.setRoot("create_question");
//
//    }
//
//    public void prev_button_click(ActionEvent actionEvent)
//    {
//        currentQuestionNumber = (currentQuestionNumber + examLength - 1 ) % examLength;
//        loadNewQuestion(currentQuestionNumber);
//    }
//    public void next_button_click(ActionEvent actionEvent)
//    {
//        currentQuestionNumber = (currentQuestionNumber+1)%examLength;
//        loadNewQuestion(currentQuestionNumber);
//    }
//
//}
