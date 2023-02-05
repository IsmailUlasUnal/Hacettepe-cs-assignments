public class CalculateDailyStuff { // This class is for static methods.

    public static int calculateCalorieTaken(int calorie, int portion){
        //int onePortion = 100;
        return  portion * calorie;
    }

    public static long calculateCalorieBurned(int calorie, int minute){
        double oneSport = 60;
        return Math.round((minute / oneSport) * calorie);
    }

    // for looking person's array position in people array
    public static int arrayPositionPerson(String command, Person[] personArray){
        int currentPerson = 0;
        for(int i = 0; i < 48; i++){
            if(personArray[i].personID.equals(command)){
                currentPerson = i;
                break;
            }
        }
        return currentPerson;
    }

    // for looking food's array position in foods array
    public static int arrayPositionFood(String command, Food[] foodArray){
        int currentFood = 0;
        for(int i = 0; i < 83; i++){
            if(foodArray[i].foodID.equals(command)){
                currentFood = i;
                break;
            }
        }
        return currentFood;
    }

    // for looking sport's array position in sports array
    public static int arrayPositionSport(String command, Sport[] sportArray){
        int currentSport = 0;
        for(int i = 0; i < 45; i++){
            if(sportArray[i].sportID.equals(command)){
                currentSport = i;
                break;
            }
        }
        return currentSport;
    }
}
