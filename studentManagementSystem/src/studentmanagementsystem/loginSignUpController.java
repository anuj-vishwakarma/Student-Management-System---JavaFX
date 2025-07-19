package studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginSignUpController extends Application implements Initializable {
    @FXML
    private AnchorPane signin_signup_root_pane;

    @FXML
    private Button close_button1;

    @FXML
    private Button close_button2;

    @FXML
    private Button minimize_button2;
    @FXML
    private Button minimize_button1;

    @FXML
    private ImageView close_button_image;

    @FXML
    private ImageView minimize_button_image;

    @FXML
    private Pane login_container;

    @FXML
    private Pane signup_container;

    @FXML
    private Pane welcome_toggle_left;

    @FXML
    private Pane welcome_toggle_right;

    @FXML
    private ToggleButton signin_button;

    @FXML
    private ToggleButton signup_button_1;

    @FXML
    private ToggleButton signup_button;

    @FXML
    private TextField signup_field_username;

    @FXML
    private TextField signup_field_email;

    @FXML
    private TextField signup_field_password;

    @FXML
    private ToggleButton signin_user_button;

    @FXML
    private TextField username_signin_field;

    @FXML
    private TextField password_signin_field;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x= 0 ;
    private double y= 0;
    public void showErrorPopup(String message) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("error_popup.fxml"));
            Parent root = fxmlLoader.load();

            ErrorPopupController popupController = fxmlLoader.getController();
            popupController.setMessage(message);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Error");
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSuccessPopup(String message){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("success_popup.fxml"));
            Parent root = fxmlLoader.load();

            SuccessPopupController successPopupController = fxmlLoader.getController();
            successPopupController.setMessage(message);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Success");
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loginAdmin(){

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = database.connectDb();

        try{ // IT WORKS GOOD : ) NOW LETS DESIGN THE DASHBOARD FORM : )
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username_signin_field.getText());
            prepare.setString(2, String.valueOf(password_signin_field.getText().hashCode()));

            result = prepare.executeQuery();
//            CHECK IF FIELDS ARE EMPTTY
            if(username_signin_field.getText().isEmpty() || password_signin_field.getText().isEmpty()){
                showErrorPopup("Please Fill In all blank fields");

            }else{
                if(result.next()){
//                    THEN PROCEED TO DASHBOARD FORM

                    getData.username = username_signin_field.getText();


//                    TO HIDE THE LOGIN FORM
                    signin_button.getScene().getWindow().hide();
                    //LINK YOUR DASHBOARD
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                }else{
                    showErrorPopup("Error, Incorrect username or password!");
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void signUpAdmin(){

        String sql = "INSERT INTO admin (username, password, email) VALUES (?, ?, ?);";

        connect = database.connectDb();

        try{ // IT WORKS GOOD : ) NOW LETS DESIGN THE DASHBOARD FORM : )
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signup_field_username.getText());
            prepare.setString(2, String.valueOf(signup_field_password.getText().hashCode()));
            prepare.setString(3, signup_field_email.getText());

//            CHECK IF FIELDS ARE EMPTTY
            if(signup_field_username.getText().isEmpty() || signup_field_password.getText().isEmpty() || signup_field_email.getText().isEmpty()){
                showErrorPopup("Please Fill In all blank fields");

            }else if(!signup_field_email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                showErrorPopup("Error, invalid email , try a different one!");
            }
            else{
                try {
                    prepare.executeUpdate();
                    showSuccessPopup("Account created, you can now sign in.");
                    handleSignIn();
                } catch (SQLException e) {
                    showErrorPopup("Username Already Exists, create unique.");
                }

            }
        }catch(Exception e){e.printStackTrace();}

    }
    @FXML
    private void handleSignIn() {
//        System.out.println("handleSignIn called");

        signup_container.setLayoutX(500.0);
        signup_container.setLayoutY(0.0);
        signup_container.setVisible(true);

        login_container.setLayoutX(0.0);
        login_container.setLayoutY(0.0);
        login_container.setVisible(true);

        welcome_toggle_right.setLayoutX(500.0);
        welcome_toggle_right.setLayoutY(0.0);
        welcome_toggle_right.setVisible(true);

        welcome_toggle_left.setVisible(false);

    }
    @FXML
    public void handleSignUp() {
//        System.out.println("handleSignUp called");

        signup_container.setLayoutX(500.0);
        signup_container.setLayoutY(0.0);
        signup_container.setVisible(true);

        welcome_toggle_left.setLayoutX(0.0);
        welcome_toggle_left.setLayoutY(0.0);
        welcome_toggle_left.setVisible(true);

        login_container.setVisible(false);
        welcome_toggle_right.setVisible(false);

    }
    public void close(){
        System.exit(0);
    }
    public void minimize() {
        Stage stage2 = (Stage) minimize_button2.getScene().getWindow();
        Stage stage1 = (Stage) minimize_button1.getScene().getWindow();
        stage2.setIconified(true);
        stage1.setIconified(true);
    }

    //LETS NAME THE COMPONENTS ON LOGIN FORM : )

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void handleMouseEnteredSignIn() {
        signin_button.setStyle("-fx-background-color: #00cc81");
    }

    @FXML
    private void handleMouseExitedSignIn() {
        signin_button.setStyle("-fx-background-color:  #00e08e");
    }

    @FXML
    private void handleMouseEnteredSignUp() {
        signup_button.setStyle("-fx-background-color: #f0f0f0");
    }

    @FXML
    private void handleMouseExitedSignUp() {
        signup_button.setStyle("-fx-background-color:  white");
    }
    @FXML
    private void handleClose(){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginSignUpController.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Attendance Management Application");
        try {
            Image icon = new Image(Objects.requireNonNull(loginSignUpController.class.getResourceAsStream("discord.png")));
            stage.getIcons().add(icon);
        }

        catch (Exception e){ }
        String str = "df";

        stage.setIconified(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}

