import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //in this part I have opened the txt files for reading

        File fileForPerson = new File("people.txt");
        Scanner scanForPerson = new Scanner(fileForPerson);

        File fileForFood = new File("food.txt");
        Scanner scanForFood = new Scanner(fileForFood);

        File fileforSport = new File("sport.txt");
        Scanner scanForSport = new Scanner(fileforSport);

        File fileForCommand = new File(args[0]);
        Scanner scanForCommand = new Scanner(fileForCommand);

        File fileForOutput = new File("monitoring.txt");
        FileWriter writer = new FileWriter(fileForOutput);

        // I opened 3 array for food person and sport for making a list
        // just because it says constant files i opened array's with spesific(48, 83, 45) length
        Person[] people = new Person[48]; //For every person
        Food[] foods = new Food[83]; //For every food
        Sport[] sports = new Sport[45]; //For every sport

        // this array is only for printing in the right order
        Person[] peopleForPrinting = new Person[48]; //for printing in the right order

        // for looking array index
        int personCount = 0;
        int foodCount = 0;
        int sportCount = 0;

        // i take an input line and make it array for all information then i created a new object for making operation
        while (scanForPerson.hasNext()) {
            String personAllInformation = scanForPerson.nextLine();
            String[] personArray = personAllInformation.split("[\t, \n]+"); // i splitted with /t
            people[personCount] = new Person(personArray[0], personArray[1], personArray[2], Integer.parseInt(personArray[3]), Integer.parseInt(personArray[4]), Integer.parseInt(personArray[5]));
            personCount += 1;
        }

        while (scanForFood.hasNext()) {
            String foodAllInformation = scanForFood.nextLine();
            String[] foodArray = foodAllInformation.split("[\t, \n]+");
            foods[foodCount] = new Food(foodArray[0], foodArray[1], Integer.parseInt(foodArray[2]));
            foodCount += 1;
        }

        while (scanForSport.hasNext()) {
            String sportAllInformation = scanForSport.nextLine();
            String[] sportArray = sportAllInformation.split("[\t, \n]+");
            sports[sportCount] = new Sport(sportArray[0], sportArray[1], Integer.parseInt(sportArray[2]));
            sportCount += 1;
        }

        /*
        I am taking command's with string form and make calculations with it
         */
        while (scanForCommand.hasNext()) {
            String commandLine = scanForCommand.nextLine();
            String[] commandArray = commandLine.split("[\t, \n]+");

            if(commandArray[0].charAt(0) == 'p'){ // for every print statement
                if(commandArray[0].charAt(5) == 'W'){ ///for printWarn
                    boolean forNoSuch = true; // if we can't print anything
                    for(int i = 0; i< 48; i++){
                        if(peopleForPrinting[i] != null && peopleForPrinting[i].isEverUsed()){ // if array's element is not null and we used it it will work
                            peopleForPrinting[i].dailyCalorie();
                            double result = peopleForPrinting[i].totalCalorieTaken - peopleForPrinting[i].totalCalorieBurned - peopleForPrinting[i].dailyCalorieNeeds;
                            if(result > 0){
                                writer.write(peopleForPrinting[i].name + "\t" + peopleForPrinting[i].age + "\t" + peopleForPrinting[i].dailyCalorieNeeds + "kcal\t" + peopleForPrinting[i].totalCalorieTaken + "kcal\t"+ peopleForPrinting[i].totalCalorieBurned + "kcal\t+" + Math.round(result) +"kcal\n");
                                forNoSuch = false;
                            }
                        }
                    }
                    if(forNoSuch){
                        writer.write("there\tis\tno\tsuch\tperson\n");
                    }
                }
                else if (commandArray[0].charAt(5) == 'L'){ ///for printList
                    for(int i = 0; i < 48; i++){
                        if(peopleForPrinting[i] != null && peopleForPrinting[i].isEverUsed()){
                            peopleForPrinting[i].dailyCalorie();
                            double result = peopleForPrinting[i].totalCalorieTaken - peopleForPrinting[i].totalCalorieBurned - peopleForPrinting[i].dailyCalorieNeeds;
                            String sign = "";
                            if(result > 0) {
                                sign = "+";
                            }
                            writer.write(peopleForPrinting[i].name + "\t" + peopleForPrinting[i].age + "\t" + peopleForPrinting[i].dailyCalorieNeeds + "kcal\t" + peopleForPrinting[i].totalCalorieTaken + "kcal\t"+ peopleForPrinting[i].totalCalorieBurned + "kcal\t" + sign + Math.round(result) +"kcal\n");
                        }
                    }
                }else{ //for printpersonID
                    String id = commandArray[0].substring(6,11);
                    int currentPerson = CalculateDailyStuff.arrayPositionPerson(id, people);
                    people[currentPerson].dailyCalorie();
                    double result = people[currentPerson].totalCalorieTaken - people[currentPerson].totalCalorieBurned - people[currentPerson].dailyCalorieNeeds;
                    String sign = "";
                    if(result > 0) {
                        sign = "+";
                    }
                    writer.write(people[currentPerson].name + "\t" + people[currentPerson].age + "\t" + people[currentPerson].dailyCalorieNeeds + "kcal\t" + people[currentPerson].totalCalorieTaken + "kcal\t"+ people[currentPerson].totalCalorieBurned + "kcal\t" + sign + Math.round(result) +"kcal\n");

                }
            }else{
                int currentPerson = CalculateDailyStuff.arrayPositionPerson(commandArray[0], people);
                people[currentPerson].checkExistence(peopleForPrinting);
                if(commandArray[1].charAt(0) == '1'){/// food
                    int currentFood = CalculateDailyStuff.arrayPositionFood(commandArray[1], foods);
                    int calorieTaken = CalculateDailyStuff.calculateCalorieTaken(foods[currentFood].calorieCount, Integer.parseInt(commandArray[2]));
                    people[currentPerson].calorieGain(calorieTaken);
                    writer.write(people[currentPerson].personID + "\thas\ttaken\t" + calorieTaken + "kcal\tfrom\t" + foods[currentFood].nameOfFood + "\n"); ///CALORIE TAKEN DEGISEBILIR KCAL FALANA GORE HOCAYA SOR
                }else{ ///sport
                    int currentSport = CalculateDailyStuff.arrayPositionSport(commandArray[1], sports);
                    long calorieBurned = CalculateDailyStuff.calculateCalorieBurned(sports[currentSport].calorieBurned, Integer.parseInt(commandArray[2]));
                    people[currentPerson].calorieLoss(calorieBurned);
                    writer.write(people[currentPerson].personID + "\thas\tburned\t" + calorieBurned + "kcal\tthanks\tto\t" + sports[currentSport].nameOfSport + "\n");
                }
            }
            if(scanForCommand.hasNext()) writer.write("***************\n");
        }
        writer.close();
    }
}