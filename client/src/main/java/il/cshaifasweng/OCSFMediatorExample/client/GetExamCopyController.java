package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedVirtual;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetExamCopyController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private ExecutedVirtual exam;
    private int examLength = 0;
    private ExamQuestion currentQuestion;
    private static int currentQuestionNumber;
    @FXML
    private Button answer1_button, answer2_button, answer3_button, answer4_button, finish_exam_button;
    private Button [] answersButtons;
    @FXML
    private Text exam_name_text, date_text, question_number_text, question_text, student_note_text, grade_text;
    @FXML
    ImageView note_ImageView;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
        exam = App.getExamCopy();

        answersButtons = new Button[4];

        exam_name_text.setText(exam.getTitle());
        date_text.setText(exam.getExamDate());
        loadGrade();

        if(exam.getExam().getExamQuestion() != null){
            examLength = exam.getExam().getExamQuestion().size();
        }

        currentQuestionNumber = 0;
        loadNewQuestion(currentQuestionNumber);
    }

    private void initializeButtons ()
    {
        answer1_button.setDisable(false);
        App.setButtonColor(answer1_button, "gray");
        answersButtons[0] = answer1_button;

        answer2_button.setDisable(false);
        App.setButtonColor(answer2_button, "gray");
        answersButtons[1] = answer2_button;

        answer3_button.setDisable(false);
        App.setButtonColor(answer3_button, "gray");
        answersButtons[2] = answer3_button;

        answer4_button.setDisable(false);
        App.setButtonColor(answer4_button, "gray");
        answersButtons[3] = answer4_button;
    }

    private void loadNewQuestion (int questionNumber)
    {
        currentQuestion = exam.getExam().getExamQuestion().get(questionNumber);
        initializeButtons();
        loadQuestionNumber(questionNumber);
        loadNote();

        question_text.setText(currentQuestion.getQuestion().getQuestion());
        for(int i=0; i<answersButtons.length; i++)
        {
            String tempAnswer = currentQuestion.getQuestion().getAnswers()[i];
            answersButtons[i].setText(tempAnswer);
        }

        int studentAnswer = exam.getSolutions().get(questionNumber);
        if(studentAnswer>=1 && studentAnswer<=4){
            App.setButtonColor(answersButtons[studentAnswer-1], "red");
        }else{
            for (Button button : answersButtons){
                App.setButtonColor(button, "red");
            }
        }
        App.setButtonColor(answersButtons[currentQuestion.getQuestion().getCorrect_answer()-1], "green");
    }

    private void loadQuestionNumber (int questionNumber)
    {
        String myQuestionNumber = "Question ";
        myQuestionNumber += Integer.toString(questionNumber+1);
        myQuestionNumber += "/";
        myQuestionNumber += Integer.toString(examLength);
        question_number_text.setText(myQuestionNumber);
    }

    private void loadNote ()
    {
        student_note_text.setText(currentQuestion.getStudent_note());
        if(currentQuestion.getStudent_note().equals("")){
            note_ImageView.setVisible(false);
        }else{
            note_ImageView.setVisible(true);
        }
    }

    private void loadGrade ()
    {
        String text = "";
        if(exam.isMarked()){
            text = "Grade: " + Double.toString(exam.getGrade());
        }else {
            text = "No Grade Yet";
        }
        grade_text.setText(text);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void finish_exam_click(ActionEvent actionEvent) throws IOException
    {
        App.setRoot("studentMain");
    }

    public void prev_button_click(ActionEvent actionEvent)
    {
        currentQuestionNumber = (currentQuestionNumber + examLength - 1 ) % examLength;
        loadNewQuestion(currentQuestionNumber);
    }
    public void next_button_click(ActionEvent actionEvent)
    {
        currentQuestionNumber = (currentQuestionNumber + 1) % examLength;
        loadNewQuestion(currentQuestionNumber);
    }

    public void msin() {
        App.setButtonColor(finish_exam_button,"green");
    }
    public void msout() {
        App.setButtonColor(finish_exam_button,"orange");
    }

}
