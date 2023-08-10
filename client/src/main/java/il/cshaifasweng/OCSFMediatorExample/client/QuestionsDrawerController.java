package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamQuestion;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import javafx.animation.FadeTransition;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import javafx.util.Callback;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class QuestionsDrawerController
{
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Class Fields ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    static int numOfQuestions=0;
//    private boolean selectQuestionFlag, removeColumnFlag;
//    //    private List<ExamQuestion> questionsList;
//    @FXML
//    private TextField Teacher_Note_TextField, Student_Note_TextField, Points_TextField;
//    @FXML
//    private TextField Title_TextField, Code_TextField, Time_TextField, Teacher_Desc_TextField, Student_Desc_TextField;
//    @FXML
//    private Text error_bar_text, question_text;
//    @FXML
//    private VBox Vbox;
//    private Question currentQuestion;
//    private ArrayList<Question> allQuestions;
//    private List<ExamQuestion> allExamQuestions;
//    private List<Subject> allSubjects;
//    private List<Course> allCourses;
//    @FXML
//    private Button Answer1, Answer2, Answer3, Answer4;
//    private Button [] answersButtons;
//    @FXML
//    ComboBox Subject_ComboBox, Course_ComboBox;
//    @FXML
//    TableView Table;
//
//    @FXML
//    private TableColumn<ExamQuestion, String> Code_Column, Question_Column, Points_Column;
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////// Initialize ///////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    @FXML
//    private void initialize ()
//    {
//        Course_ComboBox.setDisable(false);
//        answersButtons = new Button[4];
//        selectQuestionFlag = false;
//        removeColumnFlag = true;
//        error_bar_text.setText("");
//
//        initializeButtons();
//        allExamQuestions = new ArrayList<ExamQuestion>();
//        allQuestions = getQuestions();
//
//        allSubjects = new ArrayList<Subject>();
//        initSubjectComboBox();
//
//        allCourses = new ArrayList<Course>();
//    }
//
//    private void initializeButtons ()
//    {
//        Answer1.setDisable(false);
//        setButtonColor(Answer1, "orange");
//        answersButtons[0] = Answer1;
//
//        Answer2.setDisable(false);
//        setButtonColor(Answer2, "orange");
//        answersButtons[1] = Answer2;
//
//        Answer3.setDisable(false);
//        setButtonColor(Answer3, "orange");
//        answersButtons[2] = Answer3;
//
//        Answer4.setDisable(false);
//        setButtonColor(Answer4, "orange");
//        answersButtons[3] = Answer4;
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////// Common /////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    private void setButtonColor (Button B, String color)
//    {
//        String colorID = "#ffffff"; // default white
//        if(color.equals("black")){
//            colorID = "#000000";
//        } else if (color.equals("orange")) {
//            colorID = "#e28743";
//        } else if (color.equals("dblue")) {
//            colorID = "#063970";
//        } else if (color.equals("lblue")) {
//            colorID = "#abdbe3";
//        } else if (color.equals("green")) {
//            colorID = "#34b048";
//        } else if (color.equals("red")) {
//            colorID = "#ff0404";
//        } else if (color.equals("gray")) {
//            colorID = "#c5c5c5";
//        }
//        B.setStyle("-fx-background-color: " + colorID);
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Talk To Server /////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
////    private void sendMessage (String op, Object obj)
////    {
////        try {
////            Message message = new Message(op, obj);
////            SimpleClient.getClient().sendToServer(message);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Subscribe
////    public void newMessageArrived (Message message)
////    {
////        String replay = message.getMessage();
////        Object obj = message.getObject1();
////
////        if(replay.equals("Error")){ // connection error
//////            updateStudentInfo(tempStudent);
//////            errorBar.setText("ERROR! cannot connect to server");
////        }
////        else if (replay.equals("added question")){
////            // obj should be Null
////            SimpleClient.getClient().switchPage("teacher_page");
////        }
////    }
//
//    public boolean isNumber (String s)
//    {
//        char [] arr = s.toCharArray();
//        for(char c : arr){
//            if((c < '0') || (c > '9')){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private ArrayList<Question> getQuestions ()
//    {
//        String [] s1 = {"1","2","100","pi"};
//        Question e1 = new Question(111,"1+1=?", s1, 2);
//
//        String [] s2 = {"blue","green","black","red"};
//        Question e2 = new Question(222,"Apples are ____", s2, 4);
//
//        String [] s3 = {"0","10","100","1000"};
//        Question e3 = new Question(222,"100*0=?", s3, 1);
//
//        ArrayList<Question> eee = new ArrayList<Question>();
//        eee.add(e1);
//        eee.add(e2);
//        eee.add(e3);
//        return eee;
//    }
//
//    private void initSubjectComboBox ()
//    {
//        loadAllSubjects();
//        ArrayList<String> allSubjectsNames = new ArrayList<String>();
//        for (Subject subject : allSubjects)
//        {
//            allSubjectsNames.add(subject.getSubName());
//        }
//        ObservableList<String> basesList = FXCollections.observableArrayList(allSubjectsNames);
//        Subject_ComboBox.setItems(basesList);
//
//        initCourseComboBox();
//    }
//
//    private void loadAllSubjects()
//    {
//        Subject e1 = new Subject(111,"1+1=?", s1, 2);
//
//        String [] s2 = {"blue","green","black","red"};
//        Question e2 = new Question(222,"Apples are ____", s2, 4);
//
//        String [] s3 = {"0","10","100","1000"};
//        Question e3 = new Question(222,"100*0=?", s3, 1);
//
//        ArrayList<Question> eee = new ArrayList<Question>();
//        eee.add(e1);
//        eee.add(e2);
//        eee.add(e3);
//        return eee;
//    }
//
//    private void initCourseComboBox ()
//    {
//        loadAllCourses();
//        ArrayList<String> allCoursesNames = new ArrayList<String>();
//        for (Course course : allCourses)
//        {
//            allCoursesNames.add(course.getCourseName());
//        }
//        ObservableList<String> basesList = FXCollections.observableArrayList(allCoursesNames);
//        Course_ComboBox.setItems(basesList);
//    }
//
//    private void loadAllCourses()
//    {
//        String [] s1 = {"1","2","100","pi"};
//        Question e1 = new Question(111,"1+1=?", s1, 2);
//
//        String [] s2 = {"blue","green","black","red"};
//        Question e2 = new Question(222,"Apples are ____", s2, 4);
//
//        String [] s3 = {"0","10","100","1000"};
//        Question e3 = new Question(222,"100*0=?", s3, 1);
//
//        ArrayList<Question> eee = new ArrayList<Question>();
//        eee.add(e1);
//        eee.add(e2);
//        eee.add(e3);
//        return eee;
//    }
//
//    private void loadNewQuestion (Question question)
//    {
//        selectQuestionFlag = true;
//        currentQuestion = question;
//        initializeButtons();
//        question_text.setText(currentQuestion.getQuestion());
//
//        for(int i=0; i<answersButtons.length; i++)
//        {
//            String tempAnswer = currentQuestion.getAnswers()[i];
//            answersButtons[i].setText(tempAnswer);
//            if (currentQuestion.getCorrect_answer() == i+1){
//                setButtonColor(answersButtons[i], "green");
//            }
//        }
//        Points_TextField.setText("");
//        Teacher_Note_TextField.setText("");
//        Student_Desc_TextField.setText("");
//    }
//
//    private Question findQuestion (String selectedQuestionName)
//    {
//        for (Question question : allQuestions)
//        {
//            if(question.getQuestion().equals(selectedQuestionName))
//            {
//                return question;
//            }
//        }
//        return null;
//    }
//
//    private void refreshTable ()
//    {
//        ObservableList<ExamQuestion> allExamQuestions_OL = FXCollections.observableArrayList(allExamQuestions);
//        Table.setItems(allExamQuestions_OL);
//
//        Code_Column.setCellValueFactory(new PropertyValueFactory<>("code"));
//        Question_Column.setCellValueFactory(new PropertyValueFactory<>("question"));
//        Points_Column.setCellValueFactory(new PropertyValueFactory<>("points"));
//        if(removeColumnFlag){
//            addColumnToTable();
//            removeColumnFlag = false;
//        }
//    }
//
//    private void addColumnToTable() {
//        TableColumn<ExamQuestion, Void> colBtn = new TableColumn("");
//
//        Callback<TableColumn<ExamQuestion, Void>, TableCell<ExamQuestion, Void>> cellFactory = new Callback<TableColumn<ExamQuestion, Void>, TableCell<ExamQuestion, Void>>() {
//            @Override
//            public TableCell<ExamQuestion, Void> call(final TableColumn<ExamQuestion, Void> param) {
//                final TableCell<ExamQuestion, Void> cell = new TableCell<ExamQuestion, Void>() {
//
//                    private final Button btn = new Button("remove");
//
//                    {
//                        btn.setOnAction((ActionEvent event) -> {
//                            ExamQuestion remove_question = getTableView().getItems().get(getIndex());
//                            allExamQuestions.remove(remove_question);
//                            refreshTable();
//                            error_bar_text.setText("Question Removed");
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        colBtn.setCellFactory(cellFactory);
//
//        Table.getColumns().add(colBtn);
//    }
//
//    private boolean validatePoints (String points)
//    {
//        if (points.equals("")){
//            error_bar_text.setText("Please Fill Question Points");
//            return false;
//        }
//        if (!isNumber(points)){
//            error_bar_text.setText("Question Points Must Be a Number");
//            return false;
//        }
//        int Npoints = Integer.valueOf(points);
//        if(Npoints < 0){
//            error_bar_text.setText("Question Points Must Be Higher Than 0");
//            return false;
//        }
//        if(Npoints > 100){
//            error_bar_text.setText("Question Points Must Be Less Than 100");
//            return false;
//        }
//        return true;
//    }
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////// Server Replay //////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////// On Action Functions ///////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void finish_exam_click(ActionEvent actionEvent)
//    {
//        String title = Title_TextField.getText().toString();
//        String code = Code_TextField.getText().toString();
//        String time = Time_TextField.getText().toString();
//        String studentDesc = Student_Desc_TextField.getText().toString();
//        String teacherDesc = Teacher_Desc_TextField.getText().toString();
//
//        if (title.equals("")) {error_bar_text.setText("Please Enter The Exam Name");}
//        else if (code.equals("")) {error_bar_text.setText("Please Enter The Exam Cade");}
//        else if (!isNumber(code)){
//            error_bar_text.setText("Exam Code Must Be a Number");
//        }
//        else if (code.length() != 5){
//            error_bar_text.setText("Exam Code Must Be 5 Digits");
//        }
//        else if (time.equals("")) {error_bar_text.setText("Please Enter The Exam Time");}
//        else if (!isNumber(time)){
//            error_bar_text.setText("Exam Time Must Be a Number");
//        }
//        else{
//            error_bar_text.setText("Creating Exam ...");
//            //        (int time, int codeExam, String descForStudent, String descForTeacher, Teacher teacher, String type)
////        public VirtualExam(String title, int codeExam, int time, List<ExamQuestion> examQuestions, String descForStudent, String descForTeacher, Teacher teacher)
////        {
////            super(time, codeExam, descForStudent, descForTeacher, teacher, "Virtual");
////            this.examQuestion=new ArrayList<ExamQuestion>();
////        }
////        Teacher teacher = (Teacher) App.getUser();
////        VirtualExam exam = new VirtualExam (title, code, time, allExamQuestions, studentDesc, teacherDesc, teacher, "Virtual");
//        }
//    }
//
//    public void QuestionSelected(ActionEvent actionEvent)
//    {
//        String selectedQuestionName = SelectQuestionComboBox.getValue().toString();
//        Question selectedQuestion = findQuestion(selectedQuestionName);
//        if(selectedQuestion != null){
//            loadNewQuestion(selectedQuestion);
//        }
//    }
//
//    public void SubjectSelected(ActionEvent actionEvent)
//    {
//
//    }
//
//    public void CourseSelected(ActionEvent actionEvent)
//    {
//        Course_ComboBox.setDisable(false);
//    }
//
//    public void Add_Question_Click(ActionEvent actionEvent)
//    {
//        String points = Points_TextField.getText().toString();
//        if(selectQuestionFlag == false){
//            error_bar_text.setText("Please Select a Question to Add ");
//        }
//        else if(validatePoints(points)){
//            int Npoints = Integer.valueOf(points);
//            String teacher_note = Teacher_Note_TextField.getText().toString();
//            String student_note = Student_Note_TextField.getText().toString();
//            allExamQuestions.add(new ExamQuestion(currentQuestion, Npoints, teacher_note, student_note));
//            refreshTable();
//            error_bar_text.setText("The Question Has Been Added To The Exam");
//        }
//    }


}
