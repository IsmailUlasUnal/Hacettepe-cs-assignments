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

public class RemoveHallScreen extends Application {

    ChoiceBox<String> filmBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setPadding(new Insets(20,20,20,20));

        pane.getChildren().add(removeText());
        pane.getChildren().add(choiceBox());
        pane.getChildren().add(buttons(primaryStage));

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 700, 200);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * this function returns text for removing hall from current film
     * @return HBox text
     */
    private HBox removeText() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);

        Text text = new Text("Select the hall that you desire to remove from " + Film.currentFilm.getName() + " and then click OK.");
        text.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(text);

        return textHBox;
    }

    /**
     *
     * this function creates a choice box with movies
     * @return movie choice box
     */
    private HBox choiceBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        filmBox = new ChoiceBox<String>();
        filmBox.setPrefWidth(150);

        try {
            filmBox.getItems().addAll(Film.currentFilm.getHalls().keySet());
            filmBox.getSelectionModel().selectFirst();
        } catch (Exception ignored){}

        hBox.getChildren().add(filmBox);

        return hBox;
    }

    /**
     * this function creates ok and back button ok button
     * ok button is removing current movie's selected hall
     * @param primaryStage for openning new stage with back or ok button
     * @return returns ok ad back button
     */
    private HBox buttons(Stage primaryStage) {
        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("\u25C0 BACK");
        Button okButton = new Button("OK");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new FilmScreen().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(filmBox.getSelectionModel().getSelectedItem() != null){
                    Film.currentFilm.getHalls().remove(filmBox.getSelectionModel().getSelectedItem());
                    filmBox.getItems().remove(filmBox.getSelectionModel().getSelectedItem());

                    filmBox.getSelectionModel().selectFirst();
                }
            }
        });

        buttonsHBox.getChildren().add(backButton);
        buttonsHBox.getChildren().add(okButton);

        return buttonsHBox;
    }
}
