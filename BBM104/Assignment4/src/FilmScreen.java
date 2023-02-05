import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class FilmScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox pane = new VBox(15);
        pane.setPadding(new Insets(30, 20, 30, 20));
        pane.setAlignment(Pos.CENTER);

        String media = new File("assets\\trailers\\" + Film.currentFilm.getTrailerPath()).toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(media));

        pane.getChildren().add(filmName());
        pane.getChildren().add(trailerPart(mediaPlayer));
        pane.getChildren().add(bottomButtons(primaryStage, mediaPlayer));

        Scene scene = new Scene(pane, 850, 600);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * shows film name and film's duration in a text
     * @return HBox with film text
     */
    private HBox filmName(){
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text filmText = new Text(Film.currentFilm.getName() + " (" + Film.currentFilm.getDuration() + " minutes)");
        filmText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(filmText);

        return textHBox;
    }

    /**
     * shows film trailer and creates a film action buttons such as go back 5 sec, go forwward 5 sec, fullscreen, go to the beginning, mini window, etc.
     * @param mediaPlayer with this media player it creates a mediaView for showing trailer
     * @return returns HBox that contains mediaView with trailer and buttons
     */
    private HBox trailerPart(MediaPlayer mediaPlayer){
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);

        VBox mediaVBox = new VBox(20);
        mediaVBox.setAlignment(Pos.CENTER);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(405);
        mediaView.setFitWidth(720);


        Button pausePlayButton = new Button("\u25B6");
        Button backwardButton = new Button("<<");
        Button forwardButton = new Button(">>");
        Button beginningButton = new Button("|<<");
        Button sideButton = new Button("\u2198");
        Button fullScreenButton = new Button("FullScreen");

        //clicking full screen button
        fullScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaView miniMediaView = new MediaView(mediaPlayer);
                Stage miniStage = new Stage();
                StackPane pane = new StackPane(miniMediaView);
                Scene miniScene = new Scene(pane);
                pane.widthProperty().addListener((obs, oldVal, newVal) -> {miniMediaView.setFitWidth(newVal.doubleValue());});
                pane.heightProperty().addListener((obs, oldVal, newVal) -> {miniMediaView.setFitHeight(newVal.doubleValue());});
                miniStage.setScene(miniScene);
                miniStage.setFullScreen(true);
                miniStage.show();

            }
        });

        sideButton.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        sideButton.setPrefWidth(50);

        //clicking sideButton button
        sideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaView miniMediaView = new MediaView(mediaPlayer);
                Stage miniStage = new Stage();
                StackPane pane = new StackPane(miniMediaView);
                Scene miniScene = new Scene(pane);
                pane.widthProperty().addListener((obs, oldVal, newVal) -> {miniMediaView.setFitWidth(newVal.doubleValue());});
                pane.heightProperty().addListener((obs, oldVal, newVal) -> {miniMediaView.setFitHeight(newVal.doubleValue());});
                miniStage.setScene(miniScene);
                miniStage.show();
            }
        });


        mediaVBox.getChildren().add(pausePlayButton);
        mediaVBox.getChildren().add(backwardButton);
        mediaVBox.getChildren().add(forwardButton);
        mediaVBox.getChildren().add(beginningButton);
        mediaVBox.getChildren().add(sideButton);
        mediaVBox.getChildren().add(fullScreenButton);

        pausePlayButton.setPrefWidth(50);
        backwardButton.setPrefWidth(50);
        forwardButton.setPrefWidth(50);
        beginningButton.setPrefWidth(50);

        //clicking play or pause button
        pausePlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(pausePlayButton.getText().equals("||")){
                    pausePlayButton.setText("\u25B6");
                    mediaPlayer.pause();
                }
                else{
                    pausePlayButton.setText("||");
                    mediaPlayer.play();
                }
            }
        });

        //clicking backward button
        backwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration newTime = (Duration.seconds((mediaPlayer.getCurrentTime().toSeconds()-5)));
                mediaPlayer.seek(newTime);
            }
        });

        //clicking forward button
        forwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration newTime = (Duration.seconds((mediaPlayer.getCurrentTime().toSeconds()+5)));
                mediaPlayer.seek(newTime);
            }
        });

        //clicking beginning button
        beginningButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration newTime = Duration.seconds(0);
                mediaPlayer.seek(newTime);
            }
        });

        // Add Volume slider
        Slider volumeSlider = new Slider();
        volumeSlider.setPrefWidth(100);
        volumeSlider.setValue(50);
        volumeSlider.setOrientation(Orientation.VERTICAL);
        mediaVBox.getChildren().add(volumeSlider);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });

        volumeSlider.setOnMouseReleased(event -> {
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
        });


        hBox.getChildren().add(mediaView);
        hBox.getChildren().add(mediaVBox);

        return hBox;
    }

    /**
     * creates a back button and ok button and if current user is an admin this will create add hall button and remove hall button
     *
     * @param primaryStage for opening other stages
     * @param mediaPlayer current trailer
     * @return returns every button that inside in a bottom part
     */
    private HBox bottomButtons(Stage primaryStage, MediaPlayer mediaPlayer){
        HBox bottomHBox = new HBox(20);
        bottomHBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("\u25C0 BACK");
        ChoiceBox<String> chooseHall = new ChoiceBox<String>();
        Button okButton = new Button("OK");

        chooseHall.setPrefWidth(150);

        try {
            chooseHall.getItems().addAll(Film.currentFilm.getHalls().keySet());
            chooseHall.getSelectionModel().selectFirst();
        } catch (Exception ignored){}

        bottomHBox.getChildren().add(backButton);

        if(User.currentUser.isAdmin()){
            Button addHallButton = new Button("Add Hall");
            Button removeHallButton = new Button("Remove Hall");

            bottomHBox.getChildren().add(addHallButton);
            bottomHBox.getChildren().add(removeHallButton);

            //clicking add hall button
            addHallButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        mediaPlayer.pause();
                        new AddHallScreen().start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            //clicking remove hall button
            removeHallButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        mediaPlayer.pause();
                        new RemoveHallScreen().start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        bottomHBox.getChildren().add(chooseHall);
        bottomHBox.getChildren().add(okButton);

        //clicking back button
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (User.currentUser.isAdmin()) {
                        new WelcomeScreenAdmin().start(primaryStage);
                        mediaPlayer.pause();

                    } else {
                        new WelcomeScreen().start(primaryStage);
                        mediaPlayer.pause();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //clicking ok button
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.pause();
                Hall.currentHall = Film.currentFilm.getHalls().get(chooseHall.getSelectionModel().getSelectedItem());
                try {
                    if(User.currentUser.isAdmin()) new HallScreenAdmin().start(primaryStage);
                    else {
                        new HallScreen().start(primaryStage);
                    }
                } catch (Exception ignored) {}
            }
        });

        return bottomHBox;
    }
}
