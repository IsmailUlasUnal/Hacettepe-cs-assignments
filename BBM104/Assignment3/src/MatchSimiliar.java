import java.util.HashSet;
import java.util.Set;

public class MatchSimiliar extends MatchJewel{
    /*
    this class is for matching everything If they are same type.
     */

    public MatchSimiliar(Jewel newJewel, int rowDirection, int columnDirection, int matchingDistance) {
        super(newJewel, rowDirection, columnDirection, matchingDistance);
    }

    @Override
    public boolean canMatch() {
        /*
        it looks jewels in given direction and given distance
        *returns false when it sees different type of jewel or sees something null, or it is out of grid index.
        *returns true when it can't see different type of jewel
        if it returns true delete code will work and it delete everything in this distance
         */
        try{
            for(int i = 0; i <= getMatchingDistance(); i++){
                char checkJewelName = GridManager.grid.get(rowCoordinate() + i * getRowDirection()).get(columnCoordinate() + i * getColumnDirection()).getName();
                if(!(getJewelToLook().contains(checkJewelName))) {
                    return false;
                }
            }

        } catch(IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
        return true;
    }
}
