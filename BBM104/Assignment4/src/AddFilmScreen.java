import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class AddFilmScreen extends Application {

    private TextField nameEnter;
    private TextField trailerPathEnter;
    private TextField durationEnter;
    private Text errorText;


    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        pane.setPadding(new Insets(30, 20, 30, 20));

        pane.getChildren().add(addFilmText());
        pane.getChildren().add(filmInformation(primaryStage));
        pane.getChildren().add(errorTextCreate());

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 485, 275);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * This function creates text for pane, this pane is centered.
     * this text is "Please give name, relative path of the trailer and duration of the film."
     * @return this function returns text with HBox
     */
    private HBox addFilmText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text loginText = new Text("Please give name, relative path of the trailer and duration of the film.");
        loginText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(loginText);

        return textHBox;
    }

    /**
     * this function take all film information that user gives and calls filmcheck funciton for checking the user's input is correct or not
     *
     * @param primaryStage this is the stage that all these things will open, this function needs this because of button actions like clicking ok button
     * @return this function returns gridpane that user needs to write film name, trailer path, and film duration
     */
    private GridPane filmInformation(Stage primaryStage) {

        GridPane filmInfoPane = new GridPane();
        filmInfoPane.setAlignment(Pos.CENTER);

        filmInfoPane.setPadding(new Insets(20,20,20,20));
        filmInfoPane.setHgap(10);
        filmInfoPane.setVgap(10);

        addFilmText();

        Text nameText = new Text(20, 20, "Name:");
        nameEnter = new TextField();

        Text trailerPathText = new Text(20, 20, "Trailer (Path):");
        trailerPathEnter = new TextField();

        Text durationText = new Text(20, 20, "Duration (m):");
        durationEnter = new TextField();

        Button backButton = new Button("\u25C0 BACK");
        Button okButton = new Button("OK");

        filmInfoPane.add(nameText, 0, 0);
        filmInfoPane.add(nameEnter, 1, 0);
        filmInfoPane.add(trailerPathText, 0, 1);
        filmInfoPane.add(trailerPathEnter, 1, 1);
        filmInfoPane.add(durationText, 0, 2);
        filmInfoPane.add(durationEnter, 1, 2);
        filmInfoPane.add(okButton,1, 3 );
        filmInfoPane.add(backButton, 0, 3);
        GridPane.setHalignment(backButton, HPos.LEFT);
        GridPane.setHalignment(okButton,HPos.RIGHT);

        //clicking ok button
        okButton.setOnAction(event -> filmCheck());

        //clicking back button
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new WelcomeScreenAdmin().start(primaryStage);
                } catch (Exception ignored) {}
            }
        });

        return filmInfoPane;
    }

    private HBox errorTextCreate() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        errorText = new Text("");
        errorText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(errorText);

        return textHBox;
    }

    /**
     * checks users input is correct or not if it is wrong then code will give appropriate error text and sound
     */
    private void filmCheck() {

        File mediaFile = new File("assets\\effects\\error.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        errorText.setY(30);
        errorText.setX(60);

        String enteredFilmName = nameEnter.getText();
        String enteredPath = trailerPathEnter.getText();

        if(enteredFilmName.equals("")){
            errorText.setText("ERROR: Film name could not be empty!");
            mediaPlayer.play();
        }
        else if(enteredPath.equals("")){
            errorText.setText("ERROR: Trailer path could not be empty!");
            mediaPlayer.play();
        }
        else if(DataReader.films.containsKey(enteredFilmName)){
            errorText.setText("ERROR: This movie already exists!");
            mediaPlayer.play();
        }
        else {
            try{
                int enteredDuration = Integer.parseInt(durationEnter.getText());
                if(enteredDuration < 0) throw new RuntimeException();

                try{
                    File trailerFile = new File("assets\\trailers\\" + enteredPath);
                    Media trailerMedia = new Media(trailerFile.toURI().toString());
                    Film newFilm = new Film(enteredFilmName, enteredPath, enteredDuration);
                    DataReader.films.put(enteredFilmName, newFilm);
                    errorText.setText("SUCCESS: Film added successfully!");
                }
                catch(Exception e){
                    errorText.setText("ERROR: There is no such trailer!");
                    mediaPlayer.play();
                }

            } catch (Exception e) {
                errorText.setText("ERROR: Please write an positive integer to the duration time!");
                mediaPlayer.play();
            }
        }
        nameEnter.setText("");
        trailerPathEnter.setText("");
        durationEnter.setText("");
    }
}
