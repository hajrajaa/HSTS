package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.List;
import java.util.Objects;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ManualExam {
    @FXML
    Text error_bar_text, exam_name_text;
    StringBuilder documentContent = new StringBuilder();
    String downloaded = "no", uploaded = "no", submitted = "no";
    @FXML
    public  void initialize() {
        Exam mExam = App.getExam();
        exam_name_text.setText(mExam.getTitle());
        // Create title
        documentContent.append(mExam.getTitle()).append("\n\n");

        // Placeholder for questions and answers
        List<ExamQuestion> questions = mExam.getExamQuestion();


        String[] answers = new String[4];

        int questionNumber = 0;
        char ch = 'a';
        for (ExamQuestion q : questions) {
            // Create question
            questionNumber++;
            documentContent.append("Question " + questionNumber + ": " + q.getQuestion().getQuestion()).append("\n");
            answers = q.getQuestion().getAnswers();
            ch = 'a';
            // Create answers
            for (int i=0; i<4; i++) {
                documentContent.append(ch + ". " + answers[i]).append("\n");
                ch++;
            }

            // Add spacing between questions
            documentContent.append("\n");
        }

    }


    public void download_Action(ActionEvent actionEvent) {
        downloaded = "yes";
        // Save the document to a file
        try (FileOutputStream out = new FileOutputStream("target/" + "exam_document.docx")) {
            byte[] contentBytes = documentContent.toString().getBytes();
            out.write(contentBytes);
            error_bar_text.setText("Exam document downloaded successfully.");
        } catch (IOException e) {
            System.err.println("Error creating the document: " + e.getMessage());
        }
    }

    public void upload_Action(ActionEvent actionEvent) {
        if (Objects.equals(downloaded, "no"))
        {
            error_bar_text.setText("Invalid Upload! Please download first");
        }
        else {
            uploaded = "yes";
        }

    }

    public void submit_click(ActionEvent actionEvent) {
        if (Objects.equals(uploaded, "no"))
        {
            error_bar_text.setText("Invalid Submission! You must upload a file before submitting");
        }
        else {
            submitted = "yes";
        }
    }
}
