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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class LoginScreen extends Application{

    int blockedTime;

    private TextField usernameEnter;
    private PasswordField passwordEnter;
    private Text errorText;

    @Override
    public void start(Stage primaryStage) {

        VBox pane = new VBox(15);
        pane.setPadding(new Insets(20, 20, 30, 20));
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().add(loginText());
        pane.getChildren().add(loginInformation(primaryStage));
        pane.getChildren().add(errorTextCreate());

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 475, 250);
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This gridpane includes gridpane with username, password textFields with login and signup buttons
     * when user enters its password and username this code will call loginCheck function for checks username and password
     * @param primaryStage this opens other page in here
     * @return returns gridpane for username password enter
     */
    private GridPane loginInformation(Stage primaryStage) {

        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);

        loginPane.setPadding(new Insets(5,60,0,60));
        loginPane.setHgap(10);
        loginPane.setVgap(10);

        Text usernameText = new Text(20, 20, "Username:");
        usernameEnter = new TextField();

        Text passwordText = new Text(20, 20, "Password:");
        passwordEnter = new PasswordField();

        Button signUpButton = new Button("SIGN UP");
        Button loginButton = new Button("LOGIN");

        //clicking sign up
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SignUpScreen().start(primaryStage);
                } catch (Exception ignored) {}
            }
        });

        //clicking login
        loginButton.setOnAction(event -> loginCheck(primaryStage));

        loginPane.add(usernameText , 0, 0);
        loginPane.add(usernameEnter, 1, 0);
        loginPane.add(passwordText, 0, 1 );
        loginPane.add(passwordEnter, 1, 1);
        loginPane.add(signUpButton, 0, 2);
        loginPane.add(loginButton, 1, 2);
        GridPane.setHalignment(loginButton, HPos.RIGHT);

        return loginPane;
    }

    /**
     * This function creates a login screen information text.
     *
     * @return centered textHBox
     */
    private HBox loginText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text loginText = new Text("Welcome to the HUCS Cinema Reservation System!" +
                "\nPlease enter your credentials below and click LOGIN." +
                "\nYou can create a new account by clicking SIGN UP button.");
        loginText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(loginText);

        return textHBox;
    }

    /**
     * this function creates text for error text
     * in the beginning this text will be empty because when we started the program
     * code will not give error
     * @return error text place
     */
    private HBox errorTextCreate() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        errorText = new Text("");
        errorText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(errorText);

        return textHBox;
    }

    /**
     * this function will check the username's password and username is corrent.
     * if it is not it will give appropriate error text with appropriate error sound
     * @param primaryStage this is for opening new page if it is correct username and password
     */
    private void loginCheck(Stage primaryStage) {

        File mediaFile = new File("assets\\effects\\error.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        errorText.setY(30);
        errorText.setX(60);

        String enteredUserName = usernameEnter.getText();
        String enteredFirstPassword = User.passwordEncoder(passwordEnter.getText());

        blockedTime = Integer.parseInt(DataReader.prop.getProperty("block-time"));

        if(DataReader.users.containsKey(enteredUserName) && DataReader.users.get(enteredUserName).getPassword().equals(enteredFirstPassword)){
            User.currentUser = DataReader.users.get(enteredUserName);
            try {
                DataReader.maxTry = Integer.parseInt(DataReader.prop.getProperty("maximum-error-without-getting-blocked"));

                if(User.currentUser.isAdmin()){
                    new WelcomeScreenAdmin().start(primaryStage);
                }
                else{
                    new WelcomeScreen().start(primaryStage);

                }
            } catch (Exception ignored) {}
        }
        else if(DataReader.maxTry == 1){
            createTimer();
            errorText.setText("ERROR: Please wait until end of the " + blockedTime + " seconds to make a new operation!");
            mediaPlayer.play();
        }
        else {
            if(enteredUserName.equals("")){
                errorText.setText("ERROR: Username cannot be empty!");
                mediaPlayer.play();
            }
            else if(enteredFirstPassword.equals("")){
                errorText.setText("ERROR: Password cannot be empty!");
                mediaPlayer.play();
            }
            else{
                errorText.setText("ERROR: There is no such credential!");
                mediaPlayer.play();
            }
            DataReader.maxTry--;
            passwordEnter.setText("");
        }
        usernameEnter.setText("");
        passwordEnter.setText("");
    }

    /**
     * this timer is for wait user for spesific time if user enters wrong username or password over spesific number of try
     */
    private void createTimer() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask(){
            int remainTime = blockedTime;
            public void run(){
                if (remainTime > 0) {
                    remainTime--;
                }
                else{
                    timer.cancel();
                    DataReader.maxTry = Integer.parseInt(DataReader.prop.getProperty("maximum-error-without-getting-blocked"));
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }
}

