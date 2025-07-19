package studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SuccessPopupController {
    @FXML
    private Button success_popup_close_button;
    @FXML
    protected Button success_button_ok;
    @FXML
    private Label success_popup_label;
    @FXML
    private AnchorPane popup_username_valid;

    @FXML
    private Pane success_popup_pane;
    @FXML
    private AnchorPane root_success_popup_pane;

    private double initialX;
    private double initialY;

    @FXML
    public void initialize() {
        success_popup_pane.setOnMousePressed(this::handleMousePressed);
        success_popup_pane.setOnMouseDragged(this::handleMouseDragged);
//        success_button_ok.setOnAction(actionEvent -> {
//            loadSignIn();
//        });
//        success_button_ok.setOnAction(this::loadSignIn);
    }

    public void handleMousePressed(MouseEvent event) {
        // Store the initial offset of the mouse click relative to the pane's layout
        popup_username_valid.toFront();
        initialX = event.getSceneX() - popup_username_valid.getLayoutX();
        initialY = event.getSceneY() - popup_username_valid.getLayoutY();
    }

    public void handleMouseDragged(MouseEvent event) {
        double newX = event.getSceneX() - initialX;
        double newY = event.getSceneY() - initialY;

        // Debugging: Print the new coordinates
//        System.out.println("newX: " + newX + ", newY: " + newY);

        // Ensure the pane stays within the bounds of the root pane
        if (newX >= 0 && newX + popup_username_valid.getWidth() <= root_success_popup_pane.getWidth()) {
            popup_username_valid.setLayoutX(newX);
        }
        if (newY >= 20 && newY>=newY/2 && newY<=250 && newY + popup_username_valid.getHeight() <= root_success_popup_pane.getHeight()) {
            popup_username_valid.setLayoutY(newY);
        }
        if (newY >= 22 && newY + popup_username_valid.getHeight() <= root_success_popup_pane.getHeight()) {
            popup_username_valid.setLayoutY(newY-15);
        }
    }
    @FXML
    private void handleMouseEntered() {
        success_button_ok.setStyle("-fx-background-color:  #00bb73;");
    }

    @FXML
    private void handleMouseExited() {
        success_button_ok.setStyle("-fx-background-color: #0dd98e;");
    }

    public void setMessage(String message) {
        success_popup_label.setText(message);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        // Close the popup window
        Stage stage = (Stage) success_popup_close_button.getScene().getWindow();
        stage.close();
    }



}
