package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import java.io.File;
import java.io.FileOutputStream;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.stage.FileChooser;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ManualExamController {

    public  static  String ManualSolutionPath;

    public SimpleClient client;
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
    Text error_bar_text, exam_name_text, date_text, clock_text, extra_time_text;

    @FXML
    private Button download_button;

    @FXML
    private Button upload_button;

    private Timeline clock;
    @FXML
    ImageView clock_0,clock_1,clock_2,clock_3,clock_4,clock_5,clock_6,clock_7,clock_8;
    ImageView[] clks;
    private int clks_counter, extraTime;
    private LocalTime startTime;
    private boolean timeUpFlag;

    @FXML
    public void initialize() throws IOException
    {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        currExam = App.getExam();
        ManualSolutionPath = "";
        exam_name_text.setText(currExam.getTitle());
        download_button.setDisable(false);
        upload_button.setDisable(true);
        date_text.setText(App.getDate());

        timeUpFlag = true;
        extraTime = App.overtimeInSolvingExam;
        setExtraTime();
        startTime = LocalTime.now();
        initClock(currExam.getTime());
    }

    private void initClock(int duration)
    {
        initClockImages();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime endTime = startTime.plusMinutes(duration);

        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime endWithExtra = endTime.plusMinutes(extraTime);
            LocalTime currentTime = LocalTime.now();
            long diff = currentTime.until(endWithExtra, ChronoUnit.SECONDS);
            System.out.println("endWithExtra = "+endWithExtra);
            System.out.println("currentTime = "+currentTime);
            System.out.println("diff = "+diff);

            LocalTime defaultTime = LocalTime.parse("00:00:00");
            if(diff<=0 && timeUpFlag){
                timeUpFlag = false;
                exam_name_text.setText("Time Is Up !");
                clock_text.setText(defaultTime.format(dtf));
                try {
                    endExam(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                LocalTime remaining = defaultTime.plusSeconds(diff);
                clock_text.setText(remaining.format(dtf));
                loadNextClk();
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initClockImages()
    {
        clks_counter = 0;
        clks = new ImageView[9];

        clks[0] = clock_0;
        clks[1] = clock_1;
        clks[2] = clock_2;
        clks[3] = clock_3;
        clks[4] = clock_4;
        clks[5] = clock_5;
        clks[6] = clock_6;
        clks[7] = clock_7;
        clks[8] = clock_8;

        for (int i=0; i< clks.length; i++){
            clks[i].setVisible(false);
        }
    }

    private void loadNextClk()
    {
        clks[clks_counter].setVisible(false);
        clks_counter = (clks_counter+1)%9;
        clks[clks_counter].setVisible(true);
    }

    @FXML
   void upload_Action(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", "*.docx"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null)
        {
            ManualSolutionPath = selectedFile.getAbsolutePath();
            error_bar_text.setText("Exam document uploaded successfully");

            System.out.println(ManualSolutionPath);
        }
        else
        {
            error_bar_text.setText("Error, please select a document");
        }
    }

    private String getStartTime ()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return  startTime.format(dtf);
    }

    private String getEndTime ()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return  LocalTime.now().format(dtf);
    }

    @FXML
   void submit_click(ActionEvent actionEvent) throws IOException {
        if(ManualSolutionPath.equals("")){
            error_bar_text.setText("Please Upload A Document");
        }else {
            endExam(true);
        }
    }

    private void endExam (boolean inTime) throws IOException
    {
        ExecutedExam eExam = new ExecutedExam (currExam.getTitle(), App.getExamInfoID(), App.getUser().getUserName(), date_text.getText(), getStartTime(), getEndTime(), inTime, false);
        ExecutedManual mExam = new ExecutedManual(eExam, ManualSolutionPath);
        try {
            SimpleClient.getClient().sendToServer(new Message("#newExecutedManualExam", mExam));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        clock.stop();
        EventBus.getDefault().unregister(this);
        App.setRoot("studentMain");
    }

    private void setExtraTime ()
    {
        if(extraTime == 0){
            extra_time_text.setText("");
        }else if(extraTime == 1){
            extra_time_text.setText("Extra Time Added: " + extraTime + " minute");
        }else if(extraTime > 1){
            extra_time_text.setText("Extra Time Added: " + extraTime + " minutes");
        }
    }

    public void generateWordDocument(Exam exam, List<ExamQuestion> examQuestionList, String filePath) {
        XWPFDocument document = new XWPFDocument();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun title = titleParagraph.createRun();

            title.setText(exam.getTitle());
            title.addBreak();
            title.addBreak();
            title.setFontSize(28);

            int questionNumber = 1;
            for (ExamQuestion question : examQuestionList) {
                XWPFParagraph questionParagraph = document.createParagraph();
                questionParagraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun questionRun = questionParagraph.createRun();

                questionRun.setText("Question " + questionNumber + ": " + question.getQuestionMll());
                questionRun.addBreak();
                String note=question.getStudent_note();
                System.out.println(note);
                if(note!=null) {
                    if(!note.equals("")){
                        questionRun.setText("Note :" + note);
                    }
                }
                questionRun.addBreak();
                String[] answers = question.getQuestion().getAnswers();
                char answerCount = 'a';
                for (String answer : answers)
                {
                    questionRun.setText(answerCount + ". " + answer);
                    questionRun.addBreak();
                    answerCount++;
                }
                questionRun.addBreak();
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
        String name = currExam.getTitle()+".docx";
        fileChooser.setInitialFileName(name);
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null)
        {
            String filePath = selectedFile.getAbsolutePath();
            generateWordDocument(currExam, examQuestionList, filePath);

            upload_button.setVisible(true);
            upload_button.setDisable(false);
            download_button.setDisable(true);
            error_bar_text.setText("Exam document downloaded successfully");
        }
        else {
            error_bar_text.setText("Something wrong, Exam document can't downloaded successfully");
        }

    }

    @Subscribe
    public void overtimeAddedMessage(EventOvertimeAdded event)
    {
        System.out.println("--------------> overtimeAddedMessage "+App.getUser().getUserName());
        System.out.println("-----> event.getInfoID() "+event.getInfoID());
        System.out.println("-----> App.getExamInfoID() "+App.getExamInfoID());
        // TODO need to set the infoID to the vxam according to the ExecutedExamInfo
        if(event.getInfoID() == App.getExamInfoID()){
            extraTime = event.getOvertimeDuration();
            setExtraTime();
        }
    }
}
