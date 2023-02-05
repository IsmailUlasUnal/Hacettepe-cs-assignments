import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;

public class HallScreen extends Application {

    protected VBox pane = new VBox();

    Text filmText;
    GridPane seats;
    Button backButton;
    HBox buttonHbox;

    HBox seatInfoHBox;
    Text seatInfoText;

    protected ToggleButton toggleSeat;

    protected static Image emptySeatImage =  new Image(new File("assets\\icons\\empty_seat.png").toURI().toString());
    protected static Image reservedSeatImage = new Image(new File("assets\\icons\\reserved_seat.png").toURI().toString());

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneCreate(primaryStage);
    }

    /**
     * this function creates a scene with paneCreate function
     * @param primaryStage for creating current scene
     */
    public void sceneCreate(Stage primaryStage){
        Scene scene = new Scene(paneCreate(primaryStage),  250 + Hall.currentHall.getColumn() * 60, 200 + Hall.currentHall.getRow() * 60);
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * this function is for creating pane that consist every other panes
     * @param primaryStage for opening new pages with button
     * @return returns main pane
     */
    public VBox paneCreate(Stage primaryStage) {
        pane = new VBox(10);
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.getChildren().add(textCreate());
        pane.getChildren().add(gridPaneCreate());
        pane.getChildren().add(seatInformationCreate());
        pane.getChildren().add(buttons(primaryStage));

        return pane;
    }

    /**
     * this function is creating a text that says current movies and current halls information
     * @return text HBox
     */
    private HBox textCreate() {
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);
        filmText = new Text(Film.currentFilm.getName() + " (" + Film.currentFilm.getDuration() + " minutes) " + "Hall: " + Hall.currentHall.getName());

        filmText.setTextAlignment(TextAlignment.CENTER);
        textHBox.getChildren().add(filmText);

        return textHBox;
    }

    /**
     * this function creates a gridpane that shows empty seats, reserved seats with image and toggle button we con do actions on this seats with buttonAbilities funciton
     * @return grid pane with toggle buttons, seats in current hall
     */
    private GridPane gridPaneCreate() {
        seats = new GridPane();
        seats.setAlignment(Pos.CENTER);

        seats.setPadding(new Insets(0,10,10,0));
        seats.setHgap(10);
        seats.setVgap(10);

        for(Seat seat: Hall.currentHall.getSeats()){
            toggleSeat = new ToggleButton();

            ImageView imageView;
            if(seat.getOwner() == null){
                imageView = new ImageView(emptySeatImage);
            }
            else{
                imageView = new ImageView(reservedSeatImage);
            }

            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            toggleSeat.setGraphic(imageView);


            if (seat.getOwner() != null && !(User.currentUser.equals(seat.getOwner()))){
                toggleSeat.setDisable(true);
            }

            seats.add(toggleSeat, seat.getColumnOfSeat(), seat.getRowOfSeat());

            buttonAbilities(seat, toggleSeat, imageView);
        }
        return seats;
    }

    /**
     * this function creates a toggle buttons actions when we click it,
     * buy seat and change its image and show text about it
     * @param seat for current seat
     * @param toggleSeat for current seat with toggle button
     * @param imageView current image put this for changing its image
     */
    public void buttonAbilities(Seat seat, ToggleButton toggleSeat, ImageView imageView) {
        toggleSeat.setOnAction(event -> {
            if(seat.getOwner() == null){
                seat.setOwner(User.currentUser);
                seat.setPrice(Hall.currentHall.getPricePerSeat());
                seatInfoText.setText("Seat at "
                        + (seat.getRowOfSeat() + 1)
                        + " - "
                        + (seat.getColumnOfSeat() + 1)
                        + " for " + seat.getPrice()
                        + " TL successfully!");
                imageView.setImage(reservedSeatImage);

            }
            else if(seat.getOwner().equals(User.currentUser)){
                seatInfoText.setText("Seat at "
                        + (seat.getRowOfSeat() + 1)
                        + ("-")
                        + (seat.getColumnOfSeat() + 1)
                        + " refunded successfully!");
                seat.setPrice(0);
                seat.setOwner(null);
                imageView.setImage(emptySeatImage);
            }
        });
    }

    /**
     * creates a empty text that will show seat information in the future
     * @return empty text with HBox
     */
    public HBox seatInformationCreate() {
        seatInfoHBox = new HBox();
        seatInfoText = new Text("");

        seatInfoHBox.getChildren().add(seatInfoText);
        seatInfoHBox.setAlignment(Pos.CENTER);

        return seatInfoHBox;
    }

    /**
     * back button
     * @param primaryStage for opening previous screen
     * @return returns HBox with back button
     */
    private HBox buttons(Stage primaryStage) {
        buttonHbox = new HBox();
        backButton = new Button("\u25C0 BACK");
        buttonHbox.setAlignment(Pos.CENTER_LEFT);
        backButton.setAlignment(Pos.CENTER_LEFT);
        buttonHbox.setPadding(new Insets(0,0,0,   100));

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

        buttonHbox.getChildren().add(backButton);

        return buttonHbox;
    }
}
