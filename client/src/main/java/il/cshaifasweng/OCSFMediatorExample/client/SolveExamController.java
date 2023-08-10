package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class SolveExamController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private List<Integer> examAnswers;
    private int examLength = 0;

    private ExamQuestion currentQuestion;
    private static int currentQuestionNumber;
    private Exam exam;
    @FXML
    private Button answer1_button, answer2_button, answer3_button, answer4_button, finish_exam_button;
    private Button [] answersButtons;
    @FXML
    private TextArea question_text_area;
    @FXML
    private Text exam_name_text, date_text, question_number_text, note_text, student_note_text;
    @FXML
    ImageView note_ImageView;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
        exam = App.getExam();

        answersButtons = new Button[4];

        examAnswers = new ArrayList<Integer>();
        examLength = getQuestions().size();
        for(int i=0; i<examLength; i++)
        {
            examAnswers.add(0);
        }

        currentQuestionNumber = 0;
        loadNewQuestion(currentQuestionNumber);
    }

    private void loadNewQuestion (int questionNumber)
    {
        currentQuestion = getQuestions().get(questionNumber);
        initializeButtons();

        if (examAnswers.get(questionNumber) != 0){
            setCorrectAnswer(examAnswers.get(questionNumber));
        }

        question_text_area.setText(currentQuestion.getQuestion());
        for(int i=0; i<answersButtons.length; i++)
        {
            String tempAnswer = currentQuestion.getAnswers()[i];
            answersButtons[i].setText(tempAnswer);
        }

        String myQuestionNumber = "Question ";
        myQuestionNumber += Integer.toString(questionNumber+1);
        myQuestionNumber += "/";
        myQuestionNumber += Integer.toString(examLength);
        question_number_text.setText(myQuestionNumber);

        student_note_text.setText(currentQuestion.getStudent_note());
        if(currentQuestion.getStudent_note().equals("")){
            note_ImageView.setVisible(false);
        }else{
            note_ImageView.setVisible(true);
        }
    }

    private ArrayList<ExamQuestion> getQuestions ()
    {
        String [] s1 = {"1","2","100","pi"};
        Question q1 = new Question(111,"1+1=?", s1,2);
        ExamQuestion e1 = new ExamQuestion(q1, 25, "", "");

        String [] s2 = {"blue","green","black","red"};
        Question q2 = new Question(222,"Apples are ____", s2,4);
        ExamQuestion e2 = new ExamQuestion(q2, 25, "", "Choose The Best Answer");

        String [] s3 = {"0","10","100","1000"};
        Question q3 = new Question(333,"100*0=?", s3,1);
        ExamQuestion e3 = new ExamQuestion(q3, 25, "", "");

        ArrayList<ExamQuestion> eee = new ArrayList<ExamQuestion>();
        eee.add(e1);
        eee.add(e2);
        eee.add(e3);
        return eee;
//        ArrayList<ExamQuestion> allQuestions = new ArrayList<ExamQuestion>(exam.getQuestions());
//        return allQuestions;
    }


    private void initializeButtons ()
    {
        answer1_button.setDisable(false);
        setButtonColor(answer1_button, "gray");
        answersButtons[0] = answer1_button;

        answer2_button.setDisable(false);
        setButtonColor(answer2_button, "gray");
        answersButtons[1] = answer2_button;

        answer3_button.setDisable(false);
        setButtonColor(answer3_button, "gray");
        answersButtons[2] = answer3_button;

        answer4_button.setDisable(false);
        setButtonColor(answer4_button, "gray");
        answersButtons[3] = answer4_button;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Common /////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    private void setButtonColor (Button B, String color)
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
            colorID =  "#a5a9ab";
        }
        B.setStyle("-fx-background-color: " + colorID);
    }

    private void setCorrectAnswer (int n)
    {
        int correctAnswerNumber = n-1;
        examAnswers.set(currentQuestionNumber, n);

        for (int i=0; i<answersButtons.length; i++)
        {
            if(i == correctAnswerNumber){
                setButtonColor(answersButtons[i], "orange");
            }
            else {
                setButtonColor(answersButtons[i], "gray");
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void answer1_click(ActionEvent actionEvent) {
        setCorrectAnswer(1);
    }

    public void answer2_click(ActionEvent actionEvent) {
        setCorrectAnswer(2);
    }

    public void answer3_click(ActionEvent actionEvent) {
        setCorrectAnswer(3);
    }

    public void answer4_click(ActionEvent actionEvent) {
        setCorrectAnswer(4);
    }

    public void finish_exam_click(ActionEvent actionEvent) throws IOException {
        // TODO: Implement
//        HelloApplication.setRoot("create_question");

    }

    public void prev_button_click(ActionEvent actionEvent)
    {
        currentQuestionNumber = (currentQuestionNumber + examLength - 1 ) % examLength;
        loadNewQuestion(currentQuestionNumber);
    }
    public void next_button_click(ActionEvent actionEvent)
    {
        currentQuestionNumber = (currentQuestionNumber+1)%examLength;
        loadNewQuestion(currentQuestionNumber);
    }

    public void msin() {
        setButtonColor(finish_exam_button,"green");
    }
    public void msout() {
        setButtonColor(finish_exam_button,"orange");
    }

}
