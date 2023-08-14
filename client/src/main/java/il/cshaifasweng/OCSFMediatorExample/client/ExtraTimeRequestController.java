package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExtraTimeRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraTimeRequestController {
    @FXML
    private Button view_Button, sendRequest_Button;
    @FXML
    private TextField code_TextField, password_TextField, extraTime_TextField;
    @FXML
    private TextArea reason_TextArea;
    @FXML
    private Text reason_Text, extraTime_Text, minutes_Text, currentTime_Text, invalidTime_Text;

    private static Exam exam;
    @FXML
    private void initialize ()
    {
        extraTime_Text.setVisible(false);
        sendRequest_Button.setVisible(false);
        minutes_Text.setVisible(false);
        extraTime_TextField.setVisible(false);
        reason_Text.setVisible(false);
        reason_TextArea.setVisible(false);
        invalidTime_Text.setVisible(false);
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
    @FXML
    public void view_click(ActionEvent actionEvent) throws IOException
    {
        String obj = code_TextField.getText().toString();
        if (!isNumber(obj) || obj.length()!=5)
        {
            currentTime_Text.setText("Invalid Exam Code!!!");
            currentTime_Text.setVisible(true);
        } else {
            try {
                SimpleClient.getClient().sendToServer(new Message("#DoesExamCodeExist", Integer.valueOf(obj)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            exam = App.getExamByCode();
            if (exam!=null)
            {
                currentTime_Text.setText("Current Time: " + exam.getTime() + " minutes");
                currentTime_Text.setFill(Paint.valueOf("#000000"));
                currentTime_Text.setVisible(true);
                extraTime_Text.setVisible(true);
                extraTime_TextField.setVisible(true);
                minutes_Text.setVisible(true);
                reason_Text.setVisible(true);
                reason_TextArea.setVisible(true);
                sendRequest_Button.setVisible(true);
            }
        }



    }

    public void sendRequest_click(ActionEvent actionEvent)
    {
        if (!isNumber(extraTime_TextField.getText()))
            invalidTime_Text.setVisible(true);
        else {
            invalidTime_Text.setVisible(false);
            Object obj = new ExtraTimeRequest(Integer.parseInt(code_TextField.getText()), Integer.parseInt(extraTime_TextField.getText()), reason_TextArea.getText());
            try {
                SimpleClient.getClient().sendToServer(new Message("#ExtraTimeRequest", obj));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
