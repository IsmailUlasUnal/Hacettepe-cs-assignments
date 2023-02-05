public abstract class Other extends Square{

    @Override
    public abstract void calculations(Player currentPlayer, Player enemyPlayer);

}




class GO extends Other{

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        if(!Main.gameIsOver) Square.printedDescription = currentPlayer.getName() + " is in GO square " + printedDescription;
    }
}

class IncomeTax extends Other{

    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        if(currentPlayer.getMoney() >= 100){
            currentPlayer.setMoney(-100);
            Banker.bankerMoney += 100;
            if(!Main.gameIsOver) Square.printedDescription = currentPlayer.getName() + " paid Tax " + printedDescription;
        }
        else{
            Square.printedDescription = currentPlayer.getName() + " goes bankrupt ";
            Main.gameIsOver = true;

        }
    }
}

class Jail extends Other{
    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        currentPlayer.setWaitCount(3);
        currentPlayer.setInFreeParking(false);
        if(!Main.gameIsOver) Square.printedDescription = currentPlayer.getName() + " went to jail " + printedDescription;
    }
}

class FreeParking extends Other{
    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        currentPlayer.setWaitCount(1);
        currentPlayer.setInFreeParking(true);
        if(!Main.gameIsOver) Square.printedDescription = currentPlayer.getName() + " is in Free Parking " + printedDescription;
    }
}

class GoToJail extends Other{
    @Override
    public void calculations(Player currentPlayer, Player enemyPlayer) {
        currentPlayer.setWaitCount(3);
        currentPlayer.setPlaceTP(10);
        currentPlayer.setInFreeParking(false);
        if(!Main.gameIsOver) Square.printedDescription = currentPlayer.getName() + " went to jail " + printedDescription;

    }
}