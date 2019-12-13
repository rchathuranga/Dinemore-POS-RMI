package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dinemore.dto.EmployeeDTO;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VerifyController implements Initializable {

    private EmployeeDTO employee;
    private ManagerController manager;
    private Stage stage;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnClose;

    @FXML
    void btnCloseAction(ActionEvent event) {

        Alert exit = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure to Exit");
        Optional<ButtonType> result = exit.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage window = (Stage) btnClose.getScene().getWindow();
            window.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void btnLoginAction(ActionEvent event) {
        if(employee.getUsername().equals(txtUsername.getText())){
            if(employee.getPassword().equals(txtPassword.getText())){
                manager.setVerify(true);
                stage.close();
            }
        }
    }

    public void setEmployee(EmployeeDTO e){
        employee=e;
    }

    public void setUI(ManagerController mc, Stage stage){
        manager =mc;
        this.stage=stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
