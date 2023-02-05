public abstract class User{
    /*
    user was not a good naming for this class
    it means this class covers both bank and players
     */

}
class Player extends User {

    public Player(String name){
        this.name = name;
    }

    public int getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(int currentDice){
        this.currentDice = currentDice;
    }

    public int getAmountRailroads() {
        return amountRailroads;
    }

    public void setAmountRailroads(int amountRailroads) {
        amountRailroads += 1;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int cost) {
        money += cost;
    }

    public void setPlaceDice(int currentDice){
        int firstPlace = place;
        place = (place + currentDice) % 40;
        passGo(firstPlace);
    }

    public void setPlaceTP(int newPlace){
        int firstPlace = place;
        place = newPlace;
    }

    private void passGo(int firstPlace){
        if (firstPlace > place){
            money += 200;
            Banker.bankerMoney -= 200;
        }
    }

    public int getPlace(){
        return place;
    }

    public String getName() {
        return name;
    }

    public void setWaitCount(int waitCount) {
        this.waitCount = waitCount;
    }

    public int getWaitCount() {
        return waitCount;
    }

    public boolean getIsInFreeParking() {
        return inFreeParking;
    }

    public void setInFreeParking(boolean inFreeParking) {
        this.inFreeParking = inFreeParking;
    }

    public void setProperties(String properties) {
        if(this.properties.equals("")){
            this.properties = properties;
        }
        else{
            this.properties = this.properties + "," + properties;
        }
    }

    public String getProperties() {
        return properties;
    }

    private int currentDice;
    private int amountRailroads;
    private int place = 0;
    private int money = 15_000;
    private String name;
    private int waitCount; // BUNU HALLET BU YARIM GET MET LAZIM
    private boolean inFreeParking = false;

    private String properties = "";

}
class Banker extends User{
    public static int bankerMoney = 100_000;
}