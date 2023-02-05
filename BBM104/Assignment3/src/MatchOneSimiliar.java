import java.util.HashSet;
import java.util.Set;

public class MatchOneSimiliar extends MatchJewel{

    public MatchOneSimiliar(Jewel newJewel, int rowDirection, int columnDirection, int matchingDistance) {
        super(newJewel, rowDirection, columnDirection, matchingDistance);
    }

    @Override
    public boolean canMatch() {
        /*
        first it creates a set then it looks every jewel in given distance and given direcion if it find same type jewel it add to a set
        it returns true if set's size equals one or two(only itself or itself and one more jewel in same type)
        it returns false if it saw 3 different same type jewel or any different type of jewel of any errors
         */
        Set<Character> set = new HashSet<Character>();

        try{
            for(int i = 0; i <= getMatchingDistance(); i++){
                char checkJewelName = GridManager.grid.get(rowCoordinate() + i * getRowDirection()).get(columnCoordinate() + i * getColumnDirection()).getName();
                if(getJewelToLook().contains(checkJewelName)) set.add(checkJewelName);
                else return false;
            }
            return set.size() == 2 || set.size() == 1;

        } catch(IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }
}
