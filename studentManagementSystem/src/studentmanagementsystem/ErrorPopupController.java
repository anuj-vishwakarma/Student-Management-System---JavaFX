package studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ErrorPopupController {
    @FXML
    private Button close_button1;
    @FXML
    private Button myButton;
    @FXML
    private Label popup_label;
    private double initialX;
    private double initialY;

    @FXML
    private Pane error_popup_pane1;
    @FXML
    private AnchorPane root_error_popup_pane;

    @FXML
    private AnchorPane popup_username_invalid;

    @FXML
    public void initialize() {
        error_popup_pane1.setOnMousePressed(this::handleMousePressed);
        error_popup_pane1.setOnMouseDragged(this::handleMouseDragged);
    }

    public void handleMousePressed(MouseEvent event) {
        popup_username_invalid.toFront();
        initialX = event.getSceneX() - popup_username_invalid.getLayoutX();
        initialY = event.getSceneY() - popup_username_invalid.getLayoutY();
    }

    public void handleMouseDragged(MouseEvent event) {
        double newX = event.getSceneX() - initialX;
        double newY = event.getSceneY() - initialY;



        if (newX >= 0 && newX + popup_username_invalid.getWidth() <= root_error_popup_pane.getWidth()) {
            popup_username_invalid.setLayoutX(newX);
        }
        if (newY >= 15 && newY>=newY/2 && newY<=253 && newY + popup_username_invalid.getHeight() <= root_error_popup_pane.getHeight()) {
            popup_username_invalid.setLayoutY(newY);
        }
        if (newY >= 19 && newY + popup_username_invalid.getHeight() <= root_error_popup_pane.getHeight()) {
            popup_username_invalid.setLayoutY(newY-15);
        }
    }

    @FXML
    private void handleMouseEntered() {
        close_button1.setStyle("-fx-background-color: #eb0e0e;");
    }

    @FXML
    private void handleMouseExited() {
        close_button1.setStyle("-fx-background-color: red;");
    }

    public void setMessage(String message) {
        popup_label.setText(message);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        // Close the popup window
        Stage stage = (Stage) close_button1.getScene().getWindow();
        stage.close();
    }


}

// Tried to add some animations

//    private Timeline hoverEnterAnimation;
//    private Timeline hoverExitAnimation;

//    @FXML
//    public void initialize() {
//        hoverEnterAnimation = createColorTransition(Color.LIGHTBLUE, Color.DARKBLUE);
//        hoverExitAnimation = createColorTransition(Color.DARKBLUE, Color.LIGHTBLUE);
//    }

//    private Timeline createColorTransition(Color fromColor, Color toColor) {
//        return new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(close_button1.styleProperty(), toColorToCss(fromColor))),
//                new KeyFrame(Duration.seconds(0.5), new KeyValue(close_button1.styleProperty(), toColorToCss(toColor)))
//        );
//    }
//
//    private String toColorToCss(Color color) {
//        return String.format("-fx-background-color: #%02x%02x%02x;",
//                (int) (color.getRed() * 255),
//                (int) (color.getGreen() * 255),
//                (int) (color.getBlue() * 255));
//    }

