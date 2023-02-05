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

public class WelcomeScreenAdmin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().add(welcomeText());
        pane.getChildren().add(selectBox(primaryStage));
        pane.getChildren().add(adminButtons(primaryStage));
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
    private HBox welcomeText(){
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        String text = "Welcome " + User.currentUser.getName() + "(Admin";
        if(User.currentUser.isClubMember()) text += " - Club Member";
        text += ")!\n You can either select film below or do edits.";

        Text welcomeText = new Text(text);
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(welcomeText);

        return textHBox;
    }

    /**
     * this function creates choice box for choosing a movie and going to a film screen
     * @param primaryStage this is for openning new film stage
     * @return this function returns HBox with film choice box and ok button
     */
    private HBox selectBox(Stage primaryStage){
        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);

        Button okButton = new Button("OK");

        ChoiceBox<String> selectBox = new ChoiceBox<String>();
        selectBox.setPrefWidth(350);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Film.currentFilm = DataReader.films.get(selectBox.getSelectionModel().getSelectedItem());
                    new FilmScreen().start(primaryStage);
                } catch (Exception ignored) {}
            }
        });

        try {
            selectBox.getItems().addAll(DataReader.films.keySet());
            selectBox.getSelectionModel().selectFirst();
        } catch (Exception ignored){

        }

        hBox.getChildren().add(selectBox);
        hBox.getChildren().add(okButton);

        return hBox;
    }

    /**
     * this is for every admin buttons on this stage like add film, remove film, edit users
     *
     * @param primaryStage this is for opening new stages
     * @return returns HBox with all admin buttons with actions
     */
    private HBox adminButtons(Stage primaryStage){
        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.CENTER);

        Button addFilm = new Button("Add Film");
        Button removeFilm = new Button("Remove Film");
        Button editUsers = new Button("Edit Users");

        addFilm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AddFilmScreen().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        removeFilm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new RemoveFilmScreen().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        editUsers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new EditUserScreen().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        buttonsHBox.getChildren().add(addFilm);
        buttonsHBox.getChildren().add(removeFilm);
        buttonsHBox.getChildren().add(editUsers);

        return buttonsHBox;
    }

    /**
     * this function creates log out button for users to log out
     * @param primaryStage this is for opening login screen if user click log out button
     * @return logout button in HBox
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
