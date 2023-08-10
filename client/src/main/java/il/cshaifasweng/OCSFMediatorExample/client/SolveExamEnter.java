package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class SolveExamEnter
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    Button Start_Button;

    @FXML
    Text error_bar_text;

    @FXML
    TextField ExamCode_TextField, Password_TextField;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
        error_bar_text.setText("Please Enter The Exam Code");
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
    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        App.setRoot("studentMain");
    }

    public void msin() {
        setButtonColor(Start_Button,"green");
    }
    public void msout() {
        setButtonColor(Start_Button,"orange");
    }

    public void Start_Click(ActionEvent actionEvent)
    {
        String ecode = ExamCode_TextField.getText().toString();
        String pass = Password_TextField.getText().toString();

        if(ecode.equals("")){error_bar_text.setText("Please Enter The Exam Code");}
        else if(!isNumber(ecode)){error_bar_text.setText("Exam Code Must Be a Number");}
        else if(ecode.length() != 5){error_bar_text.setText("Exam Code Must 5 Digits");}
        else if(pass.equals("")){error_bar_text.setText("Please Enter The Exam Password");}
        else {
            Object[] obj = {ecode, pass};
            try {
                SimpleClient.getClient().sendToServer(new Message("#StartSolveExam", obj));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
