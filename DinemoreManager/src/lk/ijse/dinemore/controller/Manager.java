//package lk.ijse.dinemore.controller;
//
//import com.jfoenix.controls.JFXButton;
//import javafx.animation.*;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import lk.ijse.dinemore.connector.ProxyHandler;
//import lk.ijse.dinemore.dto.EmployeeDTO;
//import lk.ijse.dinemore.dto.OrderDetailDTO;
//import lk.ijse.dinemore.observer.Observer;
//import lk.ijse.dinemore.service.custom.ManagerService;
//import lk.ijse.dinemore.tm.OrderDetailTM;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.rmi.Remote;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.ResourceBundle;
//
//public class Manager implements Initializable,Remote, Observer {
//
//    private EmployeeDTO employeeDTO;
//    private ManagerService service;
//    private boolean verify=true;
//    private static Stage stage;
//
//    @FXML
//    private Label lblManagerId;
//
//    @FXML
//    private Label lblManagerName;
//
//    @FXML
//    private RadioButton rdbChef;
//
//    @FXML
//    private ToggleGroup task;
//
//    @FXML
//    private RadioButton rdbTeleOperator;
//
//    @FXML
//    private TextField txtEmpId;
//
//    @FXML
//    private TextField txtEmpName;
//
//    @FXML
//    private TextField txtEmpUsername;
//
//    @FXML
//    private TextField txtEmpPassword;
//
//    @FXML
//    private JFXButton btnAddEmp;
//
//    @FXML
//    private JFXButton btnUpdateEmp;
//
//    @FXML
//    private JFXButton btnDeleteEmp;
//
//    @FXML
//    private TableView<EmployeeDTO> tblEmployee;
//
//    @FXML
//    private TableView<OrderDetailTM> tblFinishOrders;
//
//    @FXML
//    private TableView<OrderDetailTM> tblWaitOrders;
//
//    @FXML
//    private Button btnChefReport;
//
//    @FXML
//    private TextField txtChefNo;
//
//    @FXML
//    private Button btnTOreport;
//
//    @FXML
//    private TextField txtTOno;
//
//    @FXML
//    private AnchorPane pnlAlert;
//
//    @FXML
//    private Label lblAlert;
//
//
//
//
//
//    @FXML
//    void btnAddEmpAction(ActionEvent event) {
//        if(verify) {
////            verify=false;
//            String id    = txtEmpId.getText();
//            String name = txtEmpName.getText();
//            String usernameText = txtEmpUsername.getText();
//            String passwordText = txtEmpPassword.getText();
//            try {
//                boolean b = service.addEmployee(new EmployeeDTO(id, name, (rdbChef.isSelected())? "C" : "T", usernameText, passwordText));
//                if (b) {
//                    clearEmpTxts();
//                    loadTable();
//                    setAlert(AlertTypes.DONE, "Added Successfully");
//                } else {
//                    setAlert(AlertTypes.FAIL, "Failed to Add");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            verifyManager();
//        }
//    }
//
//    private void clearEmpTxts(){
//        txtEmpId.setText("");
//        txtEmpName.setText("");
//        txtEmpUsername.setText("");
//        txtEmpPassword.setText("");
//    }
//
//    private EmployeeDTO toUpdateEmp;
//
//    @FXML
//    void txtEmpIdAction(ActionEvent event){
//        try {
//            EmployeeDTO emp=service.searchEmployee(txtEmpId.getText());
//            toUpdateEmp = emp;
//            if(emp!=null) {
//                txtEmpName.setText(emp.getName());
//                txtEmpUsername.setText(emp.getUsername());
//            }else {
//                setAlert(AlertTypes.FAIL, "No employee found to the id "+txtEmpId.getText());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void btnDeleteEmpAction(ActionEvent event) {
//        if(verify) {
////            verify=false;
//            try {
//                boolean b = service.deleteEmployee(txtEmpId.getText());
//                if (b) {
//                    setAlert(AlertTypes.DONE, "Employee deleted successfully");
//                    loadTable();
//                } else {
//                    setAlert(AlertTypes.FAIL, "Employee failed to Delete");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            verifyManager();
//        }
//    }
//
//    @FXML
//    void btnUpdateEmpAction(ActionEvent event) {
//
//    }
//
///*    @FXML
//    private AnchorPane pnlTransition;
//
//    @FXML
//    private Button btn;
//
//
//    @FXML
//    void btnAction(ActionEvent event) {
////        TranslateTransition transition = new TranslateTransition(Duration.millis(1550),pnlTransition);
////        transition.setFromY(0);
////        transition.setToY(4000);
////        transition.play();
////        lblCompanyName.changeColor();
//
////        pnlAlert.setPrefHeight(50);
//
////        TranslateTransition transition = new TranslateTransition(Duration.millis(1550),pnlAlert);
////        transition.setFromX(0);
////        transition.setToX(-1000);
////        transition.play();
//        System.out.println(pnlAlert.maxWidthProperty());
//        changeSize(pnlAlert,1000,1000);
//
//    }*/
///*
//    public void changeSize(final Pane pane, double width, double height) {
//        Duration cycleDuration = Duration.millis(100000);
//        Timeline timeline = new Timeline(
//                new KeyFrame(cycleDuration,
//                        new KeyValue(pane.maxWidthProperty(),width, Interpolator.EASE_BOTH)),
//                new KeyFrame(cycleDuration,
//                        new KeyValue(pane.maxHeightProperty(),height,Interpolator.EASE_BOTH))
//        );
//
//        timeline.play();
//        timeline.setOnFinished(event->{
//            System.out.println("finish");
//        });
//    }*/
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//
//
//
//        try {
//            service= ProxyHandler.getInstance().getService();
//            UnicastRemoteObject.exportObject(this,0);
//            service.register(this);
//
//            setTables();
//            loadTable();
//
//            stage=LoginController.getPrimaryStage();
//
//            stage.setOnCloseRequest(event ->{
//                try {
//                    service.unRegister(this);
//                }catch (Exception e){
//                   e.printStackTrace();
//                }
//            });
//
//            new Thread(() -> {
//                lblManagerId.setText(employeeDTO.getId());
//                lblManagerName.setText(employeeDTO.getName());
//            }).start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadTable() throws Exception {
////        ArrayList<EmployeeDTO> allEmployee = service.getAllEmployee();
////        tblEmployee.setItems(FXCollections.observableArrayList(allEmployee));
////
////        ArrayList<OrderDetailDTO> allOrders = service.getAllFinishedOrders();
////        tblFinishOrders.setItems(FXCollections.observableArrayList(convertOrderDTM(allOrders)));
////
////        ArrayList<OrderDetailDTO> allWaitOrders = service.getAllUnFinishedWaitOrders();
////        tblWaitOrders.setItems(FXCollections.observableArrayList(convertOrderDTM(allWaitOrders)));
//    }
//
//    private  ArrayList<OrderDetailTM> convertOrderDTM(ArrayList<OrderDetailDTO> allOrders){
//        ArrayList<OrderDetailTM> orderDetailTMs =new ArrayList<>();
////        for (OrderDetailDTO od :allOrders) {
////            orderDetailTMs.add(
////                    new OrderDetailTM(
////                            od.getCustomer().getContact(),od.getCustomer().getName(),
////                            od.getOrder().getId(),od.getOrder().getQty(),od.getOrder().getProcessTime(),od.getOrder().gettOno(),od.getOrder().getChefNo(),
////                            od.getDate()
////                    )
////            );
////        }
//        return orderDetailTMs;
//    }
//
//
//    @FXML
//    void btnChefReportAction(ActionEvent event){
//        HashMap<String, Object> map = new HashMap();
//        map.put("chefName", "CF001");
//        map.put("totalOrders", "10");
//        map.put("totalQty", "15");
//        map.put("totalTime","23:00" );
//
//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/lk/ijse/dinemore/assets/reports/ChefReport.jrxml"));
//            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
//            JasperViewer.viewReport(print, false);
//        }catch (Exception e){
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
//    }
//
//    @FXML
//    void btnTOreportAction(ActionEvent event) {
//
//    }
//
//
//
//
//    @Override
//    public void update() throws Exception {
//        try {
//            loadTable();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private enum AlertTypes{
//        DONE,FAIL,DEFAULT,WARNING;
//    }
//
//    private void setAlert(AlertTypes type,String msg){
//        switch (type){
//            case DONE:{
//                lblAlert.setText(msg);
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color: #00C9A7"); break;}
//            case FAIL:{lblAlert.setText(msg);
//                lblAlert.setStyle("-fx-text-fill: white");
//                pnlAlert.setStyle("-fx-background-color: red"); break;}
//            case WARNING:{lblAlert.setText(msg);
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color:  #FFC75F"); break;}
//            case DEFAULT:{lblAlert.setText(msg);
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color: #9e9e9e"); break;}
//        }
//    }
//
//    private void verifyManager(){
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Verify.fxml"));
//            Parent root = loader.load();
//            VerifyController controller = loader.getController();
//            controller.setEmployee(employeeDTO);
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            controller.setUI(this,stage);
//            stage.setScene(scene);
//            stage.show();
//        }catch (Exception e){
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
//    }
//
//    public void setEmployee(EmployeeDTO e){
//        employeeDTO=e;
//    }
//
//
////    public void setVerify(boolean verify) {
////        this.verify = verify;
////    }
//
//
//    private void setTables() {
//
//    }
//
//
//
//}
//
