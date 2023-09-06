package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class OvertimeForExamInfoController {
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private ExecutedExamInfo examInfo;
    @FXML
    TextField Minutes_TextField;

    @FXML
    TextArea Reason_TextArea;

    @FXML
    Text Title_Text, error_bar_text;

    @FXML
    Button Send_Button;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize ()
    {
        error_bar_text.setText("");
        examInfo = TeacherExuctedInfoDrawer.selectedExecutedExam;
        Send_Button.setDisable(true);

        if(examInfo != null){
            Title_Text.setText(examInfo.getTitle());
            Send_Button.setDisable(false);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void send_click(ActionEvent actionEvent) throws IOException
    {
        if(Minutes_TextField.getText().equals("")){
            error_bar_text.setText("Please Insert The Time Requested in Minutes");
        }else if (!App.isNumber(Minutes_TextField.getText())){
            error_bar_text.setText("The Time Requested Must Be A Number");
        }else if (Reason_TextArea.getText().equals("")){
            error_bar_text.setText("Please Insert The Overtime Request Reason");
        }else {
            error_bar_text.setText("Saving Request");
            int mins = Integer.valueOf(Minutes_TextField.getText());
            OvertimeRequest request = new OvertimeRequest(examInfo.getTitle(), App.getUser().getUserName(), examInfo.getId(), Reason_TextArea.getText(), mins);
            try {
                SimpleClient.getClient().sendToServer(new Message("#NewOvertimeRequest", request));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            App.setRoot("teacherMain");
        }
    }

    public void save_in() {
        App.setButtonColor(Send_Button,"green");
    }
    public void save_out() {
        App.setButtonColor(Send_Button,"orange");
    }

    public void Home_Click(ActionEvent actionEvent) throws IOException {
        App.setRoot("teacherMain");
    }
}
