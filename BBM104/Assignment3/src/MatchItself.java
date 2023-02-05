import java.util.HashSet;
import java.util.Set;

public class MatchItself extends MatchJewel{

    public MatchItself(Jewel newJewel, int rowDirection, int columnDirection, int matchingDistance) {
        super(newJewel, rowDirection, columnDirection, matchingDistance);
    }

    @Override
    public boolean canMatch() {
        Set<Character> set = new HashSet<Character>();
        /*
        firstly it creates a set then it add every jewel in its direction in given distance
        if set's size is greater than one it returns false otherwise it returns true
        because this code firstly add itself then add anything different than itself. so if it add something differnt than itself set's size will be greater than one
         */
        try{
            for(int i = 0; i <= getMatchingDistance(); i++){
                char checkJewelName = GridManager.grid.get(rowCoordinate() + i * getRowDirection()).get(columnCoordinate() + i * getColumnDirection()).getName();
                set.add(checkJewelName);

            }
            if(set.size() == 1){
                return true;
            }
        }catch(IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
        return false;
    }
}
