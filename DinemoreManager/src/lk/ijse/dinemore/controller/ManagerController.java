package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.dinemore.alert.MyAlert;
import lk.ijse.dinemore.connector.ProxyHandler;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.service.custom.ManagerService;
import lk.ijse.dinemore.tm.OrderDetailTM;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerController implements Initializable,Remote, Observer {

    @FXML
    private TextField txtSearchChef;

    @FXML
    private TableView<OrderDetailTM> tblChefOrders;

    @FXML
    private JFXButton btnViewReport;

    @FXML
    private JFXButton btnAddEmp;

    @FXML
    private JFXButton btnUpdateEmp;

    @FXML
    private JFXButton btnDeleteEmp;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtEmpID;

    @FXML
    private JFXTextField txtEmpName;

    @FXML
    private JFXRadioButton rdbChef;

    @FXML
    private ToggleGroup toggleEmployee;

    @FXML
    private JFXRadioButton rdbTeleOperator;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private TableView<OrderDetailTM> tblFinishOrders;

    @FXML
    private TableView<OrderDetailTM> tblWaitOrders;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;

    @FXML
    private JFXButton btnClose;

    private EmployeeDTO employee;
    private ManagerService service;
    private MyAlert myAlert;
    private boolean verify;
    private EmployeeDTO toUpdateEmp;

    private String checkPosition(){
        if(rdbChef.isSelected()){
            return "C";
        }else if(rdbTeleOperator.isSelected()){
            return "T";
        }else {
            return "M";
        }
    }

    @FXML
    void btnAddEmpAction(ActionEvent event) {
        if(verify) {
            verify=false;
            String id = txtEmpID.getText();
            String name = txtEmpName.getText();
            String usernameText = txtUsername.getText();
            String passwordText = txtUsername.getText();
            try {
                boolean b = service.addEmployee(new EmployeeDTO(id, name,checkPosition(), usernameText, passwordText));
                if (b) {
                    clearEmpTxts();
                    loadTable();
                    myAlert.show(MyAlert.AlertTypes.DONE,"Employee Added Successfully");
                } else {
                    myAlert.show(MyAlert.AlertTypes.FAIL,"Failed to Add");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            verifyManager();
        }
    }

    private void verifyManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VerifyView.fxml"));
            Parent root = loader.load();
            VerifyController controller = loader.getController();
            controller.setEmployee(employee);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            setMovable(root,stage);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            controller.setUI(this,stage);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void clearEmpTxts(){
        txtEmpID.setText("");
        txtEmpName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnDeleteEmpAction(ActionEvent event) {
        if(verify) {
            verify=false;
            try {
                if(!txtEmpID.getText().equals(employee.getId())) {
                    boolean b = service.deleteEmployee(txtEmpID.getText());
                    if (b) {
                        myAlert.show(MyAlert.AlertTypes.DONE, "Employee deleted successfully");
                        loadTable();
                    } else {
                        myAlert.show(MyAlert.AlertTypes.FAIL, "Employee failed to Delete");
                    }
                }else {
                    myAlert.show(MyAlert.AlertTypes.WARNING,"Cannot Delete the Current Employee");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            verifyManager();
        }
    }

    @FXML
    void btnUpdateEmpAction(ActionEvent event) {
        if(verify){
            verify=false;
            try {
                if(toUpdateEmp!=null) {
                    toUpdateEmp.setName(txtEmpName.getText());
                    toUpdateEmp.setPosition((rdbChef.isSelected()) ? "C" : "T");
                    if (!txtUsername.getText().isEmpty()) {
                        toUpdateEmp.setUsername(txtUsername.getText());
                    }
                    if (!txtPassword.getText().isEmpty()) {
                        toUpdateEmp.setPassword(txtPassword.getText());
                    }
                    boolean b = service.updateEmployee(toUpdateEmp);
                    if (b) {
                        myAlert.show(MyAlert.AlertTypes.DONE, "Employee " + txtEmpID.getText() + " Up to Date Successfully");
                        clearEmpTxts();
                        loadTable();
                    } else {
                        myAlert.show(MyAlert.AlertTypes.FAIL, "Employee " + txtEmpID.getText() + " Fail to Update");
                    }
                }else {
                    myAlert.show(MyAlert.AlertTypes.WARNING,"No Selected Employee To Update");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            verifyManager();
        }
    }

    @FXML
    void txtEmpIDAction(ActionEvent event) {
        try {
            EmployeeDTO emp=service.searchEmployee(txtEmpID.getText());
            toUpdateEmp = emp;
            if(emp!=null) {
                txtEmpName.setText(emp.getName());
                txtUsername.setText(emp.getUsername());

                if(emp.getPosition().equals("C")){
                    rdbChef.setSelected(true);
                }else if(emp.getPosition().equals("T")){
                    rdbTeleOperator.setSelected(true);
                }else {
                    rdbTeleOperator.setSelected(false);
                    rdbChef.setSelected(false);
                }
            }else {
                myAlert.show(MyAlert.AlertTypes.FAIL, "No employee found to the id "+txtEmpID.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service= ProxyHandler.getInstance().getService();
            myAlert=new MyAlert(lblAlert,pnlAlert);
            UnicastRemoteObject.exportObject(this,0);
            service.register(this);

            initiateTable();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTable(){
        try {
            ArrayList<OrderDTO> allUnFinishedWaitOrders = service.getAllUnFinishedWaitOrders();
            tblWaitOrders.setItems(FXCollections.observableArrayList(convertToOrderTM(allUnFinishedWaitOrders)));

            ArrayList<OrderDTO> allFinishedOrders = service.getAllFinishedOrders();
            tblFinishOrders.setItems(FXCollections.observableArrayList(convertToOrderTM(allFinishedOrders)));

            ArrayList<EmployeeDTO> allEmployee = service.getAllEmployee();
            tblEmployee.setItems(FXCollections.observableArrayList(allEmployee));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<OrderDetailTM> convertToOrderTM(ArrayList<OrderDTO> list){
        if(list!=null) {
            ArrayList<OrderDetailTM> arrayList = new ArrayList<>();
            for (OrderDTO o : list) {
                LocalTime localTime = o.getProcessTime();
                arrayList.add(new OrderDetailTM(
                        o.getId(), o.getCustomer().getContact(), o.getCustomer().getName(), localTime, o.getTeleOperatorNo(),
                        o.getChefNo(), o.getDate()
                ));
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    private void initiateTable(){
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("position"));

        tblWaitOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblWaitOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblWaitOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        tblWaitOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneOperator"));

        tblFinishOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblFinishOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblFinishOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("processTime"));
        tblFinishOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneOperator"));
        tblFinishOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("chefNo"));

        tblChefOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblChefOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblChefOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("processTime"));
        tblChefOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneOperator"));
        tblChefOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("chefNo"));
    }

    @Override
    public void update() throws Exception {
        loadTable();
    }

    public void setEmployee(EmployeeDTO employee){
        this.employee =employee;
        lblEmpId.setText(employee.getId());
        lblEmpName.setText(employee.getName());
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    @FXML
    void btnCloseAction(ActionEvent event) {
        try {
            service.unRegister(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage s= (Stage) btnClose.getScene().getWindow();
        s.close();
    }

    double xOffset = 0;
    double yOffset = 0;
    private void setMovable(Parent root,Stage s) {
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
                s.setX(event.getScreenX() - xOffset);
                s.setY(event.getScreenY() - yOffset);
            }
        });
    }
}

