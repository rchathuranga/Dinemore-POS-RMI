package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.dinemore.connector.ProxyHandler;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.main.Startup;
import lk.ijse.dinemore.service.custom.ManagerService;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private ManagerService service;
    private static Stage primaryStage;

    public LoginController() {
        try {
            service = ProxyHandler.getInstance().getService();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;


    @FXML
    void btnLoginAction(ActionEvent event) {
        String usernameText = txtUsername.getText();
        String passwordText = txtPassword.getText();


        try {
            EmployeeDTO login = service.login(usernameText, passwordText);
            if (login != null) {
                if (login.getPosition().equals("M")) {
                    primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ManagerView.fxml"));
                    Parent root = loader.load();
                    setMovable(root);
                    ManagerController controller = loader.getController();
                    controller.setEmployee(login);

                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    primaryStage.initStyle(StageStyle.TRANSPARENT);
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    Startup.getStage().close();
                    primaryStage.show();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Incorrect username or password | please open TeleOperator app").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Incorrect Username or Password").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }



    @FXML
    private JFXButton btnClose;

    @FXML
    void btnCloseAction(ActionEvent event) {
        Stage s = (Stage) btnClose.getScene().getWindow();
        s.close();
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    double xOffset = 0;
    double yOffset = 0;
    private void setMovable(Parent root) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
