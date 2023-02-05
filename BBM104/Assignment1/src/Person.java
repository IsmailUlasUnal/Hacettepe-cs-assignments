public class Person {
    String personID;
    String name;
    String gender;
    int weight;
    int height;
    int dateOfBirth;
    int age;
    double calorieNeededDouble;
    long dailyCalorieNeeds;
    int calorie;
    int totalCalorieTaken;
    int totalCalorieBurned;
    short currentYear = 2022;

    //constructor
    public Person(String personID, String name, String gender, int weight, int height, int dateOfBirth){
        this.personID = personID;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        age = currentYear - dateOfBirth;
    }

    public void dailyCalorie(){
        if(gender.equals("male")){
            calorieNeededDouble = 66 + (13.75 * weight) + (5 * height) - (6.8 * age);
        }else{
            calorieNeededDouble = 665 + (9.6 * weight) + (1.7 * height) - (4.7 * age);
        }
        dailyCalorieNeeds = Math.round(calorieNeededDouble);
    }

    public boolean isEverUsed(){
        if((totalCalorieBurned == 0) && (totalCalorieTaken == 0)){
            return false;
        }
        return true;
    }

    public void checkExistence(Person[] checkPersonArray){
        for(int i = 0; i < checkPersonArray.length; i++ ){
            if(checkPersonArray[i] == null){
                checkPersonArray[i] = this;
                break;
            }
            else if(checkPersonArray[i].personID.equals(personID)){
                break;
            }
        }
    }
    public void calorieGain(int calorieTaken){
        calorie += calorieTaken;
        totalCalorieTaken += calorieTaken;
    }

    public void calorieLoss(long calorieBurned){
        calorie -= calorieBurned;
        totalCalorieBurned += calorieBurned;

    }
}