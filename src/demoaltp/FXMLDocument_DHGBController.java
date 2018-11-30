package demoaltp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BaoBao
 */
public class FXMLDocument_DHGBController implements Initializable {

    @FXML
    private BorderPane bpMain;

    @FXML
    private void themHandler(ActionEvent event) {
        try {
            Stage curWindow = (Stage) bpMain.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLLogin_DHGB.fxml"));
            Scene scene = new Scene(loader.load());
            DangNhapController controller = loader.getController();
            controller.setPlay(false);
            curWindow.setScene(scene);
            curWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void playHandler(ActionEvent event) {
        try {
            Stage curWindow = (Stage) bpMain.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLLogin_DHGB.fxml"));
            Scene scene = new Scene(loader.load());
            DangNhapController controller = loader.getController();
            controller.setPlay(true);
            curWindow.setScene(scene);
            curWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
