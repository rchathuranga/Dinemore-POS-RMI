package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dinemore.connector.ProxyHandler;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.service.custom.TeleOperatorService;

public class LoginController {

    private TeleOperatorService service;
    private static Stage primaryStage;

    public LoginController() {
        try {
            service=  ProxyHandler.getInstance().getTeleOperatorService();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @FXML
    void btnLoginAction(ActionEvent event) {
        String usernameText = txtUsername.getText();
        String passwordText = txtPassword.getText();

        try {
            EmployeeDTO login = service.login(usernameText, passwordText);
            if(login!=null){
                if(login.getPosition().equals("T")) {
                    primaryStage = (Stage) txtUsername.getScene().getWindow();//
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/dinemore/view/TOView.fxml"));
                    Parent root = loader.load();
                    setMovable(root);
                    TOController controller = loader.getController();
                    controller.setEmployee(login);
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
//                    Startup.getLoginStage().close();

                    TranslateTransition transition = new TranslateTransition(Duration.millis(350),scene.getRoot());
                    transition.setFromX(-scene.getWidth());
                    transition.setToX(0);
                    transition.play();
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Incorrect username or password | please open TeleOperator app").show();
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Incorrect Username or Password").show();
            }

        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private double xOffset = 0;
    private double yOffset = 0;

    private void setMovable(Parent root) {
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

    }

    @FXML
    private JFXButton btnClose;

    @FXML
    void btnCloseAction(ActionEvent event) {
        Stage s = (Stage) btnClose.getScene().getWindow();
        s.close();
    }

}
