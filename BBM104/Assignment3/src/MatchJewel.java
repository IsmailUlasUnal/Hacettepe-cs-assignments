import java.util.Set;

public abstract class MatchJewel implements Jewel{
    protected Jewel tempJewel;
    private int matchingDistance;

    private int rowDirection;
    private int columnDirection;

    public MatchJewel(Jewel newJewel, int rowDirection, int columnDirection, int matchingDistance){
        tempJewel = newJewel;
        this.matchingDistance = matchingDistance;
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public abstract boolean canMatch();

    public int getMatchingDistance() {
        return matchingDistance;
    }

    public void gravity(int row, int column){
        for(int currentRow = row - 1; currentRow >= 0; currentRow--){
            Jewel checkJewel = GridManager.grid.get(currentRow).get(column);
            if(checkJewel != null){
                GridManager.grid.get(currentRow).get(column).setRowCoordinate(currentRow + 1);
                GridManager.grid.get(currentRow + 1).set(column, checkJewel);
                GridManager.grid.get(currentRow).set(column, null);
            }
        }
    }

    @Override
    public void delete(){
        // if canMatch returns true it will delete jewels in that direction
        if(canMatch()){
            for(int i = 0; i <= matchingDistance; i++){
                /*
                if part is for making gravity function O(n)
                I am calling gravity function whenever code deletes something,
                so it needs to delete from top to bottom not bottom to top
                 */
                //todo -1 1
                if(rowDirection == -1 && columnDirection == 0){
                    Player.tourScore += GridManager.grid.get(rowCoordinate() + rowDirection * (matchingDistance - i)).get(columnCoordinate()).getPoint();
                    GridManager.grid.get(rowCoordinate() + rowDirection * (matchingDistance - i)).set(columnCoordinate(), null);
                    gravity(rowCoordinate() + rowDirection * (matchingDistance - i), columnCoordinate());
                }
                else{ //
                    Player.tourScore += GridManager.grid.get(rowCoordinate() + rowDirection * i).get(columnCoordinate() + columnDirection * i).getPoint();
                    GridManager.grid.get(rowCoordinate() + i * rowDirection).set(columnCoordinate() + columnDirection * i, null);
                    gravity(rowCoordinate() + i * rowDirection, columnCoordinate() + columnDirection * i);
                }
            }
        }
        else{
            tempJewel.delete();// If there is no match in that direction It will call other direction.
        }
    }

    @Override
    public Set<Character> getJewelToLook() {
        return tempJewel.getJewelToLook();
    }

    @Override
    public int getPoint() {
        return tempJewel.getPoint();
    }

    @Override
    public char getName() {
        return tempJewel.getName();
    }

    @Override
    public int rowCoordinate() {
        return tempJewel.rowCoordinate();
    }

    @Override
    public int columnCoordinate() {
        return tempJewel.columnCoordinate();
    }

    @Override
    public void setRowCoordinate(int row){
        tempJewel.setRowCoordinate(row);
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }

}