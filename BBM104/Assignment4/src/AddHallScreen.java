import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class AddHallScreen extends Application {
    VBox allPane;
    HBox text;
    GridPane pane;
    ChoiceBox<Integer> rowBox;
    ChoiceBox<Integer> columnBox;
    HBox errorHBox;
    Text errorText;
    TextField hallName;
    TextField hallPrice;
    Button backButton;
    Button okButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        allPane = new VBox(10);
        allPane.setPadding(new Insets(30, 20, 30, 20));
        allPane.setAlignment(Pos.CENTER);

        allPane.getChildren().add(addHallText());
        allPane.getChildren().add(paneCreator(primaryStage));
        allPane.getChildren().add(errorTextCreate());

        //Create a scene and place it in the stage
        Scene scene = new Scene(allPane, 400, 325);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * this function creates a text that show current movie with duration
     * @return current movie informations with HBox
     */
    private HBox addHallText() {
        text = new HBox();
        text.setAlignment(Pos.CENTER);

        Text filmText = new Text(Film.currentFilm.getName() + "(" + Film.currentFilm.getDuration() + " minutes)");
        filmText.setTextAlignment(TextAlignment.CENTER);
        text.getChildren().add(filmText);

        return text;
    }

    /**
     * creates a gridpane that has choice box for user to choose row and column, textfield for writing a hall's name, price and back button
     * @param primaryStage this stage is for chancing screen
     * @return gridpane with wanted row column, name, price informations and back, ok button
     */
    private GridPane paneCreator(Stage primaryStage) {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setHgap(10);
        pane.setVgap(10);

        Text rowText = new Text("Row:");
        rowBox = new ChoiceBox<Integer>();
        rowBox.setPrefWidth(150);
        rowBox.getItems().addAll(3,4,5,6,7,8,9,10);
        rowBox.getSelectionModel().selectFirst();


        Text columnText = new Text("Column:");
        columnBox = new ChoiceBox<Integer>();
        columnBox.setPrefWidth(150);
        columnBox.getItems().addAll(3,4,5,6,7,8,9,10);
        columnBox.getSelectionModel().selectFirst();

        Text nameText = new Text("Name:");
        hallName = new TextField();

        Text priceText = new Text("Price:");
        hallPrice = new TextField();

        backButton = new Button("\u25C0 BACK");
        okButton = new Button("OK");

        //
        //// Clicking ok button
        //
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               checkAddHall();
            }
        });

        //
        //// Clicking back button
        //
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new FilmScreen().start(primaryStage);
                } catch (Exception ignored) {}
            }
        });

        pane.add(rowText, 0, 0);
        pane.add(rowBox, 1, 0);
        pane.add(columnText, 0, 1);
        pane.add(columnBox, 1, 1);
        pane.add(nameText, 0, 2);
        pane.add(hallName, 1, 2);
        pane.add(priceText, 0, 3);
        pane.add(hallPrice, 1, 3);
        pane.add(backButton, 0, 4);
        pane.add(okButton, 1, 4);

        GridPane.setHalignment(backButton, HPos.LEFT);
        GridPane.setHalignment(okButton, HPos.RIGHT);

        return pane;
    }

    /**
     * creates a hbox for writing a errors that my occur in the future when user enters wrong informations
     * @return returns empty HBox
     */
    private HBox errorTextCreate() {
        HBox errorHBox = new HBox();
        errorHBox.setAlignment(Pos.CENTER);

        errorText = new Text("");
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorHBox.getChildren().add(errorText);

        return errorHBox;
    }

    /**
     * this funciton check every hall information and gives right error if something is wrong
     */
    private void checkAddHall() {
        File mediaFile = new File("assets\\effects\\error.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        String enteredName = hallName.getText();
        String enteredPriceString = hallPrice.getText();

        if(enteredName.equals("")){
            errorText.setText("ERROR: hall name could not be empty!");
            mediaPlayer.play();
        }
        else if(enteredPriceString.equals("")){
            errorText.setText("ERROR: price could not be empty!");
            mediaPlayer.play();
        }
        else{
            try{
                int enteredPrice = Integer.parseInt(hallPrice.getText());
                if(enteredPrice < 0) throw new RuntimeException();

                if(doesHallExist()){
                    errorText.setText("ERROR: this hall exists!");
                    mediaPlayer.play();
                }
                else{
                    errorText.setText("Hall successfully created!");
                    addingHall();
                }
            } catch (Exception e){
                errorText.setText("ERROR: price could be a positive integer or zero!");
                mediaPlayer.play();
            }
        }
    }

    /**
     * looks if there is and hall with the same ane
     * if it is if return true else false
     * @return
     */
    private boolean doesHallExist() {
        String enteredName = hallName.getText();
        for(Film film: DataReader.films.values()){
            if(film.getHalls().containsKey(enteredName)) return true;
        }
        return false;
    }

    /**
     * this funcion is creating a hall with seats
     */
    private void addingHall() {
        int row = rowBox.getSelectionModel().getSelectedItem();
        int column = columnBox.getSelectionModel().getSelectedItem();

        String enteredName = hallName.getText();
        int enteredPrice = Integer.parseInt(hallPrice.getText());
        Hall newHall = new Hall(enteredName, enteredPrice, row, column);
        for(int currentRow = 0; currentRow < row; currentRow++){
            for(int currentColumn = 0; currentColumn < column; currentColumn++){
                Seat newSeat = new Seat(currentRow, currentColumn, null, 0);
                newHall.setSeats(newSeat);
            }
        }
        Film.currentFilm.setHalls(newHall);
    }

}
