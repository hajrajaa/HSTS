package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class PrincipleHomepage {

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
    private Label p_hi_msg;

    @FXML
    Text welcome_text;


    @FXML
    void logOutBtn(ActionEvent event)
    {
        try {
            SimpleClient.getClient().sendToServer(new Message("#LogOut", user1));
            App.setRoot("login1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    public void go_to_p_stat(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_menu");
    }

    @FXML
    public void go_to_p_req(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_requests");
    }


    @FXML
    void initialize()
    {
        welcome_text.setText("Welcome\n" + App.getUser().getUserName());

        setUser1(App.getUser());
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_hi_msg != null : "fx:id=\"p_hi_msg\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_req_btn != null : "fx:id=\"p_req_btn\" was not injected: check your FXML file 'principle_homepage.fxml'.";
        assert p_stat_btn != null : "fx:id=\"p_stat_btn\" was not injected: check your FXML file 'principle_homepage.fxml'.";

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

}
