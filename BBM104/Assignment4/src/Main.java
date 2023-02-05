import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) throws IOException {
        DataReader.readAllFile();
        launch(args);
        DataWriter.writeBackup();
    }

    @Override
    public void start(Stage primaryStage) {
        new LoginScreen().start(primaryStage);
    }
}
