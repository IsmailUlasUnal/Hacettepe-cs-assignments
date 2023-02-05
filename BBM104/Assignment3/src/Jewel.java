import java.util.Set;

     /*
    This is ancestor of the every Jewel and their decorator, Jewel name and MatchJewel implements this function
     */

public interface Jewel {

    void delete(); //this function is deleting jewels in given direction and given distance

    char getName(); //name of the jewel like W, D, -, etc.

    int getPoint(); //this is jewel's point

    Set<Character> getJewelToLook(); //this gives jewel's type to match but if only match itself we don't need to set this

    int rowCoordinate(); //jewel's row coordinate in grid

    int columnCoordinate(); //jewel's column coordinate in grid

    void setRowCoordinate(int row);
}