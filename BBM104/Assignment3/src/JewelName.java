import java.util.Set;

public class JewelName implements Jewel{

    private char name;
    private Set<Character> jewelToLook;
    private int point;
    private int rowCoordinate;
    private int columnCoordinate;

    //I have two constructor's because I don't need to initilize jewelToLook when it only look itself
    public JewelName(char name, int point, int rowCoordinate, int columnCoordinate){
        this.name = name;
        this.point = point;
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    public JewelName(char name, Set<Character> jewelToLook, int point, int rowCoordinate, int columnCoordinate){
        this.name = name;
        this.jewelToLook = jewelToLook;
        this.point = point;
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    @Override
    public void delete() {
        /*
        if code comes here it means it can't match with anything so I throw NoMatchException
         */
        throw new NoMatchException();
    }

    @Override
    public char getName() {
        return name;
    }

    @Override
    public int getPoint(){
        return point;
    }

    @Override
    public Set<Character> getJewelToLook(){
        return jewelToLook;
    }

    @Override
    public int rowCoordinate() {
        return rowCoordinate;
    }

    @Override
    public int columnCoordinate() {
        return columnCoordinate;
    }

    @Override
    public void setRowCoordinate(int row) {
        rowCoordinate = row;
    }

}
