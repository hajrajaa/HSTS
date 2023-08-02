package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class PrincipleRequests {

    @FXML
    private Button p_clr;
    @FXML
    private Button p_refresh;
    @FXML
    private Button p_con_req;
    @FXML
    private Button p_back_btn;
    @FXML
    private Button p_deny_req;
    @FXML
    private Label req_msg;
    @FXML
    private ListView<?> req_list;

    @FXML
    public void go_back(ActionEvent actionEvent) throws IOException {
        App.setRoot("principle_homepage");
    }

    @FXML
    public void deny_req(ActionEvent actionEvent) {
        ///////////////////////////////////////
    }

    @FXML
    public void clear_reqs(ActionEvent actionEvent) {
        ///////////////////////////////////////
    }

    @FXML
    public void con_req(ActionEvent actionEvent) {
        ///////////////////////////////////////
    }
}
