package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedExamInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;


public class TeacherDrawerController {

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
    private Button drawBtn;

    @FXML
    private Button Home_Button;

    @FXML
    private RadioButton manualTypeBtn;

    @FXML
    private RadioButton virtualTypeBtn;

    @FXML
    void Home_Click(ActionEvent event) throws IOException {
        App.setRoot("teacherMain");

    }

    @FXML
    void drawBtn(ActionEvent event) {

        error_bar_text.setText("");

        String ExecPassword=execCodeTxt.getText().toString();

        if(ExecPassword== null)
        {
            error_bar_text.setText("Please enter a exam executing password");
            execCodeTxt.clear();
        }
       else if(ExecPassword.length()!=4)
        {
            error_bar_text.setText("Please Choose Another Password ,Executing Password Must Be A 4 Digit Number");
            execCodeTxt.clear();
        }
       else {
            error_bar_text.setText("");
            Toggle selectedToggle = radioGroup.getSelectedToggle();

            if (selectedToggle != null) {
                ExecutedExamInfo.ExamType selectedType = null;
                if (selectedToggle == manualTypeBtn) {
                    selectedType = ExecutedExamInfo.ExamType.Manual;
                } else if (selectedToggle == virtualTypeBtn) {
                    selectedType = ExecutedExamInfo.ExamType.Virtual;
                }
                ExecutedExamInfo exeExam = new ExecutedExamInfo(ExamsDrawerController.getExecutedExamCode(), ExecPassword, selectedType, "", (Teacher) App.getUser());
                System.out.println(exeExam.getCode());
                System.out.println(ExamsDrawerController.getExecutedExamCode());

                try {
                    SimpleClient.getClient().sendToServer(new Message("#drawExamRequest", exeExam));
                    execCodeTxt.clear();
                    radioGroup.selectToggle(null);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                error_bar_text.setText("Please Choose Exam Type");
            }
        }

    }

    @FXML
    void manualTypeBtn(ActionEvent event) {}

    @FXML
    void virtualTypeBtn(ActionEvent event) {}

    @FXML
    void initialize() {
        error_bar_text.setText("");
        manualTypeBtn.setToggleGroup(radioGroup);
        virtualTypeBtn.setToggleGroup(radioGroup);

    }

}

