package lk.ijse.dinemore.alert;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MyAlert {

    private Label lblAlert;
    private Pane pnlAlert;

    public MyAlert(Label lblAlert, Pane pnlAlert){
        this.lblAlert=lblAlert;
        this.pnlAlert=pnlAlert;
    }

    public enum AlertTypes {
        DONE, FAIL, DEFAULT, WARNING
    }

    public void show(AlertTypes type, String msg){
        switch (type) {
            case DONE: {
                lblAlert.setText(msg);
                lblAlert.getStyleClass().add("doneText");
                pnlAlert.getStyleClass().add("done");
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color: #00C9A7");
                break;
            }
            case FAIL: {
                lblAlert.setText(msg);
                lblAlert.getStyleClass().add("failText");
                pnlAlert.getStyleClass().add("fail");
//                lblAlert.setStyle("-fx-text-fill: white");
//                pnlAlert.setStyle("-fx-background-color: red");
                break;
            }
            case WARNING: {
                lblAlert.setText(msg);
                lblAlert.getStyleClass().add("wariningText");
                pnlAlert.getStyleClass().add("warning");
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color:  #FFC75F");
                break;
            }
            case DEFAULT: {
                lblAlert.setText(msg);
                lblAlert.getStyleClass().add("defaultText");
                pnlAlert.getStyleClass().add("default");
//                lblAlert.setStyle("-fx-text-fill: black");
//                pnlAlert.setStyle("-fx-background-color: #9e9e9e");
                break;
            }
        }
//        pnlAlert.setStyle("-fx-background-radius: 10");
    }
}
