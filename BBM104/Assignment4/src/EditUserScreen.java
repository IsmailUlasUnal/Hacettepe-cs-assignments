import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EditUserScreen extends Application {
    TableView<User> table;

    @Override
    public void start(Stage primaryStage) throws Exception {
        tableCreate();

        Button backButton = new Button("\u25C0 BACK");
        Button changeClubMember = new Button("Promote/Demote Club Member");
        Button changeAdmin = new Button("Promote/Demote Admin");

        HBox editButtons = new HBox(15);
        editButtons.getChildren().addAll(backButton, changeClubMember, changeAdmin);
        editButtons.setAlignment(Pos.CENTER);

        buttonAbilities(primaryStage, backButton, changeClubMember, changeAdmin);

        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(30, 30, 30, 30));
        vBox.getChildren().addAll(table);
        vBox.getChildren().add(editButtons);

        Scene scene = new Scene(vBox, 550, 425);
        primaryStage.setTitle(DataReader.prop.getProperty("title"));
        primaryStage.getIcons().add(DataReader.logoImage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * creates a tableView that shows username, club member, and admin
     */
    private void tableCreate() {
        //Username column
        TableColumn<User, String> nameColumn = new TableColumn<User, String>("Username");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        //clubMember column
        TableColumn<User, Boolean> clubMemberColumn = new TableColumn<User, Boolean>("Club Member");
        clubMemberColumn.setMinWidth(150);
        clubMemberColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("clubMember"));

        //admin column
        TableColumn<User, Boolean> adminColumn = new TableColumn<User, Boolean>("Admin");
        adminColumn.setMinWidth(150);
        adminColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("admin"));

        table = new TableView<>();
        table.setPlaceholder(new Label("No user available in the database!"));
        table.setItems(getUser());
        table.getColumns().addAll(nameColumn, clubMemberColumn, adminColumn);


        table.getSelectionModel().selectFirst();

    }

    /**
     * this function make buttons work
     * @param primaryStage for opening other stages
     */
    private void buttonAbilities(Stage primaryStage, Button backButton, Button changeClubMember, Button changeAdmin) {
        //clicking back
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

        //clicking club member
        changeClubMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    User currentUser = table.getSelectionModel().getSelectedItem();
                    currentUser.setClubMember(!currentUser.isClubMember());
                    table.refresh();
                }catch(NullPointerException ignored){}
            }
        });

        //clicking admin
        changeAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    User currentUser = table.getSelectionModel().getSelectedItem();
                    currentUser.setAdmin(!currentUser.isAdmin());
                    table.refresh();
                }catch(NullPointerException ignored){}
            }
        });

    }


    /**
     * get all of the user from user hash map
     * @return
     */
    public ObservableList<User> getUser(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        DataReader.users.forEach((k,v) -> {
            if(!(User.currentUser.getName().equals(k))) userList.add(v);
        });
        return userList;
    }
}
