public class Propertie extends Square{

    /*
    this class contains every propertie.
     */

    private String name;
    private int cost;
    private Player owner;
    private Player firstPlayer;
    private Player secondPlayer;
    private String description = "";

    public RentBehaviour rentType;
    private int rent;


    public Propertie(String name, int cost, RentBehaviour rentType){
        this.name = name;
        this.cost = cost;
        this.rentType = rentType;

    }
    private void setRent() {
        rent = rentType.calculateRent(cost, owner.getAmountRailroads(), owner.getCurrentDice());
    }

    public void calculations(Player currentPlayer, Player enemyPlayer){
        if(owner != null) setRent();

        if(owner == null){
            if(currentPlayer.getMoney() >= cost){
                currentPlayer.setMoney(-cost);
                owner = currentPlayer;
                currentPlayer.setProperties(name);
                Banker.bankerMoney += cost;
                if(rentType instanceof RentByQuantity){
                    currentPlayer.setAmountRailroads(1);
                }
                if(!Main.gameIsOver) printedDescription = currentPlayer.getName() + " bought " + name + " " + printedDescription;
            }
            else{
                printedDescription = currentPlayer.getName() + " goes bankrupt ";
                Main.gameIsOver = true;
            }

        }else if(owner.equals(enemyPlayer) && currentPlayer.getMoney() >= rent){
            currentPlayer.setMoney(-rent);
            enemyPlayer.setMoney(rent);
            if(!Main.gameIsOver) printedDescription = currentPlayer.getName() + " paid rent for " + name + " " + printedDescription;
        }

        else if(owner.equals(currentPlayer)){
            if(!Main.gameIsOver) printedDescription = currentPlayer.getName() + " has " + name + " " + printedDescription;

        }
        else{
            printedDescription = currentPlayer.getName() + " goes bankrupt ";
            Main.gameIsOver = true;
        }
    }
}
