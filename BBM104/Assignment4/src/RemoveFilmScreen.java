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


public class RemoveFilmScreen extends Application {

    String filmToRemove;
    ChoiceBox<String> selectBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox(10);
        pane.setPadding(new Insets(30, 20, 30, 20));
        pane.getChildren().add(removeFilmText());
        pane.getChildren().add(filmChoice(primaryStage));
        pane.setAlignment(Pos.CENTER);

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 175);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * thin function creates a text for saying user to select a movie than click ok to remove
     * @return return text for remove film
     */
    private HBox removeFilmText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text loginText = new Text("Select the film that you desire to remove and then click OK.");
        loginText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(loginText);

        return textHBox;
    }

    /**
     * when we choose a movie from choice box and click ok this functions calls filmDelete function for deleting a movie
     * @param primaryStage for opening new window when we clicked back button
     * @return it returns choice box for film to remove, ok and back buttons
     */
    private VBox filmChoice(Stage primaryStage) {
        VBox vBox = new VBox(10);

        HBox filmHBox = new HBox();
        filmHBox.setAlignment(Pos.CENTER);

        selectBox = new ChoiceBox<String>();
        selectBox.setPrefWidth(350);

        try {
            selectBox.getItems().addAll(DataReader.films.keySet());
            selectBox.getSelectionModel().selectFirst();
        } catch (Exception ignored){ }

        filmHBox.getChildren().add(selectBox);

        HBox buttonHBox = new HBox(10);
        Button backButton = new Button("\u25C0 BACK");
        Button okButton = new Button("OK");

        buttonHBox.setAlignment(Pos.CENTER);
        backButton.setAlignment(Pos.CENTER);
        okButton.setAlignment(Pos.CENTER);

        buttonHBox.getChildren().add(backButton);
        buttonHBox.getChildren().add(okButton);

        vBox.getChildren().add(filmHBox);
        vBox.getChildren().add(buttonHBox);

        //clicking back button
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new WelcomeScreenAdmin().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //clicking ok button
        okButton.setOnAction(event -> filmDelete());
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    /**
     * this funciton deletes selected movie from both choice box and map at the same time
     */
    private void filmDelete() {
        filmToRemove = selectBox.getSelectionModel().getSelectedItem();
        DataReader.films.remove(filmToRemove);
        try {
            selectBox.getItems().remove(filmToRemove);
            selectBox.getSelectionModel().selectFirst();
        } catch (Exception ignored){}
    }
}
