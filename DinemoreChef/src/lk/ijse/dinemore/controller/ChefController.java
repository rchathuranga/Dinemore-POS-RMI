package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.dinemore.alert.MyAlert;
import lk.ijse.dinemore.connector.ProxyHandler;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.service.custom.ChefService;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class ChefController implements Initializable, Observer {

    private EmployeeDTO employee;
    private ChefService service;
    private OrderDTO currentOrder;
    private LocalTime startTime;
    private LocalTime finishTime;
    private static Stage stage;

    private Time stime;
    private Time ftime;


    public ChefController() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            service = ProxyHandler.getInstance().getService();
            service.register(this);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private TextField lblOrderId;

    @FXML
    private TextField lblCName;

    @FXML
    private JFXButton btnTakeOrder;

    @FXML
    private JFXButton btnFinishOrder;

    @FXML
    private Label lblProcessTime;

    @FXML
    private TableView<OrderDetailDTO> tblItem;

    @FXML
    private JFXButton btnClose;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;


    @FXML
    void btnCloseAction(ActionEvent event) {
        Stage s = (Stage) btnClose.getScene().getWindow();
        s.close();
    }

    @FXML
    void btnFinishOrderAction(ActionEvent event) {
        sos = true;
        if (currentOrder != null) {
            try {
                currentOrder.setChefNo(employee.getId());
                finishTime = LocalTime.now();
                long between = Duration.between(startTime, finishTime).getSeconds();
                LocalTime time = LocalTime.ofSecondOfDay(between);
                currentOrder.setState(2);
                currentOrder.setProcessTime(time);

                boolean add = service.finishOrder(currentOrder);
                if (add) {
                    timeline.stop();
                    lblProcessTime.setText("00:00:000");
                    service.notifyObservers();
                    myAlert.show(MyAlert.AlertTypes.DONE, "Order " + currentOrder.getId() + " Finished SuccessFully in " +time+ " mins");
                    clearLabels();
                    generateReport();
                    currentOrder = null;
                    tblItem.getItems().clear();
                } else {
                    myAlert.show(MyAlert.AlertTypes.FAIL, "Order " + currentOrder.getId() + " Failed to Finish");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            myAlert.show(MyAlert.AlertTypes.DEFAULT, "No Order Processing. Please Take A Order First");
        }
    }

    @FXML
    void btnTakeOrderAction(ActionEvent event) {
        if (null == currentOrder) {
            try {
                currentOrder = service.getFirstOrder();
                if (currentOrder!= null) {
                    service.notifyObservers();
                    lblCName.setText(currentOrder.getCustomer().getName());
                    lblOrderId.setText(String.valueOf(currentOrder.getId()));

                    tblItem.setItems(FXCollections.observableArrayList(currentOrder.getOrders()));

                    startTime = LocalTime.now();
                    timeline.playFromStart();
                    sos = false;
                } else {
                    myAlert.show(MyAlert.AlertTypes.WARNING, "Currently No order to Process");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            myAlert.show(MyAlert.AlertTypes.WARNING, "Please finished the current Order");
        }
    }


    @Override
    public void update() throws Exception {
        //nothing to update;
    }

    private void clearLabels() {
        lblOrderId.setText("");
        lblCName.setText("");
    }

    private Timeline timeline;
    private Text text;
    int mins = 0, secs = 0, millis = 0;
    boolean sos = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text = new Text("00:00:000");
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(lblProcessTime);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        stage = LoginController.getPrimaryStage();
        stage.setOnCloseRequest(event -> {
            try {
                service.unRegister(this);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("item"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        myAlert = new MyAlert(lblAlert, pnlAlert);
    }

    private MyAlert myAlert;

    private void change(Label text) {
        if (millis == 1000) {
            secs++;
            millis = 0;
        }
        if (secs == 60) {
            mins++;
            secs = 0;
        }
        text.setText((((mins / 10) == 0) ? "0" : "") + mins + ":"
                + (((secs / 10) == 0) ? "0" : "") + secs + ":"
                + (((millis / 10) == 0) ? "00" : (((millis / 100) == 0) ? "0" : "")) + millis++);
    }

    public void setEmployee(EmployeeDTO employee) {
        lblEmpId.setText(employee.getId());
        lblEmpName.setText(employee.getName());
        this.employee = employee;
    }

    private void generateReport() {
//        try {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("orderID", String.valueOf(currentOrder.getOrder().getId()));
//            map.put("cusName", currentOrder.getCustomer().getName());
//            map.put("contactNo", currentOrder.getCustomer().getContact());
//            map.put("chefNo", currentOrder.getOrder().getChefNo());
//            map.put("TeleOpNo", currentOrder.getOrder().gettOno());
//            map.put("Qty", String.valueOf(currentOrder.getOrder().getQty()));
//            map.put("processTime", String.valueOf(currentOrder.getOrder().getProcessTime()));
//
//            JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/lk/ijse/dinemore/assets/report/Receipt.jrxml"));
//            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
//            JasperViewer.viewReport(print, false);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
