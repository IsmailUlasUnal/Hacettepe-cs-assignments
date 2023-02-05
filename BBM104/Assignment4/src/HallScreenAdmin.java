import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HallScreenAdmin extends HallScreen {

    HBox actionInfoHBox;
    Text actionInfoText;
    ChoiceBox<String> userChoiceBox;


    /**
     * this creates an empty text this text writes what user are doing right now
     * @return empty text HBox
     */
    private HBox actionTextCreate() {
        actionInfoHBox = new HBox();
        actionInfoText = new Text("");

        actionInfoHBox.getChildren().add(actionInfoText);
        actionInfoHBox.setAlignment(Pos.CENTER);

        return actionInfoHBox;
    }

    /**
     * creates a pane with the help of the HallScreen class
     * @param primaryStage for opening new screen
     * @return returns all pane
     */
    @Override
    public VBox paneCreate(Stage primaryStage) {
        super.paneCreate(primaryStage);
        pane.getChildren().add(2, choiceBoxCreate());
        pane.getChildren().add(3, actionTextCreate());

        return pane;
    }

    /**
     * this is for button abilities if we click a seat this will work if user only put itself mouse on seat but don't click it, it will call mouse handler
     * @param seat current seat that user clicked
     * @param toggleSeat current seat with toggle button
     * @param imageView current image put this for changing its image
     */
    @Override
    public void buttonAbilities(Seat seat, ToggleButton toggleSeat, ImageView imageView){
        toggleSeat.setDisable(false);
        toggleSeat.setOnAction(event -> {
            User selectedUser = DataReader.users.get(userChoiceBox.getSelectionModel().getSelectedItem());
            if(seat.getOwner() == null){
                seat.setOwner(selectedUser);
                seat.setPrice(Hall.currentHall.getPricePerSeat());
                seatInfoText.setText("Seat at "
                        + (seat.getRowOfSeat() + 1)
                        + "-"
                        + (seat.getColumnOfSeat() + 1)
                        + " bought for "
                        + selectedUser.getName()
                        + " for "
                        + seat.getPrice()
                        + " TL successfully!");
                imageView.setImage(reservedSeatImage);

            }
            else if(seat.getOwner() != null){
                seatInfoText.setText("Seat at "
                        + (seat.getRowOfSeat() + 1)
                        + ("-")
                        + (seat.getColumnOfSeat() + 1)
                        + " refunded"
                        + " for "
                        + selectedUser.getName()
                        + " successfully!");
                seat.setPrice(0);
                seat.setOwner(null);
                imageView.setImage(emptySeatImage);
            }

        });
        mouseHandler(seat);
    }

    /**
     * for writing and doing actions for lookking with mouse(not clicking)
     * @param seat current seat
     */
    public void mouseHandler(Seat seat) {
        toggleSeat.setOnMouseEntered(event -> {
            if(seat.getOwner() != null){
                actionInfoText.setText("Bought by "
                        + seat.getOwner().getName()
                        + " for "
                        + seat.getPrice()
                        + " TL!" );
            }
            else{
                actionInfoText.setText("Not bought yet");
            }
        });
        toggleSeat.setOnMouseExited( event -> actionInfoText.setText(""));
    }


    /**
     * this is for creating choice box for choosing a user
     * but this choice box doesn't include current user
     *
     * @return HBox with choice box
     */
    private HBox choiceBoxCreate() {
        HBox userHBox = new HBox();
        userChoiceBox = new ChoiceBox<String>();
        userChoiceBox.setPrefWidth(100);

        try {
            userChoiceBox.getItems().addAll(DataReader.users.keySet());
            userChoiceBox.getSelectionModel().select(User.currentUser.getName());
        } catch (Exception ignored){}

        userHBox.setAlignment(Pos.CENTER);
        userHBox.getChildren().add(userChoiceBox);

        return userHBox;
    }


}
