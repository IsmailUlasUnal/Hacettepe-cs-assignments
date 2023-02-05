import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridManager {

    public static List<List<Jewel>> grid = new ArrayList<List<Jewel>>();

    public static void createGrid(String text){
        File gridfile = new File(text); //It will take grid file from terminal as an args[0]
        Jewel jewelToAdd = null;

        // these are for row and column coordinate of jewel
        int currentRow;
        int currentColumn;

        /*
        I am holding words and symbols as a set instead of array's for easy adding when I needed to add new jewel.
        //todo when adding new jewel don't forget to add it here too.
         */
        Set<Character> wordSet = new HashSet<Character>(Arrays.asList('D', 'S', 'T', 'W'));
        Set<Character> symbolSet = new HashSet<Character>(Arrays.asList('/', '-', '+', '\\', '|'));

        try {
            Scanner gridScan = new Scanner(gridfile);

            currentRow = 0;
            while (gridScan.hasNext()) {
                currentColumn = 0;
                ArrayList<Jewel> rowArray = new ArrayList<Jewel>();
                String[] fullRow = gridScan.nextLine().split(" ");

                for(String eachJewel : fullRow){
                    /*
                    In this project I have used decorator pattern so creating a new jewel care this steps;
                    while creating a jewel first create a Jewel name with constructor.
                    this constructor needs to have; name for printing, point for score, currentRow and currentColumn for direction in grid.
                    If this jewel match with other jewel's you need to add it in to a constructor to(in second part after the name)
                    then started to add looking direction's by using MatchJewel decorators.
                    If only match with itself use matchItself with appropriate constructors.
                    If it can match with itself and another one type jewel use matchOneSimiliar
                    If it can match with every jewel's in its type use matchSimiliar
                    their constructor needs to have; rowDirection and column direction for which direction it will look, and matching distance for how far will it look
                    they need to add to a reverse order for example if it look in direction 7 first you need to add it at last. because it will start to search from last added to oldest added decorator.
                     */

                    switch(eachJewel) {
                        case "D":
                            jewelToAdd = new JewelName('D', 30, currentRow, currentColumn);
                            jewelToAdd = new MatchItself(jewelToAdd, 1, -1, 2); // for direction 7
                            jewelToAdd = new MatchItself(jewelToAdd, - 1, 1, 2); // for direction 3
                            jewelToAdd = new MatchItself(jewelToAdd, 1, 1, 2); // for direction 9
                            jewelToAdd = new MatchItself(jewelToAdd, - 1, - 1, 2); // for direction 1

                            break;
                        case "S":
                            jewelToAdd = new JewelName('S', 15, currentRow, currentColumn);
                            jewelToAdd = new MatchItself(jewelToAdd, 0 , 1, 2); // for direction 6
                            jewelToAdd = new MatchItself(jewelToAdd, 0 , - 1, 2); // for direction 4

                            break;
                        case "T":
                            jewelToAdd = new JewelName('T', 15, currentRow, currentColumn);
                            jewelToAdd = new MatchItself(jewelToAdd, 1 , 0, 2); // for direction 8
                            jewelToAdd = new MatchItself(jewelToAdd, - 1 , 0, 2); // for direction 2

                            break;
                        case "W":
                            jewelToAdd = new JewelName('W', wordSet, 10, currentRow, currentColumn);
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, + 1 , - 1, 2); // for direction 7
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, - 1, 1, 2); // for direction 3
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, + 1, 1, 2); // for direction 9
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, - 1, - 1, 2); // for direction 1
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, 0 , 1, 2); // for direction 6
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, 0 , - 1, 2); // for direction 4
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, 1 , 0, 2); // for direction 8
                            jewelToAdd = new MatchOneSimiliar(jewelToAdd, - 1 , 0, 2); // for direction 2

                            break;
                        case "/":
                            jewelToAdd = new JewelName('/' , symbolSet, 20, currentRow, currentColumn);
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 1 , - 1, 2); // for direction 7
                            jewelToAdd = new MatchSimiliar(jewelToAdd, - 1, 1, 2); // for direction 3

                            break;
                        case "-":
                            jewelToAdd = new JewelName('-' ,symbolSet, 20, currentRow, currentColumn);
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 0 , 1, 2); // for direction 6
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 0 , - 1, 2); // for direction 4

                            break;
                        case "+": //
                            jewelToAdd = new JewelName('+' ,symbolSet, 20, currentRow, currentColumn);
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 1 , 0, 2); // for direction 8
                            jewelToAdd = new MatchSimiliar(jewelToAdd, - 1 , 0, 2); // for direction 2
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 0 , 1, 2); // for direction 6
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 0 , - 1, 2); // for direction 4

                            break;
                        case "\\":
                            jewelToAdd = new JewelName('\\' ,symbolSet, 20, currentRow, currentColumn);
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 1, 1, 2); // for direction 9
                            jewelToAdd = new MatchSimiliar(jewelToAdd, - 1, - 1, 2); // for direction 1

                            break;
                        case "|":
                            jewelToAdd = new JewelName('|' ,symbolSet, 20, currentRow, currentColumn);
                            jewelToAdd = new MatchSimiliar(jewelToAdd, 1 , 0, 2); // for direction 8
                            jewelToAdd = new MatchSimiliar(jewelToAdd, - 1 , 0, 2); // for direction 2

                            break;
                    }
                    rowArray.add(jewelToAdd);
                    currentColumn++;

                }
                grid.add(rowArray);
                currentRow++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
