package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import java.io.File;
import java.io.FileOutputStream;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javafx.stage.FileChooser;


public class ManualExamController {



    public  static  String ManualSolutionPath;

    Exam currExam;

    public Exam getCurrExam1() {
        return currExam;
    }

    public void setCurrExam1(Exam currExam) {
        this.currExam = currExam;
    }

    public static String getManualSolutionPath() {
        return ManualSolutionPath;
    }

    public static void setManualSolutionPath(String manualSolutionPath) {
        ManualSolutionPath = manualSolutionPath;
    }


    @FXML
    Text error_bar_text, exam_name_text;

    @FXML
    private Button download_button;

    @FXML
    private Button upload_button;


    @FXML
    public void initialize() {
        currExam = App.getExam();
        exam_name_text.setText(currExam.getTitle());

        download_button.setDisable(false);

        upload_button.setDisable(true);


    }
    @FXML
   void upload_Action(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", "*.docx"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            ManualSolutionPath = selectedFile.getAbsolutePath();
            error_bar_text.setText("Exam document uploaded successfully");
            System.out.println(ManualSolutionPath);
        }
        else
        {
            error_bar_text.setText("Error, please select a document");
        }
    }

    @FXML
   void submit_click(ActionEvent actionEvent)
    {
//        Object[] obj={currExam,ManualSolutionPath};
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#ManualExamSave_req", obj));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
   }

    public void generateWordDocument(Exam exam, List<ExamQuestion> examQuestionList, String filePath) {
        XWPFDocument document = new XWPFDocument();

        String fileName = exam.getTitle() + "-" + exam.getCodeExam() + ".docx";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();

            titleRun.setText(exam.getTitle());
            titleRun.addBreak();
            titleRun.addBreak();
            titleRun.addBreak();
            titleRun.setFontSize(24);

            int questionNumber = 1;
            for (ExamQuestion question : examQuestionList) {
                XWPFParagraph questionParagraph = document.createParagraph();
                questionParagraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun questionRun = questionParagraph.createRun();

                questionRun.setText("Question " + questionNumber + ": " + question.getQuestion());
                questionRun.addBreak();
                questionRun.setText("Note :"+question.getStudent_note());
                questionRun.addBreak();
                String[] answers = question.getQuestion().getAnswers();
                int answerCount = 1;
                for (String a : answers) {
                    questionRun.setText(answerCount + ". " + a);
                    questionRun.addBreak();
                    answerCount++;
                }
                questionRun.setText("Answer: ________________________");
                questionRun.addBreak();
                questionRun.addBreak();
                questionNumber++;
            }

            document.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void download_Exam(ActionEvent event)
    {

        List<ExamQuestion> examQuestionList = currExam.getExamQuestion();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", "*.docx"));
        String fileName = currExam.getTitle() + "-" + currExam.getCodeExam() + ".docx";
        fileChooser.setInitialFileName(fileName);
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null)
        {

            String filePath = selectedFile.getAbsolutePath();
            generateWordDocument(currExam, examQuestionList, filePath);

            upload_button.setVisible(true);
            upload_button.setDisable(false);
            download_button.setDisable(true);
            download_button.setVisible(false);
            error_bar_text.setText("Exam document downloaded successfully");
        }
        else {
            error_bar_text.setText("Something wrong, Exam document can't downloaded successfully");
        }


    }
}
