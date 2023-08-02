package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class PrincipleHomepage {

    @FXML
    private Button p_exit_btn;
    @FXML
    private Button p_stat_btn;
    @FXML
    private Button p_req_btn;
    @FXML
    private Label p_hi_msg;


    @FXML
	private void initialize ()
	{

	}
    @FXML
    public void p_Exit(ActionEvent actionEvent) {
        System.exit(0);
        //App.setRoot("login");
    }

    @FXML
    public void go_to_p_stat(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_menu");
    }

    @FXML
    public void go_to_p_req(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_requests");
    }

}
