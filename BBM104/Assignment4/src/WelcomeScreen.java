import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().add(welcomeText());
        pane.getChildren().add(selectBox(primaryStage));
        pane.getChildren().add(logOutButton(primaryStage));

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 445, 200);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function creates a welcome screen information text.
     * this text is centered
     * @return HBox with welcome text
     */
    private HBox welcomeText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        String text = "Welcome " + User.currentUser.getName();
        if(User.currentUser.isClubMember()) text += " (Club Member)";
        text += "!\nSelect a film and then click OK to continue.";

        Text welcomeText = new Text(text);
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(welcomeText);

        return textHBox;
    }

    /**
     * this function creates a choice box with film names first film selected as default
     * at the same time this function creates ok button for accepting the movie and open film screen
     * @param primaryStage this is for openning new film scene
     * @return HBox with film choice box
     */
    private HBox selectBox(Stage primaryStage){
        HBox HBox = new HBox(15);
        HBox.setAlignment(Pos.CENTER);

        Button okButton = new Button("OK");

        ChoiceBox<String> selectBox = new ChoiceBox<String>();
        selectBox.setPrefWidth(350);

        try {
            selectBox.getItems().addAll(DataReader.films.keySet());
            selectBox.getSelectionModel().selectFirst();
        } catch (Exception ignored){}


        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Film.currentFilm = DataReader.films.get(selectBox.getSelectionModel().getSelectedItem());
                    new FilmScreen().start(primaryStage);
                } catch (Exception ignored) {}
            }
        });

        HBox.getChildren().add(selectBox);
        HBox.getChildren().add(okButton);

        return HBox;
    }

    /**
     * this function creates log out button for users to log out
     * @param primaryStage this is for opening login screen if user click log out button
     * @return HBox with logout button
     */
    private HBox logOutButton(Stage primaryStage){
        HBox logoutHBox = new HBox();
        logoutHBox.setAlignment(Pos.CENTER_RIGHT);

        Button logOut = new Button("LOG OUT");

        logoutHBox.getChildren().add(logOut);

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new LoginScreen().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return logoutHBox;
    }
}
