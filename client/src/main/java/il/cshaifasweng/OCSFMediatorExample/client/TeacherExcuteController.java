package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import il.cshaifasweng.OCSFMediatorExample.client.ExamsDrawerController;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class TeacherExcuteController {


    public int getExamCode1() {
        return ExamCode;
    }

    public void setExamCode1(int examCode) {
        ExamCode = examCode;
    }

    public int ExamCode;



    private ToggleGroup radioGroup = new ToggleGroup();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane coursesBox;

    @FXML
    private Text error_bar_text;

    @FXML
    private TextField execCodeTxt;

    @FXML
    private Button executeBtn;

    @FXML
    private RadioButton manualTypeBtn;

    @FXML
    private RadioButton virtualTypeBtn;

    @FXML
    void executeBtn(ActionEvent event) {
        

        String ExecPassword=execCodeTxt.getText().toString();
        if(ExecPassword== null)
        {
            error_bar_text.setText("Please enter a exam executing password");
        }
        Toggle selectedToggle = radioGroup.getSelectedToggle();
     
        
        if(selectedToggle!=null)
        {
            ExecutedExamInfo.ExamType selectedType = null;
            if (selectedToggle == manualTypeBtn)
            {
                selectedType= ExecutedExamInfo.ExamType.Manual;
            }
            else if (selectedToggle == virtualTypeBtn)
            {
                selectedType= ExecutedExamInfo.ExamType.Virtual;
            }
            ExecutedExamInfo exeExam= new ExecutedExamInfo(getExamCode1(),ExecPassword,selectedType);

            try {
                SimpleClient.getClient().sendToServer(new Message("#ExecuteExamRequest",exeExam));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            error_bar_text.setText("Please Choose Exam Type");
        }

    }

    @FXML
    void manualTypeBtn(ActionEvent event) {

    }

    @FXML
    void virtualTypeBtn(ActionEvent event) {

    }

    @FXML
    void initialize() {

        setExamCode1(ExamsDrawerController.getExamCode());

        assert coursesBox != null : "fx:id=\"coursesBox\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert error_bar_text != null : "fx:id=\"error_bar_text\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert execCodeTxt != null : "fx:id=\"execCodeTxt\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert executeBtn != null : "fx:id=\"executeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert manualTypeBtn != null : "fx:id=\"manualTypeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";
        assert virtualTypeBtn != null : "fx:id=\"virtualTypeBtn\" was not injected: check your FXML file 'execute_exam.fxml'.";

        manualTypeBtn.setToggleGroup(radioGroup);
        virtualTypeBtn.setToggleGroup(radioGroup);


    }

}

