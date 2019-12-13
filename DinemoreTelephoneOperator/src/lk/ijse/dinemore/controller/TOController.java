package lk.ijse.dinemore.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinemore.alert.MyAlert;
import lk.ijse.dinemore.connector.ProxyHandler;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.service.custom.TeleOperatorService;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TOController implements Initializable, Observer {

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private JFXTextField txtCustomerContact;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtOrderQty;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnAddToList;

    @FXML
    private JFXTextField txtUpdateQty;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextField txtSearchOrder1;

    @FXML
    private TableView<OrderDetailDTO> tblItemList;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnClose;

    @FXML
    private Label lblTotal;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;

    @FXML
    private TextField txtSearchOrder;

    @FXML
    private JFXComboBox<String> cmbUpdateItem;

    @FXML
    private JFXComboBox<String> cmbOrderItem;


    private EmployeeDTO employee;

    private ArrayList<OrderDetailDTO> itemList = new ArrayList<>();
    private int no = 1;

    @FXML
    void btnAddToListAction(ActionEvent event) {
        addTo(itemList, txtOrderQty, cmbOrderItem);
    }

    private double priceGenerator(JFXComboBox<String> combo, int qty) {
        double total = 0;
        if (combo.getSelectionModel().isSelected(0)) {
            total = 239.00 * qty;
        } else if (combo.getSelectionModel().isSelected(1)) {
            total = 459.00 * qty;
        } else {
            total = 699.00 * qty;
        }
        return total;
    }

    private void setTotal() {
        double tot = 0;
        for (OrderDetailDTO o :
                itemList) {
            tot += o.getTotal();
        }
        lblTotal.setText(String.valueOf(tot));
    }

    @FXML
    void btnCancelAction(ActionEvent event) {
        try {
            service.notifyObservers();
            boolean b = service.cancelOrder(toUpdateOrder);
            if (b) {
                myAlert.show(MyAlert.AlertTypes.DONE, "Cancelation Done");
            } else {
                myAlert.show(MyAlert.AlertTypes.DONE, "Cancelation Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCloseAction(ActionEvent event) {
        try {
            service.unRegister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage s = (Stage) btnClose.getScene().getWindow();
        s.close();
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        try {
            if (toUpdateOrder == null) {
                if (!txtCustomerContact.getText().isEmpty()) {
                    if (Pattern.compile("[A-z]{0,}").matcher(txtCustomerName.getText()).matches() & !txtCustomerName.getText().isEmpty()) {
                        try {
                            if (customer == null) {
                                customer = new CustomerDTO(txtCustomerContact.getText(), txtCustomerName.getText(), false);
                            }
                            OrderDTO order = new OrderDTO(customer, itemList, null, employee.getId(), null, LocalDate.now(), 0);
                            boolean b = service.addWaitOrder(order);
                            if (b) {
                                myAlert.show(MyAlert.AlertTypes.DONE, "Order Added Successfully");
                                service.notifyObservers();
                            } else {
                                myAlert.show(MyAlert.AlertTypes.FAIL, "Error Occuring while Processing Order");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        myAlert.show(MyAlert.AlertTypes.FAIL, "Invalid Input Customer Name");
                    }
                } else {
                }
            } else {
                boolean b = service.updateWaitOrder(toUpdateOrder);
                if (b) {
                    myAlert.show(MyAlert.AlertTypes.DONE, "Order Update");
                    toUpdateOrder = null;
                } else {
                    myAlert.show(MyAlert.AlertTypes.FAIL, "Order Fail to Update");
                }
            }
            resetAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetAction(ActionEvent event) {
        resetAction();
    }

    private void resetAction() {
        try {
            txtCustomerName.setText("");
            txtCustomerContact.setText("");
            txtOrderQty.setText("");
            orderId = service.getLastOrderID() + 1;
            txtSearchOrder1.setText("");
            txtSearchOrder.setText(String.valueOf(orderId));
            lblOrderId.setText(String.valueOf(orderId));
            txtUpdateQty.setText("");
            toUpdateOrder = null;
            customer = null;
            itemList = new ArrayList<>();
            loadTable(itemList);
            setTotal();
            cmbOrderItem.getSelectionModel().selectFirst();
            cmbUpdateItem.getSelectionModel().selectFirst();
            toUpdateItemList = null;
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    ArrayList<OrderDetailDTO> toUpdateItemList;
    private OrderDetailDTO selectedToUpdateItem;

    private void addTo(ArrayList<OrderDetailDTO> arrayList, JFXTextField txtQty, JFXComboBox<String> combo) {
        if (arrayList != null) {
            if (Pattern.compile("^[0-9]{0,10}").matcher(txtQty.getText()).matches() && !txtQty.getText().isEmpty()) {
                OrderDetailDTO dto;
                if (toUpdateOrder == null) {
                    dto = new OrderDetailDTO(no++, 0, combo.getSelectionModel().getSelectedItem(), Integer.parseInt(txtQty.getText()));
                    dto.setTotal(priceGenerator(combo, Integer.parseInt(txtQty.getText())));
                } else {
                    dto = new OrderDetailDTO(selectedToUpdateItem.getId(), 0, combo.getSelectionModel().getSelectedItem(), Integer.parseInt(txtQty.getText()));
                }
                arrayList.add(dto);
                loadTable(arrayList);
                setTotal();
            } else {
                myAlert.show(MyAlert.AlertTypes.FAIL, "Invalid Input");
            }
        } else {
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        addTo(toUpdateItemList, txtUpdateQty, cmbUpdateItem);
    }

    private OrderDTO toUpdateOrder;
    private int searchingID;

    @FXML
    void txtSearchOrderAction(ActionEvent event) {
        TextField txt = (TextField) event.getSource();
        searchOrder(txt);
    }

    private void searchOrder(TextField txtSeacrch) {
        try {
            if (Pattern.compile("[0-9]{0,9}").matcher(txtSeacrch.getText()).matches() & !txtSeacrch.getText().isEmpty()) {
                service.release(searchingID);
                searchingID = Integer.parseInt(txtSeacrch.getText());
                toUpdateOrder = service.searchWaitingOrder(searchingID);
                if (toUpdateOrder != null) {
                    tblItemList.setItems(FXCollections.observableArrayList(toUpdateOrder.getOrders()));
                    txtSearchOrder.setText(String.valueOf(toUpdateOrder.getId()));
                    txtSearchOrder1.setText(String.valueOf(toUpdateOrder.getId()));
                    toUpdateItemList = toUpdateOrder.getOrders();
                    tblItemList.setItems(FXCollections.observableArrayList(toUpdateItemList));
                } else {
                    System.out.println("no order");
                }
            } else {
                System.out.println("incorrect order qty");
//                MyAlert.show(MyAlert.AlertTypes.WARNING,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CustomerDTO customer;

    @FXML
    void txtCusContactKeyAction(KeyEvent event) {
        try {
            if (Pattern.compile("^[0-9]{10}$").matcher(txtCustomerContact.getText()).matches()) {
                customer = service.searchCustomer(txtCustomerContact.getText());
                if (customer == null) {
                    myAlert.show(MyAlert.AlertTypes.WARNING, "No Customer Saved to this Contact");
                } else {
                    customer.setExist(true);
                    txtCustomerName.setText(customer.getName());
                    myAlert.show(MyAlert.AlertTypes.DONE, "Customer Found");
                }
            } else {
                myAlert.show(MyAlert.AlertTypes.FAIL, "Invalid Input Customer Contact");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TeleOperatorService service;
    private int orderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            service = ProxyHandler.getInstance().getTeleOperatorService();
            service.register(this);
            orderId = service.getLastOrderID() + 1;
            lblOrderId.setText(String.valueOf(orderId));
            txtSearchOrder.setText(String.valueOf(orderId));
            initiateTable();

            cmbOrderItem.setItems(FXCollections.observableArrayList("Submarine Small", "Submarine Medium", "Submarine Large"));
            cmbOrderItem.getSelectionModel().selectFirst();
            cmbUpdateItem.setItems(FXCollections.observableArrayList("Submarine Small", "Submarine Medium", "Submarine Large"));
            cmbUpdateItem.getSelectionModel().selectFirst();

            myAlert = new MyAlert(lblAlert, pnlAlert);


            orderId=service.getLastOrderID()+1;
            lblOrderId.setText(String.valueOf(orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MyAlert myAlert;

    @Override
    public void update() throws Exception {
        orderId = service.getLastOrderID() + 1;
//        lblOrderId.setText("no error");
    }

    public void setEmployee(EmployeeDTO login) {
        this.employee = login;
        lblEmpId.setText(employee.getId());
        lblEmpName.setText(employee.getName());
    }

    private void loadTable(ArrayList<OrderDetailDTO> arrayList) {
        tblItemList.setItems(FXCollections.observableArrayList(arrayList));
    }


    private void initiateTable() {
        tblItemList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblItemList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("item"));
        tblItemList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItemList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    void tblMouseClick(MouseEvent event) {
        OrderDetailDTO selectedItem = tblItemList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cmbUpdateItem.getSelectionModel().select(selectedItem.getItem());
            cmbUpdateItem.getSelectionModel().select(selectedItem.getItem());

            txtUpdateQty.setText(String.valueOf(selectedItem.getQty()));
            txtOrderQty.setText(String.valueOf(selectedItem.getQty()));
            if (toUpdateItemList == null) {
                itemList.remove(selectedItem);
                tblItemList.setItems(FXCollections.observableArrayList(itemList));
                setTotal();
            } else {
                selectedToUpdateItem = selectedItem;
                toUpdateItemList.remove(selectedItem);
                tblItemList.setItems(FXCollections.observableArrayList(toUpdateItemList));
            }
        }
    }
}
