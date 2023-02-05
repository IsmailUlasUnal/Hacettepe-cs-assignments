public abstract class CardReader extends Square {
    /*
    this classes is only for creating places in the square array.
    I actually don't need this class this is only for I coded cards wrong and I don't have enough time to fix it
     */

}

class ChanceCardReader extends CardReader{
    public static int chanceCardNumber;

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {

        ListJsonReader.chanceArray[chanceCardNumber % ListJsonReader.chanceArray.length].calculations(currentPlayer, enemyPlayer);
        chanceCardNumber += 1;
    }
}

class CommunityCardReader extends CardReader{
    public static int communityCardNumber;

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        ListJsonReader.communityArray[communityCardNumber % ListJsonReader.communityArray.length].calculations(currentPlayer, enemyPlayer);
        communityCardNumber += 1;
    }
}

