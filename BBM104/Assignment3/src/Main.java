import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        GridManager.createGrid(args[0]);

        //for writing file
        File monitoringFile = new File("monitoring.txt");
        File leaderboardFile = new File("leaderboard.txt");

        try {
            FileWriter monitoringWriter = new FileWriter(monitoringFile);
            new PlayerManager(); //read leaderboard txt and create player array
            Player currentPlayer = new Player(); // it creates current player which will do the actions in the command.txt file
            new CommandManager(currentPlayer, monitoringWriter, args[1]); //This will run the game
            monitoringWriter.close();

            //adding current player to the leaderboard.txt file
            FileWriter leaderboardWriter = new FileWriter(leaderboardFile, true);
            leaderboardWriter.write("\n"+ currentPlayer.getUsername() + " " + currentPlayer.getTotalScore());
            leaderboardWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}