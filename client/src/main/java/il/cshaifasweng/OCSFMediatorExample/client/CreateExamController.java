package il.cshaifasweng.OCSFMediatorExample.client;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExamController
{
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    static int numOfQuestions=0;
    //    private List<ExamQuestion> questionsList;
    @FXML
    private TextField Title_TextField, Code_TextField, Time_TextField;
    @FXML
    private Text error_bar_text;
    @FXML
    private VBox Vbox;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
//        EventBus.getDefault().register(this);
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



    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void add_question_click(ActionEvent actionEvent) throws IOException {
        // TODO Get Question: examQuestionObj
//        questionsList.add(examQuestionObj);
        numOfQuestions++;
        Node newQuestion = FXMLLoader.load(getClass().getResource("src/question_item.fxml"));
        Vbox.getChildren().add(newQuestion);
    }


    public void finish_exam_click(ActionEvent actionEvent) {
    }
}
