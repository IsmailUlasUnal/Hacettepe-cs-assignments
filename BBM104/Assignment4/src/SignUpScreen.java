import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;


public class SignUpScreen extends Application {

    private TextField usernameEnter;
    private PasswordField passwordEnter;
    private PasswordField passwordEnterAgain;
    private Text errorText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox(15);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().add(signUpText()); // center
        pane.getChildren().add(signUpInformation(primaryStage)); // center
        pane.getChildren().add(errorTextCreate()); // center

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 290);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * this is for user to enter his username and password for sign up
     * buttons for if user wants to go back login screen or applies his informations
     * if user clicks signup button it will call signUpCheck for checking informations
     * @param primaryStage this is for opening new scene if user click login button
     * @return gridpane that contains every textfield for user to enter username and password and click buttons
     */
    private GridPane signUpInformation(Stage primaryStage) {

        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);

        //loginPane.setPadding(new Insets(10,10,10,10));
        loginPane.setHgap(10);
        loginPane.setVgap(10);

        Text usernameText = new Text(20, 20, "Username:");
        usernameEnter = new TextField();

        Text passwordText = new Text(20, 20, "Password:");
        passwordEnter = new PasswordField();

        Text passwordTextAgain = new Text(20, 20, "Password:");
        passwordEnterAgain = new PasswordField();

        Button signUpButton = new Button("SIGN UP");
        Button loginButton = new Button("LOGIN");

        //clicking login button
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new LoginScreen().start(primaryStage);
            }
        });

        //clicking sign up button
        signUpButton.setOnAction(event -> signUpCheck());

        loginPane.add(usernameText , 0, 0);
        loginPane.add(usernameEnter, 1, 0);
        loginPane.add(passwordText, 0, 1 );
        loginPane.add(passwordEnter, 1, 1);
        loginPane.add(passwordTextAgain, 0, 2);
        loginPane.add(passwordEnterAgain, 1, 2);
        loginPane.add(signUpButton, 1, 3);
        loginPane.add(loginButton, 0, 3);
        GridPane.setHalignment(signUpButton, HPos.RIGHT);

        return loginPane;
    }

    /**
     * This function creates a signup screen information text.
     *
     * @return HBox with sign up text
     */
    private HBox signUpText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text signUpText = new Text("Welcome to the HUCS Cinema Reservation System!" +
                "\nFill the form bellow to create a new account" +
                "\nYou can go to Login page by clicking LOGIN Button.");
        signUpText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(signUpText);

        return textHBox;
    }

    /**
     * this function creates text for error text
     * in the beginning this text will be empty because when we started the program
     * code will not give error
     * @return error text place
     */
    private HBox errorTextCreate(){
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        errorText = new Text("");
        errorText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(errorText);

        return textHBox;
    }

    /**
     * this function check if user entered valid username and valid password
     * if it is valid it will create a new user with this username and text
     * if it is not it will give appropriate error message
     */
    private void signUpCheck() {

        File mediaFile = new File("assets\\effects\\error.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        errorText.setY(30);
        errorText.setX(60);

        String enteredUserName = usernameEnter.getText();
        String enteredFirstPassword = passwordEnter.getText();
        String enteredSecondPassword = passwordEnterAgain.getText();

        if(DataReader.users.containsKey(enteredUserName)){
            errorText.setText("ERROR: This username already exists!");
            mediaPlayer.play();
        }

        else if(enteredUserName.equals("")){
            errorText.setText("ERROR: Username cannot be empty!");
            mediaPlayer.play();
        }

        else if(enteredFirstPassword.equals("")){
            errorText.setText("ERROR: Password cannot be empty!");
            mediaPlayer.play();
        }

        else if(!enteredFirstPassword.equals(enteredSecondPassword)){
            errorText.setText("ERROR: Passwords do not match!");
            mediaPlayer.play();
        }

        else if(enteredFirstPassword.length() < 8){
            errorText.setText("Weak Password: Password length could not be lees than 8!");
            mediaPlayer.play();
        }

        else if(enteredFirstPassword.toLowerCase().equals(enteredSecondPassword)){
            errorText.setText("Weak Password: Password must be in capital letters!");
            mediaPlayer.play();
        }

        else{
            errorText.setText("You have successfully registered with your new credentials!");
            User user = new User(enteredUserName, User.passwordEncoder(enteredFirstPassword), false, false);
            DataReader.users.put(enteredUserName, user);
        }
        usernameEnter.setText("");
        passwordEnter.setText("");
        passwordEnterAgain.setText("");
    }
}
