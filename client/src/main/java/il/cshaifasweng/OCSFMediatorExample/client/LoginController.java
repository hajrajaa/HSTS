package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

    public class LoginController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button loginBtn;


        @FXML
        private PasswordField passwordTxt;

        @FXML
        private TextField userNameTxt;

        @FXML
        void loginBtn(ActionEvent event) {
            Object[] obj = {userNameTxt.getText(), passwordTxt.getText()};

            try {
                SimpleClient.getClient().sendToServer(new Message("#LoginRequest", obj));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        @FXML
        void initialize() {
            assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'login1.fxml'.";
            assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'login1.fxml'.";
            assert userNameTxt != null : "fx:id=\"userNameTxt\" was not injected: check your FXML file 'login1.fxml'.";

        }

    }





