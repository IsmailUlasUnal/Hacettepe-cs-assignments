import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerManager {
    /*
    This class is only for creating player from leaderboard file. and put them in a arraylist
     */
    File playerFile = new File("leaderboard.txt");

    public static ArrayList<Player> playerSet = new ArrayList<Player>();

    public PlayerManager(){
        try {
            Scanner playerScan = new Scanner(playerFile);

            while(playerScan.hasNext()){
                String[] playerArray = playerScan.nextLine().split(" ");
                Player player = new Player(playerArray[0]);
                int score = Integer.parseInt(playerArray[1]);
                player.setTotalScore(score);
                playerSet.add(player);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
