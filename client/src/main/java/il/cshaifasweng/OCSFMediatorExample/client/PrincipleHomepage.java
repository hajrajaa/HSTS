package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class PrincipleHomepage {

    public SimpleClient client;
    public static User user1;

    public static User getUser1() {
        return user1;
    }

    public static void setUser1(User user) {
        PrincipleHomepage.user1 = user;
    }

    @FXML
    private Button logOutBtn;
    @FXML
    private Button p_stat_btn;
    @FXML
    private Button p_req_btn;
    @FXML
    private Button p_data_btn;
    @FXML
    private Label p_hi_msg;
    @FXML
    Text welcome_text, overtime_requests_number;
    @FXML
    Circle overtime_requests_circle;


    @FXML
    void logOutBtn(ActionEvent event)
    {
        try {
            SimpleClient.getClient().sendToServer(new Message("#LogOut", user1));
            EventBus.getDefault().unregister(this);
            App.setRoot("login1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    public void go_to_p_stat(ActionEvent actionEvent) throws IOException {
        EventBus.getDefault().unregister(this);
        App.setRoot("principle_stat_menu");
    }

    @FXML
    public void go_to_p_req(ActionEvent actionEvent) throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#GetAllOvertimeRequests"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void go_to_p_data(ActionEvent actionEvent) throws IOException {
        EventBus.getDefault().unregister(this);
        App.setRoot("school_archive");
    }


    @FXML
    void initialize() throws IOException {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        welcome_text.setText("Welcome\nPrinciple " + App.getUser().getUserName());
        overtime_requests_circle.setVisible(false);
        overtime_requests_number.setText("");

        setUser1(App.getUser());
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_hi_msg != null : "fx:id=\"p_hi_msg\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_req_btn != null : "fx:id=\"p_req_btn\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_stat_btn != null : "fx:id=\"p_stat_btn\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_data_btn != null : "fx:id=\"p_stat_btn\" was not injected: check your FXML file 'principle_homepage.fxml'.";

        try {
            SimpleClient.getClient().sendToServer(new Message("#GetAllOvertimeRequestsWithoutSwitch"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Subscribe
    public void EventGetAllOvertimeRequests(EventGetAllOvertimeRequests event) throws IOException {
        App.setAllOvertimeReq(event.getAllRequests());
        if(event.getAllRequests() != null){
            int num = event.getAllRequests().size();
            if(num > 0){
                overtime_requests_circle.setVisible(true);
                overtime_requests_number.setText(Integer.toString(num));
            }else{
                overtime_requests_circle.setVisible(false);
                overtime_requests_number.setText("");
            }
        }

        if(event.isSwitchPage()){
            EventBus.getDefault().unregister(this);
            App.setRoot("overtime_requests");
        }
    }

    public void or_in(MouseEvent mouseEvent) {
        App.setButtonColor(p_req_btn, "green");
    }
    public void or_out(MouseEvent mouseEvent) {
        App.setButtonColor(p_req_btn, "orange");
    }

    public void st_in(MouseEvent mouseEvent) {
        App.setButtonColor(p_stat_btn, "green");
    }
    public void st_out(MouseEvent mouseEvent) {
        App.setButtonColor(p_stat_btn, "orange");
    }

    public void da_in(MouseEvent mouseEvent) {
        App.setButtonColor(p_data_btn, "green");
    }
    public void da_out(MouseEvent mouseEvent) {
        App.setButtonColor(p_data_btn, "orange");
    }

}
