import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static boolean gameIsOver = false;

    // i created a static output file because I need to use writer in square class and in main class
    public static File outputFile = new File("monitoring.txt");
    public static FileWriter writer;

    public static void main(String[] args) throws IOException {
        // these are for creating game map
        new ListJsonReader();
        new PropertyJsonReader();

        writer = new FileWriter(outputFile);

        File file = new File(args[0]);
        Scanner scan = new Scanner(file);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        while(scan.hasNext() && !gameIsOver && player1.getMoney() > 0 && player2.getMoney() > 0){
            String input = scan.nextLine();
            String[] inputArray = input.split(";");
            if(input.equals("show()")){
                show(player1, player2);
            }
            else {
                int dice = Integer.parseInt(inputArray[1]);

                if(inputArray[0].equals("Player 1")){
                    if(player1.getWaitCount() > 0){
                        player1.setWaitCount(player1.getWaitCount() - 1);
                        player1.setCurrentDice(dice);
                        Square.printer(player1, player2);
                        if(player1.getIsInFreeParking()){
                            Square.printedDescription = player1.getName() + " is in Free Parking (count=1)";
                            writer.write(Square.printedDescription);
                        }
                        else{
                            Square.printedDescription = player1.getName() + " in Jail " + " (count=" + (3 - player1.getWaitCount()) + ")";
                            writer.write(Square.printedDescription);
                        }
                    }
                    else{
                        player1.setCurrentDice(dice);
                        player1.setPlaceDice(dice);
                        PropertyJsonReader.squares[player1.getPlace()].calculations(player1, player2);
                        Square.printer(player1, player2);
                        writer.write(Square.printedDescription);

                    }
                }
                else{
                    if(player2.getWaitCount() > 0){
                        player2.setWaitCount(player2.getWaitCount() - 1);
                        player2.setCurrentDice(dice);
                        Square.printer(player2, player1);
                        if(player2.getIsInFreeParking()){
                            Square.printedDescription = player2.getName() + "  is in Free Parking (count=1)";
                            writer.write(Square.printedDescription);
                        }
                        else{
                            Square.printedDescription = player2.getName() + " in Jail " + " (count=" + (3 - player2.getWaitCount()) + ")";
                            writer.write(Square.printedDescription);
                        }
                    }
                    else{
                        player2.setCurrentDice(dice);
                        player2.setPlaceDice(dice);
                        PropertyJsonReader.squares[player2.getPlace()].calculations(player2, player1);
                        Square.printer(player2, player1);
                        writer.write(Square.printedDescription);
                    }
                }
            }
            Square.printedDescription = "";
            writer.write("\n");
        }
        show(player1, player2);
        writer.close();
    }

    static void show(Player firstPlayer, Player secondPlayer) throws IOException {
        writer.write("-----------------------------------------------------------------------------------------------------------\n");
        writer.write(firstPlayer.getName() + "\t" + firstPlayer.getMoney() + "\t" + "have: " + firstPlayer.getProperties() + "\n");
        writer.write(secondPlayer.getName() + "\t" + secondPlayer.getMoney() + "\t" + "have: " + secondPlayer.getProperties() + "\n");
        writer.write("Banker" + "\t" + Banker.bankerMoney + "\n");
        if(firstPlayer.getMoney() > secondPlayer.getMoney()){
            writer.write("Winner " + firstPlayer.getName() + "\n");
        }
        else if(secondPlayer.getMoney() > firstPlayer.getMoney()){// i don't make it with else because in draw position i want my code print nothing.

            writer.write("Winner " + secondPlayer.getName() + "\n");
        }
        writer.write("-----------------------------------------------------------------------------------------------------------");
    }
}