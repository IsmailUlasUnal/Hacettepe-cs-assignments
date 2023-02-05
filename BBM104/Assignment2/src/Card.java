public class Card extends Square {

    /*
    description is for card name for example advance to go etc.
    and I also have a printedDescription static string for printing
    printedDescription prints side by side what player do in his round.
    for example if he draw a card that says go back 3 squares and draw another card in the same round
    printedDescription string help me to print them in the same line
     */
    String description;

    public Card(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(Player currentPlayer){
        if(!Main.gameIsOver) printedDescription = currentPlayer.getName() + " draw " + description + " " + printedDescription;

    }


    public void againstBank(Player currentPlayer, int moneyExchange){
        /*
        this is for every calculations that cards do between player and bank
        it is in card class because both community and chest card has calculations between bank and player
         */

        if(currentPlayer.getMoney() >= -moneyExchange){
            currentPlayer.setMoney(moneyExchange);
            Banker.bankerMoney -= moneyExchange;
            setDescription(currentPlayer);
        }
        else{
            Square.printedDescription = currentPlayer.getName() + " goes bankrupt ";
            Main.gameIsOver = true;
        }
    }

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
    }
}










class ChanceCard extends Card{

    public ChanceCard(String description) {
        super(description);
    }

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        if ("Advance to Go (Collect $200)".equals(getDescription())) {
            currentPlayer.setPlaceTP(0);
            againstBank(currentPlayer, 200);

        } else if ("Advance to Leicester Square".equals(getDescription())) {
            if(currentPlayer.getPlace() > 26){
                againstBank(currentPlayer, 200);
            }
            currentPlayer.setPlaceTP(26);
            PropertyJsonReader.squares[26].calculations(currentPlayer, enemyPlayer);
            setDescription(currentPlayer);


        } else if ("Go back 3 spaces".equals(getDescription())) {
            currentPlayer.setPlaceTP(currentPlayer.getPlace() - 3);
            PropertyJsonReader.squares[currentPlayer.getPlace()].calculations(currentPlayer, enemyPlayer);
            setDescription(currentPlayer);


        } else if ("Pay poor tax of $15".equals(getDescription())) {
            againstBank(currentPlayer, -15);


        } else if ("Your building loan matures - collect $150".equals(getDescription())) {
            againstBank(currentPlayer, 150);


        } else if ("You have won a crossword competition - collect $100 ".equals(getDescription())) {
            againstBank(currentPlayer, 100);
        }
    }
}

















class CommunityCard extends Card{

    public CommunityCard(String description) {
        super(description);
    }

    public void againstPlayer(Player currentPlayer, Player enemyPlayer, int moneyExchange){
        /*
        this class is only inside community card class because change card does not have player agains player calculations
         */

        if(enemyPlayer.getMoney() >= moneyExchange){
            currentPlayer.setMoney(moneyExchange);
            enemyPlayer.setMoney(-moneyExchange);
            setDescription(currentPlayer);

        }
        else{
            printedDescription = enemyPlayer.getName() + " goes bankrupt";
            Main.gameIsOver = true;
        }
    }

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {

        if ("Advance to Go (Collect $200)".equals(getDescription())) {
            currentPlayer.setPlaceTP(0);
            againstBank(currentPlayer, 200);


        } else if ("Bank error in your favor - collect $75".equals(getDescription())) {
            againstBank(currentPlayer, 75);


        } else if ("Doctor's fees - Pay $50".equals(getDescription())) {
            againstBank(currentPlayer, -50);


        } else if ("It is your birthday Collect $10 from each player".equals(getDescription())) {
            againstPlayer(currentPlayer, enemyPlayer, 10);


        } else if ("Grand Opera Night - collect $50 from every player for opening night seats".equals(getDescription())) {
            againstPlayer(currentPlayer, enemyPlayer, 50);


        } else if ("Income Tax refund - collect $20".equals(getDescription())) {
            againstBank(currentPlayer, 20);


        } else if ("Life Insurance Matures - collect $100".equals(getDescription())) {
            againstBank(currentPlayer, 100);


        } else if ("Pay Hospital Fees of $100".equals(getDescription())) {
            againstBank(currentPlayer, -100);


        } else if ("Pay School Fees of $50".equals(getDescription())) {
            againstBank(currentPlayer, -50);


        } else if ("You inherit $100".equals(getDescription())) {
            againstBank(currentPlayer, 100);

        } else if ("From sale of stock you get $50".equals(getDescription())) {
            againstBank(currentPlayer, 50);

        }
    }
}
