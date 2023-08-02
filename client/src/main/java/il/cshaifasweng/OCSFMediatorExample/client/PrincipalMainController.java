package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrincipalMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logOutBtn;

    @FXML
    void logOutBtn(ActionEvent event)
    {


    }

    @FXML
    void initialize() {
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'principalMain.fxml'.";

    }

}
