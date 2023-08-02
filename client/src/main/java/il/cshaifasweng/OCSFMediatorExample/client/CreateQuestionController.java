package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateQuestionController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    static int correctAnswerG=0;
    @FXML
    private Button Answer1_Button, Answer2_Button, Answer3_Button, Answer4_Button;
    private Button [] answers;
    @FXML
    private TextField QuestionCode_TextField, Answer1_TextField, Answer2_TextField, Answer3_TextField, Answer4_TextField;
    @FXML
    private TextArea Question_TextArea;

    @FXML
    private Text error_bar_text;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
//        EventBus.getDefault().register(this);
        answers = new Button[4];
        initializeButtons();
    }

    private void initializeButtons ()
    {
        Answer1_Button.setDisable(false);
        Answer1_Button.setText("X");
        setButtonColor(Answer1_Button, "red");
        answers[0] = Answer1_Button;

        Answer2_Button.setDisable(false);
        Answer2_Button.setText("X");
        setButtonColor(Answer2_Button, "red");
        answers[1] = Answer2_Button;

        Answer3_Button.setDisable(false);
        Answer3_Button.setText("X");
        setButtonColor(Answer3_Button, "red");
        answers[2] = Answer3_Button;

        Answer4_Button.setDisable(false);
        Answer4_Button.setText("X");
        setButtonColor(Answer4_Button, "red");
        answers[3] = Answer4_Button;
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
            colorID = "#c5c5c5";
        }
        B.setStyle("-fx-background-color: " + colorID);
    }

    private void setCorrectAnswer (int n)
    {
        int correctAnswerNumber = n-1;
        for (int i=0; i<4; i++)
        {
            if(i == correctAnswerNumber){
                answers[i].setText("V");
                setButtonColor(answers[i], "green");
                correctAnswerG = n;
            }
            else {
                answers[i].setText("X");
                setButtonColor(answers[i], "red");
            }
        }
    }

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

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Talk To Server /////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////


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

    public void save_click(ActionEvent actionEvent)
    {
        String s_question_code = QuestionCode_TextField.getText().toString();
        String s_question = Question_TextArea.getText().toString();
        String s_answer1 = Answer1_TextField.getText().toString();
        String s_answer2 = Answer2_TextField.getText().toString();
        String s_answer3 = Answer3_TextField.getText().toString();
        String s_answer4 = Answer4_TextField.getText().toString();
        List<String> l_answers = new ArrayList<String>();
        l_answers.add(s_answer1);
        l_answers.add(s_answer2);
        l_answers.add(s_answer3);
        l_answers.add(s_answer4);

        if(s_question_code.equals(""))
        {
            error_bar_text.setText("Please Fill Question Code");
        }
        else if(s_question_code.length() != 6)
        {
            error_bar_text.setText("Question Code Must Be 6 Digits");
        }
        else if(!isNumber(s_question_code))
        {
            error_bar_text.setText("Question Code Must Be a Number");
        }
        else if (s_question.equals(""))
        {
            error_bar_text.setText("Please Fill a Question");
        }
        else if (s_answer1.equals("") || s_answer2.equals("") ||
                s_answer3.equals("") || s_answer4.equals(""))
        {
            error_bar_text.setText("Please Fill All Answers");
        }
        else if ((correctAnswerG<1) || (correctAnswerG>4))
        {
            error_bar_text.setText("Please Choose a Correct Answers");
        }
        else
        {
            error_bar_text.setText("Saving Question...");
            Question question = new Question (Integer.valueOf(s_question_code), s_question, l_answers.toArray(new String[0]), correctAnswerG);
            try {
                SimpleClient.getClient().sendToServer(new Message("#CreateQusetionRequest", question));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//
//            sendMessage("add new question", question);
        }

    }

}
