package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AConnectionGUI
{
    @FXML
    TextField IP_textField;

    @FXML
    Text error_bar_text;

    @FXML
    private void initialize () throws IOException
    {
        error_bar_text.setText("");
    }

    public void connect_click(ActionEvent actionEvent) throws IOException {
        System.out.println("GG 1");
        if(IP_textField.getText().equals("")){
            error_bar_text.setText("Please Enter IP Address");
        }else{
            System.out.println("GG 2");
            boolean sucConnection = SimpleClient.newClient(IP_textField.getText());
            System.out.println("GG 3");
            if(sucConnection){
                System.out.println("GG 4");
                App.myStart();
                System.out.println("GG 5");
            }else{
                error_bar_text.setText("Invalid IP Address");
            }
        }
    }
}
