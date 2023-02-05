import java.io.IOException;

public abstract class Square {

    /*
    every class that inside a map is created from this class
    printer writes the begining of move description
    it only writes player who is playing in this round, his dice, his place, and both players money
    printedDescription is for printing the rest of it which includes current player's action for example buy place etc.
     */
    protected static String printedDescription = "";

    public abstract void calculations(Player currentPlayer, Player enemyPlayer);

    protected static void printer(Player currentPlayer, Player enemyPlayer) throws IOException {
        Player firstPlayer;
        Player secondPlayer;

        if(currentPlayer.getName().equals("Player 1")){
            firstPlayer = currentPlayer;
            secondPlayer = enemyPlayer;
        }
        else{
            secondPlayer = currentPlayer;
            firstPlayer = enemyPlayer;
        }

        Main.writer.write(currentPlayer.getName() + "\t"
                + currentPlayer.getCurrentDice() + "\t"
                + (currentPlayer.getPlace() + 1) + "\t"
                + firstPlayer.getMoney() + "\t"
                + secondPlayer.getMoney() + "\t");
    }
}
