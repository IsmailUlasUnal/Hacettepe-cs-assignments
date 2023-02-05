import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.Collections;
import java.util.Scanner;

public class CommandManager {
    Player currentPlayer;
    int row;
    int column;
    int tourScore = 0;

    public CommandManager(Player currentPlayer, Writer monitoringWriter, String textCommand){
        //when I create this class in main this code will run
        this.currentPlayer = currentPlayer;
        File commandFile = new File(textCommand);

        try {
            Scanner commandScan = new Scanner(commandFile);

            //In the beginning of the game we need to print Game grid: then game grid so I am doing this outside of while loop
            PrintManager.printMessage(monitoringWriter, "Game grid:\n");
            PrintManager.printGrid(monitoringWriter);

            while(commandScan.hasNext()){
                //It takes users input as array and as string
                String commmandString = commandScan.nextLine();
                String[] commandArray = commmandString.split(" ");

                PrintManager.printMessage(monitoringWriter, "\nSelect coordinate or enter E to end the game: " + commmandString + "\n");

                if(commandArray[0].equals("E")){ //for ending game
                    //when game ends with E command will give us username so I set player's username with this.
                    String username = commandScan.nextLine();
                    currentPlayer.setUsername(username);

                    //I add this player in a ArrayList of players then sort it descending score for seeing its rank
                    PlayerManager.playerSet.add(currentPlayer);

                    Collections.sort(PlayerManager.playerSet);

                    // first rank is 0th index so I add 1 for taking rank
                    int rank = Collections.binarySearch(PlayerManager.playerSet, currentPlayer) + 1;
                    int totalPerson = PlayerManager.playerSet.size();

                    // to write the operations we have done above
                    PrintManager.printMessage(monitoringWriter, "\nTotal Score: " + currentPlayer.getTotalScore() +" points\n");
                    PrintManager.printMessage(monitoringWriter, "\nEnter name: " + currentPlayer.getUsername() + "\n");
                    PrintManager.printMessage(monitoringWriter, "\nYour rank is " + rank + "\\" + totalPerson + ", ");
                    PrintManager.printMessage(monitoringWriter, "your score is ");

                    //this part is for printing the player's rank with lower than... higher than situations.
                    String cont = "";
                    try{ // If there is no player who has lower point it will give error so this part won't work
                        cont += (PlayerManager.playerSet.get(rank - 2).getTotalScore() - currentPlayer.getTotalScore()) + " points lower than " + PlayerManager.playerSet.get(rank - 2).getUsername() + " and "; //todo deneme
                    } catch (IndexOutOfBoundsException e) {
                        //we don't need to do anything
                    }
                    try{ //If there is no player who has higher point it will give error so this part won't work
                        cont += (currentPlayer.getTotalScore() - PlayerManager.playerSet.get(rank).getTotalScore()) + " points higher than " + PlayerManager.playerSet.get(rank).getUsername(); //todo deneme
                    } catch (IndexOutOfBoundsException e) {
                        cont = cont.substring(0, cont.length() - 5); //this part is for deleting end that happened in the other try catch block(lower part)
                    }
                    PrintManager.printMessage(monitoringWriter, cont);

                    PrintManager.printMessage(monitoringWriter, "\n\nGood bye!"); // this means our code has ended
                    break;

                }else if(commandArray.length == 2){ // for picking coordinate from grid
                    try{
                        row = Integer.parseInt(commandArray[0]);
                        column = Integer.parseInt(commandArray[1]);
                        GridManager.grid.get(row).get(column).delete(); //call delete function

                        currentPlayer.setTotalScore(Player.tourScore); //it appends tourScore to a total score

                        // printing grid and printing tour score
                        PrintManager.printGrid(monitoringWriter);
                        PrintManager.printMessage(monitoringWriter, "\nScore:  " + Player.tourScore +" points\n");

                    } catch(NullPointerException | IndexOutOfBoundsException e){
                        PrintManager.printMessage(monitoringWriter, "\nPlease enter a valid coordinate\n");

                    } catch(NoMatchException e){
                        // if it can't match with anything we are printing grid then print score: 0
                        PrintManager.printGrid(monitoringWriter);
                        PrintManager.printMessage(monitoringWriter, "\nScore: " + Player.tourScore +" points\n");
                    }
                }
                Player.tourScore = 0; // round ends so make tourScore 0
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}