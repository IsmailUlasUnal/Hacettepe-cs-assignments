public interface RentBehaviour {
    /*
     this is for Strategy pattern. while I am creating a propertie class I am sending
     a new Rent object for indicating which propertie do I create for example If I send
     RentByCost object it means that it is a Land propertie.
     */

    int calculateRent(int cost, int amountRailroads, int currentDice);
}

class RentByCost implements RentBehaviour{

    @Override
    public int calculateRent(int cost, int amountRailroads, int currentDice) {
        if (cost <= 2000){
            return cost * 40 / 100;
        }
        else if (cost > 2000 && cost <= 3000){
            return cost * 30 / 100;
        }
        else{
            return cost * 20 / 100;
        }
    }
}

class RentByDice implements RentBehaviour{

    @Override
    public int calculateRent(int cost, int amountRailroads, int currentDice) {
        return currentDice * 4;
    }
}

class RentByQuantity implements RentBehaviour{

    @Override
    public int calculateRent(int cost, int amountRailroads, int currentDice) {
        return amountRailroads * 25;
    }
}